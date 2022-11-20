package View.DesignComponent;

import java.awt.Graphics2D;

public abstract class DC_BlankPlotChatRender {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(DC_BlankPlotChart chart, Graphics2D g2, DC_SeriesSize size, int index);
}
