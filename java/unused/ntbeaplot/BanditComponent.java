package ntbeaplot;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BanditComponent extends JComponent{
    // assume we have a set of components all of the same width

    ArrayList<BanditCell> cells = new ArrayList<>();

    MultiArmedBandit mab;

    public BanditComponent setMab(MultiArmedBandit mab) {
        this.mab = mab;
        cells = new ArrayList<>();
        for (BanditCellData bcd : mab.bandits) {
            Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, cellWidth, cellHeight);
            cells.add(new BanditCell().setData(bcd).setRect(rect));
        }
        return this;
    }

    int cellWidth = 100;
    int cellHeight = 500;
    // int cellGap = c

    Color bg = Color.lightGray;

    int cellGap() { return cellWidth / 5; }

    public void paintComponent(Graphics go) {

        Graphics2D g = (Graphics2D) go;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        go.setColor(bg);
        go.fillRect(0, 0, getWidth(), getHeight());
        // now draw them all
        g.translate(cellGap(), cellGap());
        for (BanditCell bc : cells) {

            bc.draw(g);
            g.translate(cellGap() + cellWidth, 0);

        }


    }

    public Dimension getPreferredSize() {
        int n = cells.size();
        System.out.println("n = " + n);
        return new Dimension(n * cellWidth + (n+1) * cellGap(), cellHeight + 2 * cellGap());
    }


    public static void main(String[] args) {



    }




}


