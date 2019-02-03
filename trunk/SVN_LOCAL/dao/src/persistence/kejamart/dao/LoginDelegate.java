package kejamart.dao;

public class LoginDelegate
{
		private ProfileDAO profileDAO;

		public ProfileDAO getProfileDAO(){
				return this.profileDAO;
		}

		public void setProfileDAO(ProfileDAO profileDAO){
				this.profileDAO = profileDAO;
		}

		public boolean isValidUser(String email, String password) throws Exception{
		    return profileDAO.isValidUser(email, password);
    }
		
}
