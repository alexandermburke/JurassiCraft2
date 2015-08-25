package net.timeless.jurassicraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.common.block.BlockFossil;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.lang.AdvLang;
import net.timeless.jurassicraft.common.period.EnumTimePeriod;

public class ItemFossilBlock extends ItemBlock
{
    public ItemFossilBlock(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fossil_block");
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        Dinosaur dinosaur = ((BlockFossil) block).getDinosaur(stack.getMetadata());

        if (dinosaur == null)
        {
            dinosaur = JCEntityRegistry.getDinosaurById(0);
        }

        return new AdvLang("tile.fossil_block.name").withProperty("period", "period." + dinosaur.getPeriod().getName() + ".name").build();
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        EnumTimePeriod timePeriod = JCEntityRegistry.getDinosaurById(stack.getMetadata()).getPeriod();
        return super.getUnlocalizedName() + "." + timePeriod.getName();
    }
}
