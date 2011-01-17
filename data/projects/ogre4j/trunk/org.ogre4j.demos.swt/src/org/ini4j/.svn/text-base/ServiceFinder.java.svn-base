/*
 * Copyright 2005 [ini4j] Development Team
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

package org.ini4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * JDK JAR Services API alapú service keresõ osztály.
 *
 * @author Szkiba Iván
 * @version $Name: v0_2_6 $
 */
class ServiceFinder
{
    /**
     * Service osztály nevének keresése
     *
     * a JDK JAR specifikációban definiált <B>Services API</B>-nak
     * megfelelõen service osztály keresés.</p><p>
     * Az implementáló osztály név keresése a <CODE>serviceId</CODE> nevû
     * system property vizsgálatával kezdõdik. Amennyiben nincs ilyen
     * property, úgy a keresés a
     * <CODE>/META-INF/services/<I>serviceId</I></CODE> nevû file tartalmával
     * folytatódik. Amennyiben nincs ilyen nevû file, úgy a paraméterként átadott
     * <CODE>defaultService</CODE> lesz az osztály neve.</p><p>
     * @param serviceId keresett osztály/service neve
     * @param defaultService alapértelmezett implementáló osztály neve
     * @throws IllegalArgumentException keresési vagy példányosítási hiba esetén
     * @return a keresett osztály neve
     */
    protected static String findServiceClassName(String serviceId, String defaultService)
                                       throws IllegalArgumentException
    {
        if (defaultService == null)
        {
            throw new IllegalArgumentException("Provider for " + serviceId + " cannot be found");
        }

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String serviceClassName = null;

        // Use the system property first
        try
        {
            String systemProp = System.getProperty(serviceId);

            if (systemProp != null)
            {
                serviceClassName = systemProp;
            }
        }
        catch (SecurityException x)
        {
            ;
        }

        if (serviceClassName == null)
        {
            String servicePath = "META-INF/services/" + serviceId;

            // try to find services in CLASSPATH
            try
            {
                InputStream is = null;

                if (classLoader == null)
                {
                    is = ClassLoader.getSystemResourceAsStream(servicePath);
                }
                else
                {
                    is = classLoader.getResourceAsStream(servicePath);
                }

                if (is != null)
                {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                    String line = rd.readLine();
                    rd.close();

                    if ((line != null) && !"".equals(line = line.trim()) )
                    {
                        serviceClassName = line.split("\\s|#")[0];
                    }
                }
            }
            catch (Exception x)
            {
                ;
            }
        }

        if (serviceClassName == null)
        {
            serviceClassName = defaultService;
        }

        return serviceClassName;
    }

    /**
     * Service osztály keresés
     *
     * a JDK JAR specifikációban definiált <B>Services API</B>-nak
     * megfelelõen service osztály keresés.</p><p>
     * Az implementáló osztály név keresése a <CODE>serviceId</CODE> nevû
     * system property vizsgálatával kezdõdik. Amennyiben nincs ilyen
     * property, úgy a keresés a
     * <CODE>/META-INF/services/<I>serviceId</I></CODE> nevû file tartalmával
     * folytatódik. Amennyiben nincs ilyen nevû file, úgy a paraméterként átadott
     * <CODE>defaultService</CODE> lesz az osztály neve.</p><p>
     * @param serviceId keresett osztály/service neve
     * @param defaultService alapértelmezett implementáló osztály neve
     * @throws IllegalArgumentException keresési vagy példányosítási hiba esetén
     * @return a keresett osztály objektum
     */
    protected static Class findServiceClass(String serviceId, String defaultService)
                                  throws IllegalArgumentException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String serviceClassName = findServiceClassName(serviceId, defaultService);

        try
        {
            return (classLoader == null) ? Class.forName(serviceClassName) : classLoader.loadClass(serviceClassName);
        }
        catch (ClassNotFoundException x)
        {
            throw (IllegalArgumentException) new IllegalArgumentException("Provider " + serviceClassName + " not found").initCause(x);
        }
    }

    /**
     * Service objektum keresés és példányosítás
     *
     * a JDK JAR specifikációban definiált <B>Services API</B>-nak
     * megfelelõen service osztály keresés, majd pedig példány képzés a context
     * ClassLoader segítségével.</p><p>
     * Az implementáló osztály név keresése a <CODE>serviceId</CODE> nevû
     * system property vizsgálatával kezdõdik. Amennyiben nincs ilyen
     * property, úgy a keresés a
     * <CODE>/META-INF/services/<I>serviceId</I></CODE> nevû file tartalmával
     * folytatódik. Amennyiben nincs ilyen nevû file, úgy a paraméterként átadott
     * <CODE>defaultService</CODE> lesz az osztály neve.</p><p>
     * A fenti keresést követõen történik a példány képzés. A visszatérési
     * érték mindig egy valódi objektum, lévén minden hiba exception-t generál.
     * @param serviceId keresett osztály/service neve
     * @param defaultService alapértelmezett implementáló osztály neve
     * @throws IllegalArgumentException keresési vagy példányosítási hiba esetén
     * @return a keresett osztály implementáló objektum
     */
    protected static Object findService(String serviceId, String defaultService)
                              throws IllegalArgumentException
    {
        try
        {
            return findServiceClass(serviceId, defaultService).newInstance();
        }
        catch (Exception x)
        {
            throw (IllegalArgumentException) new IllegalArgumentException("Provider " + serviceId + " could not be instantiated: " + x).initCause(x);
        }
    }
}
