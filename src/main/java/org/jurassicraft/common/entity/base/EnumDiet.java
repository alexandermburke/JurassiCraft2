package org.jurassicraft.common.entity.base;

public enum EnumDiet
{
    HERBIVORE(false, true, false), CARNIVORE(true, false, true), OMNIVORE(true, true, true), PISCIVORE(false, false, true);

    private boolean eatsMeat;
    private boolean eatsPlants;
    private boolean eatsFish;

    EnumDiet(boolean eatsMeat, boolean eatsPlants, boolean eatsFish)
    {
        this.eatsMeat = eatsMeat;
        this.eatsPlants = eatsPlants;
        this.eatsFish = eatsFish;
    }

    public boolean doesEatMeat()
    {
        return eatsMeat;
    }

    public boolean doesEatPlants()
    {
        return eatsPlants;
    }

    public boolean doesEatFish()
    {
        return eatsFish;
    }
}
