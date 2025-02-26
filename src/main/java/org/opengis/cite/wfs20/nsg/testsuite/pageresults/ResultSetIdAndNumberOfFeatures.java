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
	 * <p>
	 * Constructor for ResultSetIdAndNumberOfFeatures.
	 * </p>
	 * @param resultSetId the value of the resultSetId
	 * @param numberOfFeatures the value of the numberMatched attribute
	 */
	public ResultSetIdAndNumberOfFeatures(String resultSetId, int numberOfFeatures) {

		this.resultSetId = resultSetId;
		this.numberOfFeatures = numberOfFeatures;
	}

	/**
	 * <p>
	 * Getter for the field <code>resultSetId</code>.
	 * </p>
	 * @return the value of the resultSetId
	 */
	public String getResultSetId() {
		return resultSetId;
	}

	/**
	 * <p>
	 * Getter for the field <code>numberOfFeatures</code>.
	 * </p>
	 * @return the value of the numberMatched attribute
	 */
	public int getNumberOfFeatures() {
		return numberOfFeatures;
	}

}
