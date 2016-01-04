package org.jurassicraft.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoPad;
import org.jurassicraft.client.gui.app.GuiApp;
import org.jurassicraft.client.render.entity.IDinosaurRenderer;
import org.jurassicraft.client.render.entity.RenderIndominus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class ClientEventHandler
{
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void tick(TickEvent.ClientTickEvent event)
    {
        JurassiCraft.timerTicks++;

        if (mc.currentScreen instanceof GuiPaleoPad)
        {
            GuiPaleoPad tab = (GuiPaleoPad) mc.currentScreen;

            GuiApp focus = tab.focus;

            if (focus != null)
            {
                focus.update();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void preRender(RenderLivingEvent.Pre event)
    {
        if (event.entity instanceof EntityDinosaur && event.renderer instanceof IDinosaurRenderer)
        {
            IDinosaurRenderer dinoRenderer = (IDinosaurRenderer) event.renderer;
            EntityDinosaur entityDinosaur = (EntityDinosaur) event.entity;

            dinoRenderer.setModel(dinoRenderer.getRenderDef().getModel(entityDinosaur.getGrowthStage()));
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void postRender(RenderLivingEvent.Post event)
    {
        if (event.entity instanceof EntityDinosaur && event.renderer instanceof IDinosaurRenderer && !(event.renderer instanceof RenderIndominus))
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
    }
}
