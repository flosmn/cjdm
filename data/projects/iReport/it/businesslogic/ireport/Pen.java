/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport;

import java.awt.Color;

/**
 *
 * @author gtoffoli
 */
public class Pen {

    private float lineWidth = 0f;
    private String lineStyle = null; //"Solid";
    private Color lineColor = null;

    public

    float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getLineStyle() {
        return lineStyle;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    
    public Pen cloneMe()
    {
        Pen pen = new Pen();
        pen.setLineColor( getLineColor());
        pen.setLineStyle( getLineStyle());
        pen.setLineWidth( getLineWidth());
        return pen;
    }
    
    public String toString()
    {
        return getLineWidth() + " " + getLineStyle() + " " + getLineColor();
    }
    
}
