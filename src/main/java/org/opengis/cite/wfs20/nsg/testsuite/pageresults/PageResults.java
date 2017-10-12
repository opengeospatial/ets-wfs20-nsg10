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
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.namespace.QName;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.iso19142.FeatureTypeInfo;
import org.opengis.cite.iso19142.SuiteAttribute;
import org.opengis.cite.iso19142.util.DataSampler;
import org.opengis.cite.wfs20.nsg.utils.NamespaceUtils;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Contains the test for the PageReults operation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PageResults extends PageResultsFixture {

    private QName ftUnderTest;

    private String resultSetId;

    private int numberOfFeatures;

    /**
     * Selects a random feature type from the supported feature types.
     * 
     * @param testContext
     *            the test (set) context
     */
    @BeforeClass(alwaysRun = true)
    public void selectRandomFeatureType( ITestContext testContext ) {
        ISuite suite = testContext.getSuite();
        DataSampler dataSampler = (DataSampler) suite.getAttribute( SuiteAttribute.SAMPLER.getName() );
        Map<QName, FeatureTypeInfo> featureTypeInfo = dataSampler.getFeatureTypeInfo();
        Set<QName> featureTypes = featureTypeInfo.keySet();
        this.ftUnderTest = selectRandomFeatureType( featureTypes );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 33/34")
    public void requestResultId() {
        ClientResponse indexResponse = submitGetFeatureIndexRequest( this.ftUnderTest );
        assertEquals( indexResponse.getStatus(), OK.getStatusCode(), ErrorMessage.get( UNEXPECTED_STATUS ) );

        Document indexRspDocument = extractBodyAsDocument( indexResponse );
        assertNotNull( indexRspDocument, "Response with resultType index must not be null" );
        String xpath = "//nsg:FeatureCollection/nsg:resultSetID";
        assertXPath( xpath, indexRspDocument, NamespaceUtils.withStandardBindings().getAllBindings() );

        this.resultSetId = parseResultSetId( indexRspDocument );
        assertNotNull( resultSetId, "resultSetID from response must not be null" );

        this.numberOfFeatures = parseNumberOfFeatures( indexRspDocument );

    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 33/34", dependsOnMethods = "requestResultId")
    public void pageResultsPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "PageResults", POST );
        assertUri( uri, "POST Binding for operation PageResults must be supported." );

        initResultSetRequest( this.resultSetId );
        ClientResponse clientResponse = wfsClient.submitRequest( this.reqEntity, POST );
        assertNumberOfFeatures( clientResponse );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 33/34", dependsOnMethods = "requestResultId")
    public void pageResultsGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "PageResults", GET );
        assertUri( uri, "GET Binding for operation PageResults must be supported." );

        initResultSetRequest( this.resultSetId );
        ClientResponse clientResponse = wfsClient.submitRequest( this.reqEntity, GET );
        assertNumberOfFeatures( clientResponse );
    }

    private void assertNumberOfFeatures( ClientResponse clientResponse ) {
        this.rspEntity = extractBodyAsDocument( clientResponse );
        int numberOfFeaturesFromPageResult = parseNumberOfFeatures( this.rspEntity );
        assertEquals( numberOfFeaturesFromPageResult, numberOfFeatures,
                      "Number of features from the responses must be the same" );
    }

    private QName selectRandomFeatureType( Set<QName> featureTypes ) {
        int size = featureTypes.size();
        int item = new Random().nextInt( size );
        int i = 0;
        for ( QName featureType : featureTypes ) {
            if ( i == item )
                return featureType;
            i++;
        }
        return null;
    }

}
