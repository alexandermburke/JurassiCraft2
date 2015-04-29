package net.ilexiconn.jurassicraft.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.ilexiconn.jurassicraft.api.ISubBlocksBlock;
import net.ilexiconn.llibrary.IContentHandler;
import net.minecraft.block.Block;

import java.lang.reflect.Field;

public class JCBlockRegistry implements IContentHandler
{
    public void init()
    {

    }

    public void initCreativeTabs()
    {

    }

    public void gameRegistry() throws Exception
    {
        initCreativeTabs();
        try
        {
            for (Field f : JCBlockRegistry.class.getDeclaredFields())
            {
                Object obj = f.get(null);
                if (obj instanceof Block) registerBlock((Block) obj);
                else if (obj instanceof Block[]) for (Block block : (Block[]) obj) registerBlock(block);
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void registerBlock(Block block)
    {
        String name = block.getUnlocalizedName();
        String[] strings = name.split("\\.");

        if (block instanceof ISubBlocksBlock)
            GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
        else GameRegistry.registerBlock(block, strings[strings.length - 1]);
    }
}
