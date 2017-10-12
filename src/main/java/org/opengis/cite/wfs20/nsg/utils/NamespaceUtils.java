package org.opengis.cite.wfs20.nsg.utils;

import static org.opengis.cite.wfs20.nsg.testsuite.NSGWFSConstants.NSG_NAMESPACE;

import org.opengis.cite.iso19142.util.NamespaceBindings;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class NamespaceUtils {

    /**
     * Creates a NamespaceBindings object that declares the following namespace bindings:
     *
     * <ul>
     * <li>wfs: {@value org.opengis.cite.iso19142.Namespaces#WFS}</li>
     * <li>fes: {@value org.opengis.cite.iso19142.Namespaces#FES}</li>
     * <li>ows: {@value org.opengis.cite.iso19142.Namespaces#OWS}</li>
     * <li>xlink: {@value org.opengis.cite.iso19142.Namespaces#XLINK}</li>
     * <li>gml: {@value org.opengis.cite.iso19142.Namespaces#GML}</li>
     * <li>soap: {@value org.opengis.cite.iso19142.Namespaces#SOAP_ENV}</li>
     * <li>soap11: {@value org.opengis.cite.iso19142.Namespaces#SOAP11}</li>
     * <li>xsi: {@value javax.xml.XMLConstants#W3C_XML_SCHEMA_INSTANCE_NS_URI}</li>
     * </ul>
     *
     * @return A NamespaceBindings object.
     */
    public static NamespaceBindings withStandardBindings() {
        NamespaceBindings nsBindings = NamespaceBindings.withStandardBindings();
        nsBindings.addNamespaceBinding( NSG_NAMESPACE, "nsg" );
        return nsBindings;
    }

}
