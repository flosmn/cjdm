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
 * Pie3DChart.java
 * 
 * Created on 8 luglio 2005, 17.59
 *
 */

package it.businesslogic.ireport.chart;

/**
 *
 * @author Administrator
 */
public class Pie3DChart extends Chart {
    
    /** Creates a new instance of PieChart */
    public Pie3DChart() {
        
        setName("Pie 3D");
        setChartImage(new javax.swing.ImageIcon( getClass().getResource("/it/businesslogic/ireport/icons/charts/pie3d_big.png")).getImage());
        setDataset( new PieDataset());
        setPlot( new Pie3DPlot());
    }    
    
    public Chart cloneBaseChart()
    {
        return new Pie3DChart();
    }
}
