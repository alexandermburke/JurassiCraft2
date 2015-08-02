package net.timeless.jurassicraft.common.paleopad;

import java.util.ArrayList;
import java.util.List;

public class AppRegistry
{
    private static List<App> registeredApps = new ArrayList<>();
    public static App jurassipedia;
    public static App jurassiexplorer;

    public static void registerApp(App app)
    {
        registeredApps.add(app);
    }

    public static void register()
    {
        jurassipedia = new AppJurassiPedia();
        jurassiexplorer = new AppJurassiExplorer();

        registerApp(jurassipedia);
        registerApp(jurassiexplorer);
    }

    public static List<App> getApps()
    {
        return registeredApps;
    }
}
