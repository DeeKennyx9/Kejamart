package kejamart.dao;

import kejamart.model.LoginForm;
import kejamart.model.Profile;

public interface LogDAO{
	
	   public boolean getUserByEmailAndPassword(String email, String password) throws Exception;
       public boolean checkEmail(String email);
       public boolean checkPassword(String password);
	   public boolean isUser(String email);
	   public boolean isAdmin(String email);
	   public boolean isMerchant(String email);
	   public Profile getUserByEmail(String email) throws Exception;

}