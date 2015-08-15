package net.timeless.jurassicraft.common.paleotab;

import java.util.ArrayList;
import java.util.List;

public class AppRegistry
{
    public static App dinopedia;
    public static App file_explorer;
    private static List<App> registeredApps = new ArrayList<>();

    public static void registerApp(App app)
    {
        registeredApps.add(app);
    }

    public static void register()
    {
        dinopedia = new AppDinoPedia();
        file_explorer = new AppFileExplorer();

        registerApp(dinopedia);
        registerApp(file_explorer);
    }

    public static List<App> getApps()
    {
        return registeredApps;
    }
}
