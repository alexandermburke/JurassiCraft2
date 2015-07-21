package net.timeless.jurassicraft.common.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import net.minecraft.util.StatCollector;

public class AdvLang
{
    private String langPath;
    private Map<String, String> properties = new HashMap<>();
    
    public AdvLang(String langPath)
    {
        this.langPath = langPath;
    }
    
    public AdvLang withProperty(String propertyName, String value)
    {
        properties.put(propertyName, StatCollector.translateToLocal(value));
        
        return this;
    }
    
    @Override
    public String toString()
    {
        String translation = StatCollector.translateToLocal(langPath);
        
        for(Entry<String, String> property : properties.entrySet())
        {
            translation = translation.replaceAll(Pattern.quote("{" + property.getKey() + "}"), property.getValue());
        }
        
        return translation;
    }
}
