package net.reuxertz.ecocraft.core.handlers;

import net.ilexiconn.jurassicraft.JurassiCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatHandler
{
    public static void HandleChat(ServerChatEvent e)
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
        if (ss.get(0).equals("@eco"))
        {
            e.setCanceled(true);

            if (ChatHandler.CommandHandleDisable(e.player, ss))
                return;


            e.player.addChatMessage(new ChatComponentTranslation("Invalid AINow command"));
        }

    }

    //Commands
    public static boolean CommandHandleDisable(EntityPlayer ep, List<String> ss)
    {
        if (ss.size() >= 3 && ss.get(1).equals("debug"))
        {
            if (ss.get(2).equals("true")) {
                ep.addChatMessage(new ChatComponentTranslation("AINow debug-mode enabled"));
                JurassiCraft.debug = true;
                return true;
            }
            if (ss.get(2).equals("false")) {
                ep.addChatMessage(new ChatComponentTranslation("AINow debug-mode disabled"));
                JurassiCraft.debug = false;
                return true;
            }
            return false;
        }
        return false;
    }
}

