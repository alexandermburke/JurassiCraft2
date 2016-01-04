package org.jurassicraft.client.gui.app;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ResourceLocation;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.client.gui.GuiPaleoPad;
import org.jurassicraft.common.paleopad.App;
import org.jurassicraft.common.paleopad.AppDinoPedia;
import org.jurassicraft.common.paleopad.dinopedia.DinoPediaRegistry;

import java.util.List;

public class GuiAppDinoPedia extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.MODID, "textures/gui/paleo_pad/apps/dinopedia.png");

    private final RenderItem render = Minecraft.getMinecraft().getRenderItem();

    public GuiAppDinoPedia(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoPad gui)
    {
        super.renderButtons(mouseX, mouseY);

        AppDinoPedia app = (AppDinoPedia) this.app;

        gui.drawScaledText("Items:", 4, 5, 0xFFFFFF);

        drawItemsAndRecipes(gui, app);
    }

    private void drawItemsAndRecipes(GuiPaleoPad gui, AppDinoPedia app)
    {
        List<ItemStack> items = DinoPediaRegistry.getItems();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        RenderHelper.enableGUIStandardItemLighting();

        int renderX = 0;
        int renderY = 0;

        for (int i = 0; i < items.size(); i++)
        {
            ItemStack stack = items.get(i);

            if (stack != null)
            {
                GlStateManager.pushMatrix();

                if (app.getSelectedItem() == i)
                {
                    gui.drawBoxOutline(renderX + 4, renderY + 14, 18, 18, 1.0F, 0x606060);
                }

                ScaledResolution dimensions = new ScaledResolution(mc);
                GlStateManager.translate(dimensions.getScaledWidth() / 2 - 115, 75, 0);

                render.zLevel = 200.0F;
                render.renderItemAndEffectIntoGUI(stack, renderX + 5, renderY + 6);
                render.zLevel = 0.0F;

                if (app.getSelectedItem() == i)
                {
                    GlStateManager.translate(70, 10, 0);

                    List<IRecipe> recipes = DinoPediaRegistry.getRecipesForItem(stack);

                    gui.drawCenteredScaledText("Recipe:", -120, -12, 1.0F);
                    gui.drawCenteredScaledText(stack.getDisplayName(), -120, -80, 1.0F);

                    render.zLevel = 200.0F;
                    render.renderItemAndEffectIntoGUI(stack, 75, -8);
                    render.zLevel = 0.0F;

                    for (IRecipe recipe : recipes)
                    {
                        if (recipe instanceof ShapedRecipes)
                        {
                            ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;

                            ItemStack[] recipeItems = shapedRecipe.recipeItems;

                            int rx = 0;
                            int ry = 0;

                            for (int r = 0; r < recipeItems.length; r++)
                            {
                                ItemStack item = recipeItems[r];

                                gui.drawBoxOutline(rx * 19 - 150, ry * 19, 18, 18, 1.0F, r % 2 == 0 ? 0x606060 : 0x404040);

                                if (item != null)
                                {
                                    render.zLevel = 200.0F;
                                    render.renderItemAndEffectIntoGUI(item, (rx * 19) + 56, ry * 19 + 67);
                                    render.zLevel = 0.0F;
                                }

                                rx++;

                                if (rx >= 3)
                                {
                                    rx = 0;
                                    ry++;
                                }
                            }

                            break;
                        }
                    }
                }

                renderY += 18;

                if (renderY > 120)
                {
                    renderY = 0;
                    renderX += 19;
                }

                GlStateManager.popMatrix();
            }
        }

        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY)
    {
        ScaledResolution dimensions = new ScaledResolution(mc);
        mouseX -= dimensions.getScaledWidth() / 2 - 115;
        mouseY -= 75;

        AppDinoPedia app = (AppDinoPedia) getApp();
        List<ItemStack> items = DinoPediaRegistry.getItems();

        int renderX = 0;
        int renderY = 0;

        for (int i = 0; i < items.size(); i++)
        {
            ItemStack stack = items.get(i);

            if (stack != null)
            {
                if (mouseX > renderX && mouseX < renderX + 18 && mouseY > renderY && mouseY < renderY + 18)
                {
                    app.setSelectedItem(i);
                    break;
                }

                renderY += 18;

                if (renderY > 120)
                {
                    renderY = 0;
                    renderX += 19;
                }
            }
        }
    }

    @Override
    public void actionPerformed()
    {

    }

    @Override
    public void init()
    {
    }

    @Override
    public ResourceLocation getTexture()
    {
        return texture;
    }
}
