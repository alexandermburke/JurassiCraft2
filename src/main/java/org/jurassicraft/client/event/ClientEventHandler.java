package org.jurassicraft.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoTab;
import org.jurassicraft.client.gui.app.GuiApp;
import org.jurassicraft.client.render.entity.IDinosaurRenderer;
import org.jurassicraft.client.render.entity.RenderIndominusRex;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class ClientEventHandler
{
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event)
    {
        JurassiCraft.timer++;

        if (mc.currentScreen instanceof GuiPaleoTab)
        {
            GuiPaleoTab tab = (GuiPaleoTab) mc.currentScreen;

            GuiApp focus = tab.focus;

            if (focus != null)
            {
                focus.update();
            }
        }
    }

    @SubscribeEvent
    public void preRender(RenderLivingEvent.Pre event)
    {
        if (event.entity instanceof EntityDinosaur && event.renderer instanceof IDinosaurRenderer)
        {
            IDinosaurRenderer dinoRenderer = (IDinosaurRenderer) event.renderer;
            EntityDinosaur dinosaur = (EntityDinosaur) event.entity;

            dinoRenderer.setModel(dinoRenderer.getRenderDef().getModel(dinosaur.getGeneticVariant(), dinosaur.getGrowthStage()));
        }
    }

    @SubscribeEvent
    public void postRenderer(RenderLivingEvent.Post event)
    {
        if (event.entity instanceof EntityDinosaur && event.renderer instanceof IDinosaurRenderer && !(event.renderer instanceof RenderIndominusRex))
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
    }
}
