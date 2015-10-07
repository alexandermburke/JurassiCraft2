package net.timeless.unilib.asm;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.ModMetadata;

public class ASMCoreContainer extends DummyModContainer
{
    public ASMCoreContainer()
    {
        super(createMetadata());
    }

    private static ModMetadata createMetadata()
    {
        ModMetadata md = new ModMetadata();
        md.modId = "unilib";
        md.name = "UnilibCore";
        return md;
    }
}
