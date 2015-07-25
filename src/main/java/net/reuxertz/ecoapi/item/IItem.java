package net.reuxertz.ecoapi.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;


public interface IItem
{
    void interactEntity(ItemStack stack, EntityInteractEvent e);

    void interactBlock(ItemStack stack, PlayerInteractEvent e);
}
