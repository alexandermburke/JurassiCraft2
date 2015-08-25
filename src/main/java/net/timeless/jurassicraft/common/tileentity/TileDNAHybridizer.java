package net.timeless.jurassicraft.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.common.container.ContainerFossilGrinder;
import net.timeless.jurassicraft.common.dinosaur.Dinosaur;
import net.timeless.jurassicraft.common.dinosaur.IHybrid;
import net.timeless.jurassicraft.common.entity.base.JCEntityRegistry;
import net.timeless.jurassicraft.common.genetics.DNA;
import net.timeless.jurassicraft.common.genetics.GeneticsContainer;
import net.timeless.jurassicraft.common.item.ItemStorageDisc;
import net.timeless.jurassicraft.common.item.JCItemRegistry;

public class TileDNAHybridizer extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    private static final int[] slotsTop = new int[]{0, 1, 2, 3, 4, 5}; //input
    private static final int[] slotsBottom = new int[]{6}; //output
    private static final int[] slotsSides = new int[]{};

    /**
     * The ItemStacks that hold the items currently being used in the fossil grinder
     */
    private ItemStack[] slots = new ItemStack[7];

    private int hybridizeTime;
    private int totalHybridizeTime;

    private String customName;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.slots.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.slots[index];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.slots[index] != null)
        {
            ItemStack itemstack;

            if (this.slots[index].stackSize <= count)
            {
                itemstack = this.slots[index];
                this.slots[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.slots[index].splitStack(count);

                if (this.slots[index].stackSize == 0)
                {
                    this.slots[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.slots[index] != null)
        {
            ItemStack itemstack = this.slots[index];
            this.slots[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.slots[index]) && ItemStack.areItemStackTagsEqual(stack, this.slots[index]);
        this.slots[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.totalHybridizeTime = this.getStackHybridizeTime(stack);
            this.hybridizeTime = 0;
            worldObj.markBlockForUpdate(pos);
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.dna_hybridizer";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomInventoryName(String customName)
    {
        this.customName = customName;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        NBTTagList itemList = compound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < itemList.tagCount(); ++i)
        {
            NBTTagCompound item = itemList.getCompoundTagAt(i);

            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < this.slots.length)
            {
                this.slots[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        this.hybridizeTime = compound.getShort("HybridizeTime");
        this.totalHybridizeTime = compound.getShort("HybridizeTimeTotal");

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("HybridizeTime", (short) this.hybridizeTime);
        compound.setShort("HybridizeTimeTotal", (short) this.totalHybridizeTime);
        NBTTagList itemList = new NBTTagList();

        for (int slot = 0; slot < this.slots.length; ++slot)
        {
            if (this.slots[slot] != null)
            {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) slot);

                this.slots[slot].writeToNBT(itemTag);
                itemList.appendTag(itemTag);
            }
        }

        compound.setTag("Items", itemList);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isHybridizing()
    {
        return this.hybridizeTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isHybridizing(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update()
    {
        boolean wasHybridizing = this.isHybridizing();
        boolean sync = false;

        if (!this.worldObj.isRemote)
        {
            if (!this.isHybridizing() && (this.slots[0] == null))
            {
                if (!this.isHybridizing() && this.hybridizeTime > 0)
                {
                    this.hybridizeTime = MathHelper.clamp_int(this.hybridizeTime - 2, 0, this.totalHybridizeTime);
                }
            }
            else
            {
                if (this.canHybridize())
                {
                    ++this.hybridizeTime;

                    if (this.hybridizeTime == this.totalHybridizeTime)
                    {
                        this.hybridizeTime = 0;
                        this.totalHybridizeTime = this.getStackHybridizeTime(this.slots[0]);
                        this.hybridize();
                        sync = true;
                    }
                }
                else
                {
                    this.hybridizeTime = 0;
                    sync = true;
                }
            }

            if (wasHybridizing != this.isHybridizing())
            {
                sync = true;
            }
        }
        else
        {
            if (this.canHybridize())
            {
                ++this.hybridizeTime;
            }
        }

        if (sync)
        {
            worldObj.markBlockForUpdate(pos);
        }
    }

    public int getStackHybridizeTime(ItemStack stack)
    {
        return 1000;
    }

    /**
     * Returns true if the fossil grinder can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canHybridize()
    {
        if (this.slots[0] != null && this.slots[0].getItem() instanceof ItemStorageDisc && this.slots[1] != null && this.slots[1].getItem() instanceof ItemStorageDisc)
        {
            return getHybrid() != null;
        }

        return false;
    }

    private Dinosaur getHybrid()
    {
        return getHybrid(slots[0], slots[1], slots[2], slots[3], slots[4], slots[5]);
    }

    private Dinosaur getHybrid(ItemStack baseDino1Disc, ItemStack baseDino2Disc, ItemStack extraDNA1Disc, ItemStack extraDNA2Disc, ItemStack extraDNA3Disc, ItemStack extraDNA4Disc)
    {
        Dinosaur hybrid = null;

        Dinosaur baseGene1 = getDino(baseDino1Disc);
        Dinosaur baseGene2 = getDino(baseDino2Disc);

        Dinosaur extraGene1 = getDino(extraDNA1Disc);
        Dinosaur extraGene2 = getDino(extraDNA2Disc);
        Dinosaur extraGene3 = getDino(extraDNA3Disc);
        Dinosaur extraGene4 = getDino(extraDNA4Disc);

        Dinosaur[] baseGenes = new Dinosaur[]{baseGene1, baseGene2};

        Dinosaur[] extraGenes = new Dinosaur[]{extraGene1, extraGene2, extraGene3, extraGene4};

        for (Dinosaur dino : JCEntityRegistry.getDinosaurs())
        {
            if (dino instanceof IHybrid)
            {
                IHybrid dinoHybrid = (IHybrid) dino;

                int baseCount = 0;

                for (Class combo : dinoHybrid.getBaseGenes())
                {
                    if (combo.isInstance(baseGene1) || combo.isInstance(baseGene2))
                    {
                        baseCount++;
                    }
                }

                int count = 0;
                boolean extra = false;

                for (Dinosaur combo : extraGenes)
                {
                    Class match = null;

                    for (Class clazz : dinoHybrid.getExtraGenes())
                    {
                        if (clazz.isInstance(combo))
                        {
                            match = clazz;
                        }
                    }

                    if (match != null && match.isInstance(combo))
                    {
                        count++;
                    }
                    else if (combo != null)
                    {
                        extra = true;
                    }
                }

                boolean hasBases = baseCount == dinoHybrid.getBaseGenes().length;
                boolean hasExtras = !extra && count == dinoHybrid.getExtraGenes().length;

                if (hasBases && hasExtras)
                {
                    hybrid = dino;

                    break;
                }
            }
        }

        return hybrid;
    }

    private Dinosaur getDino(ItemStack disc)
    {
        if (disc != null)
        {
            DNA data = DNA.readFromNBT(disc.getTagCompound());

            return data.getDNAQuality() == 100 ? JCEntityRegistry.getDinosaurById(data.getContainer().getDinosaur()) : null;
        }
        else
        {
            return null;
        }
    }

    /**
     * Turn one item from the fossil grinder source stack into the appropriate grinded item in the fossil grinder result stack
     */
    public void hybridize()
    {
        if (this.canHybridize())
        {
            Dinosaur hybrid = getHybrid();

            NBTTagCompound nbt = new NBTTagCompound();

            int dinosaurId = JCEntityRegistry.getDinosaurId(hybrid);

            GeneticsContainer container = new GeneticsContainer(slots[0].getTagCompound().getString("Genetics"));
            container.set(GeneticsContainer.DINOSAUR, dinosaurId);

            DNA dna = new DNA(100, container.toString());
            dna.writeToNBT(nbt);

            ItemStack output = new ItemStack(JCItemRegistry.storage_disc, 1, dinosaurId);
            output.setItemDamage(dna.getContainer().getDinosaur());
            output.setTagCompound(nbt);

            if (this.slots[6] == null)
            {
                this.slots[6] = output;
            }
            else if (this.slots[6].getItem() == output.getItem() && ItemStack.areItemStackTagsEqual(this.slots[6], output))
            {
                this.slots[6].stackSize += output.stackSize;
            }

            for (int i = 0; i < 6; i++)
            {
                if (slots[i] != null)
                {
                    slots[i].stackSize--;

                    if (slots[i].stackSize <= 0)
                    {
                        slots[i] = null;
                    }
                }
            }
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == 2 ? false : true;
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket)
            {
                return false;
            }
        }

        return true;
    }

    public String getGuiID()
    {
        return JurassiCraft.modid + ":dna_hybridizer";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerFossilGrinder(playerInventory, this);
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.hybridizeTime;
            case 1:
                return this.totalHybridizeTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.hybridizeTime = value;
                break;
            case 1:
                this.totalHybridizeTime = value;
        }
    }

    public int getFieldCount()
    {
        return 2;
    }

    public void clear()
    {
        for (int i = 0; i < this.slots.length; ++i)
        {
            this.slots[i] = null;
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new S35PacketUpdateTileEntity(this.pos, this.getBlockMetadata(), compound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        NBTTagCompound compound = packet.getNbtCompound();
        this.readFromNBT(compound);
    }
}