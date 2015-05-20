package net.reuxertz.civnow.utils;

/**
 * Created by reuxertz on 5/20/2015.
 */
public class Counter {

    //Timing Fields
    protected int _counter;
    protected int _MSWaitInterval,_startMSValue, _MSRandomVariationRange;

    //Constructors
    public Counter(int MSWaitInterval, int startMSValue, int MSRandomVariationRange)
    {
        this._MSWaitInterval = MSWaitInterval;
        this._startMSValue = startMSValue;
        this._MSRandomVariationRange = MSRandomVariationRange;
    }

    //Functions
    public boolean addMS(int ms)
    {
        //Add Time
        this._counter += ms;

        //If not enough time, return
        if (this._counter < this._MSWaitInterval)
            return false;

        //Subtract time and random variation
        this._counter -= (this._MSWaitInterval +
                (Helper.RND.nextInt((this._MSRandomVariationRange * 2) - this._MSRandomVariationRange)));

        return true;
    }

}
