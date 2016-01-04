package org.jurassicraft.common.entity.disease;

public class DiseaseCancer extends Disease
{

    public DiseaseCancer()
    {
        super("Cancer");
        this.setTerminal();
    }

    @Override
    public void affects()
    {

    }
}
