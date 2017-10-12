package org.opengis.cite.wfs20.nsg.utils;

import static org.opengis.cite.iso19142.util.NamespaceBindings.withStandardBindings;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.util.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class NsgWfsAssertion {

    private static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";

    private static final String GUIDE_ID_PATTERN = "^guide://[0-9a-z]*/[0-9a-z]*";

    /**
     * Validates if the passed id is not null and a valid UUID or GUIDE ID identifier. The format of the GUIDE id is as
     * follows: guide://{prefix}/{suffix}
     * 
     * @param id
     *            the id to validate
     */
    public static void assertUuidOrGuideId( String id ) {
        assertNotNull( id, "Identifier must not be null" );

        boolean idUuid = Pattern.matches( UUID_PATTERN, id );
        boolean isGuideId = Pattern.matches( GUIDE_ID_PATTERN, id );
        assertTrue( idUuid || isGuideId, "Identifier must be a valid UUID or GUIDE ID" );
    }

    /**
     * Asserts if the passed uri is not null and not empty.
     * 
     * @param uri
     *            to validate
     * @param message
     *            used as assertion message
     */
    public static void assertUri( URI uri, String message ) {
        assertNotNull( uri, message );
        assertNotEquals( URI.create( "" ), uri, message );
    }

    /**
     * Asserts if the passed WFS capabilities contains the outputFormat 'application/gml+xml; version=3.2' for the
     * specified operation.
     * 
     * @param wfsMetadata
     */
    public static void assertOutputFormat( Document wfsMetadata, String operation )
                            throws XPathExpressionException {
        List<String> outputFormats = parseOutputFormats( wfsMetadata, operation );

        assertTrue( outputFormats.contains( "application/gml+xml; version=3.2" ) );
    }

    private static List<String> parseOutputFormats( Document wfsMetadata, String operation )
                            throws XPathExpressionException {
        List<String> outputFormats = new ArrayList<>();
        String xPathPerOperation = "//ows:OperationsMetadata/ows:Operation[@name='" + operation
                                   + "']/ows:Parameter[@name='outputFormat']/ows:AllowedValues/ows:Value";
        addOutputFormats( wfsMetadata, outputFormats, xPathPerOperation );

        String xPathCommon = "//ows:OperationsMetadata/ows:Parameter[@name='outputFormat']/ows:AllowedValues/ows:Value";
        addOutputFormats( wfsMetadata, outputFormats, xPathCommon );
        return outputFormats;
    }

    private static void addOutputFormats( Document wfsMetadata, List<String> outputFormats, String xPath )
                            throws XPathExpressionException {
        Map<String, String> bindings = withStandardBindings().getAllBindings();
        NodeList outputFormatNodesDescribeFeatureType = XMLUtils.evaluateXPath( wfsMetadata, xPath, bindings );

        for ( int index = 0; index < outputFormatNodesDescribeFeatureType.getLength(); index++ ) {
            String outputFormat = outputFormatNodesDescribeFeatureType.item( index ).getTextContent();
            outputFormats.add( outputFormat );
        }
    }

}
