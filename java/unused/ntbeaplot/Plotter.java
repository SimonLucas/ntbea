package ntbeaplot;

import evodef.AnnotatedFitnessSpace;
import ntbea.IntArrayPattern;
import ntbea.NTuple;
import ntbea.NTupleSystem;
import ntbea.NTupleSystemReport;
import ntuple.params.Param;
import plot.LineChart;
import plot.LineChartAxis;
import plot.LineGroup;
import utilities.JEasyFrame;
import utilities.StatSummary;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Plotter {

    NTupleSystem nTupleSystem;

    // or could just use static methods ...
    // LineChart lineChart = new

    public Plotter setModel(NTupleSystem nTupleSystem) {
        this.nTupleSystem = nTupleSystem;
        return this;
    }

    public AnnotatedFitnessSpace afs;

    public Plotter setAnnotatedFitnessSpace(AnnotatedFitnessSpace afs) {
        this.afs = afs;
        return this;
    }


    public Plotter plot1Tuples() {
        TreeSet<IntArrayPattern> orderedKeys = new TreeSet<>();
        int tupleSize = 1;
        // iterate over all the tuples, picking ones of the correct size
        // Param[] params = afs.getParams();
        for (NTuple nTuple : nTupleSystem.tuples) {
            if (nTuple.tuple.length == tupleSize) {
                ArrayList<StatSummary> ssa = new ArrayList<>();
                orderedKeys.addAll(nTuple.ntMap.keySet());
                // iterate in key order to provide a sensible looking plot
                double[] xTicks = new double[orderedKeys.size()];
                int ix = 0;
                // only look at the first index of it
                // Param param = params[nTuple.tuple[0]];
                StatSummary stats = new StatSummary();
                for (IntArrayPattern key : orderedKeys) {
                    StatSummary ss = nTuple.ntMap.get(key);
                    if (ss != null) {
                        stats.add(ss);
                        // if (nTuple.paramString())
                        xTicks[ix++] = key.v[0];
                        // xTicks[ix++] = (Double) param.getValue(key.v[0]);
                        ssa.add(ss);
                        System.out.format("%s\t %d\t %.2f\t %.2f\n", key, ss.n(), ss.mean(), ss.stdErr());
                    }
                }
                // System.out.println(ssa);
                System.out.println();
                // create a LineGroup
                LineGroup lineGroup = new LineGroup().setColor(Color.black);
                lineGroup.stats = ssa;
                LineChart lineChart = new LineChart().addLineGroup(lineGroup);
                lineChart.setYLabel("Average Fitness");
                lineChart.setXLabel("Parameter index");
                // this is just a temporary fix
                // double[] altTicks = new double[]{2, 5, 10, 20, 40, 65, 100, 150, 200};
                // xTicks = new double[]{};
                // if (xTicks.length == altTicks.length) xTicks = altTicks;
                lineChart.xAxis = new LineChartAxis(xTicks);
                lineChart.bg = Color.gray;
                lineChart.plotBG = Color.white;
                int lower = (int) stats.min();
                int upper = 1 + (int) stats.max();
                double[] yTicks = new double[]{lower, (upper + lower) / 2, upper};
                lineChart.yAxis = new LineChartAxis(yTicks);
                lineChart.title = Arrays.toString(nTuple.tuple);
                new JEasyFrame(lineChart, "Line Chart");
            }
        }
        return this;
    }



    public Plotter defaultPlot() {
        return this;
    }
}
