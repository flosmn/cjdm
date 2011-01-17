/*
 * BoxBorderSelectionPanel.java
 *
 * Created on March 11, 2008, 1:06 PM
 */

package it.businesslogic.ireport.gui.box;

import it.businesslogic.ireport.Box;
import it.businesslogic.ireport.ReportElement;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  gtoffoli
 */
public class BoxBorderSelectionPanel extends javax.swing.JPanel {

    private static final int BORDER_GAP = 15;
    private static final int REF_SIZE = 8;
    private static final int REF_DINSTANCE = 4;
    
    public void clearSelection()
    {
        this.selectedBorders.clear();
        repaint();
        fireBorderSelectionChange();
    }
    
    
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public

    List<Side> getSelectedBorders() {
        return selectedBorders;
    }

    public void setSelectedBorders(List<Side> selectedBorders) {
        this.selectedBorders = selectedBorders;
    }
    
    public enum Side { TOP, LEFT, BOTTOM, RIGHT}

    private List<Side> selectedBorders = new ArrayList<Side>();
    List<BorderSelectionListener> listeners = new ArrayList<BorderSelectionListener>();
    private Box box = null;
    
    
    public void addBorderSelectionListener(BorderSelectionListener listener)
    {
        if (!listeners.contains(listener)) listeners.add(listener);
    }
    
    public void removeBorderSelectionListener(BorderSelectionListener listener)
    {
        if (listeners.contains(listener)) listeners.remove(listener);
    }
    
    /** Creates new form BoxBorderSelectionPanel */
    public BoxBorderSelectionPanel() {
        initComponents();
        
        
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_NORMALIZE);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
            
        
        Stroke oldStroke = g2d.getStroke();
        
        Stroke defaultStroke = new BasicStroke(1f);
        
        // Draw the rectangle, border by border...
        g2d.setStroke(defaultStroke);
        g2d.setColor(Color.LIGHT_GRAY);
        
        g2d.drawLine( BORDER_GAP - (REF_SIZE+REF_DINSTANCE), BORDER_GAP, BORDER_GAP - REF_DINSTANCE, BORDER_GAP);
        g2d.drawLine( (getWidth() - BORDER_GAP) + REF_DINSTANCE, BORDER_GAP, (getWidth() - BORDER_GAP) + (REF_SIZE+REF_DINSTANCE), BORDER_GAP);
        g2d.drawLine( BORDER_GAP - (REF_SIZE+REF_DINSTANCE), getHeight() - BORDER_GAP, BORDER_GAP -REF_DINSTANCE, getHeight() - BORDER_GAP);
        g2d.drawLine( (getWidth() - BORDER_GAP) + REF_DINSTANCE, getHeight() - BORDER_GAP, (getWidth() - BORDER_GAP) + (REF_SIZE+REF_DINSTANCE), getHeight() - BORDER_GAP);
        
        g2d.drawLine( BORDER_GAP, BORDER_GAP - (REF_SIZE+REF_DINSTANCE), BORDER_GAP, BORDER_GAP-REF_DINSTANCE);
        g2d.drawLine( (getWidth() - BORDER_GAP), BORDER_GAP - REF_DINSTANCE, (getWidth() - BORDER_GAP), BORDER_GAP-(REF_SIZE+REF_DINSTANCE));
        g2d.drawLine( BORDER_GAP, getHeight() - BORDER_GAP + REF_DINSTANCE, BORDER_GAP , getHeight() - BORDER_GAP + (REF_SIZE+REF_DINSTANCE));
        g2d.drawLine( (getWidth() - BORDER_GAP), getHeight() - BORDER_GAP + REF_DINSTANCE, (getWidth() - BORDER_GAP), getHeight() - BORDER_GAP + (REF_SIZE+REF_DINSTANCE));
        
        g2d.setColor(Color.BLACK);
        
        boolean defaultPaint = false;
        Color defaultColor = Color.BLACK;
        if (getBox() != null && getBox().getPen() != null)
        {
            Stroke stroke = ReportElement.getPenStroke("", getBox().getPen(), 1f);
            if (stroke != null) defaultStroke = stroke;
            defaultPaint = getBox().getPen().getLineWidth() != 0;
            if (getBox().getPen().getLineColor() != null)
            {
                defaultColor = getBox().getPen().getLineColor();
            }
        }
        
        g2d.setStroke(defaultStroke);
        g2d.setColor(defaultColor);
        
        boolean paint = defaultPaint;
        if (getBox() != null && getBox().getTopPen() != null)
        {
            if (getBox().getTopPen().getLineColor() != null) g2d.setColor(getBox().getTopPen().getLineColor());
            Stroke stroke = ReportElement.getPenStroke("", getBox().getTopPen(), 1f);
            if (stroke != null) g2d.setStroke(stroke);
            paint = getBox().getTopPen().getLineWidth() != 0;
        }
        if (paint) g2d.drawLine(BORDER_GAP,BORDER_GAP, this.getWidth()-BORDER_GAP, BORDER_GAP);
        paint = defaultPaint;
        
        // Draw the rectangle, border by border...
        g2d.setStroke(defaultStroke);
        g2d.setColor(defaultColor);
        if (getBox() != null && getBox().getRightPen() != null)
        {
            if (getBox().getRightPen().getLineColor() != null)g2d.setColor(getBox().getRightPen().getLineColor());
            Stroke stroke = ReportElement.getPenStroke("", getBox().getRightPen(), 1f);
            if (stroke != null) g2d.setStroke(stroke);
            paint = getBox().getRightPen().getLineWidth() != 0;
        }
        if (paint) g2d.drawLine(this.getWidth()-BORDER_GAP,BORDER_GAP, this.getWidth()-BORDER_GAP, this.getHeight()-BORDER_GAP);
        paint = defaultPaint;
        
        // Draw the rectangle, border by border...
        g2d.setStroke(defaultStroke);
        g2d.setColor(defaultColor);
        if (getBox() != null && getBox().getBottomPen() != null)
        {
            if (getBox().getBottomPen().getLineColor() != null)g2d.setColor(getBox().getBottomPen().getLineColor());
            Stroke stroke = ReportElement.getPenStroke("", getBox().getBottomPen(), 1f);
            if (stroke != null) g2d.setStroke(stroke);
            paint = getBox().getBottomPen().getLineWidth() != 0;
        }
        
        if (paint) g2d.drawLine(BORDER_GAP,this.getHeight()-BORDER_GAP, this.getWidth()-BORDER_GAP, this.getHeight()-BORDER_GAP);
        paint = defaultPaint;
        
        // Draw the rectangle, border by border...
        g2d.setStroke(defaultStroke);
        g2d.setColor(defaultColor);
        if (getBox() != null && getBox().getLeftPen() != null)
        {
            if (getBox().getLeftPen().getLineColor() != null)g2d.setColor(getBox().getLeftPen().getLineColor());
            Stroke stroke = ReportElement.getPenStroke("", getBox().getLeftPen(), 1f);
            if (stroke != null) g2d.setStroke(stroke);
            paint = getBox().getLeftPen().getLineWidth() != 0;
        }
        if (paint) g2d.drawLine(BORDER_GAP,BORDER_GAP, BORDER_GAP, this.getHeight()-BORDER_GAP);
        paint = defaultPaint;
        
        // Draw selections...
        Area a = new Area();
            
        Stroke stroke = new BasicStroke(1f, 
                                            BasicStroke.CAP_BUTT, 
                                            BasicStroke.JOIN_BEVEL, 0f, 
                                            new float[] { 1f, 1f}, 0f);
        g2d.setStroke(stroke);
        g.setColor(Color.DARK_GRAY);
        g.setXORMode(Color.WHITE);
            
        for (Side s : getSelectedBorders())
        {
            switch (s)
            {
                case TOP:
                    Rectangle2D rect1 = new Rectangle2D.Float(BORDER_GAP-5,BORDER_GAP-5,getWidth()-(2*BORDER_GAP)+10,10);
                    a.add(new Area(rect1));
                    g2d.draw(rect1);
                    break;
                case RIGHT:
                    Rectangle2D rect2 = new Rectangle2D.Float(getWidth()-BORDER_GAP-5,BORDER_GAP-5,10,getHeight()-(2*BORDER_GAP) + 10);
                    a.add(new Area(rect2));
                    g2d.draw(rect2);
                    break;
                case BOTTOM:
                    Rectangle2D rect3 = new Rectangle2D.Float(BORDER_GAP-5,getHeight()-(BORDER_GAP)-5, getWidth()-(2*BORDER_GAP)+10,10);
                    a.add(new Area(rect3));
                    g2d.draw(rect3);
                    break;
                case LEFT:
                    Rectangle2D rect4 = new Rectangle2D.Float(BORDER_GAP-5,BORDER_GAP-5,10,getHeight()-(2*BORDER_GAP) + 10);
                    a.add(new Area(rect4));
                    g2d.draw(rect4);
                    break;
            }
            
            
            Area a2 = new Area(new Rectangle2D.Float(5,5,getWidth()-10,getHeight()-10));
            a2.intersect(a);
            //g2d.draw(a2);
        }
        
        g2d.setStroke(oldStroke);
    }
    
    
    private void fireBorderSelectionChange()
    {
        for (BorderSelectionListener listener : listeners)
        {
            try {
                listener.selectionChanged(getSelectedBorders());
            } catch (Throwable e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Rectangle2D rect1 = new Rectangle2D.Float(BORDER_GAP-5,BORDER_GAP-5,getWidth()-(2*BORDER_GAP)+10,10);
        Rectangle2D rect2 = new Rectangle2D.Float(getWidth()-BORDER_GAP-5,BORDER_GAP-5,10,getHeight()-(2*BORDER_GAP) + 10);
        Rectangle2D rect3 = new Rectangle2D.Float(BORDER_GAP-5,getHeight()-(BORDER_GAP)-5, getWidth()-(2*BORDER_GAP)+10,10);
        Rectangle2D rect4 = new Rectangle2D.Float(BORDER_GAP-5,BORDER_GAP-5,10,getHeight()-(2*BORDER_GAP) + 10);
        
        boolean fireEvent = false;
        
//        if (getSelectedBorders().size() > 0 && !evt.isControlDown())
//        {
//            getSelectedBorders().clear();
//            fireEvent = true;
//        }    
            
        if (rect1.contains(evt.getPoint()))
        {
            if (!selectedBorders.contains(Side.TOP))
            {
                getSelectedBorders().add(Side.TOP);
            }
            else
            {
                getSelectedBorders().remove(Side.TOP);
            }
            fireEvent = true;
        }
        else if (rect2.contains(evt.getPoint()))
        {
            if (!selectedBorders.contains(Side.RIGHT))
            {
                getSelectedBorders().add(Side.RIGHT);
            }
            else
            {
                getSelectedBorders().remove(Side.RIGHT);
            }
            fireEvent = true;
        }
        else if (rect3.contains(evt.getPoint()))
        {
            if (!selectedBorders.contains(Side.BOTTOM))
            {
                getSelectedBorders().add(Side.BOTTOM);
            }
            else
            {
                getSelectedBorders().remove(Side.BOTTOM);
            }
            fireEvent = true;
        }
        else if (rect4.contains(evt.getPoint()))
        {
            if (!selectedBorders.contains(Side.LEFT))
            {
                getSelectedBorders().add(Side.LEFT);
            }
            else
            {
                getSelectedBorders().remove(Side.LEFT);
            }
            fireEvent = true;
        }
        
        repaint();
        if (fireEvent) fireBorderSelectionChange();
    }//GEN-LAST:event_formMousePressed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
