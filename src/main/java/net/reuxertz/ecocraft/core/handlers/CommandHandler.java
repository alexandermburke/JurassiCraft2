package net.reuxertz.ecocraft.core.handlers;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements ICommand
{
    private final List aliases;

    public CommandHandler()
    {
        aliases = new ArrayList();
        aliases.add("@eco");
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return "EcoCraftCommandHandler";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "@eco <text>";
    }

    @Override
    public List getAliases()
    {
        return this.aliases;
    }

    @Override
    public void execute(ICommandSender sender, String[] argString)
    {
        World world = sender.getEntityWorld();

        if (world.isRemote)
        {
            System.out.println("Not processing on Client side");
        }
        else
        {
            System.out.println("Processing on Server side");
        }

        return;
    }

    @Override
    public boolean canCommandSenderUse(ICommandSender var1)
    {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] var1, int var2)
    {
        return false;
    }
}