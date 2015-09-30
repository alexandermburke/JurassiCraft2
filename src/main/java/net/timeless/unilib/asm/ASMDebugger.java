package net.timeless.unilib.asm;

import net.minecraftforge.fml.relauncher.IFMLCallHook;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class ASMDebugger implements IFMLLoadingPlugin, IFMLCallHook
{
    @Override
    public Void call() throws Exception
    {
        return null;
    }

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[]{ "net.timeless.unilib.asm.ASMTransformer" };
    }

    @Override
    public String getModContainerClass()
    {
        return "net.timeless.unilib.asm.ASMCoreContainer";
    }

    @Override
    public String getSetupClass()
    {
        return "net.timeless.unilib.asm.ASMDebugger";
    }

    @Override
    public void injectData(Map<String, Object> data)
    {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
}
