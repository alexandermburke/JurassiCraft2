package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;

public class ItemEntityRemover extends Item
{

    public ItemEntityRemover()
    {
        super();
        this.setCreativeTab(JCCreativeTabs.items);
        this.setUnlocalizedName("entity_remover");
        this.setMaxStackSize(1);
    }

    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
    {
        if (target instanceof EntityCreature)
        {
            EntityCreature creature = (EntityCreature) target;
            creature.setDead();

            if (player.worldObj.isRemote)
            {
                player.addChatMessage(new ChatComponentText("You removed a " + creature.getDisplayName() + " from the world."));
            }

            return true;
        }

        return false;
    }
}
