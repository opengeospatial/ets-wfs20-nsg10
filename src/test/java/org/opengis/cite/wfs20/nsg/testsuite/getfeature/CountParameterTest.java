package org.opengis.cite.wfs20.nsg.testsuite.getfeature;

import static org.opengis.cite.wfs20.nsg.TestUtils.createTestContext;

import org.junit.Test;
import org.testng.ITestContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CountParameterTest {

	@Test
	public void testCountParameter() throws Exception {
		ITestContext testContext = createTestContext("/capabilities-simple2.xml");

		CountParameter countParameter = new CountParameter();
		countParameter.countDefaultIs10(testContext);
	}

	@Test(expected = AssertionError.class)
	public void testCountParameter_Missing() throws Exception {
		ITestContext testContext = createTestContext("/capabilities-simple.xml");

		CountParameter countParameter = new CountParameter();
		countParameter.countDefaultIs10(testContext);
	}

	@Test(expected = AssertionError.class)
	public void testCountParameter_Unexpected() throws Exception {
		ITestContext testContext = createTestContext("/capabilities-withFailures.xml");

		CountParameter countParameter = new CountParameter();
		countParameter.countDefaultIs10(testContext);
	}

}
