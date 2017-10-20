package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.wfs20.nsg.TestUtils.createTestContext;

import org.junit.Test;
import org.testng.ITestContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CapabilitiesAbstractTest {

    @Test
    public void testCapabilitiesContainsNsgAbstract()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple.xml" );

        CapabilitiesAbstract capabilitiesAbstract = new CapabilitiesAbstract();
        capabilitiesAbstract.capabilitiesContainsNsgAbstract( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testCapabilitiesContainsNsgAbstract_Missing()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple2.xml" );

        CapabilitiesAbstract capabilitiesAbstract = new CapabilitiesAbstract();
        capabilitiesAbstract.capabilitiesContainsNsgAbstract( testContext );
    }
}
