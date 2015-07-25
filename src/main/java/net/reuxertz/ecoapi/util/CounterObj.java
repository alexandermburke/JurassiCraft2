package net.reuxertz.ecoapi.util;

import java.util.Random;

public class CounterObj
{
    protected Random random = new Random();

    //Timing Fields
    protected long lastMSTime = 0, lastDifTime = 0;
    protected int timeCounter, clickCounter, totalClickCounter;
    protected double lastOccurredCycles, lastOccurredClicksPerCycle, lastOccurredClicks;
    protected int msWaitInterval, msRandomVariationRange;

    //Constructors
    public CounterObj(long curTime, int MSWaitInterval, int MSRandomVariationRange)
    {
        msWaitInterval = MSWaitInterval;
        msRandomVariationRange = MSRandomVariationRange;
        lastMSTime = curTime;
    }

    //Functions
    public boolean updateMSTime(long time)
    {
        long dif = time - lastMSTime;
        lastMSTime = time;

        int d;
        if (dif > Integer.MAX_VALUE)
            d = Integer.MAX_VALUE;
        else
            d = (int) dif;

        return addMS(d);
    }

    public boolean addMS(int ms)
    {
        //Add Time
        lastDifTime = ms;
        timeCounter += ms;

        //If not enough time, return
        if (timeCounter < msWaitInterval)
            return false;

        //Subtract time and random variation
        lastOccurredCycles = timeCounter / (msWaitInterval * 1.0);
        lastOccurredClicksPerCycle = clickCounter / lastOccurredCycles;
        lastOccurredClicks = clickCounter;
        totalClickCounter += clickCounter;

        //Reset
        clickCounter = 0;
        timeCounter = random.nextInt((msRandomVariationRange * 2)) - msRandomVariationRange;

        return true;
    }

    public void addClicks(int clicks)
    {
        //Add Time
        clickCounter += clicks;
    }
}
