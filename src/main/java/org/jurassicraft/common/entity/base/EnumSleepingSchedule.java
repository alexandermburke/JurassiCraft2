package org.jurassicraft.common.entity.base;

import java.util.Random;

public enum EnumSleepingSchedule
{
    DIURNAL(fromTimeToTicks(6, 00), fromTimeToTicks(22, 00)),
    NOCTURNAL(fromTimeToTicks(18, 00), fromTimeToTicks(6, 00)),
    CREPUSCULAR(fromTimeToTicks(12, 30), fromTimeToTicks(4, 30));

    private int wakeUpTime;
    private int sleepTime;

    EnumSleepingSchedule(int wakeUpTime, int sleepTime)
    {
        this.wakeUpTime = wakeUpTime;
        this.sleepTime = sleepTime;
    }

    public static int fromTimeToTicks(int hour, int minute)
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

    public boolean sleepNextDay()
    {
        return wakeUpTime > sleepTime;
    }

    public int getBreakfastTime()
    {
        return 1000;
    }

    public int getLunchTime()
    {
        return (getDinnerTime() - getBreakfastTime()) / 2;
    }

    public int getDinnerTime()
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

    public boolean isWithinEatTime(int time, int eatTime)
    {
        return time > eatTime - 500 && time < eatTime + 500;
    }
}
