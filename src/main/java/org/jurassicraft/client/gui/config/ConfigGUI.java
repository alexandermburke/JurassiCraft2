package org.jurassicraft.client.gui.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import org.jurassicraft.JurassiCraft;
import org.jurassicraft.common.configuration.JCConfigurations;

import java.util.List;

class ConfigGUI extends GuiConfig
{
    public ConfigGUI(GuiScreen parent)
    {
        super(parent, getConfigElements(), JurassiCraft.MODID, false, false, "JurassiCraft Configuration");
    }

    private static List<IConfigElement> getConfigElements()
    {
        return JCConfigurations.getAllConfigurableOptions();
    }
}
