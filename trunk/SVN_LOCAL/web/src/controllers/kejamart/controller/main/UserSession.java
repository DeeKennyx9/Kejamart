package kejamart.controller.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kejamart.helper.Settings;
import kejamart.model.Alerts;
import kejamart.model.Messages;
import kejamart.model.Profile;
import kejamart.model.Property;
import kejamart.model.PropertyPics;

@SuppressWarnings("static-access")
public class UserSession implements Settings{
    
	static private boolean isUser;
	static private boolean isAdmin;
    static private boolean isMerchant;
    static private boolean isLoggedIn;
    
   public static Profile getUserFromRequest(HttpServletRequest request){
    	HttpSession session = request.getSession(false);
    	return ((Profile)session.getAttribute(SESSION_USER));
   }
   
   public static Profile getUserNameRequest(HttpServletRequest request){
	   	HttpSession session = request.getSession(false);
	   	return ((Profile)session.getAttribute(SESSION_NAME));
   }     
    
   public static Profile getUserLoginFromRequest(HttpServletRequest request){
    	HttpSession session = request.getSession(false);
    	return ((Profile)session.getAttribute(LOGIN_DETAILS));
   }
   
   public static Profile getProfileFromRequest(HttpServletRequest request){
   	HttpSession session = request.getSession(false);
   	return ((Profile)session.getAttribute(PROFILE_ID));
   }   
   
   public static Alerts getAlertFromRequest(HttpServletRequest request){
   	HttpSession session = request.getSession(false);
   	return ((Alerts)session.getAttribute(REQUEST_DETAILS));
   }  
   
   public static Property getPropertyFromRequest(HttpServletRequest request){
	   	HttpSession session = request.getSession(false);
	   	return ((Property)session.getAttribute(PROPERTY_DETAILS));
   }  
   
   public static Property getViewFromRequest(HttpServletRequest request){
	   	HttpSession session = request.getSession(false);
	   	return ((Property)session.getAttribute(VIEW_DETAILS));
   } 
   
   public static Property getPropertyNameFromRequest(HttpServletRequest request){
	   	HttpSession session = request.getSession(false);
	   	return ((Property)session.getAttribute(PROPERTY_NAME));
  }   
   
   public static Property getPropertyUnitFromRequest(HttpServletRequest request){
	   	HttpSession session = request.getSession(false);
	   	return ((Property)session.getAttribute(PROPERTY_UNIT));
 }    
   
   public static PropertyPics getPropertyPicsFromRequest(HttpServletRequest request){
	   	HttpSession session = request.getSession(false);
	   	return ((PropertyPics)session.getAttribute(PICTURE_DETAILS));
  }      
   
   public static Messages getMessagesFromRequest(HttpServletRequest request){
	   	HttpSession session = request.getSession(false);
	   	return ((Messages)session.getAttribute(MESSAGE_ID));
 }    
    
	public static boolean isUser() {
		return isUser;
	}

	public static void setUser(boolean isUser) {
		UserSession.isUser = isUser;
	}
	
	public static boolean isMerchant() {
		return isMerchant;
	}

	public static void setMerchant(boolean isMerchant) {
		UserSession.isMerchant = isMerchant;
	}
	
 	public boolean isAdmin() {
		return this.isAdmin;
	}

	public static void setAdmin(boolean isAdmin) {
		UserSession.isAdmin = isAdmin;
	}

	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
