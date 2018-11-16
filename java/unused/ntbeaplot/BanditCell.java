package ntbeaplot;

import spinbattle.util.DrawUtil;
import utilities.RangeMapper;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class BanditCell {

    BanditCellData data;
    Rectangle2D rect;
    RangeMapper yMap;

    public BanditCell setData(BanditCellData data) {
        this.data = data;
        return this;
    }

    public BanditCell setRect(Rectangle2D rect) {
        this.rect = rect;
        return this;
    }

    Color bg = Color.black; // Color.getHSBColor(0.5f, 1.0f, 1.0f);
    Color sample = new Color(1.0f, 0.0f, 0.0f, 0.95f);
    Color exploit = Color.green;
    Color explore = Color.blue;
    Color text = Color.white;

    public BanditCell draw(Graphics2D g) {
        yMap = new RangeMapper(0, 20000, rect.getHeight(), 0);


        g.setColor(bg);
        g.fill(rect);

        if (data.justSampled) {
            g.setColor(Color.getHSBColor(0.83f, 1f, 1f));
            g.fillRect(0, (int) rect.getHeight()/8, (int) rect.getWidth(), (int) (3 * rect.getHeight())/4);
        }

        plotUCB(g);

        // draw data
        plotSamples(g);

        // finally text overlay
        // g.setColor(text);

        plotText(g);
        return this;

    }

    private void plotText(Graphics2D g) {

        DrawUtil util = new DrawUtil().setColor(text).setFontSize(14);

        if (data.label != null) {
            util.centreString(g, data.label, rect.getCenterX(), util.fontSize);
        }

        util.centreString(g, "" + data.ss.n(), rect.getCenterX(), util.fontSize * 2.5);

    }

    private void plotUCB(Graphics2D g) {

        double w = rect.getWidth()/2;
        double yb = yMap.map(0);
        double y1 = yMap.map(data.exploit());
        double y2 = yMap.map(data.exploit() + data.explore());
        Rectangle2D.Double r1 = new Rectangle2D.Double(
                w/2, y1, w, yb-y1
        );
        Rectangle2D.Double r2 = new Rectangle2D.Double(
          w/2, y2, w, y1-y2
        );

        g.setColor(exploit);
        // lowest part of bar
        g.fill(r1);

        g.setColor(explore);
        g.fill(r2);

        System.out.println((int) data.exploit() + "\t " + (int) data.explore() + "\t " + (int) data.ucbValue());

    }

    double rh = 20;

    // todo: currently 15:49, need a simple version done in one hour, at least by 17:00

    private void plotSamples(Graphics2D g) {
        g.setColor(sample);
        double w = rect.getWidth()/3;

        for (Double y : data.data) {

            Shape r = new Ellipse2D.Double(
              rect.getWidth()/2 - w/2,
              yMap.map(y) - rh/2,
              w,
              rh
            );

            g.fill(r);

        }
    }
}
