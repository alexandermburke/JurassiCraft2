package net.timeless.jurassicraft.common.paleotab;

import java.util.ArrayList;
import java.util.List;

public class AppRegistry
{
    private static List<App> registeredApps = new ArrayList<>();
    public static App dinopedia;
    public static App file_explorer;
    public static App flappy_dino;

    public static void registerApp(App app)
    {
        registeredApps.add(app);
    }

    public void register()
    {
        dinopedia = new AppDinoPedia();
        file_explorer = new AppFileExplorer();
        flappy_dino = new AppFlappyDino();

        registerApp(dinopedia);
        registerApp(file_explorer);
        registerApp(flappy_dino);
    }

    public static List<App> getApps()
    {
        return registeredApps;
    }
}
