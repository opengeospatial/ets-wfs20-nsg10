package org.opengis.cite.wfs20.nsg.utils;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class NsgWfsAssertion {

    private static final String UUID_PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";

    // guide://{prefix}/{suffix}
    private static final String GUIDE_ID_PATTERN = "^guide://[0-9a-z]*/[0-9a-z]*";

    /**
     * Validates if the passed id is not null and a valid UUID or GUIDE ID identifier. The format of the GUIDE id is as
     * follows: guide://{prefix}/{suffix}
     * 
     * @param id
     *            the id to validate
     */
    public static void assertUuidOrGuideId( String id ) {
        assertNotNull( id, "Identifier must not be null" );

        boolean idUuid = Pattern.matches( UUID_PATTERN, id );
        boolean isGuideId = Pattern.matches( GUIDE_ID_PATTERN, id );
        assertTrue( idUuid || isGuideId, "Identifier must be a valid UUID or GUIDE ID" );
    }

    /**
     * Asserts if the passed uri is not null and not empty.
     * 
     * @param uri
     *            to validate
     * @param message
     *            used as assertion message
     */
    public static void assertUri( URI uri, String message ) {
        assertNotNull( uri, message );
        assertNotEquals( URI.create( "" ), uri, message );
    }

}
