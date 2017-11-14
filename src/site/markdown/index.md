
# Conformance Test Suite: NSG WFS 2.0 Profile

## Scope

This executable test suite (ETS) verifies the conformance of the implementation 
under test (IUT) with respect to the set of relevant specifications depicted in 
Figure 1. Conformance testing is a kind of "black box" testing that examines the 
externally visible characteristics or behaviors of the IUT while disregarding 
any implementation details.

**Figure 1: Relevant specifications**

![Set of relevant specifications](img/specifications.png)

Several conformance classes are defined in the principal specifications; the ones 
listed below are covered by this test suite:

* NSG Basic WFS Conformance Class
    - TODO: List capabilities of this conformance class
* NSG Locking WFS 
    - TODO: List capabilities of this conformance class
* Inheritance
    - TODO: List capabilities of this conformance class
* Remote Resolve
    - TODO: List capabilities of this conformance class
* NSG Manage Stored Queries
    - List capabilities of this conformance class
* SOAP
    - TODO: List capabilities of this conformance class
* Enhanced Paging
    - TODO: List capabilities of this conformance class

## Test requirements

The documents listed below stipulate requirements that must be satisfied by a 
conforming implementation.

1. [Web Content Accessibility Guidelines (WCAG) 2.0](http://www.w3.org/TR/WCAG20/)
2. [Extensible Markup Language (XML) 1.0, Fifth Edition](http://www.w3.org/TR/xml/)
3. [RFC 7231](https://tools.ietf.org/html/rfc7231) - Hypertext Transfer Protocol 
(HTTP/1.1): Semantics and Content 

The test suite is schema-aware in the sense that the WFS under test does not need to support any particular application schema or to be pre-loaded with specialized test data. However, the following preconditions must be satisfied by the implementation under test (IUT):

* The GML application schema meets the requirements of the GML conformance class "GML application schemas defining features and feature collections" (ISO 19136, A.1.4).
* Data are available for at least one feature type advertised in the capabilities document.

A feature identifier may be supplied for the purpose of verifying the behavior of the `GetFeatureById` stored query.

## Test coverage

TODO: add list of not implemented conformance classes/tests

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

* Integrated development environment (IDE): The main Java class is org.opengis.cite.iso19142.TestNGController.
* REST API: Submit a request that includes the necessary arguments to the test run controller (/rest/suites/wfs20/1.26/run).
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
      <td>A URI that refers to a representation of the service capabilities document. This document does not need to be obtained from the service under test (SUT), but it must describe the SUT. Ampersand ('&amp;') characters appearing within a query parameter value must be percent-encoded as %26.</td>
    </tr>
	  <tr>
      <td>fid</td>
      <td>NCName</td>
      <td>O</td>
      <td>An identifier that matches the @gml:id attribute value of an available feature instance (may be omitted for "Basic WFS" implementations).</td>
    </tr>
	</tbody>
</table>

**Note:** A test method is skipped if any preconditions were not satisfied. Test prerequisites are usually checked in a configuration method; the results of these can be viewed in the TestNG report by selecting the "Config" check box.

Which tests are actually run is determined by the content of the WFS capabilities document; in particular, the conformance classes that the implementation claims to support. There is a service constraint defined for each conformance class, except for the mandatory "Simple WFS" conformance class (see ISO 19142, Table 13). The boolean-valued service constraints are listed in the OperationsMetadata section of the capabilities document as shown below.