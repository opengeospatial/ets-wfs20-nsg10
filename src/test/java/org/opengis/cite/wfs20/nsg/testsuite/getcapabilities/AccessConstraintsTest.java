package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.wfs20.nsg.TestUtils.createTestContext;

import org.junit.Test;
import org.testng.ITestContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AccessConstraintsTest {

    @Test
    public void testWfsCapabilitiesAccessConstraintsExists()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple.xml" );

        AccessConstraints accessConstraints = new AccessConstraints();
        accessConstraints.wfsCapabilitiesAccessConstraintsExists( testContext );
        accessConstraints.wfsCapabilitiesAccessConstraintsContainsValueFromISM( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testWfsCapabilitiesAccessConstraintsExists_NO_AccessConstraints()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-withFailures.xml" );

        AccessConstraints accessConstraints = new AccessConstraints();
        accessConstraints.wfsCapabilitiesAccessConstraintsExists( testContext );
    }

    @Test
    public void testWfsCapabilitiesAccessConstraintsContainsValueFromISM()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple.xml" );

        AccessConstraints accessConstraints = new AccessConstraints();
        accessConstraints.wfsCapabilitiesAccessConstraintsContainsValueFromISM( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testWfsCapabilitiesAccessConstraintsContainsValueFromISM_Unexpected_AccessConstraints()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple2.xml" );

        AccessConstraints accessConstraints = new AccessConstraints();
        accessConstraints.wfsCapabilitiesAccessConstraintsContainsValueFromISM( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testWfsCapabilitiesAccessConstraintsContainsValueFromISM_NO_AccessConstraints()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-withFailures.xml" );

        AccessConstraints accessConstraints = new AccessConstraints();
        accessConstraints.wfsCapabilitiesAccessConstraintsContainsValueFromISM( testContext );
    }

}
