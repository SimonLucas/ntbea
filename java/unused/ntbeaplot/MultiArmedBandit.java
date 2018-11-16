package ntbeaplot;

import utilities.Picker;

import java.util.ArrayList;
import java.util.Random;

public class MultiArmedBandit {

    static Random random = new Random();

    ArrayList<BanditCellData> bandits = new ArrayList<>();
    int nSamples = 0;

    public MultiArmedBandit sample() {
        Picker<BanditCellData> picker = new Picker<>(Picker.MAX_FIRST);
        for (BanditCellData bcd : bandits) {
            bcd.clearFlag();
            picker.add(random.nextDouble() / 1e6 + bcd.ucbValue(nSamples), bcd);
        }
        BanditCellData chosen = picker.getBest();
        // now make a sample
        chosen.sample();
        nSamples++;
        return this;
    }

}
