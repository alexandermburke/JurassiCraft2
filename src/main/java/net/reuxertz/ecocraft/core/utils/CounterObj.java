package net.reuxertz.ecocraft.core.utils;

import net.reuxertz.ecocraft.core.EcoCraft;

public class CounterObj {

    //Timing Fields
    protected long _lastMSTime = 0, _lastDifTime = 0;
    protected int _timeCounter, _clickCounter, _totalClickCounter;
    protected double _lastOccurredCycles, _lastOccurredClicksPerCycle, _lastOccurredClicks;
    protected int _MSWaitInterval, _MSRandomVariationRange;

    //Constructors
    public CounterObj(long curTime, int MSWaitInterval, int MSRandomVariationRange)
    {
        this._MSWaitInterval = MSWaitInterval;
        this._MSRandomVariationRange = MSRandomVariationRange;
        this._lastMSTime = curTime;
    }

    //Functions
    public boolean updateMSTime(long time)
    {
        long dif = time - this._lastMSTime;
        this._lastMSTime = time;

        int d;
        if (dif > Integer.MAX_VALUE)
            d = Integer.MAX_VALUE;
        else
            d = (int)dif;

        return this.addMS(d);
    }
    public boolean addMS(int ms)
    {
        //Add Time
        this._lastDifTime = ms;
        this._timeCounter += ms;

        //If not enough time, return
        if (this._timeCounter < this._MSWaitInterval)
            return false;

        //Subtract time and random variation
        this._lastOccurredCycles = this._timeCounter / (this._MSWaitInterval * 1.0);
        this._lastOccurredClicksPerCycle = this._clickCounter / this._lastOccurredCycles;
        this._lastOccurredClicks = this._clickCounter;
        this._totalClickCounter += this._clickCounter;

        //Reset
        this._clickCounter  = 0;
        this._timeCounter = EcoCraft.RND.nextInt((this._MSRandomVariationRange * 2)) - this._MSRandomVariationRange;

        return true;
    }
    public void addClicks(int clicks)
    {
        //Add Time
        this._clickCounter += clicks;
    }

}
