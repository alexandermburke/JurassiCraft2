package org.jurassicraft.common.disease;

public abstract class Disease
{

    // name of the disease (Cancer etc...)
    private String name;
    // Diseases have levels that can range from 1 - 5 (5 obviously being the worst)
    private int level;
    // returns true or false depending on if the disease can kill the entity
    private boolean isTerminal;

    public Disease(String name)
    {
        this.name = name;
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

    public int getLevel()
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

    public boolean setTerminal()
    {
        if (getLevel() == 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public abstract void affects();
}
