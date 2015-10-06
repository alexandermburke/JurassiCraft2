package net.timeless.animationapi.client.dto;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.jurassicraft.common.entity.base.EnumGrowthStage;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

/**
 * Deserialize with the {@link DinosaurDeserializer} from {@link Gson}
 *
 * @author WorldSEnder
 */
public class DinosaurRenderDefDTO
{
    public static class DinosaurDeserializer implements JsonDeserializer<DinosaurRenderDefDTO>
    {
        @Override
        public DinosaurRenderDefDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject def = json.getAsJsonObject();
            DinosaurRenderDefDTO built = new DinosaurRenderDefDTO();
            built.version = def.get("version") == null ? 0 : def.get("version").getAsInt();
            built.perStage = new EnumMap<EnumGrowthStage, GrowthRenderDef>(EnumGrowthStage.class);
            for (EnumGrowthStage g : EnumGrowthStage.values)
            {
                JsonElement perhaps = def.get(g.name());
                GrowthRenderDef renderDef = perhaps == null ? new GrowthRenderDef() : context.<GrowthRenderDef> deserialize(perhaps, GrowthRenderDef.class);
                if (renderDef.directory == null || renderDef.directory.isEmpty())
                {
                    renderDef.directory = g.name().toLowerCase(Locale.ROOT);
                }
                built.perStage.put(g, renderDef);
            }
            return built;
        }
    }

    public int version;
    public Map<EnumGrowthStage, GrowthRenderDef> perStage;
}
