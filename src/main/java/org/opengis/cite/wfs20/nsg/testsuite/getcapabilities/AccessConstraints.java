package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static javax.xml.xpath.XPathConstants.STRING;
import static org.opengis.cite.iso19142.ETSAssert.assertXPath;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.opengis.cite.wfs20.nsg.utils.NamespaceUtils;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Tests if the service contains the expected AccessConstraints
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AccessConstraints extends BaseFixture {
    private static final Map<String, String> NAMESPACE_BINIDNGS = NamespaceUtils.withStandardBindings().getAllBindings();

    private static final List<String> EXPECTED_ACCESS_CONSTRAINTS = Arrays.asList( "TOP SECRET", "SECRET",
                                                                                   "CONFIDENTIAL", "UNCLASSIFIED" );

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 25")
    public void wfsCapabilitiesAccessConstraintsExists( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        String xPathXml = "//ows:ServiceIdentification/ows:AccessConstraints  != ''";

        assertXPath( xPathXml, this.wfsMetadata, NAMESPACE_BINIDNGS );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 25", dependsOnMethods = "wfsCapabilitiesAccessConstraintsExists")
    public void wfsCapabilitiesAccessConstraintsContainsValueFromISM( ITestContext testContext )
                            throws XPathFactoryConfigurationException, XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        String accessConstraints = parseAccessConstraints( this.wfsMetadata );
        assertTrue( EXPECTED_ACCESS_CONSTRAINTS.contains( accessConstraints ),
                    "AccessConstraints are not valid, must be one of " + EXPECTED_ACCESS_CONSTRAINTS + " but was "
                                            + accessConstraints );
    }

    private String parseAccessConstraints( Document wfsCapabilities )
                            throws XPathFactoryConfigurationException, XPathExpressionException {
        String xPathAccessConstraints = "//ows:ServiceIdentification/ows:AccessConstraints ";
        return (String) XMLUtils.evaluateXPath( wfsCapabilities, xPathAccessConstraints, NAMESPACE_BINIDNGS, STRING );
    }

}
