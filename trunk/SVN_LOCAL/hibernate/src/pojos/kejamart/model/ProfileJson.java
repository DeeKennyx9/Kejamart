package kejamart.model;


import org.json.*;

public class ProfileJson {
	/**
	 * set the variables
	 */	
	private String name;
	
	private JSONArray profileData;
	

	/**
	 * @return the email
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param email the email to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the profileData
	 */
	public JSONArray getProfileData() {
		return this.profileData;
	}

	/**
	 * @param profileData the profileData to set
	 */
	public void setProfileData(JSONArray profileData) {
		this.profileData = profileData;
	}

}
