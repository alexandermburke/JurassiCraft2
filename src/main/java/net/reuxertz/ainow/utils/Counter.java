package net.reuxertz.ainow.utils;

/**
 * Created by reuxertz on 5/20/2015.
 */
public class Counter {

    //Timing Fields
    protected long _lastMSTime = 0;
    protected int _timeCounter, _clickCounter;
    protected double _lastOccurredCycles, _lastOccurredClicksPerCycle;
    protected int _MSWaitInterval,_startMSValue, _MSRandomVariationRange;

    //Constructors
    public Counter(int MSWaitInterval, int startMSValue, int MSRandomVariationRange)
    {
        this._MSWaitInterval = MSWaitInterval;
        this._startMSValue = startMSValue;
        this._MSRandomVariationRange = MSRandomVariationRange;
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
        this._timeCounter += ms;

        //If not enough time, return
        if (this._timeCounter < this._MSWaitInterval)
            return false;

        //Subtract time and random variation
        this._lastOccurredCycles = this._timeCounter / (this._MSWaitInterval * 1.0);
        this._lastOccurredClicksPerCycle = this._clickCounter / this._lastOccurredCycles;
        this._timeCounter -= (this._MSWaitInterval +
                (Helper.RND.nextInt((this._MSRandomVariationRange * 2) - this._MSRandomVariationRange)));

        return true;
    }
    public void addClicks(int clicks)
    {
        //Add Time
        this._clickCounter += clicks;
    }

}
