package kejamart.controller.feature;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
import kejamart.dao.FeaturesDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Features;

@Controller
public class AddFeaturesController implements Settings {

	FeaturesDAO featuresDAO;
	UserSession userSession = new UserSession();
	private List<Features> featureList = new ArrayList<Features>();	
    private List<Features> FeatureList = new ArrayList<Features>();	
	
	public static Logger logger = Logger.getLogger(AddFeaturesController.class);	
	
	@RequestMapping(value="/features")
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("feat");
		
		int idz = 0;

		if (request.getSession().getAttribute(SESSION_USER) != null){

		idz = Integer.parseInt(request.getParameter("id"));
		String pname = request.getParameter("name");
			
		int idValue = 0;
		idValue = idz;
		request.getSession().setAttribute(PROPERTY_DETAILS, idValue);
		
		String str = "";
		str = pname;
		request.getSession().setAttribute(PROPERTY_NAME, str);	
		
		System.out.println("ID " +idValue);
		System.out.println("Name" +str);

		if(featuresDAO.getFeaturesForPropId(idz) != null) {
			
		List<Features> features = featuresDAO.getFeaturesForId(idz);
		List<Features> featuresByProperty = featuresDAO.getFeaturesForPropId(idz);
		
		if (featuresDAO.getFeaturesForPropId(idz).listIterator().hasNext()) {
		
		System.out.println("Property ..." + " || " +idz);
		
		modelAndView.addObject("featuresByProperty", featuresByProperty);
		modelAndView.addObject("features", features);
		
		} else {
			
			System.out.println("No Images Found");
		} 
		
		} else {
			System.out.println("No Records Found");
		}

		System.out.println("User authorized to access add property page");		
	    return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access property page");
		
		return model;
			
		}

	}
		 

	@RequestMapping(value="/features",method=RequestMethod.POST)
	public @ResponseBody JsonResponse addFeature(HttpServletRequest request, @ModelAttribute(value="features") Features features, BindingResult result) throws Exception{

	JsonResponse res = new JsonResponse();
	
	System.out.println("Add Button clicked");
	AtomicInteger idp = new AtomicInteger(0);
	idp.set(request.getSession().getAttribute(PROPERTY_DETAILS).hashCode());
	int idx = idp.get(); 
	AtomicInteger profValue = new AtomicInteger(0);
	profValue.set(request.getSession().getAttribute(LOGIN_DETAILS).hashCode());
	int prf = profValue.get();
	
	String prp = request.getSession().getAttribute(PROPERTY_NAME).toString();
	
	Date date = new Date();
	SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date dat = new java.sql.Date(date.getTime());
	String emailUser = request.getSession().getAttribute(SESSION_USER).toString();	

	//Initialising validation error messages
	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "feature", "Please insert a feature.");

	if(!result.hasErrors()){ 

		List<Features> ft = featuresDAO.getFeaturesForPropId(idx);
		int totFeats = 0;
		totFeats = ft.size();
		
		// Check Feature Limit for Property
		if(totFeats < 8) {
		
		FeatureList.add(features);
		res.setStatus("SUCCESS");
		res.setResult(featureList);	
		
		features.setProfileId(prf);
		features.setPropertyId(idx);
		features.setPropertyName(prp);
		features.setEmail(emailUser);	
		features.setCreatedDate(dat);
		
		featuresDAO.addFeature(features); 
		
		} else {
	
	result.rejectValue("feature", "You are Exceeding the Feature limit");			
    System.out.println("You are Exceeding the Feature limit");
			
		}
  
	  
	System.out.println("Feature Added Succesfully");
	
	} else { 

	System.err.println("A validation error has occurred. Feature has not been added.");
	res.setStatus("FAIL");
	res.setResult(result.getAllErrors());

	}

	return res;
 
	}
	
	
	public FeaturesDAO getFeaturesDAO() {
		return featuresDAO;
	}
	
	public void setFeaturesDAO(FeaturesDAO featuresDAO){
		this.featuresDAO = featuresDAO;
	}
	
}

