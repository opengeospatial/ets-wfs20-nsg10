package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static javax.xml.xpath.XPathConstants.STRING;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.implementsConformanceClass;
import static org.opengis.cite.wfs20.nsg.utils.NamespaceUtils.withStandardBindings;
import static org.testng.Assert.assertTrue;

import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Contains tests for the abstract in the capabilities.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CapabilitiesAbstract extends BaseFixture {

	private static final String EXPECTED_ABSTRACT = "This service implements the NSG BASIC WFS profile of WFS 2.0";

	private static final String EXPECTED_LOCKING_ABSTRACT = "This service implements the NSG LOCKING WFS profile of WFS 2.0";

	/**
	 * <p>
	 * capabilitiesContainsNsgAbstract.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 * @throws javax.xml.xpath.XPathFactoryConfigurationException if any.
	 * @throws javax.xml.xpath.XPathExpressionException if any.
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 21 + 22")
	public void capabilitiesContainsNsgAbstract(ITestContext testContext)
			throws XPathFactoryConfigurationException, XPathExpressionException {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		assertAbstract();
	}

	private void assertAbstract() throws XPathFactoryConfigurationException, XPathExpressionException {
		boolean isImplemented = implementsConformanceClass(this.wfsMetadata, "ImplementsLockingWFS");
		String abstractValue = parseAbstract(this.wfsMetadata);

		boolean abstractIsExpected = isExpected(abstractValue, EXPECTED_ABSTRACT);
		if (isImplemented) {
			boolean abstractIsLockingExpected = isExpected(abstractValue, EXPECTED_LOCKING_ABSTRACT);
			assertTrue(abstractIsExpected || abstractIsLockingExpected,
					"Abstract is not valid, must contain the string '" + EXPECTED_ABSTRACT + " or "
							+ EXPECTED_LOCKING_ABSTRACT + " but is '" + abstractValue + "'");
		}
		else {
			assertTrue(abstractIsExpected, "Abstract is not valid, must contain the string '" + EXPECTED_ABSTRACT
					+ " but is '" + abstractValue + "'");
		}
	}

	private boolean isExpected(String abstractValue, String expectedAbstract) {
		return abstractValue.contains(expectedAbstract);
	}

	private String parseAbstract(Document wfsCapabilities)
			throws XPathFactoryConfigurationException, XPathExpressionException {
		String xPathAbstract = "//wfs:WFS_Capabilities/ows:ServiceIdentification/ows:Abstract";
		return (String) XMLUtils.evaluateXPath(wfsCapabilities, xPathAbstract, withStandardBindings().getAllBindings(),
				STRING);
	}

}
