package org.opengis.cite.wfs20.nsg.testsuite.featureinstance;

import java.util.Set;

import javax.xml.namespace.QName;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.SuiteAttribute;
import org.opengis.cite.iso19142.util.DataSampler;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.assertUuidOrGuideId;

/**
 * Contains test for the feature instance identifier.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InstanceIdentifier extends BaseFixture {

	private DataSampler dataSampler;

	/**
	 * Obtains a DataSampler object from the test run context (the value of the
	 * {@link org.opengis.cite.iso19142.SuiteAttribute#SAMPLER SuiteAttribute.SAMPLER
	 * attribute}).
	 * @param testContext The test run context.
	 */
	@BeforeClass(alwaysRun = true)
	public void initTransactionFixture(ITestContext testContext) {
		ISuite suite = testContext.getSuite();
		this.dataSampler = (DataSampler) suite.getAttribute(SuiteAttribute.SAMPLER.getName());
	}

	/**
	 * <p>
	 * featureInstanceIdentifier.
	 * </p>
	 * @param featureType a {@link javax.xml.namespace.QName} object
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 9", dataProvider = "feature-types")
	public void featureInstanceIdentifier(QName featureType) {
		Set<String> ids = this.dataSampler.selectRandomFeatureIdentifiers(featureType, 10);
		for (String id : ids) {
			assertUuidOrGuideId(id);
		}
	}

}
