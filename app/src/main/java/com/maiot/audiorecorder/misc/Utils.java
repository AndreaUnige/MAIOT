package com.maiot.audiorecorder.misc;

import com.jjoe64.graphview.series.DataPoint;

public class Utils {

    public static DataPoint[] toDataPoint(short[] yValues) {
        DataPoint[] mDataPoint = new DataPoint[yValues.length];
        for (int i=0; i < yValues.length; i++)
            mDataPoint[i] = new DataPoint(i, (float) yValues[i]);

        return mDataPoint;
    }

    public static DataPoint[] toDataPoint(double[] xValues, double[] yValues) {
        DataPoint[] mDataPoint = new DataPoint[yValues.length];
        for (int i=0; i < yValues.length; i++)
            mDataPoint[i] = new DataPoint((float) xValues[i], (float) yValues[i]);

        return mDataPoint;
    }
}
