package org.jurassicraft.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoTab;
import org.jurassicraft.client.gui.app.GuiApp;
import org.jurassicraft.client.render.entity.IDinosaurRenderer;
import org.jurassicraft.client.render.entity.RenderIndominus;
import org.jurassicraft.common.entity.base.EntityDinosaur;

public class ClientEventHandler
{
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void event(PlaySoundAtEntityEvent event)
    {
//        if (event.entity instanceof EntityDinosaur)
//        {
//            EntityDinosaur theEntityDinosaur = (EntityDinosaur)event.entity;
//            
//            if (event.name.contains("roaring"))
//            {
//                JurassiCraft.instance.getNanoTimeInterval();
//                JurassiCraft.instance.getLogger().info("Playing sound "+event.name+" at entity "+event.entity.getEntityId());
//                theEntityDinosaur.setAnimID(AnimID.ROARING);
//            }
//        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event)
    {
        JurassiCraft.timerTicks++;

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
            EntityDinosaur entityDinosaur = (EntityDinosaur) event.entity;

            dinoRenderer.setModel(dinoRenderer.getRenderDef().getModel(entityDinosaur.getGrowthStage()));
        }
    }

    @SubscribeEvent
    public void postRenderer(RenderLivingEvent.Post event)
    {
        if (event.entity instanceof EntityDinosaur && event.renderer instanceof IDinosaurRenderer && !(event.renderer instanceof RenderIndominus))
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
    }
}
