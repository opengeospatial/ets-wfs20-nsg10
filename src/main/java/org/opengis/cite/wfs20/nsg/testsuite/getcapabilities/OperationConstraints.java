package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.implementsConformanceClass;
import static org.testng.Assert.assertTrue;

import org.opengis.cite.iso19142.BaseFixture;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Tests if the capabilities populates the Operation Constraints of the Operation Metadata document as described in
 * Section 8.1.3.3.4. (see NSG WFS 2.0 Profile, 8.1.3.3.4 Operation Constraints, Table 17 : WFS Operation Constraints).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OperationConstraints extends BaseFixture {

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
    public void operationConstraintCountDefault( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isDefined = implementsConformanceClass( this.wfsMetadata, "CountDefault" );
        assertTrue( isDefined, "Constraint CountDefault must be populated." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
    public void operationConstraintResolveTimeoutDefault( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isDefined = implementsConformanceClass( this.wfsMetadata, "ResolveTimeoutDefault" );
        assertTrue( isDefined, "Constraint ResolveTimeoutDefault must be populated." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
    public void operationConstraintResolveLocalScope( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isDefined = implementsConformanceClass( this.wfsMetadata, "ResolveLocalScope" );
        assertTrue( isDefined, "Constraint ResolveLocalScope must be populated." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
    public void operationConstraintResolveRemoteScope( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isDefined = implementsConformanceClass( this.wfsMetadata, "ResolveRemoteScope" );
        assertTrue( isDefined, "Constraint ResolveRemoteScope must be populated." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 28")
    public void operationConstraintAuthentication( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isDefined = implementsConformanceClass( this.wfsMetadata, "Authentication" );
        assertTrue( isDefined, "Constraint Authentication must be populated." );
    }

}
