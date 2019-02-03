package kejamart.controller.propertypic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.PropertyPicDAO;
import kejamart.helper.Settings;
import kejamart.model.PropertyPics;

	@Controller
	public class PublishListController1 implements Settings {

		PropertyPicDAO propertyPicDAO;
		
		UserSession userSession = new UserSession();
		
		public static Logger logger = Logger.getLogger(PublishListController1.class);	

	@RequestMapping(value="/picturelist", method=RequestMethod.GET)
	public ModelAndView listProperty(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("piclist");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		String status ="0";
		List<PropertyPics> propertyPics = propertyPicDAO.getPublishList(status);
		
		int piccount = propertyPicDAO.getPics().size();
		
		modelAndView.addObject("propertyPics", propertyPics);
		request.getSession().setAttribute("piccount", piccount);
		System.out.println("User authorized to access property page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access property page");
		
		return model;
			
		}

	}
	
	public PropertyPicDAO getPropertyPicDAO() {
		return propertyPicDAO;
	}
	
	public void setPropertyPicDAO(PropertyPicDAO propertyPicDAO){
		this.propertyPicDAO = propertyPicDAO;
	}
}


