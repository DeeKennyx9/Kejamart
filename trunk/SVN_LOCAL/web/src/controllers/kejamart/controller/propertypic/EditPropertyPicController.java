package kejamart.controller.propertypic;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import kejamart.dao.PropertyPicDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.PropertyPics;


@Controller
public class EditPropertyPicController implements Settings {

	PropertyPics propertyPics;
	PropertyPicDAO propertyPicDAO;
	UserSession userSession = new UserSession();
	
	private List<PropertyPics> propertyPicList = new ArrayList<PropertyPics>(); 
	
	public static Logger logger = Logger.getLogger(EditPropertyPicController.class);	

    private List<PropertyPics> PropertyPicList = new ArrayList<PropertyPics>();	
	
	@RequestMapping(value="/editpics", method=RequestMethod.GET)
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("edpic");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		String emailUser = request.getSession().getAttribute(SESSION_USER).toString();

		int id = Integer.parseInt(request.getParameter("id"));

		List<PropertyPics> propertyPics = propertyPicDAO.getPropertyPicsForId(id);

		System.out.println("User ..." + emailUser + " || " +id);
				
		int idValue = 0;
		idValue = id;
		request.getSession().setAttribute(PICTURE_DETAILS, idValue);				
		
		modelAndView.addObject("propertyPics", propertyPics);
		System.out.println("User authorized to access property page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access property page");
		
		return model;
			
		}

	}
		 
	@RequestMapping(value="/editpics", method=RequestMethod.POST)
	public @ResponseBody JsonResponse editProperty(HttpServletRequest request, @ModelAttribute(value="propertyPics") PropertyPics propertyPics, BindingResult result) throws Exception{

	JsonResponse res = new JsonResponse();

	//Initialising validation error messages
	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo1", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo2", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo3", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo4", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo5", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo6", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo7", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo8", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo9", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo10", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo11", "Please insert a picture.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "photo12", "Please insert a picture.");

	if(!result.hasErrors()){ 
		
	System.out.println("Publish Button clicked");
	
	int idp = request.getSession().getAttribute(PICTURE_DETAILS).hashCode();

	Date date = new Date();
	SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date dat = new java.sql.Date(date.getTime());
	String emailUser = request.getSession().getAttribute(SESSION_USER).toString();	
		 
	PropertyPicList.add(propertyPics);
	res.setStatus("SUCCESS");
	res.setResult(propertyPicList);		 

	propertyPics.setEmail(emailUser);	
	propertyPics.setCreatedDate(dat);
	 
	propertyPicDAO.updatePic(propertyPics, idp);	  
	  
	System.out.println("Picture Updated Succesfully");

	} else { 

	System.out.println("A validation error has occurred. Picture has not been updated.");
	res.setStatus("FAIL");
	res.setResult(result.getAllErrors());

	}

	return res;
 
	}
	
	public PropertyPicDAO getPropertyPicDAO() {
		return propertyPicDAO;
	}
	
	public void setPropertyPicDAO(PropertyPicDAO propertyPicDAO){
		this.propertyPicDAO = propertyPicDAO;
	}
}
