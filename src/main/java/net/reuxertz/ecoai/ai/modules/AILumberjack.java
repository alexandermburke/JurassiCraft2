package net.reuxertz.ecoai.ai.modules;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoai.ai.AINavigate;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoapi.entity.Target;
import net.reuxertz.ecoapi.util.BlockHelper;
import net.reuxertz.ecoapi.util.BlockPosHelper;
import net.reuxertz.ecoapi.util.EntityHelper;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

public class AILumberjack extends AIFarming
{
    public static final List<Block> leafArray = Arrays.asList(new Block[]{Blocks.leaves, Blocks.leaves2});
    public static final List<Block> logArray = Arrays.asList(new Block[]{Blocks.log, Blocks.log2});
    public static final List<Block> endDirtArray = Arrays.asList(new Block[]{Blocks.dirt, Blocks.grass, Blocks.air});
    public static final List<Block> totalArray = new ArrayList<Block>();

    static
    {
        totalArray.addAll(leafArray);
        totalArray.addAll(logArray);
    }

    public static BlockPos getSequentialBlockPos(World w, BlockPos startPos, List<Block> startBlocks, List<Block> midBlocks, List<Block> endBlocks)
    {
        IBlockState startBlock = w.getBlockState(startPos);

        if (!BlockHelper.getBlockEquals(startBlock.getBlock(), startBlocks))
            return null;

        List<BlockPos> oldPos = new ArrayList<BlockPos>();
        BlockPos curBlockPos = startPos;
        while (true)
        {
            boolean hasMidBlock = false;
            BlockPos endBlockPos = null;
            BlockPos newBlockPos = null;
            for (int dy = -1; dy <= 0; dy++)
            {
                for (int dx = -1; dx <= 1; dx++)
                {
                    for (int dz = -1; dz <= 1; dz++)
                    {
                        if (dx == 0 && dy == 0 && dz == 0)
                            continue;

                        newBlockPos = curBlockPos.add(dx, dy, dz);
                        IBlockState newBlock = w.getBlockState(newBlockPos);

                        if (BlockPosHelper.blocksAreEqual(newBlockPos, oldPos))
                            continue;

                        if (BlockHelper.getBlockEquals(newBlock.getBlock(), midBlocks))
                            hasMidBlock = true;

                        if (BlockHelper.getBlockEquals(newBlock.getBlock(), endBlocks))
                            endBlockPos = newBlockPos;

                        if (hasMidBlock)
                            break;
                    }

                    if (hasMidBlock)
                        break;
                }

                if (hasMidBlock)
                    break;
            }

            if (hasMidBlock)
            {
                curBlockPos = newBlockPos;
                oldPos.add(curBlockPos);
                continue;
            }

            if (endBlocks != null)
            {
                if (!hasMidBlock && endBlockPos != null)
                {
                    return endBlockPos;
                }
            }
            else
            {
                return newBlockPos;
            }

            return null;
        }
    }

    protected List<BlockPos> workPos;

    public void nextWorkPos(BlockPos curPos, List<BlockPos> positions)
    {
        int dx = (AICore.RND.nextInt(1) * 2) - 1;
        int dy = (AICore.RND.nextInt(1) * 2) - 1;
        int dz = (AICore.RND.nextInt(1) * 2) - 1;

        int sx = curPos.getX() - dx, ex = curPos.getX() + dx;
        int sy = curPos.getY() - dy, ey = curPos.getY() + dy;
        int sz = curPos.getZ() - dz, ez = curPos.getZ() + dz;

        for (int x = sx; dx * x <= dx * ex; x += dx)
            for (int y = sy; dy * y <= dy * ey; y += dy)
                for (int z = sz; dz * z <= dz * ez; z += dz)
                {
                    BlockPos b = new BlockPos(x, y, z);

                    if (BlockPosHelper.blocksAreEqual(b, curPos))
                        continue;

                    if (BlockHelper.getBlockEquals(this.getAgent().worldObj.getBlockState(new BlockPos(x, y, z)).getBlock(), totalArray))
                    {
                        boolean add = true;
                        for (int i = 0; i < positions.size(); i++)
                            if (BlockPosHelper.blocksAreEqual(positions.get(i), b))
                            {
                                add = false;
                                break;
                            }

                        if (add)
                            positions.add(b);
                    }

                }
    }

    public AILumberjack(IDemand demand, AICore entity, AINavigate navigate, Target t)
    {
        super(demand, entity, navigate, t);

        this.collectDistY = 30;
        this.collectDistXZ = 10;
        this.minWorkDistance = 4;
        this.blockSearchPasses = 50;
    }

    public Target nextNavigatePosition()
    {
        Target t = null;
        this.workPos = new ArrayList<BlockPos>();

        List<Entity> distEnts = EntityHelper.getEntitiesWithinDistance(this.getAgent(), this.collectDistXZ, this.collectDistY);
        for (Entity distEnt : distEnts)
        {
            //Is Entity Item
            if ((EntityItem.class.isInstance(distEnt) && (this.demand.isItemDemanded(((EntityItem) distEnt).getEntityItem()) != null) ||
                    this.demand.isEntityDemanded(distEnt) != null))
                t = new Target(this.getAgent().worldObj, distEnt);
            //seek = true;

            //return t;
        }

        for (int i = 0; i < this.blockSearchPasses; i++)
        {
            BlockPos curPos = BlockPosHelper.getRandomPos(this.getAgent().getPosition(), this.collectDistXZ, this.collectDistY, BlockPosHelper.BlockPosConditions.IncludeYPositiveOnly);

            BlockPos bp = AILumberjack.getSequentialBlockPos(this.getAgent().worldObj, curPos, leafArray, totalArray, endDirtArray);
            IBlockState block = this.getAgent().worldObj.getBlockState(bp);

            if (BlockHelper.getBlockEquals(block.getBlock(), totalArray))
                continue;

            ItemStack s = null;
            if (BlockHelper.getBlockEquals(block.getBlock(), endDirtArray))
                s = new ItemStack(this.getAgent().worldObj.getBlockState(bp).getBlock().getItem(this.getAgent().worldObj, bp));

            if (s == null)
                continue;

            if (this.demand.isItemDemanded(s) != null)
                t = new Target(this.agentAI.entity().worldObj, bp);

            if (t != null)
                break;

        }


        return t;
    }

    public Target nextNavigatePosition2()
    {
        Target t = null;
        this.workPos = new ArrayList<BlockPos>();

        List<Entity> distEnts = EntityHelper.getEntitiesWithinDistance(this.getAgent(), this.collectDistXZ, this.collectDistY);
        for (Entity distEnt : distEnts)
        {
            //Is Entity Item
            if ((EntityItem.class.isInstance(distEnt) && (this.demand.isItemDemanded(((EntityItem) distEnt).getEntityItem()) != null) ||
                    this.demand.isEntityDemanded(distEnt) != null))
                t = new Target(this.getAgent().worldObj, distEnt);
            //seek = true;

            //return t;
        }

        for (int i = 0; i < this.blockSearchPasses; i++)
        {
            BlockPos ep = this.getAgent().getPosition();
            BlockPos curPos = BlockPosHelper.getRandomPos(ep, this.collectDistXZ, this.collectDistY, BlockPosHelper.BlockPosConditions.IncludeYPositiveOnly);
            IBlockState curBlock = this.agentAI.entity().worldObj.getBlockState(curPos);

            if (curBlock == null || Block.isEqualTo(curBlock.getBlock(), Blocks.air))
                continue;

            List<Block> nextSearchBlocks = null;

            if (BlockHelper.getBlockEquals(curBlock.getBlock(), leafArray))
                nextSearchBlocks = totalArray;

            if (BlockHelper.getBlockEquals(curBlock.getBlock(), logArray))
                nextSearchBlocks = logArray;

            if (nextSearchBlocks == null)
                continue;

            boolean hasAir = false;
            BlockPos nextPos = null, lastPos = null, airPos;
            IBlockState nextBlock = null, lastBlock = null;

            boolean brk = false, endDirt = false;
            while (true)
            {
                brk = false;
                hasAir = false;
                for (int dy = -1; dy <= 0; dy++)
                {
                    for (int dx = -1; dx <= 1; dx++)
                    {
                        for (int dz = -1; dz <= 1; dz++)
                        {
                            nextPos = new BlockPos(curPos.getX() + dx, curPos.getY() + dy, curPos.getZ() + dz);
                            nextBlock = this.getAgent().worldObj.getBlockState(nextPos);

                            boolean isDirt = BlockHelper.getBlockEquals(nextBlock.getBlock(), endDirtArray);
                            hasAir = Block.isEqualTo(nextBlock.getBlock(), Blocks.air);
                            boolean isNextBlocks = BlockHelper.getBlockEquals(nextBlock.getBlock(), nextSearchBlocks);
                            if (!(isNextBlocks || isDirt))
                                continue;

                            lastPos = curPos;
                            lastBlock = curBlock;
                            curPos = nextPos;
                            curBlock = nextBlock;
                            if (curPos.getY() < lastPos.getY())
                                brk = true;

                            if (isDirt)// && !hasAir)
                                endDirt = true;

                            //if (hasAir)
                            //{
                            ///   airPos = nextPos;
                            //}

                            if (brk)
                                break;
                        }
                        if (brk)
                            break;
                    }
                    if (brk)
                        break;
                }
                if (endDirt)
                    break;

                if (!brk)
                {
                    /*if (hasAir)
                    {
                        lastPos = curPos;
                        lastBlock = curBlock;
                        curPos = nextPos;
                        curBlock = nextBlock;
                    }
                    else*/
                    break;
                }
            }

            if (brk)
            {
                ItemStack s = null;
                if (BlockHelper.getBlockEquals(nextBlock.getBlock(), endDirtArray))
                    s = new ItemStack(this.getAgent().worldObj.getBlockState(lastPos).getBlock().getItem(this.getAgent().worldObj, lastPos));

                if (s == null)
                    continue;

                if (this.demand.isItemDemanded(s) != null)
                    t = new Target(this.agentAI.entity().worldObj, lastPos);

                //return t;
            }
        }

        return t;
    }

    public boolean doWorkContinue()
    {
        if (this.workTarget.workState == 0)
        {
            boolean r = super.doWorkContinue();
            return true;
        }

        if (this.workTarget.workState == 1)
        {
            boolean r = super.doWorkContinue();

            //if (this.workTarget.workState == 2)
            //{
            this.nextWorkPos(this.workTarget.pos, this.workPos);

            if (this.workPos.size() > 0)
            {
                this.workTarget.pos = this.workPos.get(0);
                this.workPos.remove(0);
                this.workTarget.workState = 0;
                return true;
            }
            //}
        }

        return false;
    }
}
