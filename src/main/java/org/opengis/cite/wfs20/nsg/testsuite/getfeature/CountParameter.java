package org.opengis.cite.wfs20.nsg.testsuite.getfeature;

import static com.sun.jersey.api.client.ClientResponse.Status.OK;
import static javax.xml.xpath.XPathConstants.STRING;
import static org.opengis.cite.iso19142.ErrorMessageKeys.UNEXPECTED_STATUS;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.getConstraintValue;
import static org.opengis.cite.iso19142.util.WFSMessage.appendSimpleQuery;
import static org.opengis.cite.wfs20.nsg.utils.NamespaceUtils.withStandardBindings;
import static org.testng.Assert.assertEquals;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.ErrorMessage;
import org.opengis.cite.iso19142.FeatureTypeInfo;
import org.opengis.cite.iso19142.basic.filter.QueryFilterFixture;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.jersey.api.client.ClientResponse;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CountParameter extends QueryFilterFixture {

    private final static Logger LOGR = Logger.getLogger( CountParameter.class.getName() );

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 13 + 14")
    public void checkCountDefaultIs10( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        String constraintValueCountDefault = getConstraintValue( this.wfsMetadata, "CountDefault" );

        if ( constraintValueCountDefault == null || !"10".equals( constraintValueCountDefault ))
            throw new SkipException( "A value of 10 for DefaultCount is recommended" );

    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 13 + 14")
    public void defaultCountParameter( ITestContext testContext )
                            throws XPathExpressionException {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        QName featureType = findFeatureTypeWithEnoughFeatures();

        appendSimpleQuery( this.reqEntity, featureType );
        removeCountAttribute( this.reqEntity );
        ClientResponse rsp = this.wfsClient.submitRequest( this.reqEntity, POST );
        this.rspEntity = extractBodyAsDocument( rsp );
        assertEquals( rsp.getStatus(), OK.getStatusCode(), ErrorMessage.get( UNEXPECTED_STATUS ) );

        int numberOfFeatures = parseNumberOfFeatures( this.rspEntity );
        assertEquals( numberOfFeatures, 10,
                      "Expected 10 features in GetFeature request without count as CountDefault is 10" );

    }

    private QName findFeatureTypeWithEnoughFeatures() {
        Map<QName, FeatureTypeInfo> featureTypeInfo = dataSampler.getFeatureTypeInfo();
        for ( QName featureType : featureTypeInfo.keySet() ) {
            Set<String> ids = dataSampler.selectRandomFeatureIdentifiers( featureType, 11 );
            if ( ids.size() > 10 )
                return featureType;
        }
        return null;
    }

    private void removeCountAttribute( Document reqEntity ) {
        Element documentElement = reqEntity.getDocumentElement();
        documentElement.removeAttribute( "count" );
    }

    private int parseNumberOfFeatures( Document rspDocument ) {
        String xPath = "count(//wfs:member)";
        try {
            return Integer.parseInt((String) XMLUtils.evaluateXPath( rspDocument, xPath,
                                                             withStandardBindings().getAllBindings(),
                                                             STRING ) );
        } catch ( XPathExpressionException e ) {
            LOGR.warning( "XPath " + xPath + " could not be evaluated" );
        }
        return 0;
    }
}