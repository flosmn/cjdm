/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.export;

/**
 * Interface for display width provider factory
 * A class that implements this interface will
 * get the display width provider object which
 * can be used to calculate display width of a
 * given string
 *
 * @author nandini_duggal
 */
public interface IDisplayWidthProviderFactory {

    /**
     * Returns display width provider object
     */
    public IDisplayWidthProvider createDisplayWidthProvider();
}