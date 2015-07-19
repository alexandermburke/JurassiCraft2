package net.timeless.jurassicraft.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.entity.item.EntityBluePrint;

public class ItemBluePrint extends Item
{
    public ItemBluePrint()
    {
        this.setUnlocalizedName("blue_print");
        this.setCreativeTab(JCCreativeTabs.items);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        int dinoId = getDinosaur(stack);
        Dinosaur dino = JCEntityRegistry.getDinosaurById(dinoId);
        String name = "Blank"; //TODO Translation

        if(dino != null)
            name = StatCollector.translateToLocal("entity." + dino.getName().toLowerCase().replaceAll(" ", "_") + ".name");
        
        return name + " " + super.getItemStackDisplayName(stack);
    }

//    /**
//     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
//     */
//    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
//    {
//        if(target instanceof EntityDinosaur) //TODO try from EntityDinosaur interactFirst
//        {
//            EntityDinosaur dino = (EntityDinosaur) target;
//
//            setDinosaur(stack, JCEntityRegistry.getDinosaurId(dino.getDinosaur()));
//
//            return true;
//        }
//
//        return false;
//    }

    public void setDinosaur(ItemStack stack, int dino)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if(nbt == null)
            nbt = new NBTTagCompound();

        nbt.setInteger("Dinosaur", dino);
        
        stack.setTagCompound(nbt);
    }

    public int getDinosaur(ItemStack stack)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if(nbt != null)
        {
            if(nbt.hasKey("Dinosaur"))
            {
                return nbt.getInteger("Dinosaur");
            }
        }

        return -1;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
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
                
                if(dinosaur != -1)
                {
                    EntityBluePrint bluePrint = new EntityBluePrint(worldIn, blockpos1, side, dinosaur);

                    if (bluePrint.onValidSurface())
                    {
                        if (!worldIn.isRemote)
                        {
                            worldIn.spawnEntityInWorld(bluePrint);
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