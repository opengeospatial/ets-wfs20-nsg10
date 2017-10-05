package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.iso19142.ProtocolBinding.GET;
import static org.opengis.cite.iso19142.ProtocolBinding.POST;
import static org.testng.Assert.assertNotNull;

import java.net.URI;

import org.opengis.cite.iso19142.BaseFixture;
import org.opengis.cite.iso19142.util.ServiceMetadataUtils;
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
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetCapabilities", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation GetCapabilities must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetCapabilitiesPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetCapabilities", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation GetCapabilities must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeFeatureTypeGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "DescribeFeatureType", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation DescribeFeatureType must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeFeatureTypePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "DescribeFeatureType", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation DescribeFeatureType must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetPropertyValueTypeGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetPropertyValue", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation GetPropertyValue must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetPropertyValuePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetPropertyValue", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation GetPropertyValue must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeatureValueTypeGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetFeature", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation GetFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeatureValuePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetFeature", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation GetFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsPageResultsGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "PageResults", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation PageResults must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsPageResultsPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "PageResults", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation PageResults must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeatureWithLockGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetFeatureWithLock", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation GetFeatureWithLock must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsGetFeatureWithLockPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "GetFeatureWithLock", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation GetFeatureWithLock must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsLockFeatureGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "LockFeature", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation LockFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsLockFeaturePost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "LockFeature", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation LockFeature must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsTransactionPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "Transaction", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation Transaction must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsCreateStoredQueryPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "CreateStoredQuery", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation CreateStoredQuery must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDropStoredQueryGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "DropStoredQuery", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation DropStoredQuery must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsListStoredQueryGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "ListStoredQuery", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation ListStoredQuery must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsListStoredQueryPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "ListStoredQuery", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation ListStoredQuery must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeStoredQueryGet( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "DescribeStoredQuery", GET );
        assertNotNull( getCapabilitiesUrl, "GET Binding for operation DescribeStoredQuery must be supported." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 7")
    public void supportsDescribeStoredQueryPost( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT.getName() );
        URI getCapabilitiesUrl = ServiceMetadataUtils.getOperationEndpoint( wfsMetadata, "DescribeStoredQuery", POST );
        assertNotNull( getCapabilitiesUrl, "POST Binding for operation DescribeStoredQuery must be supported." );
    }

}