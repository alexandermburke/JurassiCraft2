package net.timeless.unilib.common.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.util.EnumFacing;
import net.timeless.unilib.common.structure.rules.PlaceRule;
import net.timeless.unilib.common.structure.rules.RepeatRule;

import java.util.HashMap;
import java.util.List;

public class ComponentInfo {
    public HashMap<BlockCoords, BlockList> blocks;
    public List<RepeatRule> repeats;
    public EnumFacing facing;

    public ComponentInfo() {
        facing = EnumFacing.NORTH;
        blocks = Maps.newHashMap();
        repeats = Lists.newArrayList();
        repeats.add(new PlaceRule());
    }
}
