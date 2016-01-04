package org.jurassicraft.common.paleopad;

import java.util.ArrayList;
import java.util.List;

public class AppRegistry
{
    private static final List<App> registeredApps = new ArrayList<>();
    public static App dinopedia;
    public static App file_explorer;
    public static App flappy_dino;
    public static App minimap;
    public static App security;

    private void registerApp(App app)
    {
        registeredApps.add(app);
    }

    public void register()
    {
        dinopedia = new AppDinoPedia();
        file_explorer = new AppFileExplorer();
        flappy_dino = new AppFlappyDino();
        minimap = new AppMinimap();
        security = new AppSecurity();

        registerApp(dinopedia);
        registerApp(file_explorer);
        registerApp(flappy_dino);
        registerApp(minimap);
        registerApp(security);
    }

    public static List<App> getApps()
    {
        return registeredApps;
    }
}
