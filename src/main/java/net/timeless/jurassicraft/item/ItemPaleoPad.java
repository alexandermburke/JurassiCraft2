package net.timeless.jurassicraft.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.timeless.jurassicraft.client.gui.GuiPaleoPadViewEntity;
import net.timeless.jurassicraft.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.entity.base.EntityDinosaur;

public class ItemPaleoPad extends Item
{
    public ItemPaleoPad()
    {
        super();

        this.setUnlocalizedName("paleo_pad");
        this.setMaxStackSize(1);

        this.setCreativeTab(JCCreativeTabs.items);
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
    {
        if (target instanceof EntityDinosaur)
        {
            EntityDinosaur dino = (EntityDinosaur) target;

            if (player.worldObj.isRemote)
            {
                Minecraft.getMinecraft().displayGuiScreen(new GuiPaleoPadViewEntity(dino, dino.getDinosaur())); //Test for now must not use this though
                player.addChatMessage(new ChatComponentText("Days Existed: " + dino.getDaysExisted()));
            }

            return true;
        }

        return false;
    }
}
