package org.opengis.cite.wfs20.nsg.testsuite.getfeature;

import static javax.xml.xpath.XPathConstants.STRING;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.getConstraintValue;
import static org.opengis.cite.iso19142.util.WFSMessage.appendSimpleQuery;
import static org.opengis.cite.wfs20.nsg.utils.NamespaceUtils.withStandardBindings;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.iso19142.FeatureTypeInfo;
import org.opengis.cite.iso19142.basic.filter.QueryFilterFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * <p>
 * CountParameter class.
 * </p>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CountParameter extends QueryFilterFixture {

	private final static Logger LOGR = Logger.getLogger(CountParameter.class.getName());

	private static final int COUNT_DEFAULT_VALUE = 10;

	private QName featureType;

	/**
	 * <p>
	 * countDefaultIs10.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 13 + 14")
	public void countDefaultIs10(ITestContext testContext) {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());
		String constraintValueCountDefault = getConstraintValue(this.wfsMetadata, "CountDefault");
		assertNotNull(constraintValueCountDefault, "A constraint CountDefault is expected");
		assertEquals(constraintValueCountDefault, Integer.toString(COUNT_DEFAULT_VALUE),
				"A value of 10 for constraint CountDefault is expected");
	}

	/**
	 * <p>
	 * findAppropriateFeatureType.
	 * </p>
	 * @throws javax.xml.xpath.XPathExpressionException if any.
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 13 + 14")
	public void findAppropriateFeatureType() throws XPathExpressionException {
		QName featureType = findFeatureTypeWithEnoughFeatures();
		if (featureType == null)
			throw new SkipException(
					"Feature type with more than 10 features is not available. This is required to check if CountDefault=10 is correctly applied in GetFeature request.");
		this.featureType = featureType;
	}

	/**
	 * <p>
	 * defaultCountParameter.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 * @throws javax.xml.xpath.XPathExpressionException if any.
	 */
	@Test(description = "See NSG WFS 2.0 Profile: Requirement 13 + 14",
			dependsOnMethods = { "countDefaultIs10", "findAppropriateFeatureType" })
	public void defaultCountParameter(ITestContext testContext) throws XPathExpressionException {
		this.wfsMetadata = (Document) testContext.getSuite().getAttribute(TEST_SUBJECT.getName());

		appendSimpleQuery(this.reqEntity, featureType);
		removeCountAttribute(this.reqEntity);
		Response rsp = this.wfsClient.submitRequest(this.reqEntity, POST);
		this.rspEntity = extractBodyAsDocument(rsp);
		assertEquals(rsp.getStatus(), Status.OK.getStatusCode(), ErrorMessage.get(UNEXPECTED_STATUS));

		int numberOfFeatures = parseNumberOfFeatures(this.rspEntity);
		assertEquals(numberOfFeatures, COUNT_DEFAULT_VALUE,
				"Expected 10 features in GetFeature request without count as CountDefault is 10");

	}

	private QName findFeatureTypeWithEnoughFeatures() {
		Map<QName, FeatureTypeInfo> featureTypeInfo = dataSampler.getFeatureTypeInfo();
		for (QName featureType : featureTypeInfo.keySet()) {
			Set<String> ids = dataSampler.selectRandomFeatureIdentifiers(featureType, 11);
			if (ids.size() > 10)
				return featureType;
		}
		return null;
	}

	private void removeCountAttribute(Document reqEntity) {
		Element documentElement = reqEntity.getDocumentElement();
		documentElement.removeAttribute("count");
	}

	private int parseNumberOfFeatures(Document rspDocument) {
		String xPath = "count(//wfs:member)";
		try {
			return Integer.parseInt((String) XMLUtils.evaluateXPath(rspDocument, xPath,
					withStandardBindings().getAllBindings(), STRING));
		}
		catch (XPathExpressionException e) {
			LOGR.warning("XPath " + xPath + " could not be evaluated");
		}
		return 0;
	}

}
