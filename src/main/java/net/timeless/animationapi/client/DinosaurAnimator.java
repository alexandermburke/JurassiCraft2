package net.timeless.animationapi.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.dto.AnimationsDTO;
import net.timeless.animationapi.client.dto.DinosaurRenderDefDTO;
import net.timeless.animationapi.client.dto.PoseDTO;
import net.timeless.unilib.Unilib;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumGrowthStage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@SideOnly(Side.CLIENT)
public abstract class DinosaurAnimator implements IModelAnimator {
    private static class PreloadedModelData {
        public PreloadedModelData()
        {
            this(null, null);
        }

        public PreloadedModelData(MowzieModelRenderer[][] renderers, Map<AnimID, int[][]> animations)
        {
            if (renderers == null) {
                renderers = new MowzieModelRenderer[0][];
            }

            if (animations == null) {
                animations = newEmptyMap();
            }

            this.models = renderers;
            this.animations = animations;
        }

        MowzieModelRenderer[][] models;
        Map<AnimID, int[][]> animations;
    }

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(DinosaurRenderDefDTO.class, new DinosaurRenderDefDTO.DinosaurDeserializer()).create();

    public static EnumMap<AnimID, int[][]> newEmptyMap()
    {
        return new EnumMap<AnimID, int[][]>(AnimID.class);
    }

    private Map<EnumGrowthStage, PreloadedModelData> modelData;
    protected Map<Integer, Map<EnumGrowthStage, JabelarAnimationHelper>> entityIDToAnimation = new HashMap<Integer, Map<EnumGrowthStage, JabelarAnimationHelper>>();
    private float partialTick;

    /**
     * Loads the model, etc... for the dinosaur given.
     *
     * @param dino
     */
    public DinosaurAnimator(Dinosaur dino)
    {
        String name = dino.getName().toLowerCase(); // this should match name of
                                                    // your resource package and
                                                    // files
        this.modelData = new EnumMap<EnumGrowthStage, PreloadedModelData>(EnumGrowthStage.class);
        URI dinoDirURI;

        try {
            dinoDirURI = new URI("/assets/jurassicraft/models/entities/" + name + "/");
        } catch (URISyntaxException urise) {
            JurassiCraft.instance.getLogger().fatal("Illegal URI /assets/jurassicraft/models/entities/" + name + "/", urise);
            return;
        }

        for (EnumGrowthStage growth : EnumGrowthStage.values()) {
            try {
                this.modelData.put(growth, loadDinosaur(dinoDirURI, name, growth));
            } catch (Exception e) {
                // TODO: should this be caught here? We can't continue, because
                // it breaks the contract that every ...
                // model has at least the IDLE sequence defined
                JurassiCraft.instance.getLogger().fatal("Failed to parse growth state " + growth + " for dinosaur " + name, e);
                this.modelData.put(growth, new PreloadedModelData());
            }
        }
    }

    /**
     * Loads a specific growth state
     *
     * @param dinoDir
     *            the base directory
     * @param name
     *            the name of the dino
     * @param growth
     *            the growthstate to load
     * @throws IOException
     */
    private static PreloadedModelData loadDinosaur(URI dinoDir, String name, EnumGrowthStage growth) throws IOException
    {
        String growthName = growth.name().toLowerCase(Locale.ROOT);
        URI growthSensitiveDir = dinoDir.resolve(growthName + "/");
        URI definitionFile = growthSensitiveDir.resolve(name + "_" + growthName + ".json");
        InputStream dinoDef = Unilib.class.getResourceAsStream(definitionFile.toString());

        if (dinoDef == null)
        {
            throw new IllegalArgumentException("No model definition for the dino " + name + " with grow-state " + growth + " exists. Expected at " + definitionFile);
        }

        // FIXME: Does not work with Java 1.6, which Minecraft is built for!
        try (Reader reader = new InputStreamReader(dinoDef))
        {
            AnimationsDTO rawAnimations = GSON.fromJson(reader, AnimationsDTO.class);
            PreloadedModelData data = getPosedModels(growthSensitiveDir, rawAnimations);
            JurassiCraft.instance.getLogger().debug("Successfully loaded " + name + "(" + growth + ") from " + definitionFile);

            return data;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets the posed models from the set of animations defined. Illegal poses
     * (e.g. where the file doesn't exist) will be skipped and not show up in
     * the map.
     *
     * @param anims
     *            the read animations
     * @return
     */
    private static PreloadedModelData getPosedModels(URI dinoDirURI, AnimationsDTO anims)
    {
        // Check if the file is legal: -> at least one pose for the IDLE
        // animation
        if (anims == null || anims.poses == null || anims.poses.get(AnimID.IDLE.name()) == null || anims.poses.get(AnimID.IDLE.name()).length == 0) {
            throw new IllegalArgumentException("Animation files must define at least one pose for the IDLE animation");
        }
        // Collect all needed resources
        List<String> posedModelResources = new ArrayList<String>();
        for (PoseDTO[] poses : anims.poses.values()) {
            if (poses == null) {
                continue; // Pending comma in the map, ignoring
            }
            for (PoseDTO pose : poses) {
                if (pose == null) {
                    continue; // Pending comma in the list, ignoring
                }
                if (pose.pose == null) {
                    throw new IllegalArgumentException("Every pose must define a pose file");
                }
                String resolvedRes = resolve(dinoDirURI, pose.pose);
                int index = posedModelResources.indexOf(resolvedRes);
                if (index == -1) { // Not in the list
                    pose.index = posedModelResources.size();
                    posedModelResources.add(resolvedRes);
                } else { // Already in there
                    pose.index = index;
                }
            }
        }
        assert (posedModelResources.size() > 0); // anims.poses.get(AnimID.IDLE.name()).length
                                                 // != 0
        MowzieModelRenderer[][] posedCubes = new MowzieModelRenderer[posedModelResources.size()][];
        Map<AnimID, int[][]> animationSequences = newEmptyMap();
        // find all names we need
        ModelDinosaur mainModel = JabelarAnimationHelper.getTabulaModel(posedModelResources.get(0), 0);
        if (mainModel == null) {
            throw new IllegalArgumentException("Couldn't load the model from " + posedModelResources.get(0));
        }
        String[] cubeNameArray = mainModel.getCubeNamesArray();
        int numParts = cubeNameArray.length;
        // load the models from the resource files
        for (int i = 0; i < posedModelResources.size(); i++) {
            String resource = posedModelResources.get(i);
            ModelDinosaur theModel = JabelarAnimationHelper.getTabulaModel(resource, 0);
            if (theModel == null) {
                throw new IllegalArgumentException("Couldn't load the model from " + resource);
            }
            MowzieModelRenderer[] cubes = new MowzieModelRenderer[numParts];
            for (int partIndex = 0; partIndex < numParts; partIndex++) {
                String cubeName = cubeNameArray[partIndex];
                MowzieModelRenderer cube = theModel.getCube(cubeName);
                if (cube == null) {
                    throw new IllegalArgumentException("Could not retrieve cube " + cubeName + " (" + partIndex + ") from the model " + resource);
                }
                cubes[partIndex] = cube;
            }
            posedCubes[i] = cubes;
        }
        // load the animations sequences
        for (Map.Entry<String, PoseDTO[]> entry : anims.poses.entrySet()) {
            AnimID animID = AnimID.valueOf(entry.getKey());
            PoseDTO[] poses = entry.getValue();
            int[][] poseSequence = new int[poses.length][2];
            for (int i = 0; i < poses.length; i++) {
                poseSequence[i][0] = poses[i].index;
                poseSequence[i][1] = poses[i].time;
            }
            animationSequences.put(animID, poseSequence);
        }
        return new PreloadedModelData(posedCubes, animationSequences);
    }

    private static String resolve(URI dinoDirURI, String posePath)
    {
        URI uri = dinoDirURI.resolve(posePath);
        return uri.toString();
    }

    private JabelarAnimationHelper getAnimationHelper(EntityDinosaur entity, ModelDinosaur model)
    {
        Integer id = entity.getEntityId();
        EnumGrowthStage growth = entity.getGrowthStage();
        Map<EnumGrowthStage, JabelarAnimationHelper> growthToRender = entityIDToAnimation.get(id);

        if (growthToRender == null) {
            growthToRender = new EnumMap<EnumGrowthStage, JabelarAnimationHelper>(EnumGrowthStage.class);
            entityIDToAnimation.put(id, growthToRender);
        }

        JabelarAnimationHelper render = growthToRender.get(growth);

        if (render == null) {
            PreloadedModelData growthModel = modelData.get(growth);
            int cubes = growthModel.models.length > 0 ? growthModel.models[0].length : 0;
            render = new JabelarAnimationHelper(entity, model, cubes, growthModel.models, growthModel.animations, true, 1.0f);
            growthToRender.put(growth, render);
        }

        return render;
    }

    @Override
    public final void setRotationAngles(ModelJson model, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, Entity entity)
    {
        ModelDinosaur theModel = (ModelDinosaur) model;
        EntityDinosaur theEntity = (EntityDinosaur) entity;
        ; // assert(size == 1/16f); // Ignore the size

        setRotationAngles(theModel, limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, partialTick, theEntity);
    }

    @Override
    public void preRenderCallback(EntityLivingBase entity, float partialTicks)
    {
        this.partialTick = partialTicks;
    }

    protected void setRotationAngles(ModelDinosaur model, float limbSwing, float limbSwingAmount, float rotation, float rotationYaw, float rotationPitch, float partialTick, EntityDinosaur entity)
    {
        getAnimationHelper(entity, model).performJabelarAnimations(partialTick);
        if (entity.getAnimID() != AnimID.DYING) // still alive
        {
            performMowzieAnimations(model, limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, partialTick, entity);
        }
    }

    protected void performMowzieAnimations(ModelDinosaur parModel, float parLimbSwing, float parLimbSwingAmount, float parRotation, float parRotationYaw, float parRotationPitch, float parPartialTicks, EntityDinosaur parEntity)
    {
    }
}
