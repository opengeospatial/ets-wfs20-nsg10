package org.opengis.cite.wfs20.nsg.utils;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.assertOutputFormat;
import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.parseParameters;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class NsgWfsAssertionTest {

    @Test
    public void testAssertOutputFormat_Global_DescribeFeatureType()
                            throws Exception {
        Document wfsMetadata = readCapabilities( "/capabilities-simple.xml" );
        assertOutputFormat( wfsMetadata, "DescribeFeatureType" );
    }

    @Test
    public void testAssertOutputFormat_Global_GetFeature()
                            throws Exception {
        Document wfsMetadata = readCapabilities( "/capabilities-simple.xml" );
        assertOutputFormat( wfsMetadata, "GetFeature" );
    }

    @Test
    public void testAssertOutputFormat_PerOperation_DescribeFeatureType()
                            throws Exception {
        Document wfsMetadata = readCapabilities( "/capabilities-simple2.xml" );
        assertOutputFormat( wfsMetadata, "DescribeFeatureType" );
    }

    @Test
    public void testAssertOutputFormat_PerOperation_MultipleValues_DescribeFeatureType()
                            throws Exception {
        Document wfsMetadata = readCapabilities( "/capabilities-simple2.xml" );
        assertOutputFormat( wfsMetadata, "ListStoredQueries" );
    }

    @Test(expected = AssertionError.class)
    public void testAssertOutputFormat_PerOperation_GetFeature()
                            throws Exception {
        Document wfsMetadata = readCapabilities( "/capabilities-simple2.xml" );
        assertOutputFormat( wfsMetadata, "GetFeature" );
    }

    @Test
    public void testParseParameters_DescribeFeatureType()
                            throws Exception {
        Document wfsMetadata = readCapabilities( "/capabilities-simple.xml" );

        List<String> parameters = parseParameters( wfsMetadata, "DescribeFeatureType", "Timeout" );
        assertThat( parameters.size(), is( 1 ) );
        assertThat( parameters, hasItem( "5" ) );
    }

    @Test
    public void testParseParameters_ListStoredQueries()
                            throws Exception {
        Document wfsMetadata = readCapabilities( "/capabilities-simple2.xml" );

        List<String> parameters = parseParameters( wfsMetadata, "ListStoredQueries", "Timeout" );
        assertThat( parameters.size(), is( 1 ) );
        assertThat( parameters, hasItem( "10" ) );
    }

    private Document readCapabilities( String resource )
                            throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware( true );
        dbf.newDocumentBuilder();
        dbf.setFeature( "http://apache.org/xml/features/xinclude/fixup-base-uris", false );
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();

        InputStream resourceAsStream = NsgWfsAssertion.class.getResourceAsStream( resource );
        return docBuilder.parse( resourceAsStream );
    }

}
