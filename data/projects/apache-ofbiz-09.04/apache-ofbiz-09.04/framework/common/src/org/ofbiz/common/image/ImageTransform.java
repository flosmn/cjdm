/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package org.ofbiz.common.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;

import javolution.util.FastMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilProperties;


/**
 * ImageTransform Class
 * <p>
 * Services to apply transformation to images
 */
public class ImageTransform {

    public static final String module = ImageTransform.class.getName();
    public static final String resource = "CommonErrorUiLabels";

    public ImageTransform() {
    }

    /**
     * getBufferedImage
     * <p>
     * Set a buffered image
     *
     * @param   context
     * @param   fileLocation    Full file Path or URL
     * @return                  URL images for all different size types
     * @throws  IOException Error prevents the document from being fully parsed
     * @throws  JDOMException Errors occur in parsing
     */
    public static  Map<String, Object> getBufferedImage(String fileLocation, Locale locale)
        throws IllegalArgumentException, IOException {

        /* VARIABLES */
        BufferedImage bufImg;
        Map<String, Object> result = FastMap.newInstance();

        /* BUFFERED IMAGE */
        try {
            bufImg = ImageIO.read(new File(fileLocation));
        } catch (IllegalArgumentException e) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.input_is_null", locale) + " : " + fileLocation + " ; " + e.toString();
            Debug.logError(errMsg, module);
            result.put("errorMessage", errMsg);
            return result;
        } catch (IOException e) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.error_occurs_during_reading", locale) + " : " + fileLocation + " ; " + e.toString();
            Debug.logError(errMsg, module);
            result.put("errorMessage", errMsg);
            return result;
        }

        result.put("responseMessage", "success");
        result.put("bufferedImage", bufImg);
        return result;

    }

    /**
     * scaleImage
     * <p>
     * scale original image related to the ImageProperties.xml dimensions
     *
     * @param   bufImg          Buffered image to scale
     * @param   imgHeight       Original image height
     * @param   imgwidth        Original image width
     * @param   dimensionMap    Image dimensions by size type
     * @param   sizeType        Size type to scale
     * @return                  New scaled buffered image
     */
    public static Map<String, Object> scaleImage(BufferedImage bufImg, double imgHeight, double imgWidth, Map<String, Map<String, String>> dimensionMap, String sizeType, Locale locale) {

        /* VARIABLES */
        BufferedImage bufNewImg;
        double defaultHeight, defaultWidth, scaleFactor;
        Map<String, Object> result = FastMap.newInstance();

        /* DIMENSIONS from ImageProperties */
        defaultHeight = Double.parseDouble(dimensionMap.get(sizeType).get("height").toString());
        defaultWidth = Double.parseDouble(dimensionMap.get(sizeType).get("width").toString());
        if (defaultHeight == 0.0 || defaultWidth == 0.0) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.one_default_dimension_is_null", locale) + " : defaultHeight = " + defaultHeight + " ; defaultWidth = " + defaultWidth;
            Debug.logError(errMsg, module);
            result.put("errorMessage", errMsg);
            return result;
        }

        /* SCALE FACTOR */
        // find the right Scale Factor related to the Image Dimensions
        if (imgHeight > imgWidth) {
            scaleFactor = defaultHeight / imgHeight;
            if (scaleFactor == 0.0) {
                String errMsg = UtilProperties.getMessage(resource, "ImageTransform.height_scale_factor_is_null", locale) + "  (defaultHeight = " + defaultHeight + "; imgHeight = " + imgHeight;
                Debug.logError(errMsg, module);
                result.put("errorMessage", errMsg);
                return result;
            }
            // get scaleFactor from the smallest width
            if (defaultWidth < (imgWidth * scaleFactor)) {
                scaleFactor = defaultWidth / imgWidth;
            }
        } else {
            scaleFactor = defaultWidth / imgWidth;
            if (scaleFactor == 0.0) {
                String errMsg = UtilProperties.getMessage(resource, "ImageTransform.width_scale_factor_is_null", locale) + "  (defaultWidth = " + defaultWidth + "; imgWidth = " + imgWidth;
                Debug.logError(errMsg, module);
                result.put("errorMessage", errMsg);
                return result;
            }
            // get scaleFactor from the smallest height
            if (defaultHeight < (imgHeight * scaleFactor)) {
                scaleFactor = defaultHeight / imgHeight;
            }
        }

        if (scaleFactor == 0.0) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.final_scale_factor_is_null", locale) + " = " + scaleFactor;
            Debug.logError(errMsg, module);
            result.put("errorMessage", errMsg);
            return result;
        }
        int bufImgType;
        if (BufferedImage.TYPE_CUSTOM == bufImg.getType()) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.unknown_buffered_image_type", locale);
            Debug.logWarning(errMsg, module);
            // apply a type for image majority
            bufImgType = BufferedImage.TYPE_INT_ARGB_PRE;
        } else {
            bufImgType = bufImg.getType();
        }

        bufNewImg = new BufferedImage( (int) (imgWidth * scaleFactor), (int) (imgHeight * scaleFactor), bufImgType);

        result.put("responseMessage", "success");
        result.put("bufferedImage", bufNewImg);
        result.put("scaleFactor", scaleFactor);
        return result;

    }

    /**
     * getXMLValue
     * <p>
     * From a XML element, get a values map
     *
     * @param fileFullPath      File path to parse
     * @return Map contains asked attribute values by attribute name
     */
    public static  Map<String, Object> getXMLValue(String fileFullPath, Locale locale)
        throws IllegalStateException, IOException, JDOMException {

        /* VARIABLES */
        Document document;
        Element rootElt;
        Map<String, Map<String, String>> valueMap = FastMap.newInstance();
        Map<String, Object> result = FastMap.newInstance();

        /* PARSING */
        SAXBuilder sxb = new SAXBuilder();
        try {
            // JDOM
            document = sxb.build(new File(fileFullPath));
        } catch (JDOMException e) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.errors_occured_during_parsing", locale) +  " ImageProperties.xml " + e.toString();
            Debug.logError(errMsg, module);
            result.put("errorMessage", "error");
            return result;
        } catch (IOException e) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.error_prevents_the document_from_being_fully_parsed", locale) + e.toString();
            Debug.logError(errMsg, module);
            result.put("errorMessage", "error");
            return result;
        }
        // set Root Element
        try {
            rootElt = document.getRootElement();
        } catch (IllegalStateException e) {
            String errMsg = UtilProperties.getMessage(resource, "ImageTransform.root_element_has_not_been_set", locale) + e.toString();
            Debug.logError(errMsg, module);
            result.put("errorMessage", "error");
            return result;
        }

        /* get NAME and VALUE */
        List<Element> children = rootElt.getChildren(); // FIXME : despite upgrading to jdom 1.1, it seems that getChildren is pre 1.5 java code (ie getChildren does not retun List<Element> but only List)
        for (Element currentElt : children) {
            Map<String, String> eltMap = FastMap.newInstance();
            if (currentElt.getContentSize() > 0) {
                Map<String, String> childMap = FastMap.newInstance();
                // loop over Children 1st level
                List<Element> children2 = currentElt.getChildren();
                for (Element currentChild : children2) {
                    childMap.put(currentChild.getAttributeValue("name"), currentChild.getAttributeValue("value"));
                }
                valueMap.put(currentElt.getAttributeValue("name"), childMap);
            } else {
                eltMap.put(currentElt.getAttributeValue("name"), currentElt.getAttributeValue("value"));
                valueMap.put(currentElt.getName(), eltMap);
            }
        }

        result.put("responseMessage", "success");
        result.put("xml", valueMap);
        return result;

    }
}
