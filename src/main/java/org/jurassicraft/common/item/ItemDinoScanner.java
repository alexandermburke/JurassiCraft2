package org.jurassicraft.common.item;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.entity.base.EntityDinosaur;

/**
 * Copyright 2016 Andrew O. Mellinger
 */
public class ItemDinoScanner extends Item
{
    public ItemDinoScanner()
    {
        super();
        this.setUnlocalizedName("dino_scanner");
        this.setCreativeTab(JCCreativeTabs.items);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
                             float hitX, float hitY, float hitZ)
    {
        return false;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
    {
        if (target instanceof EntityDinosaur && !player.getEntityWorld().isRemote)
        {
            EntityDinosaur dinosaur = (EntityDinosaur) target;

            // NOTE: Shift key is already used for some sort of inventory thing.
            // Command key on mac
            if (GuiScreen.isCtrlKeyDown())
            {
                int food = dinosaur.getMetabolism().getFood();
                dinosaur.getMetabolism().setFood(food - 5000);
                LOGGER.info("food: " + dinosaur.getMetabolism().getFood() + "/" + dinosaur.getMetabolism().getMaxFood() +
                        "(" + (dinosaur.getMetabolism().getMaxFood() * 0.875) + "/" +
                        (dinosaur.getMetabolism().getMaxFood() * 0.25) + ")");
            }
            else
            {
                dinosaur.writeStatsToLog();
            }

            return true;
        }
        return false;
    }

    private static final Logger LOGGER = LogManager.getLogger();
}
