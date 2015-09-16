package net.reuxertz.ecoai.ai.modules;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.reuxertz.ecoai.ai.AICore;
import net.reuxertz.ecoai.ai.AINavigate;
import net.reuxertz.ecoai.ai.Target;
import net.reuxertz.ecoai.demand.IDemand;
import net.reuxertz.ecoapi.util.BlockHelper;
import net.reuxertz.ecoapi.util.BlockPosHelper;
import net.reuxertz.ecoapi.util.EntityHelper;
import net.reuxertz.ecocraft.common.EcoCraft;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;

public class AIQuarry extends AIFarming
{
    public static List<Block> ignoreBlocks = Arrays.asList(new Block[] { Blocks.air,
            Blocks.deadbush, Blocks.double_plant, Blocks.tallgrass, Blocks.red_flower, Blocks.yellow_flower});

    protected List<BlockPos> workPos = new ArrayList<BlockPos>();

    public void nextWorkPos(BlockPos curPos, List<BlockPos> positions)
    {
        int dx = (EcoCraft.RND.nextInt(1) * 2) - 1;
        int dy = (EcoCraft.RND.nextInt(1) * 2) - 1;
        int dz = (EcoCraft.RND.nextInt(1) * 2) - 1;

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

                    if (!this.isPositionValid(this.getAgent().worldObj, b))
                        continue;

                    //if (BlockHelper.getBlockEquals(this.getAgent().worldObj.getBlockState(new BlockPos(x, y, z)).getFillBlock(), totalArray))
                    {
                        boolean add = true;
                        for (int i = 0; i < positions.size(); i++)
                        {
                            if (BlockPosHelper.blocksAreEqual(positions.get(i), b))
                            {
                                add = false;
                                break;
                            }
                        }

                        if  (add)
                            positions.add(b);
                    }

                }
    }
    public boolean isPositionValid(World w, BlockPos curPos)
    {
        IBlockState curBlock = w.getBlockState(curPos);

        if (BlockFalling.class.isInstance(curBlock.getBlock()))
            return true;

        if (!BlockHelper.getBlockEquals(w.getBlockState(curPos.add(0, 1, 0)).getBlock(), ignoreBlocks))
            return false;

        int count = 0;
        for (int x = curPos.getX() - 1; x <= curPos.getX() + 1; x++)
            for (int z = curPos.getZ() - 1; z <= curPos.getZ() + 1; z++)
                if (BlockHelper.getBlockEquals(w.getBlockState(curPos).getBlock(), ignoreBlocks))
                    count++;

        return count >= 3;

    }

    public AIQuarry(IDemand demand, AICore entity, AINavigate navigate, Target t)
    {
        super(demand, entity, navigate, t);

        this.collectDistY = 5;
        this.collectDistXZ = 5;
        this.blockSearchPasses = 10;
    }

    public Target nextNavigatePosition()
    {
        Target t = null;

        List<Entity> distEnts = EntityHelper.getEntitiesWithinDistance(this.getAgent(), this.collectDistXZ, this.collectDistY);
        for (Entity distEnt : distEnts)
        {
            //Is Entity Item
            if (EntityItem.class.isInstance(distEnt) && ((this.demand.isItemDemanded(((EntityItem) distEnt).getEntityItem()) != null)))// ||
                //this.demand.isEntityDemanded(distEnt) != null))
                t = new Target(this.getAgent().worldObj, distEnt);
            //seek = true;

            //return t;
        }

        for (int i = 0; i < this.blockSearchPasses; i++)
        {
            BlockPos ep = this.getAgent().getPosition();
            BlockPos p = BlockPosHelper.getRandomPos(ep, this.collectDistXZ, this.collectDistY);
            IBlockState b = this.agentAI.entity().worldObj.getBlockState(p);

            if (b == null || Block.isEqualTo(b.getBlock(), Blocks.air))
                continue;

            if (!this.isPositionValid(this.getAgent().worldObj, p))
                continue;


            ItemStack s = new ItemStack(Item.getItemFromBlock(b.getBlock()));

            if (s == null)
                continue;

            if (this.demand.isItemDemanded(s) != null)
                t =  new Target(this.agentAI.entity().worldObj, p);

            //return t;
        }

        if (t != null)
            return t;

        return null;
    }
    public boolean doWorkContinue()
    {
        if (this.getAgent().worldObj.isRemote)
            return false;

        boolean r = super.doWorkContinue();

        if (this.workTarget.workState == 1)
        {
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
