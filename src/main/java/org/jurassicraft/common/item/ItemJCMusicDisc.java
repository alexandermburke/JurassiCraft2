package org.jurassicraft.common.item;

import net.minecraft.item.ItemRecord;
import org.jurassicraft.JurassiCraft;

public class ItemJCMusicDisc extends ItemRecord
{
    public ItemJCMusicDisc(String name)
    {
        super(name);
        this.setUnlocalizedName("disc_" + name);
    }

    /**
     * Retrieves the resource location of the sound to play for this record.
     *
     * @param name The name of the record to play
     * @return The resource location for the audio, null to use default.
     */
    public net.minecraft.util.ResourceLocation getRecordResource(String name)
    {
        return new net.minecraft.util.ResourceLocation(JurassiCraft.MODID, name);
    }
}