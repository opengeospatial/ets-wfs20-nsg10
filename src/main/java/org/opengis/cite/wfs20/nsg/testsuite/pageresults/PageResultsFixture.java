package org.opengis.cite.wfs20.nsg.testsuite.pageresults;

import static javax.xml.xpath.XPathConstants.STRING;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.implementsConformanceClass;
import static org.opengis.cite.iso19142.util.WFSMessage.appendSimpleQuery;
import static org.opengis.cite.iso19142.util.WFSMessage.createRequestEntity;
import static org.opengis.cite.wfs20.nsg.testsuite.NSGWFSConstants.GML_OUTPUTFORMAT;
import static org.opengis.cite.wfs20.nsg.testsuite.NSGWFSConstants.NSG_NAMESPACE;
import static org.opengis.cite.wfs20.nsg.utils.NamespaceUtils.withStandardBindings;
import static org.testng.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.basic.filter.QueryFilterFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.opengis.cite.wfs20.nsg.utils.NsgWfsClient;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Fixture for PageResults operation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PageResultsFixture extends QueryFilterFixture {

    private final static Logger LOGR = Logger.getLogger( PageResultsFixture.class.getName() );

    public static final String COUNT_VALUE = "10";

    /**
     * Overwrites the used WFSClient.
     */
    @BeforeClass(alwaysRun = true, dependsOnMethods = "initBaseFixture")
    public void initPageResultsFixture( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        this.wfsClient = new NsgWfsClient( this.wfsMetadata );
    }

    @Test
    public void checkIfEnhancedPagingIsSupported( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );

        boolean isDefined = implementsConformanceClass( this.wfsMetadata, "ImplementsEnhancedPaging" );
        if ( !isDefined )
            throw new SkipException( "EnhancedPaging is not supported" );
    }

    /**
     * Initialises a PageResults request to this.reqEntity with the passed resultSetID
     * 
     * @param resultSetId
     *            the resultSetId to append to the request
     */
    protected void initResultSetRequest( String resultSetId ) {
        this.reqEntity = createRequestEntity( "/org/opengis/cite/wfs20/nsg/request/PageResults", this.wfsVersion );
        setPresentationParameters( this.reqEntity, "results" );
        appendResultSetId( this.reqEntity, resultSetId );
    }

    /**
     * Submits a GetFeature request with resultType=index for the passed feature type.
     * 
     * @param featureType
     *            the feature type to request
     * @return the GetFeature response, never <code>null</code>
     */
    protected ClientResponse submitGetFeatureIndexRequest( QName featureType ) {
        Document requestEntity = createRequestEntity( "GetFeature-Minimal", this.wfsVersion );
        appendSimpleQuery( requestEntity, featureType );
        setPresentationParameters( requestEntity, "index" );

        return wfsClient.submitRequest( requestEntity, POST );
    }

    /**
     * Submits a GetFeature request with resultType=index for the passed feature type and parses the resultSetId.
     *
     * @param featureType
     *            the feature type to request
     * @return the resultSetId from the response, may be <code>null</code> or empty
     */
    public String submitGetFeatureIndexRequestAndParseResultSetId( QName featureType ) {
        ClientResponse rsp = submitGetFeatureIndexRequest( featureType );
        Document rspDocument = extractBodyAsDocument( rsp );
        return parseResultSetId( rspDocument );
    }

    private void setPresentationParameters( Document requestEntity, String resultType ) {
        Element docElem = requestEntity.getDocumentElement();
        docElem.setAttribute( "resultType", resultType );
        docElem.setAttribute( "count", COUNT_VALUE );
        docElem.setAttribute( "startIndex", "0" );
        docElem.setAttribute( "outputFormat", GML_OUTPUTFORMAT );
    }

    protected String parseResultSetId( Document rspDocument ) {
        assertNotNull( rspDocument, "No response available" );
        Element documentElement = rspDocument.getDocumentElement();
        return documentElement.getAttribute( "resultSetID" );
    }

    protected int parseNumberOfFeatures( Document rspDocument ) {
        String xPath = "//nsg:FeatureCollection/@numberMatched";
        try {
            return Integer.parseInt( (String) XMLUtils.evaluateXPath( rspDocument, xPath,
                                                                      withStandardBindings().getAllBindings(), STRING ) );
        } catch ( XPathExpressionException e ) {
            LOGR.warning( "XPath " + xPath + " could not be evaluated" );
        }
        return 0;
    }

    private void appendResultSetId( Document reqEntity, String resultSetId ) {
        Element valueRef = XMLUtils.createElement( new QName( NSG_NAMESPACE, "resultSetID", "nsg" ) );
        valueRef.setTextContent( resultSetId );
        reqEntity.getDocumentElement().appendChild( valueRef );
    }
}
