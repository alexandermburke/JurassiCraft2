package net.timeless.unilib.common;

import net.minecraft.block.Block;

import java.util.Collection;

public interface BlockProvider {
    Collection<Block> createBlocks();

    String getModID();
}
