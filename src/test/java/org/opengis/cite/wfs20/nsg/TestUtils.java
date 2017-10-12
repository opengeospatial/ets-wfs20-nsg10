package org.opengis.cite.wfs20.nsg;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.CapabilitiesAbstractTest;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TestUtils {

    public static ITestContext createTestContext( String resource )
                            throws Exception {
        ITestContext testContext = mock( ITestContext.class );
        ISuite suite = mock( ISuite.class );
        when( testContext.getSuite() ).thenReturn( suite );
        Document capabilities = readCapabilities( resource );

        when( suite.getAttribute( TEST_SUBJECT.getName() ) ).thenReturn( capabilities );
        return testContext;
    }

    public static Document readCapabilities( String resource )
                            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware( true );
        dbf.newDocumentBuilder();
        dbf.setFeature( "http://apache.org/xml/features/xinclude/fixup-base-uris", false );
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();

        InputStream resourceAsStream = CapabilitiesAbstractTest.class.getResourceAsStream( resource );
        return docBuilder.parse( resourceAsStream );
    }

}
