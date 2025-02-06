package com.maiot.audiorecorder.plots;

import android.graphics.Color;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.maiot.audiorecorder.misc.Utils;


public class Plotter {

    private GraphView graph;

    private LineGraphSeries<DataPoint> line;

    public Plotter(GraphView graph, String title) {
        this.graph = graph;
        initAxis(title);
    }


    public void plot(short[] yValues) {
        line = new LineGraphSeries<>(Utils.toDataPoint(yValues));
        doPlot();
    }

    public void plot(double[] xValues, double[] yValues) {
        line = new LineGraphSeries<>(Utils.toDataPoint(xValues, yValues));
        doPlot();
    }

    private void doPlot() {
        graph.removeAllSeries();
        graph.addSeries(line);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
    }



    private void initAxis(String title) {
        line = new LineGraphSeries<>();
        line.setTitle(title);
        line.setThickness(8);
        line.setDrawDataPoints(true);
        line.setDataPointsRadius(15);
    }

}
