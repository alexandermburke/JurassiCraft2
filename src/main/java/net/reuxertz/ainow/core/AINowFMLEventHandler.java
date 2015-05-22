package net.reuxertz.ainow.core;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class AINowFMLEventHandler
{


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
                if (!ssi.equals("") && !ssi.equals(" "))
                    ss.add(ssi);
            }
        }

        if (ss.size() < 1)
            return;

        //AI Commands
        while (true)
        {
            if (ss.get(0).equals("/jurassicraft"))
            {
                e.setCanceled(true);

                if (ss.size() >= 3 && ss.get(1).equals("debug"))
                {
                    if (ss.get(2).equals("true"))
                    {
                        e.player.addChatMessage(new ChatComponentTranslation("AINow debug-mode enabled"));
                        JurassiCraft.debug = true;
                    }
                    if (ss.get(2).equals("false"))
                    {
                        e.player.addChatMessage(new ChatComponentTranslation("AINow debug-mode disabled"));
                        JurassiCraft.debug = false;
                    }
                    break;
                }
            }
            return;
        }

        e.player.addChatMessage(new ChatComponentTranslation("Invalid AINow command"));
    }
}
