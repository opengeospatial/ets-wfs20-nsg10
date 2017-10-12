package org.opengis.cite.wfs20.nsg.utils;

import static java.util.UUID.randomUUID;
import static org.opengis.cite.wfs20.nsg.utils.ValidationUtils.assertUuidOrGuideId;

import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidationUtilsTest {

    @Test(expected = AssertionError.class)
    public void testAssertUuidOrGuideId_null() {
        assertUuidOrGuideId( null );
    }

    @Test(expected = AssertionError.class)
    public void testAssertUuidOrGuideId_empty() {
        assertUuidOrGuideId( "" );
    }

    @Test(expected = AssertionError.class)
    public void testAssertUuidOrGuideId_invalid_UUID() {
        assertUuidOrGuideId( "125896ff8-4d8f-5zg5-7dre-kjhgf" );
    }

    @Test(expected = AssertionError.class)
    public void testAssertUuidOrGuideId_invalid_GUIDEID_withoutSeperatorPrefixSuffix() {
        assertUuidOrGuideId( "guide://presuf" );
    }

    @Test(expected = AssertionError.class)
    public void testAssertUuidOrGuideId_invalid_GUIDEID_withoutSeperatorBeforePrefix() {
        assertUuidOrGuideId( "guide:pre/suf" );
    }

    @Test(expected = AssertionError.class)
    public void testAssertUuidOrGuideId_invalid_GUIDEID_invalidString() {
        assertUuidOrGuideId( "guides://pre/suf" );
    }

    @Test
    public void testAssertUuidOrGuideId_UUID() {
        assertUuidOrGuideId( randomUUID().toString() );
    }

    @Test
    public void testAssertUuidOrGuideId_GUIDEID() {
        assertUuidOrGuideId( "guide://abc/def" );
    }

    @Test
    public void testAssertUuidOrGuideId_GUIDEID_withIntegers() {
        assertUuidOrGuideId( "guide://a1bc/zg99" );
    }

}
