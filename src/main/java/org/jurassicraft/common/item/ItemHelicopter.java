package org.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.vehicles.helicopter.EntityHelicopterBase;
import org.jurassicraft.common.vehicles.helicopter.HelicopterSeat;

import java.util.List;

public class ItemHelicopter extends Item
{
    public ItemHelicopter()
    {
        setUnlocalizedName("helicopter_spawner");
        setCreativeTab(JCCreativeTabs.items);
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
    {
        super.addInformation(stack, playerIn, tooltip, advanced);
        tooltip.add("Right click on a block to spawn the helicopter");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
            return true;
        EntityHelicopterBase helicopter = new EntityHelicopterBase(worldIn);
        helicopter.setPosition(pos.getX() + hitX, pos.getY() + hitY, pos.getZ() + hitZ);
        worldIn.spawnEntityInWorld(helicopter);

        HelicopterSeat pilotSeat = new HelicopterSeat(1.3f, EntityHelicopterBase.PILOT_SEAT, helicopter);
        helicopter.seats[EntityHelicopterBase.PILOT_SEAT] = pilotSeat;

        pilotSeat.setPosition(helicopter.posX, helicopter.posY, helicopter.posZ);
        worldIn.spawnEntityInWorld(pilotSeat);

        return true;
    }
}
