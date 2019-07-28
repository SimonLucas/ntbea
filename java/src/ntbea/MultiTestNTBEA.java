package ntbea;

import evodef.EvalMaxM;
import utilities.ElapsedTimer;
import utilities.StatSummary;

import java.util.Arrays;

/**
 * Run a simple test
 */



public class MultiTestNTBEA {
    public static void main(String[] args) {

        int nReps = 1;
        StatSummary ss = new StatSummary();
        ElapsedTimer timer = new ElapsedTimer();

        for (int i=0; i<nReps; i++) {

            int nDims = 20;
            int mValues = 2;
            int nEvals = 60;
            double noiseLevel = 0.0;
            boolean useTrap = false;
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
            model.use2Tuple = false;
            model.use3Tuple = false;
            model.useNTuple = true;
            banditEA.setModel(model);
            int[] solution = banditEA.runTrial(problem, nEvals);
            ss.add(problem.trueFitness(solution));
        }
        System.out.println(ss);
        System.out.println(timer);
    }
}

