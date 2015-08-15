package net.timeless.unilib.common;

import net.minecraft.item.Item;

import java.util.Collection;

public interface ItemProvider {
    Collection<Item> createItems();

    String getModID();
}
