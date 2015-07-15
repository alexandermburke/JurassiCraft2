package net.timeless.jurassicraft.common.item;

import java.util.List;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.creativetab.JCCreativeTabs;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;

public class ItemDinosaurSpawnEgg extends Item
{
    public ItemDinosaurSpawnEgg()
    {
        this.setUnlocalizedName("dino_spawn_egg");
        this.setHasSubtypes(true);

        this.setCreativeTab(JCCreativeTabs.spawnEggs);
    }

    public EntityDinosaur spawnCreature(World world, EntityPlayer player, ItemStack stack, double x, double y, double z)
    {
        Dinosaur dinoInEgg = getDinosaur(stack);

        if (dinoInEgg != null)
        {
            Class<? extends EntityDinosaur> dinoClass = dinoInEgg.getDinosaurClass();

            try
            {
                EntityDinosaur dino = dinoClass.getConstructor(World.class).newInstance(player.worldObj);

                if (!player.isSneaking())
                {
                    dino.setAge(dino.getDinosaur().getMaximumAge());
                }

                dino.setPosition(x, y, z);
                dino.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
                dino.rotationYawHead = dino.rotationYaw;
                dino.renderYawOffset = dino.rotationYaw;
                return dino;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        Dinosaur dinosaur = this.getDinosaur(stack);

        if (dinosaur != null)

            return (StatCollector.translateToLocal("item.dino_spawn_egg.name") + " " + StatCollector.translateToLocal("entity." + dinosaur.getName().replace(" ", "_").toLowerCase() + ".name")).trim();

        return super.getItemStackDisplayName(stack);
    }

    public Dinosaur getDinosaur(ItemStack stack)
    {
        return JCEntityRegistry.getDinosaurById(stack.getItemDamage());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        Dinosaur dino = getDinosaur(stack);

        return dino != null ? (renderPass == 0 ? dino.getEggPrimaryColor() : dino.getEggSecondaryColor()) : 16777215;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subtypes)
    {
        int i = 0;

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            if (dino.shouldRegister())
                subtypes.add(new ItemStack(item, 1, i));

            i++;
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.canPlayerEdit(pos.offset(side), side, stack))
        {
            return false;
        }
        else
        {
            IBlockState iblockstate = world.getBlockState(pos);

            if (iblockstate.getBlock() == Blocks.mob_spawner)
            {
                TileEntity tileentity = world.getTileEntity(pos);

                if (tileentity instanceof TileEntityMobSpawner)
                {
                    MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic();
                    mobspawnerbaselogic.setEntityName(EntityList.getStringFromID(stack.getMetadata()));
                    tileentity.markDirty();
                    world.markBlockForUpdate(pos);

                    if (!player.capabilities.isCreativeMode)
                    {
                        --stack.stackSize;
                    }

                    return true;
                }
            }

            pos = pos.offset(side);
            double yOffset = 0.0D;

            if (side == EnumFacing.UP && iblockstate instanceof BlockFence)
            {
                yOffset = 0.5D;
            }

            EntityDinosaur dinosaur = spawnCreature(world, player, stack, (double) pos.getX() + 0.5D, (double) pos.getY() + yOffset, (double) pos.getZ() + 0.5D);

            if (dinosaur != null)
            {
                if (stack.hasDisplayName())
                {
                    dinosaur.setCustomNameTag(stack.getDisplayName());
                }

                if (!player.capabilities.isCreativeMode)
                {
                    --stack.stackSize;
                }

                world.spawnEntityInWorld(dinosaur);
                dinosaur.playLivingSound();
            }

            return true;
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean advanced)
    {
        lore.add("Sneak + Right Click to spawn baby dinosaur"); //TODO Translations
    }
}
