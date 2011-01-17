/*
 * Copyright (C) 2005 - 2008 JasperSoft Corporation.  All rights reserved. 
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * JRPropertyDialog.java
 * 
 * Created on 9 maggio 2003, 17.25
 *
 */

package it.businesslogic.ireport.gui;

import it.businesslogic.ireport.util.*;
import it.businesslogic.ireport.*;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
/**
 *
 * @author  Administrator
 */
public class JRPropertyDialog extends javax.swing.JDialog {
    /** Creates new form JRParameterDialog */
    JRProperty tmpField = null;
    JRTextExpressionArea expressionArea = null;
    
    
    public JRPropertyDialog(java.awt.Frame parent, boolean modal) { 
        this(parent, modal, false);
    }
    public JRPropertyDialog(java.awt.Frame parent, boolean modal, boolean canUseExpression) {        
        super(parent, modal);
        initComponents();
        applyI18n();
        
        jCheckBox1.setSelected(false);
        jCheckBox1.setVisible(canUseExpression);
        expressionArea = new JRTextExpressionArea();
        
        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCancelActionPerformed(e);
            }
        };
       
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);


        jList1.setModel( new DefaultListModel());
        jList1.setCellRenderer(new PropertyHintListCellRenderer());
        addHints();
        
        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonOK);
    }

    private void addHints() {
        
        DefaultListModel dlm = (DefaultListModel)jList1.getModel();
        dlm.addElement(new PropertyHint("net.sf.jasperreports.text.truncate.at.char",
                "Applied to text elements.<br>Setting this property to true, the text contents is truncated after the last character that fits the element area.<br>When the text element is rendered on multiple lines, line breaks still occur at word boundaries.<br>It defaults to false."
                ));
        
        dlm.addElement(new PropertyHint("net.sf.jasperreports.text.truncate.suffix",
                "Applied to text elements.<br>Use this property to define a suffix to append to the text contents when it is truncated, after the last character that fits the element area.<br>When the text element is rendered on multiple lines, line breaks still occur at word boundaries.\nIt defaults to the empty string."
                ));
        
        dlm.addElement(new PropertyHint("net.sf.jasperreports.print.keep.full.text",
                "Applied to text elements.<br>This property is used to preserve the entire content of a text element  so that it's used in data-centric exporters.<br>It defaults to false."
                ));
        
        dlm.addElement(new PropertyHint("net.sf.jasperreports.text.measurer.factory",
                "\nApplied to text elements.<br>This property can be set to use custom text measurer implementation for all or specific text elements.<br>It defaults to net.sf.jasperreports.engine.fill.TextMeasurerFactory."
                ));
        
    }
    
    public void addExporterHints()
    {
        DefaultListModel dlm = (DefaultListModel)jList1.getModel();
        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.character.encoding",
        "Default: UTF-8"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.graphics2d.min.job.size",
        "Used by the Graphics2D exporter<br>" +
        "Default value: true"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.html.frames.as.nested.tables",
        "Used by the HTML exporter<br>" +
        "Default value: true"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.html.remove.empty.space.between.rows",
        "Used by the HTML exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.html.white.page.background",
        "Used by the HTML exporter<br>" +
        "Default value: true"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.html.wrap.break.word",
        "Used by the HTML exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.html.size.unit",
        "Used by the HTML exporter<br>" +
        "Default value: px"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.html.using.images.to.align",
        "Used by the HTML exporter<br>" +
        "Default value: true"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.pdf.force.svg.shapes",
        "Used by the PDF exporter<br>" +
        "Default value: true"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.pdf.force.linebreak.policy",
        "Used by the PDF exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.pdf.create.batch.mode.bookmarks",
        "Used by the PDF exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.pdf.compressed",
        "Used by the PDF exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.pdf.encrypted",
        "Used by the PDF exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.pdf.128.bit.key",
        "Used by the PDF exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.create.custom.palette",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.one.page.per.sheet",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.remove.empty.space.between.rows",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.remove.empty.space.between.columns",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.white.page.background",
        "Used by the XLS exporter<br>" +
        "Default value: true"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.detect.cell.type",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.size.fix.enabled",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.ignore.graphics",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.collapse.row.span",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.ignore.cell.border",
        "Used by the XLS exporter<br>" +
        "Default value: false"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xls.max.rows.per.sheet",
        "Used by the XLS exporter<br>" +
        "Default value: 0"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.xml.validation",
        "Used by the XML exporter<br>" +
        "Default value: true"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.csv.field.delimiter",
        "Used by the CSV exporter<br>" +
        "Default value: ,"));

        dlm.addElement(new PropertyHint("net.sf.jasperreports.export.csv.record.delimiter",
        "Used by the CSV exporter<br>" +
        "Default value: \n (New line)"));
    }
    
  
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanelExpression = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setTitle("Add/modify property");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Property name");
        jLabel1.setMaximumSize(new java.awt.Dimension(1000, 100));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 15));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jTextFieldName, gridBagConstraints);

        jCheckBox1.setText("Use an expression");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(jCheckBox1, gridBagConstraints);

        jLabel4.setText("Property value");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jLabel4, gridBagConstraints);

        jPanelExpression.setMinimumSize(new java.awt.Dimension(10, 50));
        jPanelExpression.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 40));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 40));

        jTextAreaDescription.setMinimumSize(new java.awt.Dimension(0, 64));
        jTextAreaDescription.setPreferredSize(new java.awt.Dimension(0, 64));
        jScrollPane1.setViewportView(jTextAreaDescription);

        jPanelExpression.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(jPanelExpression, gridBagConstraints);

        jLabel5.setText("Special meaning properties");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
        getContentPane().add(jLabel5, gridBagConstraints);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 200));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 4);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 35));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 35));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonOK.setMnemonic('o');
        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonOK);

        jButtonCancel.setMnemonic('c');
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCancel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-515)/2, (screenSize.height-358)/2, 515, 358);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        setVisible(false);
        this.setDialogResult( javax.swing.JOptionPane.CANCEL_OPTION);
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        
        if (this.jTextFieldName.getText().trim().length() <= 0)
        {
            javax.swing.JOptionPane.showMessageDialog(this,
                    I18n.getString( "messages.jRPropertyDialog.notValidName","Please insert a valid property name!"),
                    I18n.getString( "messages.jRPropertyDialog.notValidNameCaption","Invalid property!"),
                    javax.swing.JOptionPane.WARNING_MESSAGE );
            return;
        }
        
        tmpField = new JRProperty();
        tmpField.setName( jTextFieldName.getText().trim() );
        tmpField.setExpression( jCheckBox1.isSelected() );
        
        if (jCheckBox1.isSelected())
        {
            tmpField.setValue( this.expressionArea.getText() );
        }
        else
        {
            tmpField.setValue( this.jTextAreaDescription.getText() );
        }
        
        setVisible(false);
        this.setDialogResult( javax.swing.JOptionPane.OK_OPTION);
        dispose();
    }//GEN-LAST:event_jButtonOKActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        this.setDialogResult( javax.swing.JOptionPane.CLOSED_OPTION);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        
        if (evt.getClickCount() == 2 &&
            SwingUtilities.isLeftMouseButton(evt))
        {
            if (jList1.getSelectedValue() != null &&
                jList1.getSelectedValue() instanceof PropertyHint)
            {
                PropertyHint hint = (PropertyHint)jList1.getSelectedValue();
                jTextFieldName.setText( hint.getPropertyName());
            }
        }
        
        
    }//GEN-LAST:event_jList1MouseClicked

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        
        updateExpressionPanel();
        
    }//GEN-LAST:event_jCheckBox1StateChanged
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new JRPropertyDialog(new javax.swing.JFrame(), true).setVisible(true);
    }
    
    /** Getter for property tmpParameter.
     * @return Value of property tmpParameter.
     *
     */
    public it.businesslogic.ireport.JRProperty getProperty() {
        return tmpField;
    }    
    
    /** Setter for property tmpParameter.
     * @param tmpParameter New value of property tmpParameter.
     *
     */
    public void setProperty(it.businesslogic.ireport.JRProperty tmpField) {
        this.jTextFieldName.setText( new String(tmpField.getName()));
        this.jTextAreaDescription.setText( new String(tmpField.getValue())); 
        this.expressionArea.setText( new String(tmpField.getValue())); 
        System.out.println("Checkbox: " + tmpField.isExpression());
        this.jCheckBox1.setSelected( tmpField.isExpression() );
        updateExpressionPanel();
    }
    
    /** Getter for property dialogResult.
     * @return Value of property dialogResult.
     *
     */
    public int getDialogResult() {
        return dialogResult;
    }
    
    /** Setter for property dialogResult.
     * @param dialogResult New value of property dialogResult.
     *
     */
    public void setDialogResult(int dialogResult) {
        this.dialogResult = dialogResult;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelExpression;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables

    private int dialogResult;    
    
    public void applyI18n(){
                // Start autogenerated code ----------------------
                jButtonCancel.setText(I18n.getString("jRPropertyDialog.buttonCancel","Cancel"));
                jButtonOK.setText(I18n.getString("jRPropertyDialog.buttonOK","OK"));
                jLabel1.setText(I18n.getString("jRPropertyDialog.label1","Property name"));
                jLabel4.setText(I18n.getString("jRPropertyDialog.label4","Property value"));
                // End autogenerated code ----------------------
                
                this.setTitle(I18n.getString("jRPropertyDialog.title","Add/modify property"));
                jButtonCancel.setMnemonic(I18n.getString("jRPropertyDialog.buttonCancelMnemonic","c").charAt(0));
                jButtonOK.setMnemonic(I18n.getString("jRPropertyDialog.buttonOKMnemonic","o").charAt(0));
    }

    private void updateExpressionPanel() {
        jPanelExpression.removeAll();
        
        if (jCheckBox1.isSelected())
        {
            jPanelExpression.add(expressionArea, BorderLayout.CENTER);
            if (jScrollPane1.getBorder() != null)
            {
                jPanelExpression.setBorder( jScrollPane1.getBorder() );
            }
        }
        else
        {
            jPanelExpression.add(jScrollPane1, BorderLayout.CENTER);
            jPanelExpression.setBorder( null );
        }
        jPanelExpression.updateUI();
    }
}
