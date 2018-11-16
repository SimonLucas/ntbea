package ntbeaplot;

import utilities.StatSummary;

import java.util.ArrayList;
import java.util.Random;

public class BanditCellData {

    double k = 5000;
    double epsilon = 0.1;
    String label = "NTBEA";
    static Random random = new Random();

    StatSummary ss = new StatSummary();
    ArrayList<Double> data = new ArrayList<>();

    MultiArmedBandit mab;

    public BanditCellData setParent(MultiArmedBandit mab) {
        this.mab = mab;
        return this;
    }

    double genMean;
    double genSD;

    public BanditCellData sample() {
        double x = random.nextGaussian() * genSD + genMean;
        data.add(x);
        ss.add(x);
        justSampled = true;
        return this;
    }

    public double ucbValue(int n) {
        return exploit() + explore(n);
    }

    public double ucbValue() {
        return ucbValue(ss.n());
    }

    public double explore(int n) {
        return k * Math.sqrt(Math.log(1+n) / (ss.n() + epsilon));
    }

    public double explore() {
        return explore(mab.nSamples);
    }

    public double exploit() {
        return ss.n() > 0 ? ss.mean() : 0;
    }

    public boolean justSampled = false;
    public void clearFlag() {
        justSampled = false;
    }
}
