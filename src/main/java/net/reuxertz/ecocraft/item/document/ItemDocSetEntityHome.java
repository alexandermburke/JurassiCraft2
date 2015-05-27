package net.reuxertz.ecocraft.item.document;

import net.minecraft.entity.EntityCreature;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.reuxertz.ecocraft.utils.IDObj;
import net.reuxertz.ecocraft.utils.StringHelper;

public class ItemDocSetEntityHome extends ItemDocBase
{
    public ItemDocSetEntityHome()
    {
        super();

        this._displayName = "Set Entity Home Document";
        this.setMaxStackSize(1);
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
