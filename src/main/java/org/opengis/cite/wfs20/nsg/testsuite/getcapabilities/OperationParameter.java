package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.NamespaceBindings.withStandardBindings;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Tests if the capabilities populates the Parameters element of the Operation element as described in Section
 * 8.1.3.3.5. (see NSG WFS 2.0 Profile, 8.1.3.3.5 Operation Parameters, Table 18 : Operation Parameters).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OperationParameter extends BaseFixture {

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 29")
    public void operationParameterOutputFormat( ITestContext testContext )
                            throws XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        List<String> outputFormats = parseOutputFormats();
        System.out.println( outputFormats );
        assertTrue( outputFormats.contains( "application/gml+xml; version=3.2" ) );
    }

    private List<String> parseOutputFormats()
                            throws XPathExpressionException {
        List<String> outputFormats = new ArrayList<>();
        String xPathPerOperation = "ows:OperationsMetadata/ows:Operation[@name='DescribeFeatureType']/ows:Parameter[@name='outputFormat']/ows:AllowedValues/ows:Value";
        addOutputFormats( outputFormats, xPathPerOperation );

        String xPathCommon = "//ows:Parameter[@name='outputFormat']/ows:AllowedValues/ows:Value";
        addOutputFormats( outputFormats, xPathCommon );
        return outputFormats;
    }

    private void addOutputFormats( List<String> outputFormats, String xPath )
                            throws XPathExpressionException {
        Map<String, String> bindings = withStandardBindings().getAllBindings();
        NodeList outputFormatNodesDescribeFeatureType = XMLUtils.evaluateXPath( this.wfsMetadata, xPath, bindings );

        for ( int index = 0; index < outputFormatNodesDescribeFeatureType.getLength(); index++ ) {
            String outputFormat = outputFormatNodesDescribeFeatureType.item( index ).getTextContent();
            outputFormats.add( outputFormat );
        }
    }

}
