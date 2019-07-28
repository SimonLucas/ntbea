package evodef;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by sml on 17/01/2017.
 */
public class DefaultMutator implements Mutator {

    public static void main(String[] args) {

        // check operation on a simple case
        DefaultMutator mutator = new DefaultMutator(new EvalMaxM(3, 2));

        System.out.println(Arrays.toString(mutator.randMut(new int[]{0, 0, 0})));
        System.out.println(Arrays.toString(mutator.randMut(new int[]{1, 1, 1})));

        // check operation of this
        mutator.totalRandomChaosMutation = false;
        mutator = new DefaultMutator(new EvalMaxM(50, 10));
        System.out.println(Arrays.toString(mutator.randMut(new int[mutator.searchSpace.nDims()])));
        // mutator.totalRandomChaosMutation = true;
        System.out.println(Arrays.toString(mutator.randMut(new int[mutator.searchSpace.nDims()])));

        System.out.println();
        DefaultMutator mutator1 = new DefaultMutator(new EvalMaxM(50, 10));
        System.out.println(mutator1);
        mutator1.flipAtLeastOneValue = true;
        mutator1.pointProb = 1.0;
        System.out.println(mutator1);

        DefaultMutator mutator2 = new DefaultMutator(new EvalMaxM(50, 2));
        mutator2.pointProb = 5;
        System.out.println(mutator2);
        System.out.println(Arrays.toString(mutator2.randMut(new int[mutator2.searchSpace.nDims()])));

        DefaultMutator mutator3 = new DefaultMutator(new EvalMaxM(10, 10));
        mutator3.setSwap(true);

        System.out.println();
        int[] x = new int[]{0, 1, 2, 3, 4 ,5};
        // x = mutator3.swapMutation(x);
        x = mutator3.randMut(x);
        System.out.println(Arrays.toString(x));

    }

    // this will be set each time a DefaultMutator is created
    public double pointProb;
    static Random random = new Random();

    public boolean totalRandomChaosMutation = false;
    public static double defaultPointProb = 2.0;
    public static boolean flipAtLeastOneValueDefault = true;

    public boolean flipAtLeastOneValue = flipAtLeastOneValueDefault;

    SearchSpace searchSpace;

    public DefaultMutator(SearchSpace searchSpace) {
        this.searchSpace = searchSpace;
        pointProb = defaultPointProb;
        flipAtLeastOneValue = flipAtLeastOneValueDefault;
    }

    boolean swapMutation = false;

    public int[] swapMutation(int[] a) {

        int[] x = new int[a.length];

        // first of all make a copy

        for (int i=0; i<a.length; i++) {
            x[i] = a[i];
        }

        // now pick two to swap

        int ix1 = random.nextInt(x.length);
        int ix2 = random.nextInt(x.length);

        x[ix1] = a[ix2];
        x[ix2] = a[ix1];

        return x;

    }

    @Override
    public int[] randMut(int[] v) {
        // note: the algorithm ensures that at least one of the bits is different in the returned array
        if (swapMutation) {
            return swapMutation(v);
        }
        if (totalRandomChaosMutation) {
            return SearchSpaceUtil.randomPoint(searchSpace);
        }
        // otherwise do a proper mutation
        int n = v.length;
        int[] x = new int[n];
        // pointwise probability of additional mutations
        double mutProb = pointProb / n;
        // choose element of vector to mutate
        int ix = random.nextInt(n);
        if (!flipAtLeastOneValue) {
            // setting this to -1 means it will never match the first clause in the if statement in the loop
            // leaving it at the randomly chosen value ensures that at least one bit (or more generally value) is always flipped
            ix = -1;
        }
        // copy all the values faithfully apart from the chosen one
        for (int i=0; i<n; i++) {
            if (i == ix || random.nextDouble() < mutProb) {
                x[i] = mutateValue(v[i], searchSpace.nValues(i));
            } else {
                x[i] = v[i];
            }
        }
        return x;
    }

    int mutateValue(int cur, int nPossible) {
        // the range is nPossible-1, since we
        // selecting the current value is not allowed
        // therefore we add 1 if the randomly chosen
        // value is greater than or equal to the current value
        if (nPossible <= 1) return cur;
        int rx = random.nextInt(nPossible-1);
        return rx >= cur ? rx+1 : rx;
    }

    public static int diffHamming(int[] a, int[] b) {
        int tot =0;
        for (int i=0; i<a.length; i++) {
            tot += a[i] == b[i] ? 0 : 1;
        }
        return tot;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DefaultMutator\n");
        sb.append("Totally random mutations: " + totalRandomChaosMutation + "\n");
        sb.append("Flip at least one value:  " + flipAtLeastOneValue + "\n");
        sb.append("Point mutation prob:      " + pointProb + "\n");
        return sb.toString();
    }

    @Override
    public DefaultMutator setSearchSpace(SearchSpace searchSpace) {
        this.searchSpace = searchSpace;
        return this;
    }

    @Override
    public DefaultMutator setSwap(boolean swapMutation) {
        this.swapMutation = swapMutation;
        return this;
    }


}
