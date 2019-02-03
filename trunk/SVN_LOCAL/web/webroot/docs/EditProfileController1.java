package squirrel.kejani.controller.profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import squirrel.kejani.controller.main.UserSession;
import squirrel.kejani.dao.ProfileDAO;
import squirrel.kejani.helper.JsonResponse;
import squirrel.kejani.model.Profile;

@EnableWebMvc
@Configuration
@Controller
public class EditProfileController1 {

	ProfileDAO profileDAO;
	
	UserSession userSession = new UserSession();
	
	private List<Profile> profileList = new ArrayList<Profile>(); 
	
	public static Logger logger = Logger.getLogger(EditProfileController1.class);	

    private List<Profile> ProfileList = new ArrayList<Profile>();

@RequestMapping(value="/profile.html",method=RequestMethod.GET)
 public String showForm(){
  return "profile";
}

	 
@RequestMapping(value="/profile.html",method=RequestMethod.POST)
public @ResponseBody JsonResponse addProfile(@ModelAttribute(value="profile") Profile profile, BindingResult result) throws Exception {

JsonResponse res = new JsonResponse();

String emailz = profile.getEmail() ;
String passwordz = profile.getPassword() ;
boolean checkEmail = profileDAO.checkEmail(profile.getEmail());

boolean checkId = profileDAO.idExists(profile.getId());

//email pattern
Pattern ep=Pattern.compile("(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+");
Matcher em=ep.matcher(emailz);
boolean eb=em.matches();

//password pattern
Pattern pp=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#$%]).{8,20})");
Matcher pm=pp.matcher(passwordz);
boolean pb=pm.matches();


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
	result.rejectValue("password", "Password is invalid. Include 1 number and 1 special character");
	System.out.println("Password is invalid. Include 1 number and 1 special character");
}

Date dateobj = new Date();

if(!result.hasErrors()){
	
	if(!checkId){ 
		
profileDAO.updateProfile(profile);
res.setStatus("FAIL");
res.setResult(profileList);
System.out.println("Profile " + profile.getEmail() + " has been updated succesfully");
		
	} else

ProfileList.add(profile);
res.setStatus("SUCCESS");
res.setResult(profileList);
System.out.println("Profile " + profile.getEmail() + " has been created succesfully");

profile.setStatus("1");
profile.setEnabled(1);
profile.setRole("USER");
profile.setCreatedDate(dateobj);
profileDAO.addProfile(profile);

} else { 

System.out.println("A validation error has occurred. Profile has not been added.");
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