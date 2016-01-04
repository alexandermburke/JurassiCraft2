package net.timeless.animationapi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.timeless.animationapi.client.CommandForceAnimation;

@Mod(modid = "jcanimationapi", name = "JurassiCraft AnimationAPI", version = "1.2.5")
public class AnimationAPI
{
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandForceAnimation());
    }
}
