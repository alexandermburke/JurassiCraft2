package net.timeless.jurassicraft.client.gui.app;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.ResourceLocation;
import net.timeless.jurassicraft.JurassiCraft;
import net.timeless.jurassicraft.client.gui.GuiPaleoTab;
import net.timeless.jurassicraft.common.paleopad.App;
import net.timeless.jurassicraft.common.paleopad.AppDinoPedia;

public class GuiAppDinoPedia extends GuiApp
{
    private static final ResourceLocation texture = new ResourceLocation(JurassiCraft.modid, "textures/gui/paleo_pad/apps/dinopedia.png");

    private final RenderItem render = Minecraft.getMinecraft().getRenderItem();

    private boolean intro;

    private int scroll;

    public GuiAppDinoPedia(App app)
    {
        super(app);
    }

    @Override
    public void render(int mouseX, int mouseY, GuiPaleoTab gui)
    {
        super.renderButtons(mouseX, mouseY, gui);

        AppDinoPedia app = (AppDinoPedia) this.app;

        if (intro)
        {
            gui.drawScaledText("Hello " + mc.thePlayer.getDisplayName().getFormattedText() + "! Welcome to " + app.getName() + "!", 4, 10, 1.0F, 0xFFFFFF);
            mc.getTextureManager().bindTexture(texture);
            gui.drawScaledTexturedModalRect(1, 20, 0, 0, 32, 32, 32, 32, 1.0F);
            gui.drawScaledText("Using " + app.getName() + " you can find all the information", 34, 29, 0.7F, 0xFFFFFF);
            gui.drawScaledText("you need to start creating living dinosaurs!", 34, 37, 0.7F, 0xFFFFFF);
        }
        else
        {
            gui.drawScaledText("Items:", 4, 5, 1.0F, 0xFFFFFF);

            drawItemsAndRecipes(gui, app);
        }
    }

    private void drawItemsAndRecipes(GuiPaleoTab gui, AppDinoPedia app)
    {
//        List<ItemStack> items = DinoPediaRegistry.getItems();
//        RenderHelper.disableStandardItemLighting();
//        GlStateManager.disableLighting();
//
//        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//
//        RenderHelper.enableGUIStandardItemLighting();
//
//        for (int i = 0; i < items.size(); i++)
//        {
//            ItemStack stack = items.get(i);
//
//            if(stack != null && i >= scroll && i < 7 + scroll)
//            {
//                GlStateManager.pushMatrix();
//
//                int renderY = (i - scroll) * 18 + 10;
//
//                if(app.getSelectedItem() == i)
//                {
//                    gui.drawBoxOutline(4, renderY + 8, 18, 18, 1, 1.0F, 0x606060);
//                }
//
//                ScaledResolution dimensions = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
//                GlStateManager.translate(dimensions.getScaledWidth() / 2 - 115, 75, 0);
//
//                render.zLevel = 200.0F;
//                render.renderItemAndEffectIntoGUI(stack, 5, renderY);
//                render.zLevel = 0.0F;
//
//                if(app.getSelectedItem() == i)
//                {
//                    GlStateManager.translate(70, 10, 0);
//
//                    List<IRecipe> recipes = DinoPediaRegistry.getRecipesForItem(stack);
//
//                    for (IRecipe recipe : recipes)
//                    {
//                        if (recipe instanceof ShapedRecipes)
//                        {
//                            ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
//
//                            ItemStack[] recipeItems = shapedRecipe.recipeItems;
//
//                            int rx = 0;
//                            int ry = 0;
//
//                            for (int r = 0; r < recipeItems.length; r++)
//                            {
//                                ItemStack item = recipeItems[r];
//
//                                gui.drawBoxOutline(rx * 19 - 206, ry * 19 - 66, 18, 18, 1, 1.0F, 0x606060);
//
//                                if (item != null)
//                                {
//                                    render.zLevel = 200.0F;
//                                    render.renderItemAndEffectIntoGUI(item, rx * 19, ry * 19);
//                                    render.zLevel = 0.0F;
//                                }
//
//                                rx++;
//
//                                if (rx >= 3)
//                                {
//                                    rx = 0;
//                                    ry++;
//                                }
//                            }
//
//                            break;
//                        }
//                    }
//                }
//
//                GlStateManager.popMatrix();
//            }
//        }
//
//        GlStateManager.enableLighting();
//        GlStateManager.enableDepth();
    }


    @Override
    public void mouseClicked(int mouseX, int mouseY, GuiPaleoTab gui)
    {
//        ScaledResolution dimensions = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
//        mouseX -= dimensions.getScaledWidth() / 2 - 115;
//        mouseY -= 75;
//
//        if (intro)
//        {
//
//        }
//        else
//        {
//            AppDinoPedia app = (AppDinoPedia) getApp();
//
//            List<ItemStack> items = DinoPediaRegistry.getItems();
//
//            for (int i = 0; i < items.size(); i++)
//            {
//                ItemStack stack = items.get(i);
//
//                if (stack != null && i >= scroll && i < 7 + scroll)
//                {
//                    int y = (i - scroll) * 18 + 10;
//
//                    if(mouseX > 0 && mouseX < 22 && mouseY > y && mouseY < y + 18)
//                    {
//                        app.setSelectedItem(i);
//
//                        break;
//                    }
//                }
//            }
//        }
    }

    @Override
    public void actionPerformed(GuiButton button)
    {

    }

    @Override
    public void init()
    {
        intro = !app.hasBeenPreviouslyOpened();
    }

    @Override
    public ResourceLocation getTexture(GuiPaleoTab gui)
    {
        return texture;
    }
}
