package net.timeless.jurassicraft.common.paleopad;

import java.util.ArrayList;
import java.util.List;

public class AppRegistry
{
    private static List<App> registeredApps = new ArrayList<>();

    public static void registerApp(App app)
    {
        registeredApps.add(app);
    }

    public static void register()
    {
        registerApp(new AppTest());
    }

    public static List<App> getApps()
    {
        return registeredApps;
    }
}
