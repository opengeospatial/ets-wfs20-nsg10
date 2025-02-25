package org.opengis.cite.wfs20.nsg.utils;

import static javax.xml.xpath.XPathConstants.STRING;
import static org.opengis.cite.iso19142.ProtocolBinding.GET;
import static org.opengis.cite.wfs20.nsg.utils.NamespaceUtils.withStandardBindings;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPathExpressionException;

import org.opengis.cite.iso19142.ProtocolBinding;
import org.opengis.cite.iso19142.util.WFSClient;
import org.opengis.cite.iso19142.util.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

/**
 * Enhanced the {@link WFSClient} by the support of transforming PageResults POST binding requests to GET query string
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class NsgWfsClient extends WFSClient {

    private static final Logger LOGR = Logger.getLogger( NsgWfsClient.class.getPackage().getName() );

    NsgWfsClient() {
    }

    public NsgWfsClient( Document wfsMetadata ) {
        super( wfsMetadata );
    }

    @Override
    public Response submitRequest( Source entity, ProtocolBinding binding, URI endpoint ) {
        if ( entity instanceof DOMSource ) {
            Node node = ( (DOMSource) entity ).getNode();
            String docElemName = node.getLocalName();
            if ( "PageResults".equals( docElemName ) && GET.equals( binding ) ) {
                String queryString = createQueryString( node );
                URI requestURI = UriBuilder.fromUri( endpoint ).replaceQuery( queryString ).build();
                LOGR.log( Level.FINE, String.format( "Request URI: %s", requestURI ) );
                WebTarget target = this.client.target(requestURI);
                Builder reqBuilder = target.request();                
                return reqBuilder.buildGet().invoke();
            }
        }
        return super.submitRequest( entity, binding, endpoint );
    }

    /**
     * Creates a query string from the passed PageResults request
     * 
     * @param entity
     *            never <code>null</code>
     * @return the query string, never <code>null</code>
     */
    String createQueryString( Node entity ) {
        try {
            StringBuilder sb = new StringBuilder();
            appendValue( sb, entity, "service", "WFS" );
            appendValue( sb, entity, "version", "2.0.2" );
            sb.append( "REQUEST=PageResults&" );
            appendValue( sb, entity, "startIndex", null );
            appendValue( sb, entity, "count", null );
            appendValue( sb, entity, "outputFormat", null );
            appendValue( sb, entity, "resultType", null );
            appendValue( sb, entity, "timeout", null );
            appendResultSetId( sb, entity );
            return sb.toString();
        } catch ( XPathExpressionException e ) {
            throw new RuntimeException( "Could nor create query string for PageResults request with GET" );
        }
    }

    private void appendResultSetId( StringBuilder sb, Node entity )
                            throws XPathExpressionException {
        String xpath = "//nsg:PageResults/nsg:resultSetID";
        String resultSetId = (String) XMLUtils.evaluateXPath( entity, xpath, withStandardBindings().getAllBindings(),
                                                              STRING );
        sb.append( "RESULTSETID=" ).append( resultSetId );
    }

    private void appendValue( StringBuilder sb, Node attributes, String parameter, String defaultValue )
                            throws XPathExpressionException {
        String value = parseValue( attributes, parameter, defaultValue );
        if ( value != null )
            sb.append( parameter.toUpperCase() ).append( "=" ).append( value ).append( "&" );
    }

    private String parseValue( Node entity, String parameter, String defaultValue )
                            throws XPathExpressionException {
        String xpath = "//nsg:PageResults/@" + parameter;
        String value = (String) XMLUtils.evaluateXPath( entity, xpath, withStandardBindings().getAllBindings(), STRING );
        if ( value != null && !value.isEmpty() )
            return value;
        return defaultValue;
    }

}