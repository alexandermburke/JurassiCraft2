package net.timeless.jurassicraft.common.paleopad;

import java.util.ArrayList;
import java.util.List;

public class AppRegistry
{
    private static List<App> registeredApps = new ArrayList<>();
    public static App jurassipedia;
    public static App browser;

    public static void registerApp(App app)
    {
        registeredApps.add(app);
    }

    public static void register()
    {
        jurassipedia = new AppJurassiPedia();
        browser = new AppBrowser();

        registerApp(jurassipedia);
        registerApp(browser);
    }

    public static List<App> getApps()
    {
        return registeredApps;
    }
}
