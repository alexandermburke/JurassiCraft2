package org.jurassicraft.common.entity.disease;

abstract class Disease
{

    // name of the disease (Cancer etc...)
    private final String name;
    // Diseases have levels that can range from 1 - 5 (5 obviously being the worst)
    private int level;
    // returns true or false depending on if the disease can kill the entity
    private boolean isTerminal;

    Disease()
    {
        this.name = "Cancer";
    }

    public Disease(String name, int level)
    {
        this.name = name;
        this.level = level;
    }

    public String getName()
    {
        return name;
    }

    private int getLevel()
    {
        return level;
    }

    public int getWorse(int level)
    {
        return this.level + level;
    }

    public boolean isTerminal()
    {
        return isTerminal;
    }

    public void setTerminal(boolean terminal)
    {
        isTerminal = terminal;
    }

    void setTerminal()
    {
        if (getLevel() == 5)
        {
            return;
        }
        else
        {
            return;
        }
    }

    public abstract void affects();
}
