package com.maiot.accelerometerexample.plot;

import android.graphics.Color;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Plotter {

    private GraphView graph;

    private LineGraphSeries<DataPoint> x;
    private int xAxisIndex = 0;

    private LineGraphSeries<DataPoint> y;
    private int yAxisIndex = 0;

    private LineGraphSeries<DataPoint> z;
    private int zAxisIndex = 0;

    private final boolean scrollToEnd = true;
    private final int MAX_PLOT_DATA_POINTS = 100;

    public static int X_AXIS = 0;
    public static int Y_AXIS = 1;
    public static int Z_AXIS = 2;


    public Plotter(GraphView graph) {
        this.graph = graph;

        initXAxis();
        initYAxis();
        initZAxis();

        setPlotProperties();
        setLegend();
        setXYAxisLimits();

        addSeriesToGraphView();
    }

    public void addData(double data, int whichAxis) {
        if (whichAxis == X_AXIS)
            addDataTo_X_Axis(data);

        if (whichAxis == Y_AXIS)
            addDataTo_Y_Axis(data);

        if (whichAxis == Z_AXIS)
            addDataTo_Z_Axis(data);
    }

    private void addDataTo_X_Axis(double data) {
        x.appendData(new DataPoint(xAxisIndex, data), true, MAX_PLOT_DATA_POINTS);
        xAxisIndex++;

        graph.onDataChanged(true, true);
    }

    private void addDataTo_Y_Axis(double data) {
        y.appendData(new DataPoint(yAxisIndex, data), scrollToEnd, MAX_PLOT_DATA_POINTS);
        yAxisIndex++;
        graph.onDataChanged(true, true);
    }

    private void addDataTo_Z_Axis(double data) {
        z.appendData(new DataPoint(zAxisIndex, data), scrollToEnd, MAX_PLOT_DATA_POINTS);
        zAxisIndex++;
        graph.onDataChanged(true, true);
    }




    private void initXAxis() {
        x = new LineGraphSeries<>();
        x.setTitle("X");
        x.setColor(Color.RED);
        x.setThickness(8);
        x.setDrawDataPoints(true);
        x.setDataPointsRadius(15);
    }

    private void initYAxis() {
        y = new LineGraphSeries<>();
        y.setTitle("Y");
        y.setColor(Color.GREEN);
        y.setThickness(8);
        y.setDrawDataPoints(true);
        y.setDataPointsRadius(15);
    }

    private void initZAxis() {
        z = new LineGraphSeries<>();
        z.setTitle("Z");
        z.setColor(Color.BLUE);
        z.setThickness(8);
        z.setDrawDataPoints(true);
        z.setDataPointsRadius(15);
    }


    private void setLegend() {
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setTextSize(40);
        graph.getLegendRenderer().setBackgroundColor(Color.argb(150, 50, 0, 0));
        graph.getLegendRenderer().setTextColor(Color.WHITE);
        graph.getLegendRenderer().setFixedPosition(0, 0);
    }

    private void setXYAxisLimits() {
        setXAxisLimits();
        setYAxisLimits();
    }

    private void setXAxisLimits() {
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(MAX_PLOT_DATA_POINTS);
        graph.getViewport().setXAxisBoundsManual(true);

    }

    private void setYAxisLimits() {
        graph.getViewport().setMinY(-15.0);
        graph.getViewport().setMaxY(15.0);
        graph.getViewport().setYAxisBoundsManual(true);
    }


    private void setPlotProperties() {
        graph.getGridLabelRenderer().setVerticalAxisTitle("Acc values [m/s^2]");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Samples");
    }


    private void addSeriesToGraphView() {
        graph.addSeries(x);
        graph.addSeries(y);
        graph.addSeries(z);
    }


}
