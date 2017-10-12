package org.opengis.cite.wfs20.nsg.testsuite.getfeature;

import static de.latlon.ets.core.assertion.ETSAssert.assertSchemaValid;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.util.WFSMessage.appendSimpleQuery;
import static org.opengis.cite.wfs20.nsg.testsuite.NSGWFSConstants.GML_OUTPUTFORMAT;
import static org.opengis.cite.wfs20.nsg.utils.RequestUtils.setOutputFormatAttribute;
import static org.testng.Assert.assertEquals;

import javax.xml.namespace.QName;
import javax.xml.validation.Schema;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.iso19142.basic.filter.QueryFilterFixture;
import org.opengis.cite.wfs20.nsg.utils.SchemaUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Contains test for the outputFormat parameter in GetFeature requests.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GetFeatureOutputFormat extends QueryFilterFixture {

    private Schema wfsSchema;

    @BeforeClass
    public void init( ITestContext testContext ) {
        this.wfsSchema = SchemaUtils.createWFSSchema();
    }

    /**
     * Precondition R16 is still missing
     */
    @Test(description = "See NSG WFS 2.0 Profile: Requirement 8", dataProvider = "feature-types")
    public void getFeatureOutputFormat( QName featureType ) {
        appendSimpleQuery( this.reqEntity, featureType );
        setOutputFormatAttribute( this.reqEntity, GML_OUTPUTFORMAT );

        ClientResponse rsp = wfsClient.submitRequest( reqEntity, POST );
        assertEquals( rsp.getStatus(), ClientResponse.Status.OK.getStatusCode(), ErrorMessage.get( UNEXPECTED_STATUS ) );
        this.rspEntity = extractBodyAsDocument( rsp );
        assertSchemaValid( wfsSchema, this.rspEntity );
        // TODO: Check if response contains GML
    }

}
