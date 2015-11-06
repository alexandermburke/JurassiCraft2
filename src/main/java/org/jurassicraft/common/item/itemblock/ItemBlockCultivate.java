package org.jurassicraft.common.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import org.jurassicraft.common.lang.AdvLang;

public class ItemBlockCultivate extends ItemBlock
{
    public ItemBlockCultivate(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("cultivate_bottom");
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        EnumDyeColor color = EnumDyeColor.byMetadata(stack.getItemDamage());
        return new AdvLang("tile.cultivate.name").withProperty("color", "color." + color.getName() + ".name").build();
    }
}
