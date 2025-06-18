package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static java.util.Arrays.asList;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.implementsConformanceClass;
import static org.opengis.cite.wfs20.nsg.utils.NamespaceUtils.withStandardBindings;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Contains tests for the abstract in the capabilities.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CapabilitiesProfile extends BaseFixture {

	private static final List<String> EXPECTED_PROFILE = asList("http://www.nga.mil/service/wfs/2.0/profile/basic",
			"urn:nga:service:wfs:2.0:profile:basic");

	private static final List<String> EXPECTED_LOCKING_PROFILE = asList(
			"http://www.nga.mil/service/wfs/2.0/profile/locking", "urn:nga:service:wfs:2.0:profile:locking");

	/**
	 * <p>
	 * capabilitiesContainsNsgProfile.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 * @throws javax.xml.xpath.XPathFactoryConfigurationException if any.
	 * @throws javax.xml.xpath.XPathExpressionException if any.
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 23 + 24")
	public void capabilitiesContainsNsgProfile(ITestContext testContext)
			throws XPathFactoryConfigurationException, XPathExpressionException {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		assertProfile();
	}

	private void assertProfile() throws XPathFactoryConfigurationException, XPathExpressionException {
		boolean isImplemented = implementsConformanceClass(this.wfsMetadata, "ImplementsLockingWFS");
		List<String> profileValues = parseProfiles(this.wfsMetadata);

		boolean abstractIsExpected = isExpected(profileValues, EXPECTED_PROFILE);
		if (isImplemented) {
			boolean abstractIsLockingExpected = isExpected(profileValues, EXPECTED_LOCKING_PROFILE);
			assertTrue(abstractIsExpected || abstractIsLockingExpected,
					"Abstract is not valid, must contain the string one of '" + EXPECTED_PROFILE + " or "
							+ EXPECTED_LOCKING_PROFILE + " but is '" + profileValues + "'");
		}
		else {
			assertTrue(abstractIsExpected, "Abstract is not valid, must contain one of '" + EXPECTED_PROFILE
					+ "' but is '" + profileValues + "'");
		}
	}

	private boolean isExpected(List<String> profileValues, List<String> expectedProfileHttp) {
		for (String expected : expectedProfileHttp) {
			if (profileValues.contains(expected))
				return true;
		}
		return false;
	}

	private List<String> parseProfiles(Document wfsCapabilities)
			throws XPathFactoryConfigurationException, XPathExpressionException {
		List<String> profiles = new ArrayList<>();
		String xPathAbstract = "//wfs:WFS_Capabilities/ows:ServiceIdentification/ows:Profile";

		NodeList profileNodes = XMLUtils.evaluateXPath(wfsCapabilities, xPathAbstract,
				withStandardBindings().getAllBindings());
		for (int index = 0; index < profileNodes.getLength(); index++) {
			String profile = profileNodes.item(index).getTextContent();
			profiles.add(profile);
		}
		return profiles;
	}

}
