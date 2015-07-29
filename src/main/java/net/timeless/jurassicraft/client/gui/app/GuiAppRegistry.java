package net.timeless.jurassicraft.client.gui.app;

import net.timeless.jurassicraft.common.paleopad.App;
import net.timeless.jurassicraft.common.paleopad.AppRegistry;

import java.util.HashMap;
import java.util.Map;

public class GuiAppRegistry
{
    private static Map<App, GuiApp> registeredApps = new HashMap<App, GuiApp>();

    public static void registerApp(GuiApp gui)
    {
        registeredApps.put(gui.app, gui);
    }

    public static void register()
    {
        registerApp(new GuiAppTest(AppRegistry.test));
    }

    public static GuiApp getGui(App app)
    {
        return registeredApps.get(app);
    }
}
