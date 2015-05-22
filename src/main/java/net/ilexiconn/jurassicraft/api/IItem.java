package net.ilexiconn.jurassicraft.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


public interface IItem
{
    void InteractEntity(ItemStack stack, EntityInteractEvent e);

    void InteractBlock(ItemStack stack, PlayerInteractEvent e);
}
