package kejamart.dao;

import java.util.Collection;
import java.util.List;

import kejamart.model.Profile;

public interface ProfileDAO {

	public void addProfile(Profile profile);
	public void updateProfile(Profile profile) throws Exception;
	public Profile getProfileById(int id) throws Exception;
	public String getProfileByEmail(String email) throws Exception;
	public Profile getUserByEmail(String email) throws Exception;
	public String getProfileByPassword(String password) throws Exception;
	public void deleteProfile(Profile profile) throws Exception;
	public List<Profile> getProfiles();
	public int countProfile();
	boolean getUserByEmailAndPassword(String email, String password) throws Exception;
	public boolean checkLogin(String email, String password) throws Exception;
	public boolean isValidUser(String username, String password) throws Exception;
	boolean checkEmail(String email);
	public List<Profile> getProfilesForUser(String email);
	public boolean idExists(int id);
	public void editProfile(Profile profile, int id) throws Exception;
	public List<Profile> getProfilesForId(int id);
	public List<Profile> search(String email);
	public Collection<Profile> findInfoByEmail(String email);
	public Profile getProfileByUser(String email) throws Exception;
	public void changePassword(Profile profile, int id, String password) throws Exception;
	public boolean checkMobile(String mobile);
	public boolean uncheckEmail(String email);

}
