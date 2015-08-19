package net.timeless.jurassicraft.common.achievements;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;

public class JCAchievement extends Achievement
{
    public JCAchievement(String name, int column, int row, Item display, Achievement parent)
    {
        super("achievement.jurassicraft." + name, "jurassicraft." + name, column, row, display, parent);
    }

    public JCAchievement(String name, int column, int row, Block display, Achievement parent)
    {
        super("achievement.jurassicraft." + name, "jurassicraft." + name, column, row, display, parent);
    }
}
