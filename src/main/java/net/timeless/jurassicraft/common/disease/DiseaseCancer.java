package net.timeless.jurassicraft.common.disease;

public class DiseaseCancer extends Disease {

    public DiseaseCancer()
    {
        super("Cancer");
        this.setTerminal();
    }

    @Override
    public void affects() {

    }
}
