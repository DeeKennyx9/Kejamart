package kejamart.controller.profile;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import kejamart.helper.StringToMD5;
import kejamart.model.Profile;

@Controller
public class EditAllProfilesController implements Settings{
	
	public ProfileDAO profileDAO;
	public UserSession userSession;
	private List<Profile> profileList = new ArrayList<Profile>();
	private List<Profile> ProfileList = new ArrayList<Profile>();
	
	public static Logger logger = Logger.getLogger(EditAllProfilesController.class);
	
    @RequestMapping(value="/editprofile", method=RequestMethod.GET)
	public ModelAndView listProfiles(HttpServletRequest request, @ModelAttribute(value="editprofile") Profile profile) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("editp");

		if (request.getSession().getAttribute(SESSION_USER)!= null){

		int id = profile.getId();
		List<Profile> profiles = profileDAO.getProfilesForId(id);
		System.out.println("Get ID Value : " +profile.getId());
		request.getSession().setAttribute(PROFILE_ID, id);	

		modelAndView.addObject("profiles", profiles);
		System.out.println("User authorized to access profile page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access profile page");
		
		return model;
			
		}

	}
	
	
	@RequestMapping(value="/editprofile", method=RequestMethod.POST)
	public @ResponseBody JsonResponse updateProfile(HttpServletRequest request, BindingResult result) throws Exception {

	JsonResponse res = new JsonResponse();
	
	Profile profile = new Profile();
	
	String passwordz = profile.getPassword().trim();
	final String emailz = profile.getEmail();
	final String mobilez = profile.getMobile();
	boolean checkEmail = profileDAO.checkEmail(profile.getEmail());
	boolean checkMobile = profileDAO.checkMobile(profile.getMobile());
	
	//email pattern
	Pattern ep=Pattern.compile("(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+");
	Matcher em=ep.matcher(emailz);
	boolean eb=em.matches();	

	//mobile phone pattern
	Pattern mp = Pattern.compile("[0-9]{10}");  
	Matcher mob=mp.matcher(mobilez);
	boolean mobz=mob.matches();
	
	//password pattern
	//Pattern pp = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#$%]).{8,20})");
	Pattern pp=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})");
	Matcher pm = pp.matcher(passwordz);
	boolean pb = pm.matches();


	ValidationUtils.rejectIfEmptyOrWhitespace(result, "firstName", "First Name can not be empty.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "lastName", "Last Name can not be empty.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "category", "Please select a Category.");
	
	if (checkEmail == true) {
		result.rejectValue("email", "Email already exists");
		System.out.println("Email already exists");
	}

	if (eb == false) {
		result.rejectValue("email", "Email is invalid");
		System.out.println("Email is invalid");
	}	
	
	if (pb == false) {
		result.rejectValue("password", "Password is invalid. Must be at least 8 characters, include a number and a capital letter");
		System.out.println("Password is invalid. Must be at least 8 characters, include a number and a capital letter");
	}
	
	if (checkMobile == true) {
		result.rejectValue("mobile", "Mobile number already exists");
		System.out.println("Mobile number already exists");
	}
	
	
	if (mobz == false) {
		result.rejectValue("mobile", "Mobile is invalid");
		System.out.println("Mobile is invalid");
	}	
	
	if(profile.isIdNew()) {

	if(!result.hasErrors()){    	

	ProfileList.add(profile);
	res.setStatus("SUCCESS");
	res.setResult(profileList);
	
	String pwordz = "";

	StringToMD5 str = new StringToMD5();
	try {
		pwordz = str.MD5(profile.getPassword());
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	String pw = pwordz.toString();

	int idx = request.getSession().getAttribute(PROFILE_ID).hashCode();

	//profile.setPassword(pw);	
	//profileDAO.updateUserProfile(profile);
	
	System.out.println("Profile " + profile.getEmail() + " >|< " + idx + " has been updated succesfully");
	
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
