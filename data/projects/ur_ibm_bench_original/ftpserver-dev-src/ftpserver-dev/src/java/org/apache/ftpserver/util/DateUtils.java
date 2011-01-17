// $Id: DateUtils.java 306725 2005-09-09 16:55:35 +0530 (Fri, 09 Sep 2005) rana_b $
/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ftpserver.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Standard date related utility methods.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public 
class DateUtils {
    
    private final static String[] MONTHS = {
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec"
    };
            
    /**
     * Get unix style date string.
     */
    public final static String getUnixDate(long millis) {
        if (millis < 0) {
            return "------------";
        }
        
        StringBuffer sb = new StringBuffer(16);
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(millis);
        
        // month
        sb.append( MONTHS[cal.get(Calendar.MONTH)] );
        sb.append(' ');
        
        // day
        int day = cal.get(Calendar.DATE);
        if(day < 10) {
            sb.append(' ');
        }
        sb.append(day);
        sb.append(' ');
        
        long sixMonth = 15811200000L; //183L * 24L * 60L * 60L * 1000L;
        long nowTime = System.currentTimeMillis();
        if( Math.abs(nowTime - millis) > sixMonth) {
            
            // year
            int year = cal.get(Calendar.YEAR);
            sb.append(' ');
            sb.append(year);
        }
        else {
            
            // hour
            int hh = cal.get(Calendar.HOUR_OF_DAY);
            if(hh < 10) {
                sb.append('0');
            }
            sb.append(hh);
            sb.append(':');
            
            // minute
            int mm = cal.get(Calendar.MINUTE);
            if(mm < 10) {
                sb.append('0');
            }
            sb.append(mm);
        }
        return sb.toString();
    }
    
    /**
     * Get ISO 8601 timestamp.
     */
    public final static String getISO8601Date(long millis) {
        StringBuffer sb = new StringBuffer(19);
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(millis);
        
        // year
        sb.append(cal.get(Calendar.YEAR));
        
        // month
        sb.append('-');
        int month = cal.get(Calendar.MONTH) + 1;
        if(month < 10) {
            sb.append('0');
        }
        sb.append(month);
        
        // date
        sb.append('-');
        int date = cal.get(Calendar.DATE);
        if(date < 10) {
            sb.append('0');
        }
        sb.append(date);
        
        // hour
        sb.append('T');
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(hour < 10) {
            sb.append('0');
        }
        sb.append(hour);
        
        // minute
        sb.append(':');
        int min = cal.get(Calendar.MINUTE);
        if(min < 10) {
            sb.append('0');
        }
        sb.append(min);
        
        // second
        sb.append(':');
        int sec = cal.get(Calendar.SECOND);
        if(sec < 10) {
            sb.append('0');
        }
        sb.append(sec);
        
        return sb.toString();
    } 
    
    /**
     * Get FTP date.
     */
    public final static String getFtpDate(long millis) {
        StringBuffer sb = new StringBuffer(20);
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(millis);
        
        // year
        sb.append(cal.get(Calendar.YEAR));
        
        // month
        int month = cal.get(Calendar.MONTH) + 1;
        if(month < 10) {
            sb.append('0');
        }
        sb.append(month);
        
        // date
        int date = cal.get(Calendar.DATE);
        if(date < 10) {
            sb.append('0');
        }
        sb.append(date);
        
        // hour
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(hour < 10) {
            sb.append('0');
        }
        sb.append(hour);
        
        // minute
        int min = cal.get(Calendar.MINUTE);
        if(min < 10) {
            sb.append('0');
        }
        sb.append(min);
        
        // second
        int sec = cal.get(Calendar.SECOND);
        if(sec < 10) {
            sb.append('0');
        }
        sb.append(sec);
        
        // millisecond
        sb.append('.');
        int milli = cal.get(Calendar.MILLISECOND);
        if(milli < 100) {
            sb.append('0');
        }
        if(milli < 10) {
            sb.append('0');
        }
        sb.append(milli);
        return sb.toString();
    }
}
