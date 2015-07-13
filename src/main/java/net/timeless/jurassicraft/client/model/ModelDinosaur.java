package net.timeless.jurassicraft.client.model;

import java.util.List;

import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.modelbase.MowzieModelRenderer;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.ilexiconn.llibrary.common.json.container.JsonTabulaModel;

public class ModelDinosaur extends ModelJson
{
    public ModelDinosaur(JsonTabulaModel model)
    {
        super(model);
    }

    public ModelDinosaur(JsonTabulaModel model, IModelAnimator animator)
    {
        super(model, animator);
    }

    public List<MowzieModelRenderer> getParts()
    {
        return super.parts;
    }
}
