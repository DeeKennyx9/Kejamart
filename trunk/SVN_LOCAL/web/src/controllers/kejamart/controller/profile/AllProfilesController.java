package kejamart.controller.profile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
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
public class AllProfilesController implements Settings{
	
	public ProfileDAO profileDAO;
	public UserSession userSession;
	
	public static Logger logger = Logger.getLogger(AllProfilesController.class);
	
    @RequestMapping(value="/allprofiles", method=RequestMethod.GET)
	public ModelAndView listProfiles(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("allp");

		if (request.getSession().getAttribute(SESSION_USER)!= null){

		List<Profile> profiles = profileDAO.getProfiles();
		
		modelAndView.addObject("profiles", profiles);
		System.out.println("User authorized to access profile page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access profile page");
		
		return model;
			
		}

	}	    
    
    @RequestMapping(value="/allprofiles", method=RequestMethod.POST)
    public @ResponseBody JsonResponse listByKeyWord(HttpServletRequest request) throws Exception {
		
    	JsonResponse resem = new JsonResponse();
    	JsonResponse res = new JsonResponse();	
		String keyword = request.getParameter("email");

		System.out.println("Search button clicked");
		List<Profile> profilex = profileDAO.search(keyword);
		
		if(profilex != null && profilex.listIterator().hasNext()){
	
		res.setStatus("SUCCESS");
			
		int id = profilex.listIterator().next().getId();
		String email = profilex.listIterator().next().getEmail();
		String firstName = profilex.listIterator().next().getFirstName();
		String lastName = profilex.listIterator().next().getLastName();
		String category = profilex.listIterator().next().getCategory();
		String role = profilex.listIterator().next().getRole();
		
		res.setResult(id);
		resem.setResult(email);
		
		System.out.println(id+ " <> " +email+ " <> " +firstName+ " <> " +lastName+ " <> " +category+ " <> " +role); 

		} else {
		
		res.setStatus("FAIL");
		System.out.println("No results found for " +keyword); 
		res.setResult("No results found for " +keyword);			
			
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
