package ntbea;

import evodef.EvalMaxM;
import utilities.ElapsedTimer;

import java.util.Arrays;

/**
 * Run a simple test
 */



public class TestNTBEA {
    public static void main(String[] args) {
        int nDims = 5;
        int mValues = 4;
        double noiseLevel = 1.0;
        boolean useTrap = true;
        // EvalMaxM is like Noisy OneMax but generalised to M values
        // instead of binary
        EvalMaxM problem = new EvalMaxM(nDims, mValues, noiseLevel).setTrap(useTrap);


        double kExplore = 2;
        double epsilon = 0.5;
        NTupleBanditEA banditEA = new NTupleBanditEA().setKExplore(kExplore).setEpsilon(epsilon);
        // set a particlar NTuple System as the model
        // if this is not set, then it will use a default model
        NTupleSystem model = new NTupleSystem();
        // set up a non-standard tuple pattern
        model.use1Tuple = true;
        model.use2Tuple = true;
        model.use3Tuple = false;
        model.useNTuple = true;
        banditEA.setModel(model);

        ElapsedTimer timer = new ElapsedTimer();
        int nEvals = 5000;
        int[] solution = banditEA.runTrial(problem, nEvals);

        System.out.println("Report: ");
        new NTupleSystemReport().setModel(model).printDetailedReport();
        new NTupleSystemReport().setModel(model).printSummaryReport();

        System.out.println("Model created: ");
        System.out.println(model);
        System.out.println("Model used: ");
        System.out.println(banditEA.getModel());

        System.out.println();
        System.out.println("Solution returned: " + Arrays.toString(solution));
        System.out.println("Solution fitness:  " + problem.trueFitness(solution));
        System.out.println("k Explore: " + banditEA.kExplore);
        System.out.println(timer);
    }
}

