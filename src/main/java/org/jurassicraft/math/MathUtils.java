package org.jurassicraft.math;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

public class MathUtils
{
    public static final double PI = Math.PI;

    public static long mean(long[] values)
    {
        if (values.length <= 0)
        {
            throw new IllegalArgumentException("Array contains no elements");
        }
        // java 8: return LongStream.of(values).sum() / values.length;
        long sum = 0l;
        for (long v : values)
        {
            sum += v;
        }

        return sum / values.length;
    }

    public static double median(int[] values)
    {
        // FIXME: https://discuss.codechef.com/questions/1489/find-median-in-an-unsorted-array-without-sorting-it
        // ^ solve the problem in O(n)
        // v is O(n*log(n)) because of sorting it
        Arrays.sort(values);
        double median;
        if (values.length % 2 == 0)
        {
            median = ((double) values[values.length / 2] + (double) values[values.length / 2 - 1]) / 2;
        }
        else
        {
            median = values[values.length / 2];
        }

        return median;
    }

    public static int mode(int values[])
    {
        int maxValue = 0, maxCount = 0;

        for (int value : values)
        {
            int count = 0;
            for (int value2 : values)
            {
                if (value2 == value)
                {
                    ++count;
                }
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = value;
            }
        }

        return maxValue;
    }

    public static boolean hasNoRange(int num1, int num2, int difference)
    {
        if (num1 == num2)
        {
            return true;
        }
        if (num1 > num2)
        {
            if (num1 - num2 <= 0)
            {
                return true;
            }
        }
        if (num2 > num1)
        {
            if (num2 - num1 <= 0)
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String str)
    {
        return NumberUtils.isNumber(str);
    }

    public static boolean isNegative(double num)
    {
        return num < 0;
    }

    public static double makePositive(double num)
    {
        if (num >= 0)
        {
            return num;
        }
        return -num;
    }

    public static double makeNegative(double num)
    {
        if (num < 0)
        {
            return num;
        }
        return -num;
    }

}
