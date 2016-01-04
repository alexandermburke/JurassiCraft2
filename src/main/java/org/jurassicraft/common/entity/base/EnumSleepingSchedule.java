package org.jurassicraft.common.entity.base;

import java.util.Random;

public enum EnumSleepingSchedule
{
    DIURNAL(fromTimeToTicks(6, 0), fromTimeToTicks(22, 0)),
    NOCTURNAL(fromTimeToTicks(18, 0), fromTimeToTicks(6, 0)),
    CREPUSCULAR(fromTimeToTicks(12, 30), fromTimeToTicks(4, 30));

    private final int wakeUpTime;
    private final int sleepTime;

    EnumSleepingSchedule(int wakeUpTime, int sleepTime)
    {
        this.wakeUpTime = wakeUpTime;
        this.sleepTime = sleepTime;
    }

    private static int fromTimeToTicks(int hour, int minute)
    {
        int ticksPerMinute = 1000 / 60;
        return ((hour - 6) * 1000) + (minute * ticksPerMinute);
    }

    public int getWakeUpTime()
    {
        return wakeUpTime;
    }

    public int getSleepTime()
    {
        return sleepTime;
    }

    private boolean sleepNextDay()
    {
        return wakeUpTime > sleepTime;
    }

    private int getBreakfastTime()
    {
        return 1000;
    }

    private int getLunchTime()
    {
        return (getDinnerTime() - getBreakfastTime()) / 2;
    }

    private int getDinnerTime()
    {
        return getAwakeTime() - 1000;
    }

    public boolean isWithinEatingTime(int dinosaurTime, Random rand)
    {
        dinosaurTime += (rand.nextInt(320) - 160);

        return isWithinEatTime(dinosaurTime, getBreakfastTime()) || isWithinEatTime(dinosaurTime, getLunchTime()) || isWithinEatTime(dinosaurTime, getDinnerTime());
    }

    public int getAwakeTime()
    {
        int newSleepTime = sleepTime;

        if (sleepNextDay())
        {
            newSleepTime += 24000;
        }

        return newSleepTime - wakeUpTime;
    }

    private boolean isWithinEatTime(int time, int eatTime)
    {
        return time > eatTime - 500 && time < eatTime + 500;
    }
}
