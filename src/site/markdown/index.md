
# Conformance Test Suite: NSG WFS 2.0 Profile

## Scope

This executable test suite (ETS) verifies the conformance of the implementation 
under test (IUT). Conformance testing is a kind of "black box" testing that examines the 
externally visible characteristics or behaviors of the IUT while disregarding 
any implementation details.

Several conformance classes are defined in the principal specifications; the ones 
listed below are covered by this test suite:

* NSG Basic WFS Conformance Class
    - The service shall comply with the DGIWG Basic WFS conformance class as extended and restricted by this specification.
* NSG Locking WFS 
    - The service shall comply with the DGIWG Locking (Transactional) WFS conformance class as extended and restricted by this specification.
* Inheritance
    - The service shall implement the schema-element() function for XPath expressions.
* Remote Resolve
    - The service shall implement the ability to resolve remote resource references.
* NSG Manage Stored Queries
    - The service shall implement the CreateStoredQuery and the DropStoredQuery operations.
* SOAP
    - The service shall implement XML encoded requests and results within SOAP Envelopes.
* Enhanced Paging
    - The service shall implement the ability to return the page specified by the user from a set of response features or values.

## Test requirements

The documents listed below stipulate requirements that must be satisfied by a 
conforming implementation.

1. [National System for Geospatial-Intelligence (NSG) Web Feature Service 2.0](https://nsgreg.nga.mil/doc/view?i=4283)
2. [DGIWG - Web Feature Service 2.0 Profile](https://portal.opengeospatial.org/files/?artifact_id=66933)
3. [OGC Web Feature Service 2.0 Interface Standard](http://docs.opengeospatial.org/is/09-025r2/09-025r2.html)
4. [OGC Filter Encoding 2.0 Encoding Standard](http://docs.opengeospatial.org/is/09-026r2/09-026r2.html)

## Test coverage

Some optional conformance classes are not covered by the test suite. The following capabilities are not tested:

 * Inheritance
 * Remote resolve

As a request timeout cannot be produced by a test suite, tests for following requirements are restricted:

 * Requirement 12 - OperationProcessingTimeout is not tested.
 * Requirement 15 - Is not tested as request timeout is required.

Hints:

 * Requirements 22 and 24 are included in NSG Basic WFS Conformance Class test group. However, the specification states that these two requirements should be included in NSG Locking WFS Conformance Class test group. Due to technical reasons this is not possible.

## Test suite structure

The test suite definition file (testng.xml) is located in the root package, 
`org.opengis.cite.wfs20.nsg`. A conformance class corresponds to a &lt;test&gt; element, each 
of which includes a set of test classes that contain the actual test methods. 
The general structure of the test suite is shown in Table 1.

<table>
  <caption>Table 1 - Test suite structure</caption>
  <thead>
    <tr style="text-align: left; background-color: LightCyan">
      <th>Conformance class</th>
      <th>Test packages and classes</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Preconditions</td>
      <td>
       <ul>
         <li>org.opengis.cite.iso19142.SuitePreconditions</li>
       </ul>
      </td>
    </tr>
    <tr>
      <td>All GML application schemas</td>
      <td>
       <ul>
        <li>org.opengis.cite.iso19136.general.XMLSchemaTests</li>
        <li>org.opengis.cite.iso19136.general.GeneralSchemaTests</li>
        <li>org.opengis.cite.iso19136.general.ModelAndSyntaxTests</li>
        <li>org.opengis.cite.iso19136.general.ComplexPropertyTests</li>
       </ul>
      </td>
    </tr>
    <tr>
      <td>GML application schemas defining features</td>
      <td>
       <ul>
         <li>org.opengis.cite.iso19136.components.FeatureComponentTests</li>
       </ul>
      </td>
    </tr>
    <tr>
      <td>NSG Basic WFS Conformance Class</td>
      <td>
       <ul>
        <li>org.opengis.cite.iso19142.simple</li>
        <li>org.opengis.cite.iso19142.basic</li>
        <li>org.opengis.cite.iso19142.basic.filter</li>
        <li>org.opengis.cite.iso19142.basic.filter.temporal</li>
        <li>org.opengis.cite.iso19142.basic.filter.spatial</li>
        <li>org.opengis.cite.iso19142.paging</li>
        <li>org.opengis.cite.iso19142.versioning</li>
        <li>org.opengis.cite.iso19142.joins.SpatialJoinTests</li>
        <li>de.latlon.ets.wfs20.core.dgiwg.testsuite.getcapabilities.GetCapabilitiesKeywordTest</li>
        <li>de.latlon.ets.wfs20.core.dgiwg.testsuite.getcapabilities.GetCapabilitiesVersionTest</li>
        <li>de.latlon.ets.wfs20.core.dgiwg.testsuite.getcapabilities.GetCapabilitiesFeatureTypeElementsTest</li>
        <li>de.latlon.ets.wfs20.core.dgiwg.testsuite.describestoredqueries.DescribeStoredQueriesElementsTest</li>
        <li>de.latlon.ets.wfs20.core.wfs20.testsuite.basic.filter.PropertyIsBetweenOperatorTests</li>
        <li>de.latlon.ets.wfs20.core.wfs20.testsuite.spatialfilter.SpatialFilterTest</li>
        <li>de.latlon.ets.wfs20.core.wfs20.testsuite.spatialfilter.ExtendedSpatialFilterTest</li>
        <li>de.latlon.ets.wfs20.core.wfs20.testsuite.temporalfilter.TemporalFilterTest</li>
        <li>de.latlon.ets.wfs20.core.wfs20.testsuite.temporalfilter.ExtendedTemporalFilterTest</li>
        <li>de.latlon.ets.wfs20.core.dgiwg.testsuite.getcapabilities.GetCapabilitiesSrsTest</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.CapabilitiesServiceBindings</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.ServiceConstraints (partly)</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.OperationConstraints (partly)</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.describefeaturetype.DescribeFeatureTypeOutputFormat</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getfeature.GetFeatureOutputFormat</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getfeaturewithlock.GetFeatureWithLockOutputFormat</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getpropertyvalue.GetPropertyValueOutputFormat</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.pageresults.PageResultsOutputFormat</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.featureinstance.InstanceIdentifier</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getfeature.CountParameter</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.CapabilitiesAbstract</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.CapabilitiesProfile</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.CapabilitiesTimeout</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getfeature.GetFeatureWIthResultTypeIndex</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.AccessConstraints</li>
       </ul>
      </td>
    </tr>
    <tr>
      <td>NSG Locking WFS</td>
      <td>
       <ul>
        <li>org.opengis.cite.iso19142.transaction.TransactionCapabilitiesTests</li>
        <li>org.opengis.cite.iso19142.transaction.Update</li>
        <li>org.opengis.cite.iso19142.transaction.InsertTests</li>
        <li>org.opengis.cite.iso19142.transaction.ReplaceTests</li>
        <li>org.opengis.cite.iso19142.transaction.DeleteTests</li>
        <li>org.opengis.cite.iso19142.locking.LockingCapabilitiesTests</li>
        <li>org.opengis.cite.iso19142.locking.LockFeatureTests</li>
        <li>org.opengis.cite.iso19142.locking.GetFeatureWithLockTests</li>
        <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.ServiceConstraints (partly)</li>
       </ul>
      </td>
    </tr>
    <tr>
      <td>Inheritance</td>
      <td>Not tested yet</td>
    </tr>
    <tr>
      <td>Remote Resolve</td>
      <td>
       <ul>
         <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.OperationConstraints (partly)</li>
       </ul>
      </td>
    </tr>
    <tr>
      <td>NSG Manage Stored Queries</td>
      <td>
       <ul>
         <li>org.opengis.cite.iso19142.querymgmt</li>
       </ul>
      </td>
    </tr>
    <tr>
      <td>Enhanced Paging</td>
      <td>
       <ul>
         <li>org.opengis.cite.wfs20.nsg.testsuite.getcapabilities.ServiceConstraints(partly)</li>
         <li>org.opengis.cite.wfs20.nsg.testsuite.pageresults.PageResults</li>
       </ul>
      </td>
    </tr>
  </tbody>
</table>

The Javadoc documentation provides more detailed information about the test 
methods that constitute the suite.


## How to run the tests

The test suite may be run in any of the following environments:

* Integrated development environment (IDE): The main Java class is org.opengis.cite.wfs20.nsg.TestNGController.
* REST API: Submit a request that includes the necessary arguments to the test run controller (/rest/suites/wfs20-nsg/0.2/run).
* TEAM Engine: Run the CTL script located in the /src/main/ctl/ directory.


The test run arguments are summarized in Table 2. The _Obligation_ descriptor can 
have the following values: M (mandatory), O (optional), or C (conditional).

<table>
	<caption>Table 2 - Test run arguments</caption>
	<thead>
    <tr>
      <th>Name</th>
      <th>Value domain</th>
	    <th>Obligation</th>
	    <th>Description</th>
    </tr>
  </thead>
	<tbody>
    <tr>
      <td>wfs</td>
      <td>URI</td>
      <td>M</td>
      <td>A URI that refers to a representation of the service capabilities document. This document does not need to be obtained from the service under test (SUT), but it must describe the SUT. Ampersand ('&amp;') characters appearing within a query parameter value must be percent-encoded as '%26'.</td>
    </tr>
	</tbody>
</table>

**Note:** A test method is skipped if any preconditions were not satisfied. Test prerequisites are usually checked in a configuration method; the results of these can be viewed in the TestNG report by selecting the "Config" check box.