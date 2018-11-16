package ntbeaplot;

import evodef.EvalMaxM;
import ntbea.NTupleBanditEA;
import ntbea.NTupleSystem;
import ntbea.NTupleSystemReport;
import utilities.ElapsedTimer;

import java.util.Arrays;

public class TestPlotter {
    public static void main(String[] args) {
        int nDims = 5;
        int mValues = 4;
        double noiseLevel = 1.0;
        double kExplore = 10;
        boolean useTrap = true;
        // EvalMaxM is like Noisy OneMax but generalised to M values
        // instead of binary
        EvalMaxM problem = new EvalMaxM(nDims, mValues, noiseLevel).setTrap(useTrap);

        NTupleBanditEA banditEA = new NTupleBanditEA().setKExplore(kExplore);

        // set a particlar NTuple System as the model
        // if this is not set, then it will use a default model
        NTupleSystem model = new NTupleSystem();
        // set up a non-standard tuple pattern
        model.use1Tuple = true;
        model.use2Tuple = true;
        model.useNTuple = false;

        banditEA.setModel(model);

        ElapsedTimer timer = new ElapsedTimer();
        int nEvals = 200;
        int[] solution = banditEA.runTrial(problem, nEvals);
        System.out.println(Arrays.toString(solution));

        System.out.println(timer);

        new Plotter().setModel(model).defaultPlot().plot1Tuples();
        System.out.println(timer);

    }
}
