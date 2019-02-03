package kejamart.controller.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.LogDAO;
import kejamart.dao.MessagesDAO;
import kejamart.dao.ProfileDAO;
import kejamart.helper.Settings;
import kejamart.helper.StringToMD5;
import kejamart.model.Messages;
import kejamart.model.Profile;

public class LoginController implements Settings {

	public LogDAO logDAO;
	public ProfileDAO profileDAO;
	MessagesDAO messagesDAO;

	public UserSession userSession = new UserSession();
	

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Map model, HttpServletRequest request) {
            Profile profile = new Profile();
            model.put("profile", profile);
        	request.getSession().setAttribute("errmg", "");
        	request.getSession().setAttribute("loginProcess","");
            return "login";
    }
    
	@RequestMapping(value="/loginx", method = RequestMethod.GET)
	public ModelAndView logoutForm(HttpServletResponse response, HttpServletRequest request)
	        throws IOException, ServletException {

		ModelAndView modelAndView = new ModelAndView("login");		
	
		HttpSession session = request.getSession(false);
		session.removeAttribute(" userSession");
		session.invalidate();
	    
		userSession.setLoggedIn(false);

		return modelAndView;
		
	}    
       
        @RequestMapping(method = RequestMethod.POST)
        public ModelAndView processForm(HttpServletRequest request,
    			HttpServletResponse response, @RequestParam("email")String email,@RequestParam("password")String password, @ModelAttribute("profile") @Valid Profile profile, BindingResult result) throws Exception {
	          
	         ModelAndView modelAndView = new ModelAndView("login");
	        // ModelAndView mav = new ModelAndView("search");
	          String processStatus = "";
	          String rUrl = request.getContextPath();
	          
	        // Decrypt password	          
	          StringToMD5 str = new StringToMD5();	          
	          String hashPassword = str.MD5(profile.getPassword());	          
              
              boolean checkEmail = logDAO.checkEmail(profile.getEmail());
              //boolean checkPassx = logDAO.checkPassword(profile.getPassword());
              boolean checkPass = logDAO.checkPassword(hashPassword);
              boolean checkAdmin = logDAO.isAdmin(profile.getEmail());
              boolean checkMerchant = logDAO.isMerchant(profile.getEmail());
              boolean checkUser = logDAO.isUser(profile.getEmail());
              
	          processStatus= "start";
	          System.out.println("Login Process Started");   
  
              
               
  	          if (!result.hasErrors()) { 	        	  
 	        	  
  	        	
                if((checkEmail) && (checkPass) && (checkUser)){                	

                	Profile pr = new Profile();
                	pr = logDAO.getUserByEmail(email);
                	int idValue = pr.getId();
                	//int idValue = 0;
                	//String fnameValue = "";
                	//idValue = pr.getId();
                	String fnameValue = pr.getFirstName();                	
                	request.getSession().setAttribute(SESSION_USER, email);
                	request.getSession().setAttribute(SESSION_NAME, fnameValue);
                	request.getSession().setAttribute(LOGIN_DETAILS, idValue);
            		userSession.setLoggedIn(true);
            				
            		request.getSession().setAttribute("emailz", email);
            		
       	         List<Messages> messages = messagesDAO.getMessagesForEmail(email);     	     	
     	     	 int usralerts = messages.size();
     	     	 System.out.println("Total messages for User: " +usralerts);
     	     	 System.out.println("Message List: " +messages);
     	     	 request.getSession().setAttribute("mtotals", usralerts);
            		
            		//request.getSession().setAttribute("userSession","Welcome " + email+ "&nbsp;|&nbsp;<a href='"+ rUrl + "/logout.html'>Logout</a>");
     	     	    request.getSession().setAttribute("userSession", "<img src ='led-icons/status_online.png'>&nbsp; Hello " + fnameValue);
            		//+ "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='"+ rUrl + "/logout.html'>Logout</a>");
            		//request.getSession().setAttribute("logoutLink","<a href='"+ rUrl + "/logout.html'>Logout</a>");
     	     	    request.getSession().setAttribute("logoutLink","<a href='"+ rUrl + "/loginx.html'>Logout</a>");
            		request.getSession().setAttribute("profileLink","<a href='"+ rUrl + "/profile.html'>Profile</a>");
            		//request.getSession().setAttribute("hom", "<li><a href='"+ rUrl + "/index.html' class='eventimg'>Home</a></li>");
            		request.getSession().setAttribute("sea", "<li><a href='"+ rUrl + "/search.html' class='eventimg'>Home</a></li>");           		
            		request.getSession().setAttribute("prof", "<li><a href='"+ rUrl + "/profile.html' class='eventimg'><img src ='led-icons/user_silhouette.png'>&nbsp;Profile</a></li>");
                	request.getSession().setAttribute("alert", "<li><a href='"+ rUrl + "/requests.html' class='eventimg'><img src ='led-icons/exclamation.png'>&nbsp;Alerts</a></li>");
                	request.getSession().setAttribute("notif", "<li><a href='"+ rUrl + "/notifications.html' class='eventimg'><img src ='led-icons/comment.png'>&nbsp;Notifications</a></li>");
                	//request.getSession().setAttribute("dash", "<li><a class='eventimg'><img src ='led-icons/chart_bar.png'><a/><a href='"+ rUrl + "/dashboard.html' class='eventimg'>Analytics</a></li>");
                	//request.getSession().setAttribute("help", "<li><a class='eventimg'><img src ='led-icons/help.png'><a/><a href='"+ rUrl + "/help.html' class='eventimg'>Help</a></li>");       	
 	             	                	
                	    processStatus = "stop";
                	    System.out.println("Login Process Stopped");
                        System.out.println("Successfully logged in as " + profile.getEmail() + " with password " + profile.getPassword()+" decoded "+ hashPassword);
                        System.out.println("MERCHANT Menu");
                        return new ModelAndView("redirect:/search.html");
                        
                }
                if((checkEmail) && (checkPass) && (checkMerchant)){
                	
                	Profile pr = new Profile();
                	pr = logDAO.getUserByEmail(email);
                	int idValue = pr.getId();
                	//int idValue = 0;
                	//String fnameValue = "";
                	//idValue = pr.getId();
                	String fnameValue = pr.getFirstName();                	
                	request.getSession().setAttribute(SESSION_USER, email);
                	request.getSession().setAttribute(SESSION_NAME, fnameValue);
                	request.getSession().setAttribute(LOGIN_DETAILS, idValue);
            		userSession.setLoggedIn(true);
            				
            		request.getSession().setAttribute("emailz", email);
            		
          	         List<Messages> messages = messagesDAO.getMessagesForEmail(email);     	     	
         	     	 int usralerts = messages.size();
         	     	 System.out.println("Total messages for User: " +usralerts);
         	     	 System.out.println("Message List: " +messages);
         	     	 request.getSession().setAttribute("mtotals", usralerts);            		
            		
             		//request.getSession().setAttribute("userSession","Welcome " + email+ "&nbsp;|&nbsp;<a href='"+ rUrl + "/logout.html'>Logout</a>");
         	     	request.getSession().setAttribute("userSession", "<img src ='led-icons/status_online.png'>&nbsp; Hello " + fnameValue);
             		//+ "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='"+ rUrl + "/logout.html'>Logout</a>");
            		//request.getSession().setAttribute("logoutLink","<a href='"+ rUrl + "/logout.html'>Logout</a>");
         	     	request.getSession().setAttribute("logoutLink","<a href='"+ rUrl + "/loginx.html'>Logout</a>");
             		request.getSession().setAttribute("profileLink","<a href='"+ rUrl + "/profile.html'>Profile</a>");
                	
            		//request.getSession().setAttribute("hom", "<li><a href='"+ rUrl + "/index.html' class='eventimg'>Home</a></li>");
            		request.getSession().setAttribute("sea", "<li><a href='"+ rUrl + "/search.html' class='eventimg'>Home</a></li>");
            		request.getSession().setAttribute("prof", "<li><a href='"+ rUrl + "/profile.html' class='eventimg'><img src ='led-icons/user_silhouette.png'>&nbsp;Profile</a></li>");
                	request.getSession().setAttribute("addp", "<li><a href='"+ rUrl + "/property.html' class='eventimg'><img src ='led-icons/house.png'>&nbsp;My Properties</a></li>");
                	request.getSession().setAttribute("alert", "<li><a href='"+ rUrl + "/requests.html' class='eventimg'><img src ='led-icons/exclamation.png'>&nbsp;Alerts</a></li>");
                	request.getSession().setAttribute("notif", "<li><a href='"+ rUrl + "/notifications.html' class='eventimg'><img src ='led-icons/comment.png'>&nbsp;Notifications</a></li>");
                	//request.getSession().setAttribute("dash", "<li><a class='eventimg'><img src ='led-icons/chart_bar.png'><a/><a href='"+ rUrl + "/dashboard.html' class='eventimg'>Analytics</a></li>");
                	//request.getSession().setAttribute("help", "<li><a class='eventimg'><img src ='led-icons/help.png'><a/<a href='"+ rUrl + "/help.html' class='eventimg'>Help</a></li>");                 	

                	    processStatus = "stop";
                	    System.out.println("Login Process Stopped");
                	    System.out.println("Successfully logged in as " + profile.getEmail() + " with password " + profile.getPassword()+" decoded "+ hashPassword);
                        System.out.println("MERCHANT Menu");                    	
                        return new ModelAndView("redirect:/search.html");
                        
                }
                if((checkEmail) && (checkPass) && (checkAdmin)){  
                	
                	Profile pr = new Profile();
                	pr = logDAO.getUserByEmail(email);
                	int idValue = pr.getId();
                	//int idValue = 0;
                	//String fnameValue = "";
                	//idValue = pr.getId();
                	String fnameValue = pr.getFirstName();        
                	
                	System.out.println("email " +email);
                	System.out.println("idValue " +idValue);
                	System.out.println("fnameValue " +fnameValue);
                	
                	request.getSession().setAttribute(SESSION_USER, email);
                	request.getSession().setAttribute(SESSION_NAME, fnameValue);
                	request.getSession().setAttribute(LOGIN_DETAILS, idValue);
            		userSession.setLoggedIn(true);
            				
            		request.getSession().setAttribute("emailz", email);
            		
          	         List<Messages> messages = messagesDAO.getMessagesForEmail(email);     	     	
          	         int usralerts = messages.size();
         	     	 System.out.println("Total messages for User: " +usralerts);
         	     	 System.out.println("Message List: " +messages);
         	     	 request.getSession().setAttribute("mtotals", usralerts);            		
            		
             		//request.getSession().setAttribute("userSession","Welcome " + email+ "&nbsp;|&nbsp;<a href='"+ rUrl + "/logout.html'>Logout</a>");
         	     	request.getSession().setAttribute("userSession", "<img src ='led-icons/status_online.png'>&nbsp; Hello " + fnameValue);
             		//+ "&nbsp;&nbsp;|&nbsp;&nbsp;<a href='"+ rUrl + "/logout.html'>Logout</a>");
            		//request.getSession().setAttribute("logoutLink","<a href='"+ rUrl + "/logout.html'>Logout</a>");
         	     	request.getSession().setAttribute("logoutLink","<a href='"+ rUrl + "/loginx.html'>Logout</a>");
             		request.getSession().setAttribute("profileLink","<a href='"+ rUrl + "/profile.html'>Profile</a>");
                	
            		//basic links
            		//request.getSession().setAttribute("hom", "<li><a href='"+ rUrl + "/index.html' class='eventimg'>Home</a></li>");
            		request.getSession().setAttribute("sea", "<li><a href='"+ rUrl + "/search.html' class='eventimg'>Home</a></li>");
            		request.getSession().setAttribute("prof", "<li><a href='"+ rUrl + "/profile.html' class='eventimg'><img src ='led-icons/user_silhouette.png'>&nbsp;Profile</a></li>");
                	request.getSession().setAttribute("addp", "<li><a href='"+ rUrl + "/property.html' class='eventimg'><img src ='led-icons/house.png'>&nbsp;My Properties</a></li>");
                	request.getSession().setAttribute("alert", "<li><a href='"+ rUrl + "/requests.html' class='eventimg'><img src ='led-icons/exclamation.png'>&nbsp;Alerts</a></li>");
                	request.getSession().setAttribute("notif", "<li><a href='"+ rUrl + "/notifications.html' class='eventimg'><img src ='led-icons/comment.png'>&nbsp;Notifications</a></li>");
                	//request.getSession().setAttribute("dash", "<li><a class='eventimg'><img src ='led-icons/chart_bar.png'></a><a href='"+ rUrl + "/dashboard.html' class='eventimg'>Analytics</a></li>");   
                 	//request.getSession().setAttribute("help2", "<li><a class='eventimg'><img src ='led-icons/lifebuoy.png'></a><a href='"+ rUrl + "/help.html' class='eventimg'>Help</a></li>"); 
                 	request.getSession().setAttribute("editpp", "<li><a href='"+ rUrl + "/propertylist.html' class='eventimg'><img src ='led-icons/camera.png'>&nbsp;Publish Properties</a></li>");
                 	
                	//admin links
                	
                	request.getSession().setAttribute("musers", "<li><a href='"+ rUrl + "/allprofiles.html' class='eventimg'><img src ='led-icons/group.png'>&nbsp;Manage Profiles</a></li>");
                	request.getSession().setAttribute("addpp", "<li><a href='"+ rUrl + "/allproperty.html' class='eventimg'><img src ='led-icons/picture.png'>&nbsp;Manage Property</a></li>");
                	request.getSession().setAttribute("propmg", "<li><a href='"+ rUrl + "/manageproperty.html' class='eventimg'><img src ='led-icons/wall_brick.png'>&nbsp;New Property</a></li><p><br>");
                	request.getSession().setAttribute("proprec", "<li><a href='"+ rUrl + "/resources.html' class='eventimg'><img src ='led-icons/joystick.png'>&nbsp;Resources</a></li><p><br>");
                	//request.getSession().setAttribute("advert", "<li><a class='eventimg'><img src ='led-icons/world.png'></a><a href='"+ rUrl + "/adverts.html' class='eventimg'>Manage Adverts</a></li>");
                                      
                	//setup links
                	    processStatus = "stop";
                	    System.out.println("Login Process Stopped");
                        System.out.println("Successfully logged in as " + profile.getEmail() + " with password " + profile.getPassword()+" decoded "+ hashPassword);
                        System.out.println("ADMIN Menu");                    	
                        return new ModelAndView("redirect:/search.html");
                   
                }  else
                	
                	result.rejectValue("email", "loginForm.email","Please confirm your credentials");
                    System.err.println("Please confirm your credentials");
                    request.getSession().setAttribute("errmg", "<div class='alert alert-danger'>&nbsp;Please confirm your credentials</div><p><br>");
                    request.getSession().setAttribute("loginProcess","");
                    processStatus = "stop";
                    System.out.println("Login Process Stopped");
                    System.out.println("Login failure as " + profile.getEmail() + " with password " + profile.getPassword() +" decoded "+ hashPassword);
                
                	return modelAndView;
  	            } 
  	         
  	        request.getSession().setAttribute("errmg", "");
  	        request.getSession().setAttribute("loginProcess","");
  	        
  	        return modelAndView;
  	          
        }   
        
    	public ProfileDAO getProfileDAO() {
    		return profileDAO;
    	}
    	
    	public void setProfileDAO(ProfileDAO profileDAO){
    		this.profileDAO = profileDAO;
    	}
   
    	public LogDAO getLogDAO() {
    		return logDAO;
    	}

    	public void setLogDAO(LogDAO logDAO) {
    		this.logDAO = logDAO;
    	}
    	
    	public MessagesDAO getMessagesDAO() {
    		return messagesDAO;
    	}

    	public void setMessagesDAO(MessagesDAO messagesDAO){
    		this.messagesDAO = messagesDAO;
    	}

}