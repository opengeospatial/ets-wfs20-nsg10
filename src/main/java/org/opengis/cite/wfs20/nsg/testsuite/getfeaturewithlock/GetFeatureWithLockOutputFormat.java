package org.opengis.cite.wfs20.nsg.testsuite.getfeaturewithlock;

import static de.latlon.ets.core.assertion.ETSAssert.assertSchemaValid;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.WFSMessage.appendSimpleQuery;
import static org.opengis.cite.wfs20.nsg.testsuite.NSGWFSConstants.GML_OUTPUTFORMAT;
import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.assertOutputFormat;
import static org.opengis.cite.wfs20.nsg.utils.RequestUtils.setOutputFormatAttribute;
import static org.testng.Assert.assertEquals;

import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.iso19142.locking.LockingFixture;
import org.opengis.cite.iso19142.util.WFSMessage;
import org.opengis.cite.wfs20.nsg.utils.SchemaUtils;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * Contains test for the outputFormat parameter in GetFeature requests.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GetFeatureWithLockOutputFormat extends LockingFixture {

    private Schema wfsSchema;

    @BeforeClass
    public void init( ITestContext testContext ) {
        this.wfsSchema = SchemaUtils.createWFSSchema();
    }

    /**
     * Builds a DOM Document representing a GetFeatureWithLock request entity. It contains default values for all
     * lock-related attributes.
     */
    @BeforeMethod
    public void buildGetFeatureWithLockRequest() {
        this.reqEntity = WFSMessage.createRequestEntity( "GetFeatureWithLock", this.wfsVersion );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 16")
    public void getFeatureWithLockOperationParameterOutputFormat( ITestContext testContext )
                            throws XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        assertOutputFormat( this.wfsMetadata, "GetFeatureWithLock" );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 8", dataProvider = "feature-types", dependsOnMethods = "getFeatureWithLockOperationParameterOutputFormat")
    public void getFeatureWithLockOutputFormat( QName featureType ) {
        appendSimpleQuery( this.reqEntity, featureType );
        setOutputFormatAttribute( this.reqEntity, GML_OUTPUTFORMAT );

        Response rsp = wfsClient.submitRequest( reqEntity, POST );
        assertEquals( rsp.getStatus(), Status.OK.getStatusCode(), ErrorMessage.get( UNEXPECTED_STATUS ) );
        this.rspEntity = extractBodyAsDocument( rsp );
        assertSchemaValid( wfsSchema, this.rspEntity );
        // TODO: Check if response contains GML
    }

}
