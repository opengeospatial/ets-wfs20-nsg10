package org.opengis.cite.wfs20.nsg.testsuite.pageresults;

import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.implementsConformanceClass;
import static org.opengis.cite.iso19142.util.WFSMessage.appendSimpleQuery;
import static org.opengis.cite.iso19142.util.WFSMessage.createRequestEntity;
import static org.testng.Assert.assertNotNull;

import javax.xml.namespace.QName;

import org.opengis.cite.iso19142.basic.filter.QueryFilterFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.jersey.api.client.ClientResponse;

/**
 *
 * Fixture for PageResults operation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PageResultsFixture extends QueryFilterFixture {

    @Test
    public void checkIfEnhancedPagingIsSupported( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );

        boolean isDefined = implementsConformanceClass( this.wfsMetadata, "ImplementsEnhancedPaging" );
        if ( !isDefined )
            throw new SkipException( "EnhancedPaging is not supported" );
    }

    protected void initResultSetRequest( QName featureType ) {
        this.reqEntity = createRequestEntity( "/org/opengis/cite/wfs20/nsg/request/PageResults", this.wfsVersion );

        String resultSetId = retrieveResultSetId( featureType );
        appendResultSetId( this.reqEntity, resultSetId );
    }

    private String retrieveResultSetId( QName featureType ) {
        Document requestEntity = createRequestEntity( "GetFeature-Minimal", this.wfsVersion );
        appendSimpleQuery( requestEntity, featureType );
        setPresentationParameters( requestEntity );

        ClientResponse rsp = wfsClient.submitRequest( requestEntity, POST );
        Document rspDocument = extractBodyAsDocument( rsp );
        return parseResultSetId( rspDocument );
    }

    private void setPresentationParameters( Document requestEntity ) {
        Element docElem = requestEntity.getDocumentElement();
        docElem.setAttribute( "resultType", "index" );
        docElem.setAttribute( "count", "10" );
        docElem.setAttribute( "startIndex", "0" );
    }

    private String parseResultSetId( Document rspDocument ) {
        assertNotNull( this.rspEntity, "No response available" );
        Element documentElement = this.rspEntity.getDocumentElement();
        return documentElement.getAttribute( "resultSetID" );
    }

    private void appendResultSetId( Document reqEntity, String resultSetId ) {
        Element valueRef = XMLUtils.createElement( new QName( "http://www.opengis.net/nsg/2.0", "resultSetID", "nsg" ) );
        valueRef.setTextContent( resultSetId );
        reqEntity.getDocumentElement().appendChild( valueRef );
    }
}
