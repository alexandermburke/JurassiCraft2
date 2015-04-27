package net.ilexiconn.jurassicraft.api;

import net.minecraft.item.ItemBlock;

public interface ISubBlocksBlock
{
    Class<? extends ItemBlock> getItemBlockClass();
}