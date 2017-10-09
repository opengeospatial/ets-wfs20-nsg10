package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.BeforeClass;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.w3c.dom.Document;

import java.io.InputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OperationParameterTest {

    private static ITestContext testContext;

    private static ISuite suite;

    @BeforeClass
    public static void setUpClass()
                            throws Exception {
        testContext = mock( ITestContext.class );
        suite = mock( ISuite.class );
        when( testContext.getSuite() ).thenReturn( suite );
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware( true );
        dbf.newDocumentBuilder();
        dbf.setFeature( "http://apache.org/xml/features/xinclude/fixup-base-uris", false );
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();

        InputStream resourceAsStream = OperationParameterTest.class.getResourceAsStream( "/capabilities-simple.xml" );
        Document capabilities = docBuilder.parse( resourceAsStream );

        when( suite.getAttribute( TEST_SUBJECT.getName() ) ).thenReturn( capabilities );
    }

    @org.junit.Test
    public void testOperationParameterOutputFormat()
                            throws Exception {
        OperationParameter op = new OperationParameter();
        op.operationParameterOutputFormat( testContext );
    }

}
