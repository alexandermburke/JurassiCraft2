package net.ilexiconn.jurassicraft.item;

import java.util.ArrayList;
import java.util.List;

import net.ilexiconn.jurassicraft.entity.Creature;
import net.ilexiconn.jurassicraft.entity.EntityDinosaur;
import net.ilexiconn.jurassicraft.entity.JCEntityRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDinosaurSpawnEgg extends Item
{
    public static List<Class<? extends EntityLiving>> creatures = new ArrayList<Class<? extends EntityLiving>>();

    public ItemDinosaurSpawnEgg()
    {
        this.setUnlocalizedName("dino_spawn_egg");
        this.setHasSubtypes(true);
    }

    public EntityDinosaur spawnCreature(World world, EntityPlayer player, ItemStack egg, double x, double y, double z)
    {
        Class creatureClass = creatures.get(egg.getItemDamage());

        try
        {
            Entity creatureToSpawn = (Entity) creatureClass.getConstructor(World.class).newInstance(player.worldObj);

            if (creatureToSpawn instanceof EntityDinosaur)
            {
                EntityDinosaur creature = (EntityDinosaur) creatureToSpawn;
                creature.setPosition(x, y, z);
                creature.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
                creature.rotationYawHead = creature.rotationYaw;
                creature.renderYawOffset = creature.rotationYaw;

                return creature;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        return StatCollector.translateToLocal("item.dino_spawn_egg.name").trim() + " " + getCreature(stack).getName();
    }

    public Creature getCreature(ItemStack stack)
    {
        return JCEntityRegistry.getCreatureFromClass(creatures.get(stack.getItemDamage()));
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        Creature creature = getCreature(stack);

        return creature != null ? (renderPass == 0 ? creature.getEggPrimaryColor() : creature.getEggSecondaryColor()) : 16777215;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subtypes)
    {
        for (int i = 0; i < creatures.size(); i++)
        {
            subtypes.add(new ItemStack(item, 1, i));
        }
    }

    /**
     * Called when a Block is right-clicked with this Item
     *  
     * @param pos The block being right-clicked
     * @param side The side being right-clicked
     */
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
                    MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();
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
                if (dinosaur instanceof EntityLivingBase && stack.hasDisplayName())
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
}
