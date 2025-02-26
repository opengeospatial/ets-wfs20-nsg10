package org.opengis.cite.wfs20.nsg.testsuite.getpropertyvalue;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.Namespaces;
import org.opengis.cite.iso19142.SuiteAttribute;
import org.opengis.cite.iso19142.WFS2;
import org.opengis.cite.iso19142.util.WFSMessage;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <p>
 * PropertyValueFixture class.
 * </p>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PropertyValueFixture extends BaseFixture {

	private Schema wfsSchema;

	/**
	 * Retrieves the (pre-compiled) WFS schema from the suite fixture.
	 * @param testContext The test (group) context.
	 */
	@BeforeClass
	public void setupClassFixture(ITestContext testContext) {
		this.wfsSchema = (Schema) testContext.getSuite().getAttribute(SuiteAttribute.WFS_SCHEMA.getName());
		Assert.assertNotNull(this.wfsSchema, "WFS schema not found in suite fixture.");
	}

	/**
	 * Builds a DOM Document node representing the entity body for a GetPropertyValue
	 * request. A minimal XML representation is read from the classpath (at
	 * util/GetPropertyValue.xml).
	 */
	@BeforeMethod
	public void buildRequestEntity() {
		this.reqEntity = WFSMessage.createRequestEntity("GetPropertyValue", this.wfsVersion);
	}

	/**
	 * Sets the valueReference attribute on the request entity.
	 * @param entity The request entity (wfs:GetPropertyValue).
	 * @param xpath A String representing an XPath expression.
	 */
	protected void setValueReference(Document entity, String xpath) {
		entity.getDocumentElement().setAttribute("valueReference", xpath);
	}

	/**
	 * Adds a simple query element to the request entity.
	 * @param request The request entity (wfs:GetPropertyValue).
	 * @param qName A QName representing the qualified name of a feature type.
	 */
	protected void addQuery(Document request, QName qName) {
		Element docElem = request.getDocumentElement();
		String nsPrefix = docElem.lookupPrefix(qName.getNamespaceURI());
		if (null == nsPrefix) {
			nsPrefix = "ns" + Integer.toString((int) (Math.random() * 100));
		}
		Element query = request.createElementNS(Namespaces.WFS, WFS2.QUERY_ELEM);
		query.setPrefix("wfs");
		query.setAttribute("typeNames", nsPrefix + ":" + qName.getLocalPart());
		docElem.appendChild(query);
		docElem.setAttribute(XMLConstants.XMLNS_ATTRIBUTE + ":" + nsPrefix, qName.getNamespaceURI());
	}

}
