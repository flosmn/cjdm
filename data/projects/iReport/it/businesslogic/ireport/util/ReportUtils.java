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
 * ReportUtils.java
 * 
 */

package it.businesslogic.ireport.util;

import java.util.*;


public class ReportUtils {
	
	/**
		Esempio di utilizzo:
		
		encodeParameters($P{REPORT_MAP},
				new String[]{
				   "REPORT_ID=107",
				   "02_Area_Manager_ID=" + $F{Manager_ID},
				   "PIPPO=Pluto"
				});	
		
		Ii parametri2 hanno precedenza su parametri1
	
	*/
	public static String encodeParameters(Map parametri1, String[] parametri2)
	{
		String url = "";
		HashMap param_map = new HashMap();
		if (parametri1 == null) parametri1 = new HashMap();
		if (parametri2 == null) parametri2 = new String[]{};
		
		param_map.putAll( parametri1 );
		
		for (int i=0; i<parametri2.length; ++i)
		{
			
			if (parametri2[i].indexOf("=") > 0)
			{	
				String key = parametri2[i].substring(0, parametri2[i].indexOf("="));
				String val = parametri2[i].substring(parametri2[i].indexOf("=")+1);
				
				parametri1.put(key, val);				
			}
		}
		
		Set keys = parametri1.keySet();
		Iterator params_iterator = keys.iterator();
		while (params_iterator.hasNext())
		{
			try {
				String key = (String)params_iterator.next();
				Object val = (Object)parametri1.get(key);
			
				if (url.length() > 0) url += "&";
			
				url += java.net.URLEncoder.encode(key,"UTF-8") + "=" + java.net.URLEncoder.encode(""+val,"UTF-8");
			
			} catch (Exception ex)
			{}
		}
		
		return url;
	}
}
