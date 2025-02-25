package org.opengis.cite.wfs20.nsg.testsuite.getfeature;

import static org.opengis.cite.iso19142.ETSAssert.assertXPath;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import javax.xml.namespace.QName;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.wfs20.nsg.testsuite.pageresults.PageResultsFixture;
import org.opengis.cite.wfs20.nsg.utils.NamespaceUtils;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * Contains the test for the PageReults operation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GetFeatureWIthResultTypeIndex extends PageResultsFixture {

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 17, 19", dataProvider = "feature-types")
    public void getFeatureWithResultTypeIndex( QName featureType ) {
        Response indexResponse = submitGetFeatureIndexRequest( featureType );
        assertEquals( indexResponse.getStatus(), Status.OK.getStatusCode(), ErrorMessage.get( UNEXPECTED_STATUS ) );

        Document indexRspDocument = extractBodyAsDocument( indexResponse );
        assertNotNull( indexRspDocument, "Response with resultType index must not be null" );

        assertXPath( "count(//wfs:member) = 0", indexRspDocument,
                     NamespaceUtils.withStandardBindings().getAllBindings() );

        assertXPath( "//nsg:FeatureCollection/@next", indexRspDocument,
                     NamespaceUtils.withStandardBindings().getAllBindings() );

        assertXPath( "//nsg:FeatureCollection/nsg:resultSetID", indexRspDocument,
                     NamespaceUtils.withStandardBindings().getAllBindings() );
    }

}
