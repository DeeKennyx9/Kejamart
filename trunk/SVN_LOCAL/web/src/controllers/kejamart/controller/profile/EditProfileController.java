package kejamart.controller.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import kejamart.controller.main.UserSession;
import kejamart.dao.ProfileDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Profile;

@Controller
public class EditProfileController implements Settings{
	
	public ProfileDAO profileDAO;
	public UserSession userSession;
	private List<Profile> profileList = new ArrayList<Profile>();
	private List<Profile> ProfileList = new ArrayList<Profile>();
	
	public static Logger logger = Logger.getLogger(EditProfileController.class);
	
    @RequestMapping(value="/profile")
	public ModelAndView listProfiles(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("prof");

		if (request.getSession().getAttribute(SESSION_USER)!= null){

		String emailUser = request.getSession().getAttribute(SESSION_USER).toString();

		List<Profile> profiles = profileDAO.getProfilesForUser(emailUser);
		System.out.println("Email ..." + emailUser);

		modelAndView.addObject("profiles", profiles);
		System.out.println("User authorized to access profile page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access profile page");
		
		return model;
			
		}

	}
	
	
	@RequestMapping(value="/profile",method=RequestMethod.POST)
	public @ResponseBody JsonResponse editProfile(HttpServletRequest request, @ModelAttribute(value="profile") Profile profile, BindingResult result) throws Exception {

	JsonResponse res = new JsonResponse();
	boolean checkMobile = profileDAO.checkMobile(profile.getMobile());
	boolean uncheckEmail = profileDAO.uncheckEmail(profile.getEmail());

	final String mobilez = profile.getMobile();

	//mobile phone pattern
	Pattern mp = Pattern.compile("[0-9]{10}");  
	Matcher mob=mp.matcher(mobilez);
	boolean mobz=mob.matches();


	ValidationUtils.rejectIfEmptyOrWhitespace(result, "firstName", "First Name cannot be empty.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "lastName", "Last Name cannot be empty.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "mailing", "Please select Mailing option.");
	
	if (checkMobile && uncheckEmail == true) {
		result.rejectValue("mobile", "Mobile number already exists");
		System.out.println("Mobile number already exists");
	}
	
	if (mobz == false) {
		result.rejectValue("mobile", "Mobile is invalid");
		System.out.println("Mobile is invalid");
	}
	
	request.getSession().getAttribute(LOGIN_DETAILS);
	int idz = request.getSession().getAttribute(LOGIN_DETAILS).hashCode();
	
	if(profile.isIdNew()) {

	if(!result.hasErrors()){    	

	ProfileList.add(profile);
	res.setStatus("SUCCESS");
	res.setResult(profileList);
	
	profile.setId(idz);
	profileDAO.updateProfile(profile);
	
	System.out.println("Profile " + profile.getEmail() + " has been updated succesfully");
	
	 } else {
		  
	 System.out.println("Exception: User not found " + profile.getEmail());
	 
	 System.out.println("A validation error has occurred. Profile has not been updated.");
	 res.setStatus("FAIL");
	 res.setResult(result.getAllErrors());	  
	  
	 }

	} else { 

	System.out.println("A validation error has occurred. Profile has not been updated.");
	res.setStatus("FAIL");
	res.setResult(result.getAllErrors());

	}

	return res;
 
	}
	
	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}
	
	public void setProfileDAO(ProfileDAO profileDAO){
		this.profileDAO = profileDAO;
	}
	
}
