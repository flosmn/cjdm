/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.gui.box;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

/**
 *
 * @author gtoffoli
 */
public class LineStyleListCellRenderer extends JComponent implements ListCellRenderer {

    private Color selectionBackground = null;
    private Color  background = null;
    private Color  selectionForeground = null;
    private Color  foreground = null;
    
    private String styleName = "";
    
    public LineStyleListCellRenderer()
    {
        setOpaque(true);
        setBackground(Color.WHITE);
        selectionBackground = UIManager.getColor("List.selectionBackground");
        background = UIManager.getColor("List.background");
        selectionForeground = UIManager.getColor("List.selectionForeground");
        foreground = UIManager.getColor("List.foreground");
        
        
        setMinimumSize(new Dimension(20,16));
        setPreferredSize(new Dimension(20,16));
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        if (isSelected)
        {
            setForeground(selectionForeground);
            setBackground(selectionBackground);
        }
        else 
        {
            setForeground(foreground);
            setBackground(background);
        }
        styleName = ""+value;
        repaint();
        return this;
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
        if (styleName != null)
        {
            Stroke oldStroke = ((Graphics2D)g).getStroke();
            
            
            
            Stroke stroke = null;
            float penWidth = 1f;
            if (styleName.equals("Solid"))
            {
                stroke = (Stroke) new BasicStroke((float) (penWidth));
            }
            else if (styleName.equals("Dashed"))
            {
                stroke =  (Stroke) new BasicStroke((float) (penWidth), 
                                            BasicStroke.CAP_BUTT, 
                                            BasicStroke.JOIN_BEVEL, 0f, 
                                            new float[] { 5f, 3f }, 0f);
            }
            else if (styleName.equals("Dotted"))
            {
                stroke =  (Stroke) new BasicStroke((float) (penWidth), 
                                            BasicStroke.CAP_BUTT, 
                                            BasicStroke.JOIN_BEVEL, 0f, 
                                            new float[] { 1f*penWidth, 1f*penWidth }, 0f);
            }
            else if (styleName.equals("Double"))
            {
                stroke =  (Stroke) new BasicStroke((float) (penWidth/3f));
            }
            
            if (stroke != null)
            {
                ((Graphics2D)g).setStroke(stroke);

                g.setColor(getForeground());
                
                if (!styleName.equals("Double"))
                {
                    ((Graphics2D)g).drawLine(5, getHeight()/2, getWidth()-5, getHeight()/2);
                }
                else
                {
                    ((Graphics2D)g).drawLine(5, (getHeight()/2) - 1, getWidth()-5, (getHeight()/2) - 1);
                    ((Graphics2D)g).drawLine(5, (getHeight()/2) + 1, getWidth()-5, (getHeight()/2) + 1);
                }
            }
            ((Graphics2D)g).setStroke(oldStroke);
        }
        
        g.setPaintMode();
        g.setColor(Color.LIGHT_GRAY);
        ((Graphics2D)g).drawLine(0, getHeight()-1,  getWidth(), getHeight() - 1);
    }
    
    
}
