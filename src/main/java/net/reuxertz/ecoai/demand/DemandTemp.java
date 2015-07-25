package net.reuxertz.ecoai.demand;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.reuxertz.ecoai.ai.AICore;

import java.util.Arrays;

public class DemandTemp extends BaseDemand
{
    public DemandTemp(AICore ai)
    {
        super(ai, Arrays.asList(new ItemStack(Item.getItemFromBlock(Blocks.log)), new ItemStack(Item.getItemFromBlock(Blocks.log2)),
                new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 0), new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 1),
                new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 2), new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 3),
                new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 4), new ItemStack(Item.getItemFromBlock(Blocks.sapling), 1, 5),
                new ItemStack(Item.getItemFromBlock(Blocks.sand)), new ItemStack(Item.getItemFromBlock(Blocks.sandstone)),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone)), new ItemStack(Item.getItemFromBlock(Blocks.stone)),
                new ItemStack(Item.getItemFromBlock(Blocks.dirt)), new ItemStack(Item.getItemFromBlock(Blocks.grass)),
                new ItemStack(Item.getItemFromBlock(Blocks.gravel)), new ItemStack(Item.getItemFromBlock(Blocks.clay)),
                new ItemStack(Item.getItemFromBlock(Blocks.hardened_clay)), new ItemStack(Item.getItemFromBlock(Blocks.stained_hardened_clay))));
    }

    public int wantedDemandCount(ItemStack stack)
    {
        if (!ItemFood.class.isInstance(stack.getItem()))
            return 0;

        return 64;
    }

    public ItemStack isItemDemanded(ItemStack item)
    {
        return super.isItemDemanded(item);
    }

    public ItemStack isEntityDemanded(Entity e)
    {
        return super.isEntityDemanded(e);
    }

    public void performSeekedEntityAction2(Entity e)
    {
        /*
        if (e == null)
            return;

        if (EntityItem.class.isInstance(e))
        {

            EntityItem ei = ((EntityItem) e);
            ItemStack stack = ei.getEntityItem();

            if (stack != null && this.isItemDemanded(stack) != null)
            {
                //Raw Eat
                ItemStack retItemStack = stack.copy();

                if (this.aiCore.getBioState().isHungry())
                    retItemStack = this.aiCore.getBioState().intakeFood(retItemStack);

                //Add To Inventory
                if (retItemStack != null && this.aiCore.getMaxInventorySize() > 0)
                    retItemStack = this.aiCore.addToInventory(retItemStack);

                //Handle environment
                if (retItemStack != null && retItemStack.stackSize > 0)
                    ei.setEntityItemStack(retItemStack);

                //Remove if null/zero
                if (retItemStack == null || retItemStack.stackSize == 0)
                    this.aiCore.agentAI().worldObj.removeEntity(ei);
            }
        }*/

    }
}
