package net.timeless.jurassicraft.client.model.animation;

import net.timeless.animationapi.client.AnimatorJabelar;
import net.timeless.jurassicraft.client.model.ModelDinosaur;
import net.timeless.unilib.client.model.json.IModelAnimator;
import net.timeless.unilib.client.model.json.JsonTabulaModel;

/**
 * @author jabelar
 *
 */
public class ModelDinosaurJabelar extends ModelDinosaur
{

    /**
     * @param parModel
     */
    public ModelDinosaurJabelar(JsonTabulaModel parModel)
    {
        super(parModel);
        animator = new AnimatorJabelar(this);
    }

    public ModelDinosaurJabelar(JsonTabulaModel parModel, IModelAnimator parAnimation)
    {
        super(parModel, parAnimation);
        animator = new AnimatorJabelar(this);
    }

}
