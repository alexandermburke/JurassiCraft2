package net.timeless.unilib.common;

import com.google.common.collect.Maps;
import net.timeless.unilib.common.structure.StructureBuilder;
import net.timeless.unilib.common.structure.StructureGenerator;

import java.util.HashMap;

public class StructureRegistry {

    private static final StructureRegistry instance = new StructureRegistry();
    private final HashMap<String, StructureGenerator> map;

    private StructureRegistry() {
        map = Maps.newHashMap();
    }

    public void registerStructure(String name, StructureGenerator generator) {
        map.put(name, generator);
    }

    public StructureBuilder createStructure(String name) {
        StructureBuilder builder = new StructureBuilder();
        registerStructure(name, builder);
        return builder;
    }

    public StructureGenerator getStructure(String name) {
        return map.get(name);
    }

    public static StructureRegistry getInstance() {
        return instance;
    }
}
