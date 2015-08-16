package net.timeless.jurassicraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.timeless.jurassicraft.common.container.ContainerEmbryonicMachine;

@SideOnly(Side.CLIENT)
public class GuiEmbryonicMachine extends GuiContainer
{
    private static final ResourceLocation texture = new ResourceLocation("jurassicraft:textures/gui/embryonic_machine.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private IInventory embryonicMachine;

    public GuiEmbryonicMachine(InventoryPlayer playerInv, IInventory embryonicMachine)
    {
        super(new ContainerEmbryonicMachine(playerInv, (TileEntity) embryonicMachine));
        this.playerInventory = playerInv;
        this.embryonicMachine = embryonicMachine;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.embryonicMachine.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, 120 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Args : renderPartialTicks, mouseX, mouseY
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(texture); 
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int progress = this.getProgress(17);        
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, progress + 1, 16);
        
        this.drawTexturedModalRect(k + 57, l + 31 + 13 - progress, 179, 45 - progress, 1 , progress);
        
        this.drawTexturedModalRect(k + 57, l + 31 + 6 - progress, 179, 38 - progress, 1 , progress);
                

    }

    private int getProgress(int scale)
    {
        int j = this.embryonicMachine.getField(0);
        int k = this.embryonicMachine.getField(1);
        return k != 0 && j != 0 ? j * scale / k : 0;
    }
}