/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.gui.sheet;

import it.businesslogic.ireport.gui.box.LineStyleListCellRenderer;
import javax.swing.JComboBox;

/**
 *
 * @author gtoffoli
 */
public class LineComboBoxSheetProperty extends ComboBoxSheetProperty {

    public LineComboBoxSheetProperty(String key, String name) {
        super( key, name);
        ((JComboBox)getEditor()).setRenderer(new LineStyleListCellRenderer());
        ((JComboBox)getEditor()).addItem(null); 
        ((JComboBox)getEditor()).addItem("Solid"); 
        ((JComboBox)getEditor()).addItem("Dashed");
        ((JComboBox)getEditor()).addItem("Dotted");
        ((JComboBox)getEditor()).addItem("Double");
    }
}
