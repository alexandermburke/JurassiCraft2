/**
    Copyright (C) 2015 by jabelar

    This file is part of jabelar's Minecraft Forge modding examples; as such,
    you can redistribute it and/or modify it under the terms of the GNU
    General Public License as published by the Free Software Foundation,
    either version 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    For a copy of the GNU General Public License see <http://www.gnu.org/licenses/>.
*/

package net.timeless.animationapi.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;

import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.base.EntityDinosaur;

/**
 * @author jabelar
 *
 */
public class CommandForceAnimation  implements ICommand
{ 
    private final List aliases;
  
    protected EntityDinosaur theClosestDinosaur = null; 
  
    public CommandForceAnimation() 
    { 
        aliases = new ArrayList(); 
        aliases.add("animate"); 
        aliases.add("anim"); 
    } 
  
    @Override 
    public int compareTo(Object o)
    { 
        return 0; 
    } 

    @Override 
    public String getCommandName() 
    { 
        return "animate"; 
    } 

    @Override         
    public String getCommandUsage(ICommandSender parSender) 
    { 
        return "animate <text>"; 
    } 

    @Override 
    public List getCommandAliases() 
    { 
        return this.aliases;
    } 

    @Override 
    public void processCommand(ICommandSender parSender, String[] argString)
    { 
        World theWorld = parSender.getEntityWorld(); 
    
        if (theWorld.isRemote) 
        { 
            JurassiCraft.instance.getLogger().info("Not processing on Client side"); 
        } 
        else 
        { 
            JurassiCraft.instance.getLogger().info("Processing on Server side"); 
            if(argString.length == 0) 
            { 
                parSender.addChatMessage(new ChatComponentText("Invalid argument")); 
                return; 
            } 
    
            getClosestDinosaur(parSender);
            if (theClosestDinosaur == null)
            {
                parSender.addChatMessage(new ChatComponentText("No IAnimatedEntity to animate"));
            }
            else
            {
                parSender.addChatMessage(new ChatComponentText("Animating entity "+theClosestDinosaur.getEntityId()+" with animation type "+argString[0]  
                        + "]")); 
                setDinoAnimation(parSender, argString[0]);
            }
       } 
    } 

    @Override 
    public boolean canCommandSenderUseCommand(ICommandSender var1) 
    { 
        return true;
    } 

    @Override 
    public boolean isUsernameIndex(String[] var1, int var2) 
    { 
        return false;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args,
            BlockPos pos)
    {
        return null;
    }
    
    private void getClosestDinosaur(ICommandSender sender)
    {
        EntityPlayer thePlayer = (EntityPlayer)sender;
        Iterator iterator = thePlayer.worldObj.loadedEntityList.iterator();

        while (iterator.hasNext())
        {
            Entity theEntity = (Entity)iterator.next();
            if (theEntity instanceof EntityDinosaur)
            {
                EntityDinosaur theDinosaur = (EntityDinosaur)theEntity;
                if ((theClosestDinosaur == null) || 
                        (thePlayer.getDistanceSqToEntity(theDinosaur) < thePlayer.getDistanceSqToEntity(theClosestDinosaur)))
                {
                    theClosestDinosaur = theDinosaur;
                }                    
            }
        }
        return;
    }
    
    private void setDinoAnimation(ICommandSender parSender, String parAnimType)
    {
        switch (parAnimType.toUpperCase()) {
        case "IDLE":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.IDLE);
            break;
        case "ATTACKING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.ATTACKING);
            break;
        case "INJURED":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.INJURED);
            break;
        case "HEAD_COCKING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.HEAD_COCKING);
            break;
        case "CALLING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.CALLING);
            break;
        case "HISSING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.HISSING);
            break;
        case "POUNCING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.POUNCING);
            break;
        case "SNIFFING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.SNIFFING);
            break;
        case "EATING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.EATING);
            break;
        case "DRINKING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.DRINKING);
            break;
        case "MATING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.MATING);
            break;
        case "SLEEPING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.SLEEPING);
            break;
        case "RESTING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.RESTING);
            break;
        case "FLYING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.FLYING);
            break;
        case "ROARING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.ROARING);
            break;
        case "SCRATCHING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.SCRATCHING);
            break;
        case "LOOKING_LEFT":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.LOOKING_LEFT);
            break;
        case "LOOKING_RIGHT":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.LOOKING_RIGHT);
            break;
        case "BEGGING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.BEGGING);
            break;
        case "DYING":
            AnimationAPI.sendAnimPacket(theClosestDinosaur, AnimID.DYING);
            break;
        default:
            parSender.addChatMessage(new ChatComponentText("Not a valid animation type"));
            break;
        }
    }
}
