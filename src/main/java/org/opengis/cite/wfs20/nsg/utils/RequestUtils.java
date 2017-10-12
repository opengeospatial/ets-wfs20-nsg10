package org.opengis.cite.wfs20.nsg.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RequestUtils {

    /**
     * Appends the attribute outputFormat with the specified value to the document element.
     * 
     * @param document
     *            to add the attribute, never <code>null</code>
     * @param outputFormat
     *            the value of the attribute, should not be <code>null</code>
     */
    public static void setOutputFormatAttribute( Document document, String outputFormat ) {
        Element docElem = document.getDocumentElement();
        docElem.setAttribute( "outputFormat", outputFormat );
    }

}
