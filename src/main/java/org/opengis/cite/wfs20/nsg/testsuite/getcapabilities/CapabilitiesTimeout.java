package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.parseParameters;
import static org.testng.Assert.assertFalse;

import java.util.List;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.opengis.cite.iso19142.BaseFixture;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Contains tests for the timeout constraint in the capabilities.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CapabilitiesTimeout extends BaseFixture {

    private static final String TIMEOUT_PARAMETER_NAME = "Timeout";

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 27")
    public void capabilitiesContainsTimeoutForGetPropertyValue( ITestContext testContext )
                            throws XPathFactoryConfigurationException, XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        List<String> parameters = parseParameters( this.wfsMetadata, "GetPropertyValue", TIMEOUT_PARAMETER_NAME );
        assertTimeoutIsInteger( "GetPropertyValue", parameters );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 27")
    public void capabilitiesContainsTimeoutForGetFeature( ITestContext testContext )
                            throws XPathFactoryConfigurationException, XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        List<String> parameters = parseParameters( this.wfsMetadata, "GetFeature", TIMEOUT_PARAMETER_NAME );
        assertTimeoutIsInteger( "GetFeature", parameters );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 27")
    public void capabilitiesContainsTimeoutForPageResults( ITestContext testContext )
                            throws XPathFactoryConfigurationException, XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        List<String> parameters = parseParameters( this.wfsMetadata, "PageResults", TIMEOUT_PARAMETER_NAME );
        assertTimeoutIsInteger( "PageResults", parameters );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 27")
    public void capabilitiesContainsTimeoutForGetFeatureWithLock( ITestContext testContext )
                            throws XPathFactoryConfigurationException, XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        List<String> parameters = parseParameters( this.wfsMetadata, "GetFeatureWithLock", TIMEOUT_PARAMETER_NAME );
        assertTimeoutIsInteger( "GetFeatureWithLock", parameters );
    }

    private void assertTimeoutIsInteger( String operation, List<String> parameters ) {
        if ( parameters.isEmpty() )
            throw new SkipException( "Timeout is not configured for operation " + operation );
        for ( String parameter : parameters ) {
            try {
                Integer.parseInt( parameter );
            } catch ( NumberFormatException e ) {
                assertFalse( true, "Value for timeout parameter of GetPropertyValue operation (value: " + parameter
                                   + ") is not a valid integer" );
            }
        }
    }

}
