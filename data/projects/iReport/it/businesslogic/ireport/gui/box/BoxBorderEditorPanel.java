/*
 * BoxBorderEditorPanel.java
 *
 * Created on March 11, 2008, 2:56 PM
 */

package it.businesslogic.ireport.gui.box;

import it.businesslogic.ireport.Box;
import it.businesslogic.ireport.Pen;
import it.businesslogic.ireport.gui.box.BoxBorderSelectionPanel.Side;
import it.businesslogic.ireport.gui.sheet.ColorSelectorPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author  gtoffoli
 */
public class BoxBorderEditorPanel extends javax.swing.JPanel implements BorderSelectionListener {
    
    private BoxBorderSelectionPanel selectionPanel = null;
    private Box box = null;
    private SpinnerNumberModel spinnedModel = null;
    private DefaultListModel styleListModel = null;
    private ColorSelectorPanel colorSelector = null;
    
    private List<ActionListener> listeners = new ArrayList<ActionListener>();
    
    public void addActionPerformedListener(ActionListener listener)
    {
        if (!listeners.contains(listener)) listeners.add(listener);
    }
    
    public void removeActionPerformedListener(ActionListener listener)
    {
        if (listeners.contains(listener)) listeners.remove(listener);
    }
    
    private void fireActionPerformed()
    {
        ActionEvent evt = new ActionEvent(this,0,"pen");
        for (ActionListener listener : listeners)
        {
            listener.actionPerformed(evt);
        }
    }
    
    private boolean init = false;
    
    
    /** Creates new form BoxBorderEditorPanel */
    public BoxBorderEditorPanel() {
        initComponents();
        
        selectionPanel = new BoxBorderSelectionPanel();
        selectionPanel.addBorderSelectionListener(this);
        jPanelPreview.add(selectionPanel, BorderLayout.CENTER);
        
        colorSelector = new ColorSelectorPanel();
        jPanelColorSelector.add(colorSelector, BorderLayout.CENTER);
        
        colorSelector.setColor(null);
        colorSelector.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                colorChanged();
            }
        });
        
        spinnedModel = new SpinnerNumberModel(1, 0, 100, 0.25); 
        jSpinnerLineWidth.setModel(spinnedModel);
        spinnedModel.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                widthChanged();
            }
        });
        
        jSpinnerLineWidth.setFont( UIManager.getFont("TextField.font"));
        
        jList1.setCellRenderer(new LineStyleListCellRenderer());
        styleListModel = new DefaultListModel();
        jList1.setModel(styleListModel);

        //styleListModel.addElement("");
        styleListModel.addElement("Solid");
        styleListModel.addElement("Dashed");
        styleListModel.addElement("Dotted");
        styleListModel.addElement("Double");
        
        jList1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                lineStyleChanged();
            }
        });

    }
    
    public void selectionChanged(List<Side> selectedBorders) {
              if (isInit() || box == null) return;  
              
              setInit(true);
              
              boolean sameWidth = true;
              boolean sameStyle = true;
              boolean sameColor = true;
              
              boolean first = true;
              
              // Reset all...
              spinnedModel.setValue(0.0);
              colorSelector.setColor(null);
              jList1.clearSelection();
              
              if (selectedBorders.size() == 0)
              {
                  if (getBox() != null && getBox().getPen() != null)
                  {
                      Pen pen = getBox().getPen();
                      spinnedModel.setValue(pen.getLineWidth());
                      colorSelector.setColor( pen.getLineColor() );
                      if (pen.getLineStyle() != null)
                        {
                            jList1.setSelectedValue( pen.getLineStyle(), true);
                        }
                  }
              }
              
              for (it.businesslogic.ireport.gui.box.BoxBorderSelectionPanel.Side s : selectedBorders)
              {
                    Pen pen = null;
                    if (getBox() != null)
                    {
                        switch (s)
                        {
                            case TOP: pen =  getBox().getTopPen(); break;
                            case BOTTOM: pen =  getBox().getBottomPen(); break;
                            case RIGHT: pen =  getBox().getRightPen(); break;
                            case LEFT: pen =  getBox().getLeftPen(); break;
                        }
                    }
                    
                    if (pen != null)
                    {
                        if (first)
                        {
                            spinnedModel.setValue(pen.getLineWidth());
                            colorSelector.setColor( pen.getLineColor() );
                            if (pen.getLineStyle() != null)
                            {
                                jList1.setSelectedValue( pen.getLineStyle(), true);
                            }
                        }
                        else 
                        {
                            if (sameWidth)
                            {
                                if (pen.getLineWidth() != spinnedModel.getNumber().floatValue())
                                {
                                    sameWidth = false;
                                    // Keep the first value...
                                }
                            }
                            
                            if (sameColor)
                            {
                                if (pen.getLineColor() == null ||
                                    !pen.getLineColor().equals( colorSelector.getColor() ))
                                {
                                    sameColor = false;
                                    colorSelector.setColor( null );
                                }
                            }
                            
                            if (sameStyle)
                            {
                                String ss = jList1.getSelectedValue()+"";
                                if (pen.getLineStyle() == null || !pen.getLineStyle().equals(ss))
                                {
                                    sameStyle = false;
                                    jList1.clearSelection();
                                }
                            }
                            
                        }
                        first = false;
                    }
              
                    
              }
              
              setInit(false);
              
    }
    
    public void widthChanged() {
        
        if (isInit() || box == null) return;
                
        List<Side> borders =  new ArrayList<Side>();
        borders.addAll(selectionPanel.getSelectedBorders());
       
        if (borders.size() == 0)
        {
            if (box.getPen() == null) box.setPen(new Pen());
            Pen pen = box.getPen();
            pen.setLineWidth( spinnedModel.getNumber().floatValue() );
            borders.add( Side.TOP);
            borders.add(Side.BOTTOM);
            borders.add(Side.LEFT);
            borders.add(Side.RIGHT);
        }
        
            
        for (it.businesslogic.ireport.gui.box.BoxBorderSelectionPanel.Side s : borders)
        {
            switch (s)
            {
                case TOP:
                {
                    if (box.getTopPen() == null) box.setTopPen(new Pen());
                    Pen pen = box.getTopPen();
                    pen.setLineWidth( spinnedModel.getNumber().floatValue() );
                    break;
                }
                case LEFT:
                {
                    if (box.getLeftPen() == null) box.setLeftPen(new Pen());
                    Pen pen = box.getLeftPen();
                    pen.setLineWidth( spinnedModel.getNumber().floatValue() );
                    break;
                }
                case BOTTOM:
                {
                    if (box.getBottomPen() == null) box.setBottomPen(new Pen());
                    Pen pen = box.getBottomPen();
                    pen.setLineWidth( spinnedModel.getNumber().floatValue() );
                    break;
                }
                case RIGHT:
                {
                    if (box.getRightPen() == null) box.setRightPen(new Pen());
                    Pen pen = box.getRightPen();
                    pen.setLineWidth( spinnedModel.getNumber().floatValue() );
                    break;
                }
            }
        }
        
        this.getSelectionPanel().repaint();
        fireActionPerformed();
    }
    
    public void colorChanged() {
        
        if (isInit() || box == null) return;
                
        Color color = colorSelector.getColor();
        
        List<Side> borders =  new ArrayList<Side>();
        borders.addAll(selectionPanel.getSelectedBorders());
        
        if (borders.size() == 0)
        {
            if (box.getPen() == null && color != null) box.setPen(new Pen());
            Pen pen = box.getPen();
            if (pen != null) pen.setLineColor(color );
            borders.add( Side.TOP);
            borders.add(Side.BOTTOM);
            borders.add(Side.LEFT);
            borders.add(Side.RIGHT);
        }
         
        for (it.businesslogic.ireport.gui.box.BoxBorderSelectionPanel.Side s : borders)
        {
            switch (s)
            {
                case TOP:
                {
                    if (box.getTopPen() == null && color != null) box.setTopPen(new Pen());
                    Pen pen = box.getTopPen();
                    if (pen != null) pen.setLineColor(color );
                    break;
                }
                case LEFT:
                {
                    if (box.getLeftPen() == null && color != null) box.setLeftPen(new Pen());
                    Pen pen = box.getLeftPen();
                    if (pen != null) pen.setLineColor(color );
                    break;
                }
                case BOTTOM:
                {
                    if (box.getBottomPen() == null && color != null) box.setBottomPen(new Pen());
                    Pen pen = box.getBottomPen();
                    if (pen != null) pen.setLineColor(color );
                    break;
                }
                case RIGHT:
                {
                    if (box.getRightPen() == null && color != null) box.setRightPen(new Pen());
                    Pen pen = box.getRightPen();
                    if (pen != null) pen.setLineColor(color );
                    break;
                }
            }
        }
        
        this.getSelectionPanel().repaint();
        fireActionPerformed();
    }
    
    public void lineStyleChanged() {
        
        if (isInit() || box == null) return;
                
        String style = null;
        if (jList1.getSelectedIndex() >= 0)
        {
            style = (String)jList1.getSelectedValue();
        }
        
        List<Side> borders =  new ArrayList<Side>();
        borders.addAll(selectionPanel.getSelectedBorders());
        
        if (borders.size() == 0)
        {
            if (box.getPen() == null) box.setPen(new Pen());
            Pen pen = box.getPen();
            pen.setLineStyle( style );
            borders.add( Side.TOP);
            borders.add(Side.BOTTOM);
            borders.add(Side.LEFT);
            borders.add(Side.RIGHT);
        }
            
        for (it.businesslogic.ireport.gui.box.BoxBorderSelectionPanel.Side s : borders)
        {
            switch (s)
            {
                case TOP:
                {
                    if (box.getTopPen() == null) box.setTopPen(new Pen());
                    Pen pen = box.getTopPen();
                    pen.setLineStyle( style );
                    break;
                }
                case LEFT:
                {
                    if (box.getLeftPen() == null) box.setLeftPen(new Pen());
                    Pen pen = box.getLeftPen();
                    pen.setLineStyle( style );
                    break;
                }
                case BOTTOM:
                {
                    if (box.getBottomPen() == null) box.setBottomPen(new Pen());
                    Pen pen = box.getBottomPen();
                    pen.setLineStyle( style );
                    break;
                }
                case RIGHT:
                {
                    if (box.getRightPen() == null) box.setRightPen(new Pen());
                    Pen pen = box.getRightPen();
                    pen.setLineStyle( style );
                    break;
                }
            }
        }
        
        this.getSelectionPanel().repaint();
        fireActionPerformed();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanelPreview = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerLineWidth = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jPanelColorSelector = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanelPreview.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelPreview.setMinimumSize(new java.awt.Dimension(120, 80));
        jPanelPreview.setPreferredSize(new java.awt.Dimension(120, 80));
        jPanelPreview.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 4, 4);
        jPanel1.add(jPanelPreview, gridBagConstraints);

        jButton1.setText("Restore defaults");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        add(jPanel1, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(200, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 150));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Line width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 4, 4);
        jPanel2.add(jLabel1, gridBagConstraints);

        jSpinnerLineWidth.setMinimumSize(new java.awt.Dimension(120, 20));
        jSpinnerLineWidth.setPreferredSize(new java.awt.Dimension(120, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 8);
        jPanel2.add(jSpinnerLineWidth, gridBagConstraints);

        jLabel3.setText("Line Style");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 4, 4);
        jPanel2.add(jLabel3, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(120, 80));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(120, 80));

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 8);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jLabel2.setText("Line color");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(8, 4, 4, 4);
        jPanel2.add(jLabel2, gridBagConstraints);

        jPanelColorSelector.setBackground(new java.awt.Color(255, 255, 255));
        jPanelColorSelector.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.SystemColor.controlShadow));
        jPanelColorSelector.setMinimumSize(new java.awt.Dimension(50, 20));
        jPanelColorSelector.setPreferredSize(new java.awt.Dimension(120, 20));
        jPanelColorSelector.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 8);
        jPanel2.add(jPanelColorSelector, gridBagConstraints);

        jPanel4.setLayout(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 100;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (box == null) return;
        
        List<Side> borders =  new ArrayList<Side>();
        borders.addAll(selectionPanel.getSelectedBorders());
        
        if (borders.size() == 0)
        {
            box.setPen(null);
            borders.add( Side.TOP);
            borders.add(Side.BOTTOM);
            borders.add(Side.LEFT);
            borders.add(Side.RIGHT);
        }
        for (it.businesslogic.ireport.gui.box.BoxBorderSelectionPanel.Side s : borders)
        {
            switch (s)
            {
                case TOP:
                {
                    box.setTopPen(null);
                    break;
                }
                case LEFT:
                {
                    box.setLeftPen(null);
                    break;
                }
                case BOTTOM:
                {
                    box.setBottomPen(null);
                    break;
                }
                case RIGHT:
                {
                    box.setRightPen(null);
                    break;
                }
            }
        }
        
        selectionChanged(borders);
        getSelectionPanel().repaint();
        fireActionPerformed();
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelColorSelector;
    private javax.swing.JPanel jPanelPreview;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerLineWidth;
    // End of variables declaration//GEN-END:variables

    public

    BoxBorderSelectionPanel getSelectionPanel() {
        return selectionPanel;
    }

    public void setSelectionPanel(BoxBorderSelectionPanel selectionPanel) {
        this.selectionPanel = selectionPanel;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        
        boolean oldInit = isInit();
        setInit(true);
        this.box = box;
        setInit(oldInit);
        getSelectionPanel().clearSelection();
        getSelectionPanel().setBox(box);
        getSelectionPanel().repaint();
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
    
}
