package kejamart.controller.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.concurrent.atomic.*;

import kejamart.controller.main.UserSession;
import kejamart.dao.AdvertDAO;
import kejamart.dao.CountiesDAO;
import kejamart.dao.CountriesDAO;
import kejamart.dao.LocationsDAO;
import kejamart.dao.PropertyDAO;
import kejamart.dao.PropertyPicDAO;
import kejamart.dao.RangeAmountDAO;
import kejamart.helper.Settings;
import kejamart.model.Adverts;
import kejamart.model.Counties;
import kejamart.model.Countries;
import kejamart.model.Locations;
import kejamart.model.Property;
import kejamart.model.PropertyPics;
import kejamart.model.RangeAmount;

@Controller
public class ViewAdvertController implements Settings {

	AdvertDAO advertDAO;
	UserSession userSession = new UserSession();
	CountriesDAO countriesDAO;		
	CountiesDAO countiesDAO;	
	LocationsDAO locationsDAO;
	PropertyDAO propertyDAO;
	PropertyPicDAO propertyPicDAO;
	RangeAmountDAO rangeAmountDAO;	
	
	public static Logger logger = Logger.getLogger(ViewAdvertController.class);	
	
	@RequestMapping(value="/search")
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("searchx");		
    
		request.getSession().setAttribute("logout", "Logged out");  
		System.out.println("User Logged out");	
		System.out.println("Session removed: " +request.getRequestedSessionId()); 
 
        int idz = 0;
        if (request.getParameter("id") != null) {
			
		idz = Integer.parseInt(request.getParameter("id"));
		String pname = request.getParameter("name");
			
		int idValue = 0;
		idValue = idz;
		request.getSession().setAttribute(PROPERTY_DETAILS, idValue);
		
		String str = "";
		str = pname;
		request.getSession().setAttribute(PROPERTY_NAME, str);		
		
		} else {
			
		System.out.println("No Adverts Found");	
		}
               
		if (advertDAO.getAdvertsForPropId(idz).listIterator().hasNext()) {
		
		String picName = advertDAO.getAdvertsForPropId(idz).listIterator().next().getFileName();
		
		} else {
			
			System.out.println("No Images Found");
		} 
		
		String county = request.getParameter("county");
		String countyName = county;
		int countyId = 0;
		int cid = 0;
		String country = request.getParameter("country");
		String countryName = country;
		int countryId = 0;

		if(countryName != null) {
			
		try {
		countryId = Integer.parseInt(countryName);
		} catch (NumberFormatException e) {
			System.err.println("Country ID is not a number. Default Value 1 inserted : " +countryId);
			countryId = 1;
		}
		
		System.err.println("Country Parameter is NOT empty : " +countryName);
		
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
			
			System.err.println("County Parameter is NOT empty : " +countyName);
			
			} else {
			System.err.println("Exception: County Parameter is empty");
		}

		List<Countries> countryList = countriesDAO.getCountries();
		List<Counties> countyList = countiesDAO.getCountyForId(countryId);
		List<Locations> locationList = locationsDAO.getLocationsForCountyId(countyId);		
		
		String top = "TopCenter";
		String left = "LeftPixel";
		
		String categoryx = request.getParameter("category");
		List<Adverts> topadverts = advertDAO.getAdvertsForLocation(top);
		List<Adverts> leftadverts = advertDAO.getAdvertsForLocation(left);
		List<Adverts> adverts = advertDAO.getAdvertsForId(idz);
		List<Adverts> advertsByProperty = advertDAO.getAdvertsForPropId(idz);
		List<Property> propertyList = propertyDAO.getDefaultProperty();
		//List<RangeAmount> rangeList = rangeAmountDAO.getRangeForCategory(categoryx);
		//List<RangeAmount> rangeList = rangeAmountDAO.getAllRangeAmount();
		//List<RangeAmount> rangeList = rangeAmountDAO.getRangeForCategory("Residential Property");
		List<RangeAmount> rangeList = rangeAmountDAO.getResidentialRange();
		List<RangeAmount> rangeListx = rangeAmountDAO.getCommercialRange();
		
		System.out.println("Property List: "+propertyList);

		modelAndView.addObject("advertsByProperty", advertsByProperty);
		modelAndView.addObject("topadverts", topadverts);	
		modelAndView.addObject("leftadverts", leftadverts);
    	modelAndView.addObject("adverts", adverts);
		modelAndView.addObject("countryList", countryList);
		modelAndView.addObject("countyList", countyList);
		modelAndView.addObject("locationList", locationList);
		modelAndView.addObject("propertyList", propertyList);	
		modelAndView.addObject("rangeList", rangeList);
		modelAndView.addObject("rangeListx", rangeListx);
		
		//
		String searchProcess = "";
		modelAndView.addObject("searchProcess", searchProcess);

		System.out.println("Search Page Loaded");
		
		if (request.getParameter("country")!=null || request.getParameter("county")!=null || request.getParameter("location")!=null || request.getParameter("propertyType")!=null || request.getParameter("category")!=null || request.getParameter("bedrooms")!=null || request.getParameter("prange")!=null) {
			
	
			String pcountry = request.getParameter("country");
			String pcounty = request.getParameter("county");
			String plocation = request.getParameter("location");
			String ppropType = request.getParameter("propertyType");
			String pcategory = request.getParameter("category");
			String pbrooms = request.getParameter("bedrooms");
			String prange = request.getParameter("prange");
			String pprop = pcountry+pcounty+plocation+ppropType+pcategory+pbrooms+prange;		
			
			System.out.println("Search Criteria List ..." +pprop);
			
			if(propertyDAO.getPropertyForString(pprop) != null) {
				
			//
			String processStatus = "";
			processStatus = "start";
			searchProcess = "Search Process Started";
			//request.getSession().setAttribute("searchProcess","<img src ='img/progress.gif'>");
			modelAndView.addObject("searchProcess", searchProcess);
			System.out.println("Search Process Started");				
			
			List<Property> pstringList = propertyDAO.getPropertyForString(pprop);
			//List<Property> pstringList = propertyDAO.getAllProperty(pcountry, pcounty, plocation, ppropType, pcategory, pbrooms, prange);
			List<Property> classifieds = propertyDAO.getPropertyForStatus(1);
			List<Property> pstringListx = propertyDAO.getPropertyForStringLimit(pprop);
			List<Property> classifiedsx = propertyDAO.getPropertyForStatusLimit(1);			

			int pcount = pstringList.size();
			int ccount = classifieds.size();
						
			if (pcount > 1) {
				String result = +pcount+ " results found ...";
				searchProcess = "";
				modelAndView.addObject("result", result);
				modelAndView.addObject("pstringList", pstringList);
				modelAndView.addObject("classifieds", classifieds);
				modelAndView.addObject("pstringListx", pstringListx);
				modelAndView.addObject("classifieds", classifieds);	
				modelAndView.addObject("searchProcess", searchProcess);	
				System.out.println("Results found");
				//
		        //request.getSession().setAttribute("searchProcess","");
		        processStatus= "stop";
		        System.out.println("Search Process Stopped"); 				
			}  
			//if (pcount != 0 && pcount == 1) {
			else if (pcount != 0 && pcount == 1) {
				String result = +pcount+ " result found ...";
				searchProcess = "";
				modelAndView.addObject("result", result);
				modelAndView.addObject("pstringList", pstringList);
				modelAndView.addObject("classifieds", classifieds);
				modelAndView.addObject("pstringListx", pstringListx);
				modelAndView.addObject("classifieds", classifieds);		
				modelAndView.addObject("searchProcess", searchProcess);	
				System.out.println("Results found");
				//
		        request.getSession().setAttribute("searchProcess","");
		        processStatus= "stop";
		        System.out.println("Search Process Stopped"); 	
		        
			} else if (pcount == 0) {
				String result = "No results found";
				searchProcess = "";
				modelAndView.addObject("result", result);
				modelAndView.addObject("searchProcess", searchProcess);	
				System.err.println("Results not found");
				//
		        request.getSession().setAttribute("searchProcess","");
		        processStatus= "stop";
		        System.out.println("Search Process Stopped"); 
		        
				modelAndView.addObject("classifieds", classifieds);
			}

			List<PropertyPics> pics = propertyPicDAO.getPropertyPicsForPropId(idz);			
			
			System.out.println("Property List ..." +pstringList);
			System.out.println("Search Page Loaded");	
			searchProcess = "";

			modelAndView.addObject("pstringList", pstringList);
			modelAndView.addObject("classifieds", classifieds);
			modelAndView.addObject("pstringListx", pstringListx);
			modelAndView.addObject("classifiedsx", classifiedsx);			
			modelAndView.addObject("pcount", pcount);
			modelAndView.addObject("pics", pics);
			modelAndView.addObject("ccount", ccount);
			modelAndView.addObject("searchProcess", searchProcess);	
			
						
			} else {
			System.err.println("No results found"); 
			}		
			} else {	
		    
			//Set default List			
			String result = "Select Options to Search";
			String resultx = "Classified Properties";			
			modelAndView.addObject("resultx", resultx);	
			//List<Property> classifieds = propertyDAO.getAllProperty();
			List<Property> classifieds = propertyDAO.getPropertyForStatus(1);
			List<Property> classifiedsx = propertyDAO.getPropertyForStatusLimit(1);
			modelAndView.addObject("classifieds", classifieds);
			modelAndView.addObject("classifiedsx", classifiedsx);
			System.err.println("Empty Parameters");
				
			}		
		
		// 0 = not published yet
		// 1 = published
		// 2 = classified

	    return modelAndView; 

	}
	
	@RequestMapping(value = "/search2.html", headers = "Accept=*/*", method=RequestMethod.GET)
	public @ResponseBody List<Counties> loadCounties(HttpServletRequest request) throws IllegalStateException{

	if(request.getParameter("country") != null) {
	
	String country = request.getParameter("country");
	String countryName = country;
	int countryId = 0;
	
	try {
	countryId = Integer.parseInt(countryName);
	} catch (NumberFormatException e) {
	System.err.println("Country ID is not a number. Default Value 1 inserted : " +countryId);
	countryId = 1;
    }

	System.err.println("Country Parameter is NOT empty : " +countryName);
	System.err.println("Country Integer Value : " +countryId);

	List<Counties> countyList = countiesDAO.getCountyForCountryId(countryId);
	System.out.println("Dependent Location List selected : >|< " +countryId);
	System.out.println("County List ..." + countyList);

	return countyList;

	} else {
	System.err.println("Exception: Country Parameter is empty");
	
    // calling garbage collector 
    System.gc(); 
    
	return null;
	}
	}

	@RequestMapping(value = "/search3.html", headers = "Accept=*/*", method=RequestMethod.GET)
	public @ResponseBody List<Locations> loadLocations(HttpServletRequest request) throws IllegalStateException{

	if(request.getParameter("county") != null) {

	String county = request.getParameter("county");
	String countyName = county;
	
	int countyId = 0;
	
	try {
	countyId = Integer.parseInt(countyName);
	} catch (NumberFormatException e) {
	System.err.println("County ID is not a number. Default Value 1 inserted : " +countyId);
	countyId = 1;
    }

	System.err.println("County Parameter is NOT empty : " +countyName);
	System.err.println("County Integer Value : " +countyId);

	List<Locations> locationList = locationsDAO.getLocationsForCountyId(countyId);
	System.out.println("Dependent Location List selected : >|< " +countyId);
	System.out.println("Location List ..." + locationList);

	return locationList;

	} else {
	System.err.println("Exception: County Parameter is empty");
	
	return null;
	}
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
		 
	public AdvertDAO getAdvertDAO() {
		return advertDAO;
	}
	
	public void setAdvertDAO(AdvertDAO advertDAO){
		this.advertDAO = advertDAO;
	}
	
	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
	
	public PropertyPicDAO getPropertyPicDAO() {
		return propertyPicDAO;
	}
	
	public void setPropertyPicDAO(PropertyPicDAO propertyPicDAO){
		this.propertyPicDAO = propertyPicDAO;
	}
	
	public RangeAmountDAO getRangeAmountDAO() {
		return rangeAmountDAO;
	}

	public void setRangeAmountDAO(RangeAmountDAO rangeAmountDAO){
		this.rangeAmountDAO = rangeAmountDAO;
	}	

}

