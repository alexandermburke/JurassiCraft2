package net.reuxertz.ecoai.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Target
{
    public World world;
    public BlockPos pos;
    public IBlockState posBlockState;
    public Entity entity;
    public ItemStack entityHeldItem, targetStack;
    public int workState = 0;
    public boolean enableEvaluateTarget = true;

    public BlockPos getRealTimePos()
    {
        if (this.pos != null)
            return this.pos;
        if (this.entity != null)
            return this.entity.getPosition();

        return null;
    }

    protected Target(World w)
    {
        this.world = w;
    }
    public Target(World w, BlockPos pos)
    {
        this(w, pos, null);
    }
    public Target(World w, BlockPos pos, ItemStack stack)
    {
        this(w);
        this.pos = pos;
        this.posBlockState = w.getBlockState(pos);
        this.targetStack = stack;
    }
    public Target(World w, Entity entity)
    {
        this(w, entity, null);
    }
    public Target(World w, Entity entity, ItemStack heldItem)
    {
        this(w);
        this.entity = entity;
        this.entityHeldItem = heldItem;
        this.targetStack = heldItem;
    }

    public void activateNavigate(AINavigate navigate)
    {
        if (this.pos != null)
            navigate.activateSeekPosTask(this.pos);
        if (this.entity != null)
            navigate.activateSeekEntityTask(this.entity);
    }
}
