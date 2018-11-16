package ntbea.speedtest;

import utilities.ElapsedTimer;
import utilities.StatSummary;

public class StatSummarySpeed {

    // create a number of stat summary objects
    // creates more than 1 million in half a second ...

    public static void main(String[] args) {

        int n = (int) 1e6;
        StatSummary[] ssa = new StatSummary[n];

        ElapsedTimer t = new ElapsedTimer();

        for (int i=0; i<n; i++) {
            ssa[i] = new StatSummary();
            ssa[i].add(i);
        }
        System.out.println("Created: " + n);
        System.out.println(t);

    }
}
