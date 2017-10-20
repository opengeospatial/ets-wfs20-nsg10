package org.opengis.cite.wfs20.nsg.testsuite.pageresults;

/**
 * Container class for relevant detail from GetFeature response with resultType="index".
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class ResultSetIdAndNumberOfFeatures {

    private String resultSetId;

    private int numberOfFeatures;

    /**
     * @param resultSetId
     *            the value of the resultSetId
     * @param numberOfFeatures
     *            the value of the numberMatched attribute
     */
    public ResultSetIdAndNumberOfFeatures( String resultSetId, int numberOfFeatures ) {

        this.resultSetId = resultSetId;
        this.numberOfFeatures = numberOfFeatures;
    }

    /**
     * @return the value of the resultSetId
     */
    public String getResultSetId() {
        return resultSetId;
    }

    /**
     * @return the value of the numberMatched attribute
     */
    public int getNumberOfFeatures() {
        return numberOfFeatures;
    }
}