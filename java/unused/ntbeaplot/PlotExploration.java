package ntbeaplot;

public class PlotExploration {



    public static void main(String[] args) {

        double epsilon = 0.0;
        for (int n=0; n<=100; n+=5) {
            for (int i=0; i<=n; i++) {
                double e = explore(n, i, epsilon);
                System.out.format("%d\t %d\t %.2f\n", n, i, e);
            }
        }


    }

    static double explore(double n, double i, double epsilon) {
        return Math.sqrt(Math.log(n) / (i + epsilon));
    }



}
