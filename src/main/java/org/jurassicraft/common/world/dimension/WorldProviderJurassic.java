package org.jurassicraft.common.world.dimension;

import net.minecraft.world.WorldProvider;

class WorldProviderJurassic extends WorldProvider
{
    @Override
    public String getDimensionName()
    {
        return "JurassiCraft";
    }

    @Override
    public String getInternalNameSuffix()
    {
        return "";
    }

    @Override
    public String getWelcomeMessage()
    {
        return "Welcome to JurassiCraft.";
    }

    @Override
    protected void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerJurassic(this.worldObj);
    }
    //
    // @Override
    // public IChunkProvider createChunkGenerator()
    // {
    // return new ChunkGeneratorJurassic(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getWorldInfo().getGeneratorOptions());
    // }

}
