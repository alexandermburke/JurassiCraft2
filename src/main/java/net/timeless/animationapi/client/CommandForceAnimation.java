/**
 * Copyright (C) 2015 by jabelar
 * <p/>
 * This file is part of jabelar's Minecraft Forge modding examples; as such, you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p/>
 * For a copy of the GNU General Public License see <http://www.gnu.org/licenses/>.
 */

package net.timeless.animationapi.client;

import com.google.common.collect.Lists;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats.Type;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.timeless.animationapi.AnimationAPI;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.entity.base.EntityDinosaur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jabelar
 */
public class CommandForceAnimation implements ICommand
{
    /**
     * A proxy sender that can always execute the "@" (selection) command.
     *
     * @author WorldSEnder
     */
    private static class ProxySender implements ICommandSender
    {
        private final ICommandSender original;

        public ProxySender(ICommandSender proxy)
        {
            this.original = Objects.requireNonNull(proxy);
        }

        @Override
        public void addChatMessage(IChatComponent component)
        {
            original.addChatMessage(component);
        }

        @Override
        public boolean canUseCommand(int permLevel, String commandName)
        {
            if (commandName.equals("@"))
            {
                return true;
            }

            return original.canUseCommand(permLevel, commandName);
        }

        @Override
        public Entity getCommandSenderEntity()
        {
            return original.getCommandSenderEntity();
        }

        @Override
        public String getName()
        {
            return original.getName();
        }

        @Override
        public IChatComponent getDisplayName()
        {
            return original.getDisplayName();
        }

        @Override
        public World getEntityWorld()
        {
            return original.getEntityWorld();
        }

        @Override
        public BlockPos getPosition()
        {
            return original.getPosition();
        }

        @Override
        public Vec3 getPositionVector()
        {
            return original.getPositionVector();
        }

        @Override
        public boolean sendCommandFeedback()
        {
            return original.sendCommandFeedback();
        }

        @Override
        public void setCommandStat(Type type, int amount)
        {
            original.setCommandStat(type, amount);
        }
    }

    private final List<String> aliases;

    public CommandForceAnimation()
    {
        aliases = new ArrayList<String>();
        aliases.add("animate");
        aliases.add("anim");
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "animate";
    }

    @Override
    public String getCommandUsage(ICommandSender parSender)
    {
        return "animate <AnimID> [<entitySelector>]";
    }

    @Override
    public List getAliases()
    {
        return this.aliases;
    }

    @Override
    public void execute(ICommandSender parSender, String[] argString) throws CommandException
    {
        World theWorld = parSender.getEntityWorld();

        if (theWorld.isRemote)
        {
            JurassiCraft.instance.getLogger().debug("Not processing on Client side");
        }
        else
        {
            JurassiCraft.instance.getLogger().debug("Processing on Server side");
            if (argString.length < 1)
            {
                throw new WrongUsageException("Missing the animation to set");
            }
            String entitySelector = argString.length < 2 ? "@e[c=1]" : argString[1];
            List<EntityDinosaur> dinos = PlayerSelector.matchEntities(new ProxySender(parSender), entitySelector, EntityDinosaur.class);
            if (dinos == null || dinos.size() == 0)
            {
                throw new EntityNotFoundException("No IAnimatedEntity to animate");
            }
            for (EntityDinosaur entity : dinos)
            {
                setDinoAnimation(parSender, entity, argString[0]);
                parSender.addChatMessage(new ChatComponentText("Animating entity " + entity.getEntityId() + " with animation type " + argString[0]));
            }
        }
    }

    @Override
    public boolean canCommandSenderUse(ICommandSender var1)
    {
        return true;
    }

    @Override
    public boolean isUsernameIndex(String[] var1, int var2)
    {
        return false;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        if (args.length == 1)
        {
            List<String> animations = Lists.newArrayList();
            String current = args[0].toLowerCase();
            for (AnimID animation : AnimID.values())
            {
                if (animation.name().toLowerCase().startsWith(current))
                {
                    animations.add(animation.name());
                }
            }
            return animations;
        }
        return null;
    }

    private static void setDinoAnimation(ICommandSender sender, EntityDinosaur entity, String parAnimType) throws CommandException
    {
        try
        {
            AnimID animation = AnimID.valueOf(parAnimType.toUpperCase());
            AnimationAPI.sendAnimPacket(entity, animation);
        }
        catch (IllegalArgumentException iae)
        {
            throw new CommandException(parAnimType + " is not a valid animation.");
        }
    }
}
