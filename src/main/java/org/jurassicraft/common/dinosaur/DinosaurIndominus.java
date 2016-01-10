package org.jurassicraft.common.dinosaur;

import net.minecraft.util.ResourceLocation;
import org.jurassicraft.common.api.IHybrid;
import org.jurassicraft.common.entity.EntityIndominus;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.EnumDiet;
import org.jurassicraft.common.entity.base.EnumGrowthStage;
import org.jurassicraft.common.entity.base.EnumSleepingSchedule;
import org.jurassicraft.common.period.EnumTimePeriod;

public class DinosaurIndominus extends Dinosaur implements IHybrid
{
    private ResourceLocation texture;

    private ResourceLocation overlayTexture;

    private Class[] baseGenes;
    private Class[] extraGenes;

    public DinosaurIndominus()
    {
        this.texture = new ResourceLocation(getDinosaurTexture("camouflage"));
        this.overlayTexture = new ResourceLocation(getDinosaurTexture(""));

        this.baseGenes = new Class[] { DinosaurTyrannosaurus.class, DinosaurVelociraptor.class };
        this.extraGenes = new Class[] { DinosaurGiganotosaurus.class, DinosaurRugops.class, DinosaurMajungasaurus.class, DinosaurCarnotaurus.class }; // TODO therizino
    }

    @Override
    public String getName()
    {
        return "Indominus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityIndominus.class;
    }

    @Override
    public EnumTimePeriod getPeriod()
    {
        return EnumTimePeriod.CRETACEOUS;
    }

    @Override
    public int getEggPrimaryColorMale()
    {
        return 0xBEBABB;
    }

    @Override
    public int getEggSecondaryColorMale()
    {
        return 0x95949A;
    }

    @Override
    public int getEggPrimaryColorFemale()
    {
        return getEggPrimaryColorMale();
    }

    @Override
    public int getEggSecondaryColorFemale()
    {
        return getEggSecondaryColorMale();
    }

    @Override
    public double getBabyHealth()
    {
        return 20;
    }

    @Override
    public double getAdultHealth()
    {
        return 100;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.45;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.40;
    }

    public double getAttackSpeed()
    {
        return 0.5;
    }

    @Override
    public double getBabyStrength()
    {
        return 5;
    }

    @Override
    public double getAdultStrength()
    {
        return 40;
    }

    @Override
    public int getMaximumAge()
    {
        return fromDays(30);
    }

    @Override
    public ResourceLocation getMaleTexture(EnumGrowthStage stage)
    {
        return texture;
    }

    @Override
    public ResourceLocation getFemaleTexture(EnumGrowthStage stage)
    {
        return texture;
    }

    public ResourceLocation getCamoTexture(EnumGrowthStage stage)
    {
        return overlayTexture;
    }

    @Override
    public float getBabyEyeHeight()
    {
        return 0.55F;
    }

    @Override
    public float getAdultEyeHeight()
    {
        return 5.4F;
    }

    @Override
    public float getBabySizeX()
    {
        return 0.4F;
    }

    @Override
    public float getBabySizeY()
    {
        return 0.7F;
    }

    @Override
    public float getAdultSizeX()
    {
        return 4.0F;
    }

    @Override
    public float getAdultSizeY()
    {
        return 6.0F;
    }

    @Override
    public Class[] getBaseGenes()
    {
        return baseGenes;
    }

    @Override
    public Class[] getExtraGenes()
    {
        return extraGenes;
    }

    @Override
    public int getStorage()
    {
        return 54;
    }

    @Override
    public EnumDiet getDiet()
    {
        return EnumDiet.CARNIVORE;
    }

    @Override
    public EnumSleepingSchedule getSleepingSchedule()
    {
        return EnumSleepingSchedule.DIURNAL;
    }

    @Override
    public String[] getBones()
    {
        return new String[] { "skull", "tooth", "tail_vertebrae", "shoulder", "ribcage", "pelvis", "neck_vertebrae", "leg_bones", "foot_bones", "arm_bones" };
    }
}
