/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.DesignComponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author quanc
 */
public class BieuDo extends javax.swing.JPanel {

    private List<DC_ModelLoai> legends = new ArrayList<>();
    private List<DC_ModelBieuDo> model = new ArrayList<>();
    private final int seriesSize = 12;
    private final int seriSpace = 6;
    private final Animator animator;
    private float animate;

    public BieuDo() {
        initComponents();
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
            
        };
        animator = new Animator(800 , target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        
        blankPlotChart1.setBlankPlotChatRender(new DC_BlankPlotChatRender() {
            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(DC_BlankPlotChart chart, Graphics2D g2, DC_SeriesSize size, int index) {
                double totalSeriesWidth = (seriSpace * legends.size()) + (seriSpace * (legends.size() - 1));
//                g2.drawRect();
                double x = (size.getWidth() - totalSeriesWidth) / 2;
                for (int i = 0; i < legends.size(); i++) {
                    DC_ModelLoai legend = legends.get(i);
                    g2.setColor(legend.getColor());
                    double seriValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight()) * animate;
                    g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriValues), seriesSize, (int) seriValues);
                    x += seriSpace + seriesSize;
                }

            }
        });
    }

    public void addLegend(String name, Color color) {
        DC_ModelLoai data = new DC_ModelLoai(name, color);
        legends.add(data);
        PanelLegends.add(new DC_LoaiCot(data));
        PanelLegends.repaint();
        PanelLegends.revalidate();

    }

    public void addData(DC_ModelBieuDo data) {
        model.add(data);
        blankPlotChart1.setLabelCount(model.size());
        double max = data.getMaxValue();
        if (max > blankPlotChart1.getMaxValues()) {
            blankPlotChart1.setMaxValues(max);
        }
    }
    
    public void clear(){
        animate = 0;
        blankPlotChart1.setLabelCount(0);
        model.clear();
        repaint();
    }
    
    public void start(){
        if(!animator.isRunning()){
            animator.start();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blankPlotChart1 = new View.DesignComponent.DC_BlankPlotChart();
        PanelLegends = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        blankPlotChart1.setForeground(new java.awt.Color(255, 255, 255));

        PanelLegends.setBackground(new java.awt.Color(17, 28, 68));
        PanelLegends.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(blankPlotChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelLegends, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(blankPlotChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PanelLegends, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLegends;
    private View.DesignComponent.DC_BlankPlotChart blankPlotChart1;
    // End of variables declaration//GEN-END:variables
}
