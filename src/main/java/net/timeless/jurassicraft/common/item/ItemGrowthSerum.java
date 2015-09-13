package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class ItemGrowthSerum extends Item
{
    public ItemGrowthSerum()
    {
        super();
        this.setCreativeTab(JCCreativeTabs.items);
        this.setUnlocalizedName("growth_serum");
    }

    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target)
    {
        if (target instanceof EntityDinosaur)
        {
            EntityDinosaur dinosaur = (EntityDinosaur) target;

            dinosaur.increaseGrowthSpeed();
//            dinosaur.setAge(dinosaur.getDinosaurAge() + 750);

            stack.stackSize--;

            if (!player.capabilities.isCreativeMode)
            {
                player.inventory.addItemStackToInventory(new ItemStack(JCItemRegistry.empty_syringe));
            }

            return true;
        }

        return false;
    }
}
