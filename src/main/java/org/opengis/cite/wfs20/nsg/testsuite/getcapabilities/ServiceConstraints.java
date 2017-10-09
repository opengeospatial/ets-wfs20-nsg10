package org.opengis.cite.wfs20.nsg.testsuite.getcapabilities;

import static org.opengis.cite.iso19142.SuiteAttribute.TEST_SUBJECT;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.getConstraintValue;
import static org.opengis.cite.iso19142.util.ServiceMetadataUtils.implementsConformanceClass;
import static org.testng.Assert.assertTrue;

import org.opengis.cite.iso19142.BaseFixture;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

/**
 * Tests if the capabilities populates the Service Constraints of the OperationsMetadata document as described in
 * Section 8.1.3.3.1. (see NSG WFS 2.0 Profile, 8.1.3.3.1 Service Constraints, Table 15 : WFS Service Constraints).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServiceConstraints extends BaseFixture {

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void ImplementsTransactionalWFS( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        String constraintValueTransactionalWFS = getConstraintValue( this.wfsMetadata, "ImplementsTransactionalWFS" );
        String constraintValueLockingWFS = getConstraintValue( this.wfsMetadata, "ImplementsLockingWFS" );
        assertTrueOrFalse( constraintValueTransactionalWFS, "ImplementsTransactionalWFS" );

        boolean isEqual = constraintValueLockingWFS.equals( constraintValueTransactionalWFS );
        assertTrue( isEqual,
                    "Constraint of ImplementsTransactionalWFS have to be the same value as ImplementsLockingWFS" );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void KVPEncoding( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isImplemented = implementsConformanceClass( this.wfsMetadata, "KVPEncoding" );
        assertTrue( isImplemented, "Constraint KVPEncoding must be implemented." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void XMLEncoding( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isImplemented = implementsConformanceClass( this.wfsMetadata, "XMLEncoding" );
        assertTrue( isImplemented, "Constraint XMLEncoding must be implemented." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void ImplementsResultPaging( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isImplemented = implementsConformanceClass( this.wfsMetadata, "ImplementsResultPaging" );
        assertTrue( isImplemented, "Constraint ImplementsResultPaging must be implemented." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void ImplementsStandardJoins( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isImplemented = implementsConformanceClass( this.wfsMetadata, "ImplementsStandardJoins" );
        assertTrue( isImplemented, "Constraint ImplementsStandardJoins must be implemented." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void ImplementsSpatialJoins( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isImplemented = implementsConformanceClass( this.wfsMetadata, "ImplementsSpatialJoins" );
        assertTrue( isImplemented, "Constraint ImplementsSpatialJoins must be implemented." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void ImplementsTemporalJoins( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isImplemented = implementsConformanceClass( this.wfsMetadata, "ImplementsTemporalJoins" );
        assertTrue( isImplemented, "Constraint ImplementsTemporalJoins must be implemented." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void ImplementsFeatureVersioning( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        boolean isImplemented = implementsConformanceClass( this.wfsMetadata, "ImplementsFeatureVersioning" );
        assertTrue( isImplemented, "Constraint ImplementsFeatureVersioning must be implemented." );
    }

    @Test(description = "See NSG WFS 2.0 Profile: Requirement 26")
    public void ImplementsEnhancedPaging( ITestContext testContext ) {
        this.wfsMetadata = (Document) testContext.getSuite().getAttribute( TEST_SUBJECT.getName() );
        String constraintValueTransactionalWFS = getConstraintValue( this.wfsMetadata, "ImplementsEnhancedPaging" );
        assertTrueOrFalse( constraintValueTransactionalWFS, "ImplementsEnhancedPaging" );
    }

    private void assertTrueOrFalse( String constraintValue, String constraint ) {
        boolean isTrueOrFalse = "TRUE".equals( constraintValue ) || "FALSE".equals( constraintValue );
        assertTrue( isTrueOrFalse, "The constraint " + constraint
                                   + " must be declared and the value must be TRUE or FALSE" );
    }

}