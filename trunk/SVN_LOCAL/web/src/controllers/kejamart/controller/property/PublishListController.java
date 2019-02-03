package kejamart.controller.property;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.PropertyDAO;
import kejamart.helper.Settings;
import kejamart.model.Property;

	@Controller
	public class PublishListController implements Settings {

		PropertyDAO propertyDAO;
		
		UserSession userSession = new UserSession();
		
		public static Logger logger = Logger.getLogger(PublishListController.class);	

	@RequestMapping(value="/propertylist", method=RequestMethod.GET)
	public ModelAndView listProperty(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("proplist");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		int status = 0;
		List<Property> property = propertyDAO.getPublishList(status);
		
		int propcount = propertyDAO.getAllProperty().size();
		
		modelAndView.addObject("property", property);
		request.getSession().setAttribute("propcount", propcount);
		System.out.println("User authorized to access property page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access property page");
		
		return model;
			
		}

	}
	
	public PropertyDAO getPropertyPicDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
}


