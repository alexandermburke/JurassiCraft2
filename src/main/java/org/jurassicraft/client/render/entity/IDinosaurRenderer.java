package org.jurassicraft.client.render.entity;

import net.minecraft.client.model.ModelBase;
import org.jurassicraft.client.render.renderdef.RenderDinosaurDefinition;

public interface IDinosaurRenderer
{
    void setModel(ModelBase model);

    RenderDinosaurDefinition getRenderDef();
}
