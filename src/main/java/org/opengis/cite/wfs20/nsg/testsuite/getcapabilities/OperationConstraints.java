package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static javax.xml.xpath.XPathConstants.BOOLEAN;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.testng.Assert.assertTrue;

import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.opengis.cite.wfs20.nsg.utils.NamespaceUtils;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Tests if the capabilities populates the Operation Constraints of the Operation Metadata
 * document as described in Section 8.1.3.3.4. (see NSG WFS 2.0 Profile, 8.1.3.3.4
 * Operation Constraints, Table 17 : WFS Operation Constraints).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OperationConstraints extends BaseFixture {

	/**
	 * <p>
	 * operationConstraintCountDefault.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
	public void operationConstraintCountDefault(ITestContext testContext) {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		boolean isDefined = hasConstraint(this.wfsMetadata, "CountDefault");
		assertTrue(isDefined, "Constraint CountDefault must be populated.");
	}

	/**
	 * <p>
	 * operationConstraintResolveTimeoutDefault.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
	public void operationConstraintResolveTimeoutDefault(ITestContext testContext) {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		boolean isDefined = hasConstraint(this.wfsMetadata, "ResolveTimeoutDefault");
		assertTrue(isDefined, "Constraint ResolveTimeoutDefault must be populated.");
	}

	/**
	 * <p>
	 * operationConstraintResolveLocalScope.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
	public void operationConstraintResolveLocalScope(ITestContext testContext) {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		boolean isDefined = hasConstraint(this.wfsMetadata, "ResolveLocalScope");
		assertTrue(isDefined, "Constraint ResolveLocalScope must be populated.");
	}

	/**
	 * <p>
	 * operationConstraintResolveRemoteScope.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
	public void operationConstraintResolveRemoteScope(ITestContext testContext) {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		boolean isDefined = hasConstraint(this.wfsMetadata, "ResolveRemoteScope");
		assertTrue(isDefined, "Constraint ResolveRemoteScope must be populated.");
	}

	/**
	 * <p>
	 * operationConstraintAuthentication.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
	public void operationConstraintAuthentication(ITestContext testContext) {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		boolean isDefined = hasConstraint(this.wfsMetadata, "Authentication");
		assertTrue(isDefined, "Constraint Authentication must be populated.");
	}

	private boolean hasConstraint(Document wfsMetadata, String constraintName) {
		String xpath = "//ows:Constraint[@name='%s']";
		try {
			return (Boolean) XMLUtils.evaluateXPath(wfsMetadata, String.format(xpath, constraintName),
					NamespaceUtils.withStandardBindings().getAllBindings(), BOOLEAN);
		}
		catch (XPathExpressionException e) {
		}
		return false;
	}

}
