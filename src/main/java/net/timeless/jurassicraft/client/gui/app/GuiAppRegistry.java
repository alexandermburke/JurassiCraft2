package net.timeless.jurassicraft.client.gui.app;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.paleotab.App;
import net.timeless.jurassicraft.common.paleotab.AppRegistry;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class GuiAppRegistry
{
    private static Map<App, GuiApp> registeredApps = new HashMap<App, GuiApp>();

    public static void registerApp(GuiApp gui)
    {
        registeredApps.put(gui.app, gui);
    }

    public static void register()
    {
        registerApp(new GuiAppDinoPedia(AppRegistry.dinopedia));
        registerApp(new GuiAppFileExplorer(AppRegistry.file_explorer));
        registerApp(new GuiAppFlappyDino(AppRegistry.flappy_dino));
    }

    public static GuiApp getGui(App app)
    {
        return registeredApps.get(app);
    }
}
