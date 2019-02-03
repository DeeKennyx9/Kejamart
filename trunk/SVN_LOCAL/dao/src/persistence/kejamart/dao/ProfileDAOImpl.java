package kejamart.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import kejamart.dao.ProfileDAO;
import kejamart.model.Profile;

	public class ProfileDAOImpl extends HibernateDaoSupport implements ProfileDAO {
		
		@SuppressWarnings("unchecked")
		public Profile getProfileById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Profile p where p.id = ?";

			List<Profile> profile = (List<Profile>) getHibernateTemplate().find(queryString, id);

			if (profile.size() > 1) {
				throw new Exception("More than one Profile with same Id");
			}
			return profile.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public String getProfileByEmail(String email) throws Exception {
			if (email == null) {
				throw new Exception("email parameter cannot be null!");
			}

			String queryString = "from Profile p where p.email = ?";

			List<Profile> profile = (List<Profile>) getHibernateTemplate().find(queryString, email);

			if (profile.size() > 1) {
				throw new Exception("More than one Profile with same email");
			}
			return email;
		}
		
		@SuppressWarnings("unchecked")
		public Profile getProfileByUser(String email) throws Exception {
			if (email == null) {
				throw new Exception("email parameter cannot be null!");
			}

			String queryString = "from Profile p where p.email = ?";

			List<Profile> profile = (List<Profile>) getHibernateTemplate().find(queryString, email);

			if (profile.size() > 1) {
				throw new Exception("More than one Profile with same email");
			}
			return profile.get(0);
		}		
		
		@SuppressWarnings("unchecked")
	   	public Profile getUserByEmail(String email) throws Exception {
	   		if (email == null) {
	   			throw new Exception("Email parameter cannot be null!");
	   		}
	   		String queryString = "from Profile p where p.email = ?";
	   		List<Profile> prof = getHibernateTemplate().find(queryString,
	   				email);

	   		if (prof.size() == 0) {
	   			return null;
	   		}

	   		if (prof.size() > 1) {
	   			throw new Exception("More than one person with same email");
	   		}

	   		return prof.get(0);
	   	}
		
		@SuppressWarnings("unchecked")
		public String getProfileByPassword(String password) throws Exception {
			if (password == null) {
				throw new Exception("password parameter cannot be null!");
			}

			String queryString = "from Profile p where p.password = ?";

			List<Profile> profile = (List<Profile>) getHibernateTemplate().find(queryString, password);

			if (profile.size() > 1) {
				throw new Exception("More than one Profile with same password");
			}
			return password;
		}
	   
		public void addProfile(Profile profile){
			
			getHibernateTemplate().save(profile);
			
		}

		public void editProfile(Profile profile, int id) throws Exception {

			Profile profileToUpdate = getProfileById(profile.getId());
			
			profileToUpdate.setFirstName(profile.getFirstName());
			profileToUpdate.setLastName(profile.getLastName());
			profileToUpdate.setPassword(profile.getPassword());
			profileToUpdate.setEmail(profile.getEmail());
			profileToUpdate.setCategory(profile.getCategory());
			profileToUpdate.setMailing(profile.getMailing());
			profileToUpdate.setMobile(profile.getMobile());
			
			getHibernateTemplate().update(profileToUpdate);
			
		}
		
		public void updateProfile(Profile profile) throws Exception {
			
			Profile profileToUpdate = getProfileById(profile.getId());
			
			profileToUpdate.setFirstName(profile.getFirstName());
			profileToUpdate.setLastName(profile.getLastName());
			//profileToUpdate.setPassword(profile.getPassword());
			profileToUpdate.setEmail(profile.getEmail());
			profileToUpdate.setCategory(profile.getCategory());
			profileToUpdate.setMailing(profile.getMailing());
			profileToUpdate.setMobile(profile.getMobile());
			
			getHibernateTemplate().update(profileToUpdate);
		}
		
		public void changePassword(Profile profile, int id, String password) throws Exception {
			
			Profile profileToUpdate = getProfileById(id);
			
			profileToUpdate.setPassword(password);
			
			getHibernateTemplate().update(profileToUpdate);
		}		
		
		public void deleteProfile(Profile profile) throws Exception{
			Profile profileToDelete = getProfileById(profile.getId());
				getHibernateTemplate().delete(profileToDelete);
		}
		
		public List<Profile> getProfiles() {
			return this.getHibernateTemplate().loadAll(Profile.class);
		
		}
			  
		public int countProfile(){
			
			int iSize = this.getHibernateTemplate().loadAll(Profile.class).size();
			
			return iSize;
			
	    }
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean getUserByEmailAndPassword(String email, String password)
				throws Exception {
			boolean userFound = false;
			if (password == null || email == null) {
				throw new Exception("Password and email parameter cannot be null!");
			}
			String queryString = "from Profile b where b.email=? and b.password=?";
			List<Profile> profile = getHibernateTemplate().find(queryString,
					email);

			if (profile.size() == 0) {
				userFound = false;
			}

			else if (profile.size() == 1) {
				userFound = true;
			}

			return userFound;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean isValidUser(String email, String password) throws Exception{
			
			boolean userFound = false;
			if (password == null || email == null) {
				throw new Exception("Password and email parameter cannot be null!");
			}
			
			String queryString = "from Profile b where b.email=? and b.password=?";
			List<Profile> profile = getHibernateTemplate().find(queryString,
					email);

			if (profile.size() == 0) {
				userFound = false;
			}

			else if (profile.size() == 1) {
				userFound = true;
			}

			return userFound;	
		}
		
	       @SuppressWarnings("unchecked")
	   	   @Override
	       public boolean checkLogin(String email, String password) throws Exception{
				System.out.println("In Check login");
				boolean userFound = false;
				
				if (email == null) {
					throw new Exception("Email parameter cannot be null!");
				}
				String queryString = "from Profile b where b.email=? and b.password=?";
				List<Profile> profile = getHibernateTemplate().find(queryString,
						email);

				if ((profile != null) && (profile.size() > 0)) {
					userFound= true;
				}

				return userFound;              
	      }
	       
	       @SuppressWarnings("unchecked")
		   @Override
	       public boolean checkMobile(String mobile){
	           System.out.println("Verify if mobile exists");
	           boolean mobileMatch = false;
	           
	           String queryString = "from Profile o where o.mobile = ?";
	      		List<Profile> profile = getHibernateTemplate().find(queryString,
	      				mobile);
	           
	      		if (profile.size() == 0) {
	      			mobileMatch = false;
	       		}
	       		
	       		else if(profile.size() == 1){
	       			mobileMatch =  true;
	       		}
	       		return mobileMatch;
	       	}	          
	       
		   @SuppressWarnings("unchecked")
		   @Override
	       public boolean checkEmail(String email){
	           System.out.println("Verify if email exists");
	           boolean emailMatch = false;
	           
	           String queryString = "from Profile o where o.email = ?";
	      	   List<Profile> profile = getHibernateTemplate().find(queryString,
	      				email);
	           
	      		if (profile.size() == 0) {
	      			emailMatch = false;
	       		}
	       		
	       		else if(profile.size() == 1){
	       			emailMatch =  true;
	       		}
	       		return emailMatch;
	       	}	    
		   
		   @SuppressWarnings("unchecked")
		   @Override
	       public boolean uncheckEmail(String email){
	           System.out.println("Verify if email exists");
	           boolean emailMatch = false;
	           
	           String queryString = "from Profile o where o.email != ?";
	      	   List<Profile> profile = getHibernateTemplate().find(queryString,
	      				email);
	           
	      		if (profile.size() == 0) {
	      			emailMatch = false;
	       		}
	       		
	       		else if(profile.size() == 1){
	       			emailMatch =  true;
	       		}
	       		return emailMatch;
	       	}		   
		   
			@SuppressWarnings("unchecked")
			public List<Profile> getProfilesForUser(String email) {
				List<Profile> profiles = null;
				String query="From Profile p where p.email=? ";
				profiles = getHibernateTemplate().find(query,email);
				return profiles;
			}		
			
			@SuppressWarnings("unchecked")
			public List<Profile> getProfilesForId(int id) {
				List<Profile> profiles = null;
				String query="From Profile p where p.id=? ";
				profiles = getHibernateTemplate().find(query,id);
				return profiles;
			}				
			
			@SuppressWarnings("unchecked")
			@Override
		    public boolean idExists(int id){
		       System.out.println("Check if ID Exists");
		       boolean emailMatch = false;
		          
		       String queryString = "from Profile p where p.id = ?";
			  
		       List<Profile> profile = (List<Profile>) getHibernateTemplate().find(queryString, id);
		           
		       if (profile.size() == 0) {
		      			emailMatch = false;
		       		}
		       		
		       		else if(profile.size() == 1){
		       			emailMatch =  true;
		       		}
		       		return emailMatch;
		       	} 			
			
			@SuppressWarnings("unchecked")
			public List<Profile> search(String email) {
				List<Profile> profiles = null;
				String query="From Profile p where p.email = ?";
				//String query="From Profile p where p.email LIKE" + "'%" +email+ "%'";
				profiles = getHibernateTemplate().find(query, email);
				return profiles;
			}	
			
		    @SuppressWarnings("unchecked")
			public Collection<Profile> findInfoByEmail(String email){
		        //logger.info("fetching data with specified query");
		        return this.getHibernateTemplate().find("from Profile p where p.email LIKE '%?%' order by p.email", email);
		        } 			
			
}
	
	
	