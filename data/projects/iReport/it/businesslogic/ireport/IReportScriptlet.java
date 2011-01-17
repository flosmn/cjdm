/* ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2001-2007 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This class is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This class is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this class; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 *
 *
 *
 * IReportScriptlet.java
 * 
 * Created on 26 settembre 2004, 16.25
 *
 */

package it.businesslogic.ireport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRScriptletException;

import java.util.*;
/**
 *
 * @author  Administrator
 */
public class IReportScriptlet extends net.sf.jasperreports.engine.JRAbstractScriptlet
{
        // Vector of things to collect...
        HashMap series = new java.util.HashMap();
        HashMap groupStarted = new java.util.HashMap();
        
	/**
	 *
	 */
	public void beforeReportInit() throws JRScriptletException
	{
        }


	/**
	 *
	 */
	public void afterReportInit() throws JRScriptletException
	{
               
        }


	/**
	 *
	 */
	public void beforePageInit() throws JRScriptletException
	{

	}


	/**
	 *
	 */
	public void afterPageInit() throws JRScriptletException
	{
	}


	/**
	 *
	 */
	public void beforeColumnInit() throws JRScriptletException
	{
	}


	/**
	 *
	 */
	public void afterColumnInit() throws JRScriptletException
	{
	}


	/**
	 *
	 */
	public void beforeGroupInit(String groupName) throws JRScriptletException
	{
            
	}


	/**
	 *
	 */
	public void afterGroupInit(String groupName) throws JRScriptletException
	{
           resetSeries(groupName);
        }


	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException
	{
	}


	/**
	 *
	 */
	public void afterDetailEval() throws JRScriptletException
	{
            processSeries();
	}
        
        protected void processSeries()
        {
            // Looking for serie_to_calc in variables...
            Set vars = variablesMap.keySet();
            Iterator iter = vars.iterator();
            while( iter.hasNext())
            {
                String key = (String)iter.next();
                if (key.startsWith("SERIE_"))
                {
                    Vector serie = (Vector)series.get(key);
                    if (serie == null)
                    {
                        serie = new Vector();
                        series.put(key,serie);
                    }
                    try {
                        serie.add( getVariableValue(key) );
                    } catch (Exception ex) {}
                }
            }
        }
        
        protected void resetSeries(String group)
        {
            // Looking for serie_to_calc in variables...
            Set vars = variablesMap.keySet();
            Iterator iter = vars.iterator();
            while( iter.hasNext())
            {
                String key = (String)iter.next();
                if (key.startsWith("SERIE_") && key.indexOf("G_" + group)> 0)
                {
                    series.remove(key);
                }
            }
        }
        
        public String getSerieAsString(String name)
        {
            Vector v =  (Vector)series.get(name);
            Enumeration enum_v = v.elements();
            String tot = "";
            while (enum_v.hasMoreElements())
            {
                String s = ""+enum_v.nextElement();
                tot += s + "\n";
            }
            
            return tot;
        }
        
        public Vector getSerie(String serieName)
        {
            
            Vector v = (Vector)series.get(serieName);
            if (v==null)
            {
                v = new Vector();
                series.put(serieName, v);
                
            }
            
            return v;
        }    
        
        
        public Boolean addValueToSerie(String serieName, Object value)
        {
            Vector v = getSerie(serieName);
            v.add( value );
            return new Boolean(false);
        }
        
        public Boolean resetSerie(String serieName)
        {
            series.remove(serieName);
            return new Boolean(false);
        }
}
