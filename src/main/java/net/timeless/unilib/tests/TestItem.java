package net.timeless.unilib.tests;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.timeless.unilib.common.StructureRegistry;
import net.timeless.unilib.common.structure.EnumRotAngle;
import net.timeless.unilib.common.structure.StructureGenerator;

public class TestItem extends Item {
    public TestItem(String name) {
        setUnlocalizedName(name);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote)
            return true;
        StructureGenerator gen = StructureRegistry.getInstance().getStructure("testStructure").rotateClockwise(EnumRotAngle.DEGREES_180);
        gen.generate(worldIn, pos.getX(), pos.getY(), pos.getZ(), worldIn.rand);
        return true;
    }
}
