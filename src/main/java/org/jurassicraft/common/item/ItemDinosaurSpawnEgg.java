package org.jurassicraft.common.item;

import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.EntityDinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.lang.AdvLang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                dino.setDNAQuality(100);

                int mode = getMode(stack);

                if (mode == 1)
                {
                    dino.setMale(true);
                }
                else if (mode == 2)
                {
                    dino.setMale(false);
                }

                if (player.isSneaking())
                {
                    dino.setAge(0);
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

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        int mode = changeMode(stack);

        if (world.isRemote)
        {
            String modeString = "";

            if (mode == 0)
            {
                modeString = "random";
            }
            else if (mode == 1)
            {
                modeString = "male";
            }
            else if (mode == 2)
            {
                modeString = "female";
            }

            player.addChatMessage(new ChatComponentText(new AdvLang("spawnegg.genderchange.name").withProperty("mode", StatCollector.translateToLocal("gender." + modeString + ".name")).build()));
        }

        return stack;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        Dinosaur dinosaur = this.getDinosaur(stack);

        return new AdvLang("item.dino_spawn_egg.name").withProperty("dino", "entity.jurassicraft." + dinosaur.getName().replace(" ", "_").toLowerCase() + ".name").build();
    }

    public Dinosaur getDinosaur(ItemStack stack)
    {
        Dinosaur dinosaur = JCEntityRegistry.getDinosaurById(stack.getItemDamage());

        if (dinosaur == null)
        {
            dinosaur = JCEntityRegistry.achillobator;
        }

        return dinosaur;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        Dinosaur dino = getDinosaur(stack);

        if (dino != null)
        {
            int mode = getMode(stack);

            if (mode == 0)
            {
                mode = JurassiCraft.timerTicks % 64 > 32 ? 1 : 2;
            }

            if (mode == 1)
            {
                return renderPass == 0 ? dino.getEggPrimaryColorMale() : dino.getEggSecondaryColorMale();
            }
            else
            {
                return renderPass == 0 ? dino.getEggPrimaryColorFemale() : dino.getEggSecondaryColorFemale();
            }
        }

        return 16777215;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subtypes)
    {
        List<Dinosaur> dinosaurs = new ArrayList<Dinosaur>(JCEntityRegistry.getDinosaurs());

        Map<Dinosaur, Integer> ids = new HashMap<Dinosaur, Integer>();

        int id = 0;

        for (Dinosaur dino : dinosaurs)
        {
            ids.put(dino, id);

            id++;
        }

        Collections.sort(dinosaurs);

        for (Dinosaur dino : dinosaurs)
        {
            if (dino.shouldRegister())
            {
                subtypes.add(new ItemStack(item, 1, ids.get(dino)));
            }
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
                    mobspawnerbaselogic.setEntityName((String) EntityList.classToStringMapping.get(getDinosaur(stack).getDinosaurClass()));
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

            if (side == EnumFacing.UP && iblockstate.getBlock() instanceof BlockFence)
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

    public int getMode(ItemStack stack)
    {
        return getNBT(stack).getInteger("GenderMode");
    }

    public int changeMode(ItemStack stack)
    {
        NBTTagCompound nbt = getNBT(stack);

        int mode = getMode(stack);
        mode++;

        if (mode > 2)
        {
            mode = 0;
        }

        nbt.setInteger("GenderMode", mode);

        stack.setTagCompound(nbt);

        return mode;
    }

    public NBTTagCompound getNBT(ItemStack stack)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        stack.setTagCompound(nbt);

        return nbt;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List<String> lore, boolean advanced)
    {
        lore.add(StatCollector.translateToLocal("lore.baby_dino.name"));
        lore.add(StatCollector.translateToLocal("lore.change_gender.name"));
    }
}
