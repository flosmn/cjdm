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
 * BcImage.java
 * 
 * Created on 20. April 2004, 13:21
 *
 */

package it.businesslogic.ireport.barcode;

/**
 *
 * @author  Heiko
 */

import java.awt.image.*;
import net.sourceforge.barbecue.*;
import net.sourceforge.barbecue.linear.ean.UCCEAN128Barcode;

public class BcImage {
	private static net.sourceforge.barbecue.Barcode bc = null;
	
	public static net.sourceforge.barbecue.Barcode getBarcode() {
		return bc;
	}
	
        public static BufferedImage getBarcodeImage(int type, Object aText, boolean showText, boolean checkSum) {
            return getBarcodeImage(type, aText, showText, checkSum,"",0,0);
        }
        
	public static BufferedImage getBarcodeImage(int type, Object aText, boolean showText, boolean checkSum, String applicationIdentifier, int width, int height) {
		// 2of7, 3of9, Bookland, Codabar, Code128, Code128A, Code128B, Code128C, Code39, EAN128, EAN13, GlobalTradeItemNumber, Int2of5, Int2of5, Monarch, NW7, PDF417, SCC14ShippingCode, ShipmentIdentificationNumber, SSCC18, Std2of5, Std2of5, UCC128, UPCA, USD3, USD4, USPS
		
		String text = new StringBuffer().append(aText).toString();

		try {
			switch (type) {
                                case 0: return null;
                                case 1: bc = BarcodeFactory.create2of7(text); break;
				case 2: bc = BarcodeFactory.create3of9(text, checkSum);	break;
				case 3: bc = BarcodeFactory.createBookland(text); break;
				case 4: bc = BarcodeFactory.createCodabar(text); break;
				case 5: bc = BarcodeFactory.createCode128(text); break;
				case 6: bc = BarcodeFactory.createCode128A(text); break;
				case 7: bc = BarcodeFactory.createCode128B(text); break;
				case 8: bc = BarcodeFactory.createCode128C(text); break;
				case 9: bc = BarcodeFactory.createCode39(text, checkSum ); break;
				case 10: bc = BarcodeFactory.createEAN128(text); break;
				case 11: bc = BarcodeFactory.createEAN13(text);	break;
				case 12: bc = BarcodeFactory.createGlobalTradeItemNumber(text);	break;
				case 13: bc = BarcodeFactory.createInt2of5(text, checkSum); break;
				case 14: bc = BarcodeFactory.createMonarch(text); break;
				case 15: bc = BarcodeFactory.createNW7(text); break;
				case 16: bc = BarcodeFactory.createPDF417(text); break;
				case 17: bc = BarcodeFactory.createSCC14ShippingCode(text); break;
				case 18: bc = BarcodeFactory.createShipmentIdentificationNumber(text); break;
				case 19: bc = new UCCEAN128Barcode(UCCEAN128Barcode.SSCC_18_AI, text, checkSum); break; //BarcodeFactory.createSSCC18(text); break;
				case 20: bc = BarcodeFactory.createStd2of5(text, checkSum); break;
				case 21: bc = new UCCEAN128Barcode(applicationIdentifier, text, checkSum); break; //BarcodeFactory.createUCC128(applicationIdentifier, text); break;
				case 22: bc = BarcodeFactory.createUPCA(text); break;
				case 23: bc = BarcodeFactory.createUSD3(text, checkSum); break;
				case 24: bc = BarcodeFactory.createUSD4(text); break;
				case 25: bc = BarcodeFactory.createUSPS(text); break;
                                case 26: bc = new net.sourceforge.barbecue.linear.code39.Code39Barcode(text, checkSum, true);  break;
			}
			
                        if (width > 0) bc.setBarWidth(width);
                        if (height > 0) bc.setBarHeight(height);
			bc.setDrawingText(showText);
                        return net.sourceforge.barbecue.BarcodeImageHandler.getImage(bc);
		}
		catch (Exception e) {
			e.printStackTrace();
			//generate a runtime exception, invalid value passed. 
			//the user must be notified if fail 
			throw new RuntimeException(e.getMessage());
			//return null;
		}
	}
}


