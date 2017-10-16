package org.opengis.cite.wfs20.nsg.testsuite.pageresults;

import static com.sun.jersey.api.client.ClientResponse.Status.OK;
import static org.opengis.cite.iso19142.ETSAssert.assertXPath;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.opengis.cite.iso19142.ProtocolBinding.GET;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.getOperationEndpoint;
import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.assertUri;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.iso19142.SuiteAttribute;
import org.opengis.cite.wfs20.nsg.utils.NamespaceUtils;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Contains the test for the PageReults operation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PageResults extends PageResultsFixture {

    private Map<QName, ResultSetIdAndNumberOfFeatures> featureTypeToAdditionalInfos = new HashMap<>();

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 17, 19, 33, 34", dataProvider = "feature-types", dependsOnMethods = "checkIfEnhancedPagingIsSupported")
    public void requestResultId( QName featureType ) {
        ClientResponse indexResponse = submitGetFeatureIndexRequest( featureType );
        assertEquals( indexResponse.getStatus(), OK.getStatusCode(), ErrorMessage.get( UNEXPECTED_STATUS ) );

        Document indexRspDocument = extractBodyAsDocument( indexResponse );
        assertNotNull( indexRspDocument, "Response with resultType index must not be null" );

        assertXPath( "count(//wfs:member) = 0", indexRspDocument,
                     NamespaceUtils.withStandardBindings().getAllBindings() );

        assertXPath( "//nsg:FeatureCollection/nsg:resultSetID", indexRspDocument,
                     NamespaceUtils.withStandardBindings().getAllBindings() );

        String resultSetId = parseResultSetId( indexRspDocument );
        assertNotNull( resultSetId, "resultSetID from response must not be null" );

        int numberOfFeatures = parseNumberOfFeatures( indexRspDocument );

        featureTypeToAdditionalInfos.put( featureType, new ResultSetIdAndNumberOfFeatures( resultSetId,
                                                                                           numberOfFeatures ) );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 17, 19, 33, 34", dataProvider = "feature-types", dependsOnMethods = "requestResultId")
    public void pageResultsPost( ITestContext testContext, QName featureType ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "PageResults", POST );
        assertUri( uri, "POST Binding for operation PageResults must be supported." );

        ResultSetIdAndNumberOfFeatures resultSetAndNumberOfFeatures = findResultSetIdAndNumberOfFeatures( featureType );

        initResultSetRequest( resultSetAndNumberOfFeatures.getResultSetId() );
        ClientResponse clientResponse = wfsClient.submitRequest( this.reqEntity, POST );
        assertNumberOfFeatures( clientResponse, resultSetAndNumberOfFeatures.getNumberOfFeatures() );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 17, 19, 33, 34", dataProvider = "feature-types", dependsOnMethods = "requestResultId")
    public void pageResultsGet( ITestContext testContext, QName featureType ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "PageResults", GET );
        assertUri( uri, "GET Binding for operation PageResults must be supported." );

        ResultSetIdAndNumberOfFeatures resultSetAndNumberOfFeatures = findResultSetIdAndNumberOfFeatures( featureType );

        initResultSetRequest( resultSetAndNumberOfFeatures.getResultSetId() );
        ClientResponse clientResponse = wfsClient.submitRequest( this.reqEntity, GET );
        assertNumberOfFeatures( clientResponse, resultSetAndNumberOfFeatures.getNumberOfFeatures() );
    }

    private ResultSetIdAndNumberOfFeatures findResultSetIdAndNumberOfFeatures( QName featureType ) {
        if ( featureTypeToAdditionalInfos.containsKey( featureType ) )
            return featureTypeToAdditionalInfos.get( featureType );
        throw new SkipException( "GetFeature request with resultType='index' already failed for feature type "
                                 + featureType );
    }

    private void assertNumberOfFeatures( ClientResponse clientResponse, int numberOfFeaturesFromIndexResponse ) {
        this.rspEntity = extractBodyAsDocument( clientResponse );
        int numberOfFeaturesFromPageResult = parseNumberOfFeatures( this.rspEntity );
        assertEquals( numberOfFeaturesFromPageResult, numberOfFeaturesFromIndexResponse,
                      "Number of features from the responses must be the same" );
    }

}
