package kejamart.controller.property;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import kejamart.controller.main.UserSession;
import kejamart.dao.PropertyDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Property;

	@Controller
	public class AllPropertyController implements Settings {

		PropertyDAO propertyDAO;		
		UserSession userSession = new UserSession();
		
		public static Logger logger = Logger.getLogger(AllPropertyController.class);	

	@RequestMapping(value="/allproperty", method=RequestMethod.GET)
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("alprop");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		List<Property> property = propertyDAO.getAllProperty();
		
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
	
    @RequestMapping(value="/allproperty", method=RequestMethod.POST)
    public @ResponseBody JsonResponse listByKeyWord(HttpServletRequest request) throws Exception {
		
    	JsonResponse res = new JsonResponse();	
		String keyword = request.getParameter("name");

		System.out.println("Search button clicked");
		List<Property> propertyx = propertyDAO.getPropertybyName(keyword);
		
		if(propertyx  != null && propertyx .listIterator().hasNext()){
	
		res.setStatus("SUCCESS");
		
		int id = propertyx.listIterator().next().getId();
		String name = propertyx.listIterator().next().getName();
		String location = propertyx.listIterator().next().getLocation();
		
		res.setResult(id);
		
		System.out.println(id+ " <> " +name+ " <> " +location); 

		} else {
		
		res.setStatus("FAIL");
		System.err.println("No results found for " +keyword); 
		res.setResult("No results found for " +keyword);			
			
		}
		
		return res;

	  }	
	
	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
}
