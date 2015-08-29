package net.timeless.jurassicraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerCarnivoreFeeder extends Container
{

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return false;
    }
}
