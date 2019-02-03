package squirrel.kejani.controller.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import squirrel.kejani.controller.main.UserSession;
import squirrel.kejani.dao.PropertyDAO;
import squirrel.kejani.helper.Settings;
import squirrel.kejani.model.Property;

	@Controller
	public class EditPropertyController2 implements Settings {

		PropertyDAO propertyDAO;
		
		UserSession userSession = new UserSession();
		
		private List<Property> propertyList = new ArrayList<Property>(); 
		
		public static Logger logger = Logger.getLogger(EditPropertyController2.class);	

	    private List<Property> PropertyList = new ArrayList<Property>();


		@RequestMapping(value="/editproperty", method=RequestMethod.GET)
	    public String showForm(Model model, HttpServletRequest request) throws Exception {

			if (request.getSession().getAttribute(SESSION_USER) != null){
				int id = Integer.parseInt(request.getParameter("id"));
				//Property property = propertyDAO.getPropertyById(id);
				List<Property> property = propertyDAO.getPropertyForId(id);
	            ((HashMap<String, Object>) model).put("property", property);
	            //model.asMap().clear();
	            return "edprop";
	            
			    } else {
			    	
			    return "restricted";
			}
	    }	   
	    
	@RequestMapping(value="/editproperty",method=RequestMethod.POST)
	public String editProperty(HttpServletRequest request, @ModelAttribute(value="property") Property property, Errors errors) throws Exception{

	int id = Integer.parseInt(request.getParameter("id"));

	//Initialising validation error messages
	
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "county", "required.county", "Please select a County.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "required.location", "Please select a Location.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "propertyType", "required.propertyType", "Please select a Property Type.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "required.category", "Please select a Category.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contacts", "required.contacts", "Please insert your Contacts.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bedrooms", "required.bedrooms", "Please insert a number or 0.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "Please insert a Name for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "required.website", "Please insert a Website for your property.");
	//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "required.amount", "Please insert an Amount for your property.");	

	System.out.println("Delete button clicked: ");
	
	if(!errors.hasErrors()){    

	//propertyDAO.updateProperty(id);
	
	System.out.println("Property has been updated succesfully. Refference :  >|< " +id);
	return "prop";

	} else { 

	System.out.println("Errors: " +errors); 
	return "edprop";
	
	    }
    }
	
	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
}
