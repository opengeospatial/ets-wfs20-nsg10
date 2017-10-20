package org.opengis.cite.wfs20.nsg.utils;

import java.net.URL;
import java.util.logging.Level;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;

import org.opengis.cite.iso19142.util.TestSuiteLogger;
import org.opengis.cite.validation.XmlSchemaCompiler;
import org.xml.sax.SAXException;

import de.latlon.ets.wfs20.core.utils.ValidationUtils;

/**
 * A utility class that provides access to schema files.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SchemaUtils {

    /**
     * Creates a single Schema object representing the NSG WFS schema.
     *
     * @return An immutable Schema object, or <code>null</code> if one cannot be constructed.
     * @see <a href="http://schemas.opengis.net/wfs/2.0/wfs.xsd" target="_blank">XML Schema for WFS 2.0</a>
     */
    public static Schema createWFSSchema() {
        URL entityCatalog = ValidationUtils.class.getResource( "schema-catalog.xml" );
        XmlSchemaCompiler xsdCompiler = new XmlSchemaCompiler( entityCatalog );
        try {
            URL schemaURL = SchemaUtils.class.getResource( "/org/opengis/cite/wfs20/nsg/xsd/nsg/wfs_nsg.xsd" );
            Source xsdSource = new StreamSource( schemaURL.toString() );
            return xsdCompiler.compileXmlSchema( new Source[] { xsdSource } );
        } catch ( SAXException e ) {
            TestSuiteLogger.log( Level.WARNING, "Failed to create WFS Schema object.", e );
        }
        return null;
    }

}
