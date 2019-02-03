package squirrel.kejani.controller.profile;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import squirrel.kejani.controller.main.UserSession;
import squirrel.kejani.dao.ProfileDAO;
import squirrel.kejani.helper.Settings;
import squirrel.kejani.model.Profile;

@Controller
public class EditProfileControllerx implements Settings{
	
	ProfileDAO profileDAO;
	
	Profile profile;
	public UserSession userSession;
	
	public static Logger logger = Logger.getLogger(EditProfileControllerx.class);
	
	@RequestMapping(value="/editprofile.html")
	public ModelAndView editProfile(HttpServletRequest request, @ModelAttribute Profile profile) throws Exception {
	ModelAndView modelAndView = new ModelAndView("editprofile");
	
	if (request.getSession().getAttribute(SESSION_USER) != null){
		
	    System.out.println("User authorized to access profile page");		
		
		request.getSession().getAttribute(SESSION_USER);
		String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
	
	if (!profile.isIdNew()) {	
		
	profileDAO.updateProfile(profile);
	
	System.out.println("User UPDATED successfully ..." +emailUser);
	String message = "Profile " +emailUser + " was successfully edited.";
	System.out.println(message);
	
	return modelAndView;
	
	}	
	
	return modelAndView;
		
	} else {
		
	ModelAndView model = new ModelAndView("restricted");
	System.out.println("Visitor NOT authorized to access profile page");
	
	return model;
		
	}
	}
	
	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}
	
	public void setProfileDAO(ProfileDAO profileDAO){
		this.profileDAO = profileDAO;
	}

}
