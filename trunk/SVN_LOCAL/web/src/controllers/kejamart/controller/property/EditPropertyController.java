package kejamart.controller.property;

import java.util.ArrayList;
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
import kejamart.dao.CountiesDAO;
import kejamart.dao.CountriesDAO;
import kejamart.dao.LocationsDAO;
import kejamart.dao.PropertyDAO;
import kejamart.dao.RangeAmountDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Counties;
import kejamart.model.Countries;
import kejamart.model.Locations;
import kejamart.model.Property;
import kejamart.model.RangeAmount;

	@Controller
	public class EditPropertyController implements Settings {

		PropertyDAO propertyDAO;
		CountriesDAO countriesDAO;		
		CountiesDAO countiesDAO;		
		LocationsDAO locationsDAO;	
		RangeAmountDAO rangeAmountDAO;	
		
		UserSession userSession = new UserSession();
		
		private List<Property> propertyList = new ArrayList<Property>(); 
		
		public static Logger logger = Logger.getLogger(EditPropertyController.class);	

	    private List<Property> PropertyList = new ArrayList<Property>();

	    @RequestMapping(value="/editproperty", method=RequestMethod.GET)
		public ModelAndView listProperty(HttpServletRequest request) throws Exception {
			
			ModelAndView modelAndView = new ModelAndView("edprop");

			if (request.getSession().getAttribute(SESSION_USER) != null){

			String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
			String county = request.getParameter("county");
			String countyName = county;
			int countyId = 0;
			String country = request.getParameter("country");
			String countryName = country;
			int countryId = 0;

			if(countryName != null) {
				
				try {
					countryId = Integer.parseInt(countryName);
					} catch (NumberFormatException e) {
					System.err.println("County ID is not a number. Default Value 1 inserted : " +countryId);
					countryId = 1;
				    }
			
			System.out.println("Country Parameter is NOT empty : " +countryName);
			
			} else {
			System.err.println("Exception: Country Parameter is empty");
			}
			
			if(countyName != null) {
				try {
					countyId = Integer.parseInt(countyName);
					} catch (NumberFormatException e) {
					System.err.println("County ID is not a number. Default Value 1 inserted : " +countyId);
					countyId = 1;
				    }
				
				System.out.println("County Parameter is NOT empty : " +countyName);
				
				} else {
				System.err.println("Exception: County Parameter is empty");
			}

			List<Countries> countryList = countriesDAO.getCountries();
			List<Counties> countyList = countiesDAO.getCountyForId(countryId);
			List<Locations> locationList = locationsDAO.getLocationsForCountyId(countyId);
			List<RangeAmount> rangeLists = rangeAmountDAO.getAllRangeAmount();
			List<RangeAmount> rangeList = rangeAmountDAO.getResidentialRange();
			List<RangeAmount> rangeListx = rangeAmountDAO.getCommercialRange();

			int id = Integer.parseInt(request.getParameter("id"));
			
			List<Property> property = propertyDAO.getPropertyForId(id);
			System.out.println("User ..." + emailUser + " || " +id);
			
			int idValue = 0;
			idValue = id;
			request.getSession().setAttribute(PROPERTY_DETAILS, idValue);
			
			modelAndView.addObject("property", property);
			modelAndView.addObject("countryList", countryList);
			modelAndView.addObject("countyList", countyList);
			modelAndView.addObject("locationList", locationList);
			modelAndView.addObject("rangeList", rangeList);
			modelAndView.addObject("rangeListx", rangeListx);
			modelAndView.addObject("rangeLists", rangeLists);
			
			System.out.println("User authorized to access property page");
			return modelAndView; 
			
			} else {
				
			ModelAndView model = new ModelAndView("restricted");
			System.out.println("Visitor NOT authorized to access property page");
			
			return model;
				
			}

		}	
	    
		@RequestMapping(value = "/editproperty2.html", headers = "Accept=*/*", method=RequestMethod.GET)
		public @ResponseBody List<Counties> loadCounties(HttpServletRequest request) throws IllegalStateException{

		String country = request.getParameter("country");
		String countryName = country;
		int countryId = 0;

		if(countryName != null) {
			
			try {
				countryId = Integer.parseInt(countryName);
				} catch (NumberFormatException e) {
				System.err.println("County ID is not a number. Default Value 1 inserted : " +countryId);
				countryId = 1;
				}

		System.err.println("Country Parameter is NOT empty : " +countryName);
		System.out.println("Country Integer Value : " +countryId);

		List<Counties> countyList = countiesDAO.getCountyForCountryId(countryId);
		System.out.println("Dependent Location List selected : >|< " +countryId);
		System.out.println("County List ..." + countyList);

		return countyList;

		} else {
		System.err.println("Exception: Country Parameter is empty");
		return null;
		}
		}

		@RequestMapping(value = "/editproperty3.html", headers = "Accept=*/*", method=RequestMethod.GET)
		public @ResponseBody List<Locations> loadLocations(HttpServletRequest request) throws IllegalStateException{

		String county = request.getParameter("county");
		String countyName = county;
		int countyId = 0;

		if(countyName != null) {
			
			try {	
				countyId = Integer.parseInt(countyName);
				} catch (NumberFormatException e) {
				System.err.println("County ID is not a number. Default Value 1 inserted : " +countyId);
				countyId = 1;
				}

		System.out.println("County Parameter is NOT empty : " +countyName);
		System.out.println("County Integer Value : " +countyId);

		List<Locations> locationList = locationsDAO.getLocationsForCountyId(countyId);
		System.out.println("Dependent Location List selected : >|< " +countyId);
		System.out.println("Location List ..." + locationList);

		return locationList;

		} else {
		System.out.println("Exception: County Parameter is empty");
		return null;
		}
		}	    
	    
	@RequestMapping(value="/editproperty",method=RequestMethod.POST)
	public @ResponseBody JsonResponse editProperty(HttpServletRequest request, @ModelAttribute(value="property") Property property, BindingResult result) throws Exception{

	JsonResponse res = new JsonResponse();	
	String unitx = (property.getName()+property.getBedrooms());
	boolean checkUnit = propertyDAO.checkUnit(property.getName()+property.getBedrooms());
	
	//Initialising validation error messages
	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "website", "Please insert a website for your property.");	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "amount", "Please insert an amount for your property.");	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "latitude", "Please insert latitude.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "longitude", "Please insert longitude.");	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "parking", "Please insert parking space available.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "bathroom", "Please insert bathrooms available.");
	//ValidationUtils.rejectIfEmptyOrWhitespace(result, "videourl", "Please insert videourl.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "schedule", "Please insert schedule.");	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "email", "Please insert an email.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "bedrooms", "Please insert bedrooms.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "contacts", "Please insert your contact information.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "name", "Please insert a name for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "company", "Please insert a company name.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "description", "Please insert a description for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "prange", "Please insert a range for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "country", "Please select a country.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "county", "Please select a county.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "location", "Please select a location.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "propertyType", "Please select a property type.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "category", "Please select a category.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "status", "Please select a status.");	
	
	if(!result.hasErrors()){  
		
	System.out.println("Edit Button clicked");	
	int idz = request.getSession().getAttribute(PROPERTY_DETAILS).hashCode();
	
	PropertyList.add(property);
	res.setStatus("SUCCESS");
	res.setResult(propertyList);
	
	//String propList = property.getCountry()+property.getCounty()+property.getLocation()+property.getPropertyType()+property.getCategory()+property.getBedrooms()+property.getPrange();
	//System.out.println("PropList :" +propList);
	
	String orient = "style='height:150px; width:250px; border: 1px solid #E6E6FA; border-radius:0px; margin-top: 0px !important;'";
	
	try {
		int test1 = Integer.parseInt(property.getCounty());
		Counties ct = countiesDAO.getCountiesById(test1);
		String contVal = ct.getCounty();
		property.setCountyVal(contVal);
		System.out.println("ContVal1 " +contVal);
		} catch (NumberFormatException e) {
		System.err.println("Variable ct is not a number. Default Value 1 inserted : ");
		Counties ct = countiesDAO.getCountiesById(1);
		String contVal = ct.getCounty();
		property.setCountyVal(contVal);
		System.out.println("ContVal2 " +contVal);
	}
	
	try {
		int test2 = Integer.parseInt(property.getLocation());
		Locations lc = locationsDAO.getLocationsById(test2);
		String locVal = lc.getLocation();
		property.setLocationVal(locVal);
		System.out.println("LocVal1 " +locVal);
		} catch (NumberFormatException e) {
		System.err.println("Variable lc is not a number. Default Value 1 inserted : ");
		Locations lc = locationsDAO.getLocationsById(1);
		String locVal = lc.getLocation();
		property.setLocationVal(locVal);
		System.out.println("LocVal2 " +locVal);
		}	
	
	try {
		int test3 = Integer.parseInt(property.getPrange());
		RangeAmount rv = rangeAmountDAO.getRangeAmountById(test3);
		String rangeVal = rv.getPrange();
		property.setRangeValue(rangeVal);
		System.out.println("RangeVal1 " +rangeVal);
		} catch (NumberFormatException e) {
		System.err.println("Variable rv is not a number. Default Value 1 inserted : ");
		RangeAmount rv = rangeAmountDAO.getRangeAmountById(1);
		String rangeVal = rv.getPrange();
		property.setRangeValue(rangeVal);
		System.out.println("RangeVal2 " +rangeVal);
		}

	String propList = property.getCountry()+property.getCounty()+property.getLocation()+property.getPropertyType()+property.getCategory()+property.getBedrooms()+property.getPrange();
	System.out.println("PropList :" +propList);
	
	property.setPropString(propList);
	property.setOrientation(orient);	
	property.setUnit(unitx);
	
	propertyDAO.updateProperty(property, idz);
	
	System.out.println("Property >|< : " + idz + " has been updated succesfully");
	
	} else { 

	System.out.println("A validation error has occurred. Property has not been updated.");
	res.setStatus("FAIL");
	res.setResult(result.getAllErrors());

	}

	return res;
 
	}
	
	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
	
	public LocationsDAO getLocationsDAO() {
		return locationsDAO;
	}

	public void setLocationsDAO(LocationsDAO locationsDAO){
		this.locationsDAO = locationsDAO;
	}

	public CountiesDAO getCountiesDAO() {
		return countiesDAO;
	}

	public void setCountiesDAO(CountiesDAO countiesDAO){
		this.countiesDAO = countiesDAO;
	}

	public CountriesDAO getCountriesDAO() {
		return countriesDAO;
	}

	public void setCountriesDAO(CountriesDAO countriesDAO){
		this.countriesDAO = countriesDAO;
	}		
	
	public RangeAmountDAO getRangeAmountDAO() {
		return rangeAmountDAO;
	}

	public void setRangeAmountDAO(RangeAmountDAO rangeAmountDAO){
		this.rangeAmountDAO = rangeAmountDAO;
	}	
}
