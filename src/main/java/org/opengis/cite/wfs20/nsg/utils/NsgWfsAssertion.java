package org.opengis.cite.wfs20.nsg.utils;

import static javax.xml.xpath.XPathConstants.BOOLEAN;
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

    private static final String XPATH_OPERATION_SUPPORTED = "//ows:OperationsMetadata/ows:Operation[@name='%s']";

    private static final String XPATH_PARAM_PER_OPERATION = "//ows:OperationsMetadata/ows:Operation[@name='%s']/ows:Parameter[@name='%s']/ows:AllowedValues/ows:Value";

    private static final String XPATH_PARAM_GLOBAL = "//ows:OperationsMetadata/ows:Parameter[@name='%s']/ows:AllowedValues/ows:Value";

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
     *            the document to parse, never <code>null</code>
     * @param operation
     *            the operation to parse the outputFormat for, never <code>null</code>
     * @throws XPathExpressionException
     *             if an expression could not be evaluated (should never happen)
     */
    public static void assertOutputFormat( Document wfsMetadata, String operation )
                            throws XPathExpressionException {
        List<String> outputFormats = parseParameters( wfsMetadata, operation, "outputFormat" );

        String msg = String.format( "The operation %s is not supported or does not support the outputFormat application/gml+xml; version=3.2",
                                    operation );
        assertTrue( outputFormats.contains( "application/gml+xml; version=3.2" ), msg );
    }

    /**
     * Parases the parameter values of a passed parameter name (per operation and global).
     *
     * @param wfsMetadata
     *            the capabilities, never <code>null</code>
     * @param operationName
     *            the name of the operation
     * @param parameterName
     *            the name of the parameter
     * @return all parameter values, never <code>null</code>
     * @throws XPathExpressionException
     *             if an expression could not be evaluated (should never happen)
     */
    public static List<String> parseParameters( Document wfsMetadata, String operationName, String parameterName )
                            throws XPathExpressionException {
        List<String> outputFormats = new ArrayList<>();
        if ( operationExists( wfsMetadata, operationName ) ) {
            String xPathPerOperation = String.format( XPATH_PARAM_PER_OPERATION, operationName, parameterName );
            addOutputFormats( wfsMetadata, outputFormats, xPathPerOperation );

            String xPathCommon = String.format( XPATH_PARAM_GLOBAL, parameterName );
            addOutputFormats( wfsMetadata, outputFormats, xPathCommon );
        }
        return outputFormats;
    }

    private static boolean operationExists( Document wfsMetadata, String operationName ) {
        try {
            String xPathOperation = String.format( XPATH_OPERATION_SUPPORTED, operationName, operationName );
            Map<String, String> bindings = withStandardBindings().getAllBindings();
            return (Boolean) XMLUtils.evaluateXPath( wfsMetadata, xPathOperation, bindings, BOOLEAN );
        } catch ( XPathExpressionException e ) {
        }
        return false;
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
