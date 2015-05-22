package net.reuxertz.ainow.core;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 5/21/2015.
 */
public class AINowFMLEventHandler {


    @Mod.EventHandler
    public void onChat(ServerChatEvent e)
    {
        String msg = e.message;

        //Get Sub Strings
        List<String> ss = new ArrayList<String>();
        int h = 0;
        for (int i = 0; i < msg.length(); i++)
        {
            if (msg.charAt(i) == ' ')
            {
                String ssi = msg.substring(h, i);
                if (ssi != "" && ssi != " ")
                    ss.add(ssi);
            }
        }

        if (ss.size() < 1)
            return;

        //AI Commands
        while(true) {
            if (ss.get(0) == "/" + AINow.ModID) {
                e.setCanceled(true);

                //Handle Debug Commands
                if  (ss.size() >= 3 && ss.get(1) == "debug") {
                    if (ss.get(2) == "true") {
                        e.player.addChatMessage(new ChatComponentTranslation("AINow debug-mode enabled"));
                        AINow.Instance.DebugMode = true;
                    }
                    if (ss.get(2) == "false") {
                        e.player.addChatMessage(new ChatComponentTranslation("AINow debug-mode disabled"));
                        AINow.Instance.DebugMode = false;
                    }
                    break;
                }
            }
            return;
        }

        e.player.addChatMessage(new ChatComponentTranslation("Invalid AINow command"));
    }
}
