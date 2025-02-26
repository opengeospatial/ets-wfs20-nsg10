package org.opengis.cite.wfs20.nsg.testsuite.describefeaturetype;

import java.util.logging.Level;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.Namespaces;
import org.opengis.cite.iso19142.WFS2;
import org.opengis.cite.iso19142.util.TestSuiteLogger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.w3c.dom.Element;

/**
 * Provides configuration methods that facilitate the testing of DescribeFeatureType.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DescribeFeatureTypeFixture extends BaseFixture {

	private DocumentBuilder docBuilder;

	/**
	 * Builds a DOM Document node representing the request entity
	 * (/wfs:DescribeFeatureType).
	 */
	@BeforeClass
	public void buildRequestEntity() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			this.docBuilder = factory.newDocumentBuilder();
		}
		catch (Exception e) {
			TestSuiteLogger.log(Level.WARNING, "Failed to parse request entity from classpath", e);
		}
	}

	/**
	 * <p>
	 * createRequestEntity.
	 * </p>
	 */
	@BeforeMethod
	public void createRequestEntity() {
		try {
			this.reqEntity = docBuilder
				.parse(getClass().getResourceAsStream("/org/opengis/cite/iso19142/simple/DescribeFeatureType.xml"));
		}
		catch (Exception e) {
			TestSuiteLogger.log(Level.WARNING, "Failed to parse request entity from classpath", e);
		}
	}

	/**
	 * Adds a wfs:TypeName child element to a wfs:DescribeFeatureType entity. A suitable
	 * namespace binding will be added to the document element if necessary.
	 * @param qName The qualified name of the feature type.
	 */
	public void addFeatureType(QName qName) {
		Element docElem = this.reqEntity.getDocumentElement();
		Element typeName = this.reqEntity.createElementNS(Namespaces.WFS, WFS2.TYPENAME_ELEM);
		String nsPrefix = docElem.lookupPrefix(qName.getNamespaceURI());
		if (null == nsPrefix) {
			nsPrefix = "ns" + Integer.toString((int) (Math.random() * 100));
		}
		typeName.setTextContent(nsPrefix + ":" + qName.getLocalPart());
		typeName.setPrefix("wfs");
		docElem.appendChild(typeName);
		docElem.setAttribute(XMLConstants.XMLNS_ATTRIBUTE + ":" + nsPrefix, qName.getNamespaceURI());
	}

}
