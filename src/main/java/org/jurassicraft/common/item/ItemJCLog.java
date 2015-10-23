package org.jurassicraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jurassicraft.common.lang.AdvLang;

public class ItemJCLog extends ItemBlock
{
    public ItemJCLog(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("jc_log");
    }

    @Override
    public int getMetadata(int metadata) // In here we extend ItemBlock and return metadata (the block's metadata) for the item damage
    {
        return metadata;
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        // So there I specify the lang
        return new AdvLang("tile.jc_wood.name").withProperty("wood", "wood." + stack.getItemDamage() + ".name").build(); // For now will use wood lang in this format: wood.0.name
    }
}
