package org.opengis.cite.wfs20.nsg.testsuite.pageresults;

import static de.latlon.ets.core.assertion.ETSAssert.assertSchemaValid;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.assertOutputFormat;
import static org.testng.Assert.assertEquals;

import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.wfs20.nsg.utils.SchemaUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Contains test for the outputFormat parameter in PageResults requests.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PageResultsOutputFormat extends PageResultsFixture {

    private Schema wfsSchema;

    @BeforeClass
    public void init( ITestContext testContext ) {
        this.wfsSchema = SchemaUtils.createWFSSchema();
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 16 + 29")
    public void pageResultsOperationParameterOutputFormat( ITestContext testContext )
                            throws XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        assertOutputFormat( this.wfsMetadata, "PageResults" );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 8", dataProvider = "feature-types", dependsOnMethods = {
                                                                                                                      "pageResultsOperationParameterOutputFormat",
                                                                                                                      "checkIfEnhancedPagingIsSupported" })
    public void pageResultsOutputFormat( QName featureType ) {
        String resultSetId = submitGetFeatureIndexRequestAndParseResultSetId( featureType );
        initResultSetRequest( resultSetId );

        ClientResponse rsp = wfsClient.submitRequest( reqEntity, POST );
        assertEquals( rsp.getStatus(), ClientResponse.Status.OK.getStatusCode(), ErrorMessage.get( UNEXPECTED_STATUS ) );
        this.rspEntity = extractBodyAsDocument( rsp );
        assertSchemaValid( wfsSchema, this.rspEntity );
        // TODO: check that response contains GML
    }

}
