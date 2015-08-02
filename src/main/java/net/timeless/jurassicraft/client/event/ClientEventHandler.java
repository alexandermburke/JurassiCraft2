package net.timeless.jurassicraft.client.event;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.timeless.jurassicraft.client.render.entity.IDinosaurRenderer;
import net.timeless.jurassicraft.client.render.entity.RenderIndominusRex;
import net.timeless.jurassicraft.common.entity.base.EntityDinosaur;

public class ClientEventHandler
{
    @SubscribeEvent
    public void preRender(RenderLivingEvent.Pre event)
    {
        if(event.entity instanceof EntityDinosaur && event.renderer instanceof IDinosaurRenderer)
        {
            IDinosaurRenderer dinoRenderer = (IDinosaurRenderer) event.renderer;
            EntityDinosaur dinosaur = (EntityDinosaur) event.entity;

            dinoRenderer.setModel(dinoRenderer.getRenderDef().getModel(dinosaur.getGeneticVariant()));
        }
    }

    @SubscribeEvent
    public void postRenderer(RenderLivingEvent.Post event)
    {
        if(event.entity instanceof EntityDinosaur && event.renderer instanceof IDinosaurRenderer && !(event.renderer instanceof RenderIndominusRex))
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
    }
}
