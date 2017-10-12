package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import org.junit.Test;
import org.testng.ITestContext;

import static org.opengis.cite.wfs20.nsg.TestUtils.createTestContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CapabilitiesProfileTest  {

    @Test
    public void testCapabilitiesContainsNsgProfile()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple.xml" );

        CapabilitiesProfile capabilitiesProfile = new CapabilitiesProfile();
        capabilitiesProfile.capabilitiesContainsNsgProfile( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testCapabilitiesContainsNsgProfile_Missing()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple2.xml" );

        CapabilitiesProfile capabilitiesProfile = new CapabilitiesProfile();
        capabilitiesProfile.capabilitiesContainsNsgProfile( testContext );
    }
}
