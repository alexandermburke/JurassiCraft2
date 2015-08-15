package net.reuxertz.ecoai.ai.modules;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoai.ai.AINavigate;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoapi.ecology.EcoFlora;
import net.reuxertz.ecoapi.entity.Target;
import net.reuxertz.ecoapi.util.BlockHelper;
import net.reuxertz.ecoapi.util.BlockPosHelper;
import scala.actors.threadpool.Arrays;

public class AIFarming extends AIGather
{
    public AIFarming(IDemand demand, AICore entity, AINavigate navigate, Target t)
    {
        super(demand, entity, navigate, t);
    }

    public boolean doWorkContinue()
    {
        if (this.getAgent().worldObj.isRemote)
            return false;

        if (this.workTarget.entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) this.workTarget.entity;
            ItemStack r = this.agentAI.addToInventory(entityItem.getEntityItem());

            if (r == null)
                entityItem.setDead();

            if (r != null && r.stackSize != entityItem.getEntityItem().stackSize)
            {
                if (r.stackSize != 0)
                    entityItem.setEntityItemStack(r);
            }
            return false;
        }

        if (this.workTarget.pos == null)
            return false;

        if (this.workTarget.workState == 0)
        {
            //S25PacketBlockBreakAnim packet = new S25PacketBlockBreakAnim(0, this.workTarget.pos, DAMAGE);
            //int dimension = this.getAgent().dimension;
            float bd = 0;
            //Minecraft.getMinecraft().theWorld.sendBlockBreakProgress(this.getAgent().getEntityId(), this.workTarget.pos, (int) (bd * 10.0F) - 1);

            this.getAgent().worldObj.destroyBlock(this.workTarget.pos, true);
            this.workTarget.enableEvaluateTarget = false;
            this.workTarget.workState++;
            return true;
        }

        BlockPos replantPos = new BlockPos(this.workTarget.pos.getX(), this.workTarget.pos.getY(), this.workTarget.pos.getZ());
        boolean replantOverride = false;

        //Handle Farm Expansion
        if (this.workTarget.workState == 2)
        {
            BlockPos p = this.findNewFarmland(this.getAgent().worldObj, this.workTarget.pos.add(0, -1, 0));

            if (p == null)
                return false;

            this.getAgent().worldObj.setBlockState(p, Blocks.farmland.getStateFromMeta(0));
            replantPos = p.add(0, 1, 0);
            replantOverride = true;
        }

        //Handle Replant
        if (this.workTarget.workState == 1 || replantOverride)
        {
            ItemStack seeds = EcoFlora.getFloraBlockSeed(this.workTarget.posBlockState);

            if (seeds != null)
            {
                BlockPos posBelow = new BlockPos(replantPos.getX(), replantPos.getY() - 1, replantPos.getZ());

                boolean isFarmland = true;
                if (replantOverride)
                    isFarmland = this.isValidFarmland(this.getAgent().worldObj, posBelow);//Block.isEqualTo(blockBelow.getBlock(), Blocks.farmland);// && blockBelow.getBlock().getMetaFromState(blockBelow) == 1;

                //this.workTarget.posBlockState.getBlock().getMetaFromState(
                //        this.getAgent().worldObj.getBlockState(new BlockPos(this.workTarget.pos.getX(), this.workTarget.pos.getY() - 1, this.workTarget.pos.getZ()))) == 1;

                ItemStack seedInv = this.agentAI.getFromInventory(new ItemStack(seeds.getItem(), 1, seeds.getItemDamage()));

                if (isFarmland && seedInv.stackSize > 0)
                    this.getAgent().worldObj.setBlockState(replantPos, this.workTarget.posBlockState.getBlock().getStateFromMeta(0));
            }

            this.workTarget.workState++;

            return !replantOverride;
        }


        return false;
    }

    protected boolean isValidFarmland(World w, BlockPos farmlandPos)
    {
        BlockPos cropPos = farmlandPos.add(0, 1, 0);
        IBlockState stateBelow = w.getBlockState(farmlandPos);
        Block blockBelow = stateBelow.getBlock();
        Block blockAbove = w.getBlockState(cropPos).getBlock();

        boolean belowIsFarmable = BlockHelper.getBlockEquals(blockBelow, Arrays.asList(new Block[] { Blocks.farmland, Blocks.dirt, Blocks.grass }));//Block.isEqualTo(blockBelow, Blocks.farmland);
        boolean aboveIsAcceptable = BlockHelper.getBlockEquals(blockAbove,
                Arrays.asList(new Block[] { Blocks.air, Blocks.tallgrass, Blocks.double_plant,
                        Blocks.red_flower, Blocks.yellow_flower, Blocks.brown_mushroom, Blocks.red_mushroom }));
        boolean isFertile = this.isBlockFertile(w, farmlandPos);

        return belowIsFarmable && isFertile && aboveIsAcceptable;

    }

    protected boolean isBlockFertile(World w, BlockPos farmlandPos)
    {
        //center.add(0, -1, 0);
        int dx = (AICore.RND.nextInt(1) * 2) - 1;
        int dy = (AICore.RND.nextInt(1) * 2) - 1;
        int dz = (AICore.RND.nextInt(1) * 2) - 1;

        int size = 2;
        int sx = farmlandPos.getX() - (dx * size), ex = farmlandPos.getX() + (dx * size);
        int sy = farmlandPos.getY() - (dy * size), ey = farmlandPos.getY() + (dy * size);
        int sz = farmlandPos.getZ() - (dz * size), ez = farmlandPos.getZ() + (dz * size);

        for (int x = sx; dx * x <= dx * ex; x += dx)
            for (int y = sy; dy * y <= dy * ey; y += dy)
                for (int z = sz; dz * z <= dz * ez; z += dz)
                {
                    Block b = w.getBlockState(new BlockPos(x, y, z)).getBlock();
                    if (Block.isEqualTo(b, Blocks.water) || Block.isEqualTo(b, Blocks.flowing_water))
                        return true;
                }

        return false;

    }

    protected BlockPos findNewFarmland(World w, BlockPos farmlandPos)
    {
        //center.add(0, -1, 0);
        int dx = (AICore.RND.nextInt(1) * 2) - 1;
        int dy = (AICore.RND.nextInt(1) * 2) - 1;
        int dz = (AICore.RND.nextInt(1) * 2) - 1;

        int sx = farmlandPos.getX() - dx, ex = farmlandPos.getX() + dx;
        int sy = farmlandPos.getY() - dy, ey = farmlandPos.getY() + dy;
        int sz = farmlandPos.getZ() - dz, ez = farmlandPos.getZ() + dz;

        for (int x = sx; dx * x <= dx * ex; x += dx)
            for (int y = sy; dy * y <= dy * ey; y += dy)
                for (int z = sz; dz * z <= dz * ez; z += dz)
                {
                    BlockPos b = new BlockPos(x, y, z);

                    if (BlockPosHelper.blocksAreEqual(b, farmlandPos))
                        continue;

                    /*BlockPos posBelow = new BlockPos(x, y, z);
                    BlockPos posAbove = new BlockPos(x, y + 1, z);
                    Block blockBelow = w.getBlockState(posBelow).getBlock();
                    Block blockAbove = w.getBlockState(posAbove).getBlock();

                    boolean belowIsFarmable = BlockHelper.getBlockEquals(blockBelow, Arrays.asList(new Block[]{Blocks.farmland, Blocks.dirt, Blocks.grass}));//Block.isEqualTo(blockBelow, Blocks.farmland);
                    boolean aboveIsAcceptable = BlockHelper.getBlockEquals(blockAbove,
                            Arrays.asList(new Block[]{Blocks.air, Blocks.tallgrass, Blocks.double_plant,
                                    Blocks.red_flower, Blocks.yellow_flower, Blocks.brown_mushroom, Blocks.red_mushroom }));

                    if (belowIsFarmable && aboveIsAcceptable)
                        return posAbove;*/

                    if (this.isValidFarmland(w, new BlockPos(x, y, z)))
                        return new BlockPos(x, y, z);
                }

        return null;
    }
}
