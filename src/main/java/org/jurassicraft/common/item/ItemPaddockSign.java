package org.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.entity.item.EntityBluePrint;
import org.jurassicraft.common.entity.item.EntityPaddockSign;
import org.jurassicraft.common.lang.AdvLang;

public class ItemPaddockSign extends Item
{
    public ItemPaddockSign()
    {
        this.setUnlocalizedName("paddock_sign");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        int dinoId = getDinosaur(stack);
        Dinosaur dino = JCEntityRegistry.getDinosaurById(dinoId);
        String name = "blue_print.blank.name";

        if (dino != null)
        {
            name = "entity." + dino.getName().toLowerCase().replaceAll(" ", "_") + ".name";
        }

        return new AdvLang("item.paddock_sign.name").withProperty("type", name).build();
    }

    public void setDinosaur(ItemStack stack, int dino)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        nbt.setInteger("Dinosaur", dino);

        stack.setTagCompound(nbt);
    }

    public int getDinosaur(ItemStack stack)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt != null)
        {
            if (nbt.hasKey("Dinosaur"))
            {
                return nbt.getInteger("Dinosaur");
            }
        }

        return -1;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (side == EnumFacing.DOWN)
        {
            return false;
        }
        else if (side == EnumFacing.UP)
        {
            return false;
        }
        else
        {
            BlockPos blockpos1 = pos.offset(side);

            if (!playerIn.canPlayerEdit(blockpos1, side, stack))
            {
                return false;
            }
            else
            {
                int dinosaur = getDinosaur(stack);

                if (dinosaur != -1)
                {
                    EntityPaddockSign paddockSign = new EntityPaddockSign(world, blockpos1, side, dinosaur);

                    if (paddockSign.onValidSurface())
                    {
                        if (!world.isRemote)
                        {
                            world.spawnEntityInWorld(paddockSign);
                        }

                        --stack.stackSize;

                        return true;
                    }
                }
            }
        }

        return false;
    }
}
