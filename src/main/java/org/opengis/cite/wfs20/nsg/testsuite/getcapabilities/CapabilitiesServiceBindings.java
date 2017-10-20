package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.iso19142.ProtocolBinding.GET;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.getOperationEndpoint;
import static org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion.assertUri;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.net.URI;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.wfs20.nsg.utils.NsgWfsAssertion;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Tests if the capabilities provides all mandatory ServiceBindings (see NSG WFS 2.0 Profile, 6.5 Service Bindings,
 * Table 6 : Service Bindings).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CapabilitiesServiceBindings extends BaseFixture {

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetCapabilitiesGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetCapabilities", GET );
        assertUri( uri, "GET Binding for operation GetCapabilities must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetCapabilitiesPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetCapabilities", POST );
        assertUri( uri, "POST Binding for operation GetCapabilities must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeFeatureTypeGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "DescribeFeatureType", GET );
        assertUri( uri, "GET Binding for operation DescribeFeatureType must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeFeatureTypePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "DescribeFeatureType", POST );
        assertUri( uri, "POST Binding for operation DescribeFeatureType must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetPropertyValueTypeGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetPropertyValue", GET );
        assertUri( uri, "GET Binding for operation GetPropertyValue must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetPropertyValuePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetPropertyValue", POST );
        assertUri( uri, "POST Binding for operation GetPropertyValue must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeatureTypeGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetFeature", GET );
        assertUri( uri, "GET Binding for operation GetFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeaturePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetFeature", POST );
        assertUri( uri, "POST Binding for operation GetFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsPageResultsGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "PageResults", GET );
        assertUri( uri, "GET Binding for operation PageResults must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsPageResultsPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "PageResults", POST );
        assertUri( uri, "POST Binding for operation PageResults must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeatureWithLockGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetFeatureWithLock", GET );
        assertUri( uri, "GET Binding for operation GetFeatureWithLock must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeatureWithLockPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "GetFeatureWithLock", POST );
        assertUri( uri, "POST Binding for operation GetFeatureWithLock must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsLockFeatureGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "LockFeature", GET );
        assertUri( uri, "GET Binding for operation LockFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsLockFeaturePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "LockFeature", POST );
        assertUri( uri, "POST Binding for operation LockFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsTransactionPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "Transaction", POST );
        assertUri( uri, "POST Binding for operation Transaction must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsCreateStoredQueryPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "CreateStoredQuery", POST );
        assertUri( uri, "POST Binding for operation CreateStoredQuery must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDropStoredQueryGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "DropStoredQuery", GET );
        assertUri( uri, "GET Binding for operation DropStoredQuery must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsListStoredQueriesGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "ListStoredQueries", GET );
        assertUri( uri, "GET Binding for operation ListStoredQueries must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsListStoredQueriesPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "ListStoredQueries", POST );
        assertUri( uri, "POST Binding for operation ListStoredQueries must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeStoredQueriesGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "DescribeStoredQueries", GET );
        assertUri( uri, "GET Binding for operation DescribeStoredQueries must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeStoredQueriesPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI uri = getOperationEndpoint( wfsMetadata, "DescribeStoredQueries", POST );
        assertUri( uri, "POST Binding for operation DescribeStoredQueries must be supported." );
    }

}