package ntbeaplot;

import utilities.JEasyFrame;

public class BanditCellTest {
    public static void main(String[] args) throws Exception {
        int[] len = new int[]{2, 5, 10, 20, 50, 100, 150};
        double[] means = {5000, 6000, 7000, 8000, 8400, 8300, 7000};
        double[] sds = {1000, 2000, 1700, 1500, 1600, 1700, 1800};

        BanditComponent bc = new BanditComponent();
        MultiArmedBandit mab = new MultiArmedBandit();

        for (int i=0; i<len.length; i++) {

            BanditCellData bcd = new BanditCellData();
            bcd.genMean = means[i];
            bcd.genSD = sds[i];

            bcd.label = "l = " + len[i];
            bcd.setParent(mab);
            mab.bandits.add(bcd);

        }
        bc.setMab(mab);

        String message = "nSamples = ";
        JEasyFrame frame = new JEasyFrame(bc, message + 0);


        int nSamples = 50;

        Thread.sleep(20000);

        for (int i=1; i<=nSamples; i++) {
            mab.sample();
            bc.repaint();
            frame.setTitle(message + i);
            Thread.sleep(200);
        }

    }


}
