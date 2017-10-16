package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.wfs20.nsg.TestUtils.createTestContext;

import org.junit.Test;
import org.testng.ITestContext;
import org.testng.SkipException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CapabilitiesTimeoutTest {

    @Test
    public void testCapabilitiesContainsTimeoutForGetFeature_Configured()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple.xml" );

        CapabilitiesTimeout capabilitiesTimeout = new CapabilitiesTimeout();
        capabilitiesTimeout.capabilitiesContainsTimeoutForGetFeature( testContext );
    }

    @Test(expected = SkipException.class)
    public void testCapabilitiesContainsTimeoutForGetFeature_NotConfigured()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple2.xml" );

        CapabilitiesTimeout capabilitiesTimeout = new CapabilitiesTimeout();
        capabilitiesTimeout.capabilitiesContainsTimeoutForGetFeature( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testCapabilitiesContainsTimeoutForGetFeature_Invalid()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-withFailures.xml" );

        CapabilitiesTimeout capabilitiesTimeout = new CapabilitiesTimeout();
        capabilitiesTimeout.capabilitiesContainsTimeoutForGetFeature( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testCapabilitiesContainsTimeoutForGetFeatureWithLock_Invalid()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-withFailures.xml" );

        CapabilitiesTimeout capabilitiesTimeout = new CapabilitiesTimeout();
        capabilitiesTimeout.capabilitiesContainsTimeoutForGetFeatureWithLock( testContext );
    }
}
