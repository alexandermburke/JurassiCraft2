package net.reuxertz.ainow.item;

import net.ilexiconn.jurassicraft.creativetab.JCCreativeTabs;
import net.minecraft.entity.EntityCreature;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ainow.core.AINow;
import net.reuxertz.ainow.utils.IDObj;
import net.reuxertz.ainow.utils.NBTHelper;
import net.reuxertz.ainow.utils.StringHelper;

public class ItemDocSetEntityHome extends ItemDocBase
{
    public ItemDocSetEntityHome()
    {
        super();
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

    public void InteractBlock(ItemStack stack, PlayerInteractEvent e)
    {
        BlockPos p = e.pos;
        String id = ItemDocSetEntityHome.GetID(stack);
        EntityCreature ec = IDObj.GetEntity(e.world, id);
        String className = ec.getClass().toString();


        e.entityPlayer.addChatMessage(new ChatComponentTranslation("Entity (" + className + ":" + id + ") Home Set to " + StringHelper.BlockPosToString(p)));
    }
}
