package kejamart.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.model.Profile;

public class LogDAOImpl extends HibernateDaoSupport implements LogDAO{

	public static Logger logger = Logger.getLogger(LogDAOImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean getUserByEmailAndPassword(String email, String password)
			throws Exception {
		boolean userFound = false;
		if (password == null || email == null) {
			throw new Exception("Password and email parameter cannot be null!");
		}
		String queryString = "from Profile b where b.password=" + password	+ " b.email = ?";
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
       			System.out.println("Email is correct");
       			emailMatch =  true;
       		}
       		return emailMatch;
       	}
       
       @SuppressWarnings("unchecked")
   	   @Override
       public boolean checkPassword(String password){
           System.out.println("Verify if password belongs to email");
           boolean passwordMatch = false;
           
           String queryString = "from Profile o where o.password = ?";
      		List<Profile> profile = getHibernateTemplate().find(queryString,
      				password);
           
      		if (profile.size() == 0) {
      			passwordMatch = false;
       		}
       		
       		else if(profile.size() == 1){
       			System.out.println("Password is correct");
       			passwordMatch =  true;
       		}
       		return passwordMatch;
       	}     
       
       @SuppressWarnings("unchecked")
   	   @Override
       public boolean isUser(String email){
           System.out.println("Check if User has DEFAULT Roles");
           boolean emailMatch = false;
           
           String queryString = "from Profile u, Roles r where r.role = 'USER' and r.role = u.role and u.email = ?";
      		List<Profile> profile = getHibernateTemplate().find(queryString,
      				email);
           
      		if (profile.size() == 0) {
      			emailMatch = false;
       		}
       		
       		else if(profile.size() == 1){
       			System.out.println("User is Default");
       			emailMatch =  true;
       		}
       		return emailMatch;
       	}       
       
       @SuppressWarnings("unchecked")
   	   @Override
       public boolean isAdmin(String email){
           System.out.println("Check if User has ADMIN roles");
           boolean emailMatch = false;
           
           String queryString = "from Profile u, Roles r where r.role = 'ADMIN' and r.role = u.role and u.email = ?";
      		List<Profile> profile = getHibernateTemplate().find(queryString,
      				email);
           
      		if (profile.size() == 0) {
      			emailMatch = false;
       		}
       		
       		else if(profile.size() == 1){
       			System.out.println("User is Admin");
       			emailMatch =  true;
       		}
       		return emailMatch;
       	}
       
       @SuppressWarnings("unchecked")
   	   @Override
       public boolean isMerchant(String email){
           System.out.println("Check if User has BUSINESS roles");
           boolean emailMatch = false;
           
           String queryString = "from Profile u, Roles r where r.role = 'MANAGER' and r.role = u.role and u.email = ?";
      		List<Profile> profile = getHibernateTemplate().find(queryString,
      				email);
           
      		if (profile.size() == 0) {
      			emailMatch = false;
       		}
       		
       		else if(profile.size() == 1){
       			System.out.println("User is Manager");
       			emailMatch =  true;
       		}
       		return emailMatch;
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
     
}