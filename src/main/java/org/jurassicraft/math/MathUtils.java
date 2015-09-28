package org.jurassicraft.math;

import java.util.Arrays;

public class MathUtils {

    public static double getPi()
    {
        return Math.PI;
    }

    public static long mean(long[] values)
    {
        long sum = 0l;
        for (long v : values) {
            sum += v;
        }

        return sum / values.length;
    }

    public static double median(int[] values)
    {
        Arrays.sort(values);
        double median;
        if (values.length % 2 == 0)
            median = ((double) values[values.length / 2] + (double) values[values.length / 2 - 1]) / 2;
        else
            median = (double) values[values.length / 2];

        return median;
    }

    public static int mode(int values[])
    {
        int maxValue = 0, maxCount = 0;

        for (int i = 0; i < values.length; ++i) {
            int count = 0;
            for (int j = 0; j < values.length; ++j) {
                if (values[j] == values[i])
                    ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = values[i];
            }
        }

        return maxValue;
    }

    public static boolean hasNoRange(int num1, int num2, int difference)
    {
        if (num1 == num2) {
            return true;
        }
        if (num1 > num2) {
            if (num1 - num2 <= 0) {
                return true;
            }
        }
        if (num2 > num1) {
            if (num2 - num1 <= 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String str)
    {
        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNegative(double num)
    {
        return num < 0;
    }

}
