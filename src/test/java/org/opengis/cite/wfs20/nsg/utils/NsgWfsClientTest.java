package org.opengis.cite.wfs20.nsg.utils;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.opengis.cite.iso19142.util.WFSMessage;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class NsgWfsClientTest {

    @Test
    public void testCreateQueryString_Minimal() {
        NsgWfsClient client = new NsgWfsClient();

        Node entity = WFSMessage.createRequestEntity( "/org/opengis/cite/wfs20/nsg/utils/PageResultsRequest_Minimal",
                                                      "2.0.0" );
        List<String> keyValuePairs = parseKVPs( client.createQueryString( entity ) );
        assertThat( keyValuePairs, hasItem( "SERVICE=WFS" ) );
        assertThat( keyValuePairs, hasItem( "VERSION=2.0.0" ) );
        assertThat( keyValuePairs, hasItem( "REQUEST=PageResults" ) );
        assertThat( keyValuePairs, hasItem( "RESULTSETID=ABC" ) );
        assertThat( keyValuePairs.size(), is( 4 ) );
    }

    @Test
    public void testCreateQueryString() {
        NsgWfsClient client = new NsgWfsClient();

        Node entity = WFSMessage.createRequestEntity( "/org/opengis/cite/wfs20/nsg/utils/PageResultsRequest", "2.0.0" );
        List<String> keyValuePairs = parseKVPs( client.createQueryString( entity ) );
        assertThat( keyValuePairs, hasItem( "SERVICE=WFS" ) );
        assertThat( keyValuePairs, hasItem( "VERSION=2.0.0" ) );
        assertThat( keyValuePairs, hasItem( "REQUEST=PageResults" ) );
        assertThat( keyValuePairs, hasItem( "RESULTSETID=xyz" ) );
        assertThat( keyValuePairs, hasItem( "STARTINDEX=10" ) );
        assertThat( keyValuePairs, hasItem( "COUNT=9" ) );
        assertThat( keyValuePairs, hasItem( "OUTPUTFORMAT=gml" ) );
        assertThat( keyValuePairs, hasItem( "RESULTTYPE=results" ) );
        assertThat( keyValuePairs, hasItem( "TIMEOUT=145" ) );
        assertThat( keyValuePairs.size(), is( 9 ) );
    }

    private List<String> parseKVPs( String queryString ) {
        String[] split = queryString.split( "&" );
        return Arrays.asList( split );
    }
}
