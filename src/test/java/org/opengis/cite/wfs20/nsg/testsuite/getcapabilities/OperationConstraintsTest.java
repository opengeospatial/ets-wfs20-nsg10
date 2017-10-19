package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.wfs20.nsg.TestUtils.createTestContext;

import org.junit.Test;
import org.testng.ITestContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OperationConstraintsTest {

    @Test
    public void testOoperationConstraintCountDefault_Configured()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple2.xml" );

        OperationConstraints operationConstraints = new OperationConstraints();
        operationConstraints.operationConstraintCountDefault( testContext );
    }

    @Test(expected = AssertionError.class)
    public void testOoperationConstraintCountDefault_Missing()
                            throws Exception {
        ITestContext testContext = createTestContext( "/capabilities-simple.xml" );

        OperationConstraints operationConstraints = new OperationConstraints();
        operationConstraints.operationConstraintCountDefault( testContext );
    }

}
