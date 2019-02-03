package kejamart.controller.property;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kejamart.controller.main.UserSession;
import kejamart.dao.PropertyDAO;
import kejamart.helper.Settings;
import kejamart.model.Property;

@EnableWebMvc
@Controller
public class DeletePropertyController implements Settings{

	PropertyDAO propertyDAO;
	
	UserSession userSession = new UserSession();
	
	public static Logger logger = Logger.getLogger(DeletePropertyController.class);		
	
	@RequestMapping(value="/deleteproperty", method=RequestMethod.GET)
    public String showForm(Model model, HttpServletRequest request) {
            Property property = new Property();
            ((HashMap<String, Object>) model).put("property", property);
            model.asMap().clear();
            return "redirect:" + "property.html";
    }
	
	@RequestMapping(value="/deleteproperty", method=RequestMethod.POST)
    public String deleteProperty(Model model, HttpServletRequest request) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));

		System.out.println("Delete button clicked: ");

    	propertyDAO.removeProperty(id);

    	System.out.println("Property Deleted for user:  >|< " +id);
    	
        return "redirect:" + "property.html";
        
    }	


public PropertyDAO getPropertyDAO() {
	return propertyDAO;
}

public void setPropertyDAO(PropertyDAO propertyDAO){
	this.propertyDAO = propertyDAO;
}

}