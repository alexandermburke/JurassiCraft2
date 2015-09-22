package net.timeless.animationapi.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.animationapi.client.DinosaurAnimator.AnimationsDTO.PoseDTO;
import net.timeless.unilib.Unilib;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.ModelJson;
import net.timeless.unilib.client.model.tools.MowzieModelRenderer;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.model.ModelDinosaur;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import com.google.gson.Gson;
import com.mojang.realmsclient.util.Pair;

@SideOnly(Side.CLIENT)
public abstract class DinosaurAnimator implements IModelAnimator
{
    /**
     * This class can be loaded via {@link Gson#fromJson}. It represents the poses
     * of the animations of a model.
     *
     * @author WorldSEnder
     */
    public static class AnimationsDTO
    {
        /**
         * Maps an {@link AnimID} as a string to the list of sequential poses
         */
        public Map<String, PoseDTO[]> poses;
        public int version;

        public static class PoseDTO
        {
            /**
             * The pose's tabular file location
             */
            public String pose;
            public transient int index;
            /**
             * The ticks it takes to transition into the pose
             */
            public int time;
        }
    }

    private static final Gson GSON = new Gson();

    public static EnumMap<AnimID, int[][]> newEmptyMap() {
        EnumMap<AnimID, int[][]> map = new EnumMap<>(AnimID.class);
        for(AnimID id : AnimID.values()) {
            map.put(id, new int[0][2]);
        }
        return map;
    }

    private MowzieModelRenderer[][] models;
    private Map<AnimID, int[][]> animations;
    protected Map<Integer, JabelarAnimationHelper> entityIDToAnimation = new HashMap<Integer, JabelarAnimationHelper>();

    /**
     * Loads the model, etc... for the dinosaur given.
     * @param dino
     * @throws IOException
     */
    public DinosaurAnimator(Dinosaur dino)
    {
        String name = dino.getName(0).toLowerCase(); // this should match name of your resource package and files
        String dinoDir = "/assets/jurassicraft/models/entities/" + name + "/";
        String dinoDefinition = dinoDir + name + ".json";
        this.models = new MowzieModelRenderer[0][]; // Pre-init with an empty map and no models
        this.animations = newEmptyMap();
        try(Reader reader = new InputStreamReader(Unilib.class.getResourceAsStream(dinoDefinition)))
        {
            AnimationsDTO rawAnimations = GSON.fromJson(reader, AnimationsDTO.class);
            URI dinoDirURI = new URI(dinoDir);
            Pair<MowzieModelRenderer[][], Map<AnimID, int[][]>> posings = getPosedModels(dinoDirURI, rawAnimations);
            this.models = posings.first();
            this.animations = posings.second();
        }
        catch (URISyntaxException e)
        {
            JurassiCraft.instance.getLogger().fatal("Invalid URI: " + dinoDir, e);
        }
        catch (IllegalArgumentException | IOException iae)
        {
            JurassiCraft.instance.getLogger().fatal("Failed to parse the dinosaur animation file" + dinoDefinition, iae);
        }

    }
    /**
     * Gets the posed models from the set of animations defined. Illegal poses (e.g. where the file
     * doesn't exist) will be skipped and not show up in the map.
     * @param anims the read animations
     * @return
     */
    private Pair<MowzieModelRenderer[][], Map<AnimID, int[][]>> getPosedModels(URI dinoDirURI, AnimationsDTO anims)
    {
        // Collect all needed resources
        List<String> posedModelResources = new ArrayList<>();
        for(PoseDTO[] poses : anims.poses.values()) {
            for(PoseDTO pose : poses) {
                String resolvedRes = normalize(dinoDirURI, pose.pose);
                int index = posedModelResources.indexOf(resolvedRes);
                if(index == -1) { // Not in the list
                    pose.index = posedModelResources.size();
                    posedModelResources.add(resolvedRes);
                } else { // Already in there
                    pose.index = index;
                }
            }
        }

        MowzieModelRenderer[][] posedCubes = new MowzieModelRenderer[posedModelResources.size()][];
        Map<AnimID, int[][]> animationSequences = newEmptyMap();
        if(posedModelResources.size() == 0)
            return Pair.of(posedCubes, animationSequences);
        // find all names we need
        ModelDinosaur mainModel = JabelarAnimationHelper.getTabulaModel(posedModelResources.get(0), 0);
        if(mainModel == null)
            throw new IllegalArgumentException("Couldn't load the model from " + posedModelResources.get(0));
        String[] cubeNameArray = mainModel.getCubeNamesArray();
        int numParts = cubeNameArray.length;
        // load the models from the resource files
        for(int i = 0; i < posedModelResources.size(); i++) {
            String resource = posedModelResources.get(i);
            ModelDinosaur theModel = JabelarAnimationHelper.getTabulaModel(resource, 0);
            if(theModel == null)
                throw new IllegalArgumentException("Couldn't load the model from " + resource);
            MowzieModelRenderer[] cubes = new MowzieModelRenderer[numParts];
            for (int partIndex = 0; partIndex < numParts; partIndex++)
            {
                String cubeName = cubeNameArray[partIndex];
                MowzieModelRenderer cube = theModel.getCube(cubeName);
                if (cube == null)
                    throw new IllegalArgumentException("Could not retrieve cube " + cubeName + " (" + partIndex + ") from the model " + resource);
                cubes[partIndex] = cube;
            }
            posedCubes[i] = cubes;
        }
        // load the animations sequences
        for(Map.Entry<String, PoseDTO[]> entry : anims.poses.entrySet()) {
            AnimID animID = AnimID.valueOf(entry.getKey());
            PoseDTO[] poses = entry.getValue();
            int[][] poseSequence = new int[poses.length][2];
            for(int i = 0; i < poses.length; i++) {
                poseSequence[i][0] = poses[i].index;
                poseSequence[i][1] = poses[i].time;
            }
            animationSequences.put(animID, poseSequence);
        }
        return Pair.of(posedCubes, animationSequences);
    }

    private String normalize(URI dinoDirURI, String posePath) {
        URI uri = dinoDirURI.resolve(posePath);
        return uri.toString();
    }

    private JabelarAnimationHelper forEntity(EntityDinosaur entity, ModelDinosaur model) {
        Integer id = entity.getEntityId();
        JabelarAnimationHelper render = entityIDToAnimation.get(id);
        if(render == null) {
            int cubes = models.length > 0 ? models[0].length : 0;
            render = new JabelarAnimationHelper(entity, model, cubes, models, animations, true, 1.0f);
            entityIDToAnimation.put(id, render);
        }
        JurassiCraft.instance.getLogger().debug("DEBUG MESSAGE I SCREEEAM AT THE TOP OF MY ASCII!!!!");
        return render;
    }

    @Override
    public final void setRotationAngles(ModelJson model, float limbSwing, float limbSwingAmount, float rotation,
                                  float rotationYaw, float rotationPitch, float partialTicks, Entity entity)
    {
        ModelDinosaur theModel = (ModelDinosaur) model;
        EntityDinosaur theEntity = (EntityDinosaur) entity;

        setRotationAngles(theModel, limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, partialTicks, theEntity);
}

    protected void setRotationAngles(ModelDinosaur model, float limbSwing, float limbSwingAmount, float rotation,
                                  float rotationYaw, float rotationPitch, float partialTicks, EntityDinosaur entity)
    {
        JabelarAnimationHelper render = forEntity(entity, model);
        render.performJabelarAnimations(model);
        performMowzieAnimations(model, limbSwing, limbSwingAmount, rotation, rotationYaw, rotationPitch, partialTicks, entity);

    }

    protected void performMowzieAnimations(ModelDinosaur parModel, float parLimbSwing, float parLimbSwingAmount, float parRotation, float parRotationYaw, float parRotationPitch, float parPartialTicks, EntityDinosaur parEntity)
    {
    }
}
