package org.opengis.cite.wfs20.nsg.testsuite.getfeature;

import static de.latlon.ets.core.assertion.ETSAssert.assertSchemaValid;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.testng.Assert.assertEquals;

import javax.xml.namespace.QName;
import javax.xml.validation.Schema;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.iso19142.basic.filter.QueryFilterFixture;
import org.opengis.cite.iso19142.util.WFSMessage;
import org.opengis.cite.wfs20.nsg.utils.SchemaUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.ClientResponse;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FeatureEncoding extends QueryFilterFixture {

    private Schema wfsSchema;

    @BeforeClass
    public void init( ITestContext testContext ) {
        this.wfsSchema = SchemaUtils.createWFSSchema();
    }

    /**
     * Precondition R16 is still missing
     */
    @Test(description = "See NSG WFS 2.0 Profile: Requirement 8", dataProvider = "feature-types")
    public void featureInstancesMustBeGml( QName featureType ) {
        WFSMessage.appendSimpleQuery( this.reqEntity, featureType );

        ClientResponse rsp = wfsClient.submitRequest( reqEntity, POST );
        assertEquals( rsp.getStatus(), ClientResponse.Status.OK.getStatusCode(),
                      ErrorMessage.get( UNEXPECTED_STATUS ) );
        this.rspEntity = extractBodyAsDocument( rsp );
        assertSchemaValid( wfsSchema, this.rspEntity );
    }

}
