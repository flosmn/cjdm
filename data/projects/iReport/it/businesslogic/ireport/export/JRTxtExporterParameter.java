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
 * JRTxtExporterParameter.java
 * 
 * Created on 16 aprile 2004, 10.28
 *
 */

package it.businesslogic.ireport.export;

import net.sf.jasperreports.engine.JRExporterParameter;

/**
 *
 * @author  Giulio Toffoli
 */
public class JRTxtExporterParameter extends JRExporterParameter
{
	/**
	 *
	 */
	protected JRTxtExporterParameter(String name)
	{
		super(name);
                
	}

	public static final JRTxtExporterParameter PAGE_ROWS = new JRTxtExporterParameter("Rows per page");
        public static final JRTxtExporterParameter PAGE_COLUMNS = new JRTxtExporterParameter("Columns per page");  
        public static final JRTxtExporterParameter ADD_FORM_FEED = new JRTxtExporterParameter("Add FORM FEED");
        /*
         * Parameter for Display Width Provider class
        */
        public static final JRTxtExporterParameter DISPLAY_WIDTH_PROVIDER_FACTORY = new JRTxtExporterParameter("Display Width Provider Factory");
}
