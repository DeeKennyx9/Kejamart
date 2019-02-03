package kejamart.controller.property;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.AlertsDAO;
import kejamart.dao.CountiesDAO;
import kejamart.dao.CountriesDAO;
import kejamart.dao.LocationsDAO;
import kejamart.dao.MailConfigDAO;
import kejamart.dao.PropertyDAO;
import kejamart.dao.RangeAmountDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Alerts;
import kejamart.model.Counties;
import kejamart.model.Countries;
import kejamart.model.Locations;
import kejamart.model.Property;
import kejamart.model.RangeAmount;

	@Controller
	public class AddPropertyController implements Settings {

		PropertyDAO propertyDAO;		
		CountriesDAO countriesDAO;		
		CountiesDAO countiesDAO;		
		LocationsDAO locationsDAO;		
		RangeAmountDAO rangeAmountDAO;		
		MailConfigDAO mailConfigDAO;		
		AlertsDAO alertsDAO;		
		UserSession userSession = new UserSession();
		
		private List<Property> propertyList = new ArrayList<Property>(); 
		
		private List<Property> PropertyList = new ArrayList<Property>();
		
		public static Logger logger = Logger.getLogger(AddPropertyController.class);	  
	    
	    private JavaMailSender mailSender;

	    public void setMailSender(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    } 	    


	@RequestMapping(value="/property")
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("prop");

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
		List<Counties> countyList = countiesDAO.getCountyForId(countyId);
		List<Locations> locationList = locationsDAO.getLocationsForCountyId(countryId);
		List<Property> property = propertyDAO.getPropertyForUser(emailUser);
		List<RangeAmount> rangeList = rangeAmountDAO.getAllRangeAmount();
		
		System.out.println("Email ..." + emailUser);
		
		modelAndView.addObject("property", property);
		modelAndView.addObject("countryList", countryList);
		modelAndView.addObject("countyList", countyList);
		modelAndView.addObject("locationList", locationList);
		modelAndView.addObject("rangeList", rangeList);
		
		System.out.println("User authorized to access add property page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access add property page");
		
		return model;
			
		}

	}
	
	@RequestMapping(value = "/property2.html", headers = "Accept=*/*", method=RequestMethod.GET)
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

	@RequestMapping(value = "/property3.html", headers = "Accept=*/*", method=RequestMethod.GET)
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
		 
	@RequestMapping(value="/property",method=RequestMethod.POST)
	public @ResponseBody JsonResponse addProperty(HttpServletRequest request, @ModelAttribute(value="property") Property property, BindingResult result) throws Exception{

	JsonResponse res = new JsonResponse();
	
	// Prepare key values for email message
	int cntId = 0;
	boolean checkUnit = propertyDAO.checkUnit(property.getUnit());

	try {
	cntId = Integer.parseInt(property.getCounty());
	} catch (NumberFormatException e) {
	System.err.println("County ID is not a number. Default Value 1 inserted : " +cntId);
	cntId = 1;
	}

	int locId = 0;
	try {
	locId = Integer.parseInt(property.getLocation());
	} catch (NumberFormatException e) {
	System.err.println("Location ID is not a number. Default Value 1 inserted : " +locId);
	locId = 1;
	}
	
	int ranId = 0;
	try {
	ranId = Integer.parseInt(property.getPrange());
	} catch (NumberFormatException e) {
	System.err.println("Range ID is not a number. Default Value 6 inserted : " +ranId);
	ranId = 1;
	}

	String count = "";
	String loc = "";
	String ran = "";
	Counties cnts = countiesDAO.getCountiesById(cntId);
	Locations locs = locationsDAO.getLocationsById(locId);
	RangeAmount rang = rangeAmountDAO.getRangeAmountById(ranId);
	count = cnts.getCounty();
	loc = locs.getLocation();
	ran = rang.getPrange();

	System.out.println("County to Email: " +count);
	System.out.println("Location to Email: " +loc);
	System.out.println("Range to Email: " +ran);

	//Initialising validation error messages
	
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "country", "Please select a country.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "county", "Please select a county.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "location", "Please select a location.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "propertyType", "Please select a property type.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "category", "Please select a category.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "bedrooms", "Please insert a number or 0.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "contacts", "Please insert your contact information.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "name", "Please insert a name for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "company", "Please insert a company name.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "description", "Please insert a description for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "website", "Please insert a website for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "schedule", "Please insert a schedule your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "prange", "Please select a range for your property.");
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "status", "Please select a status for your property.");	
	//ValidationUtils.rejectIfEmptyOrWhitespace(result, "amount", "Please insert an Amount for your property.");	
	
	if(!result.hasErrors()){ 
		
	System.out.println("Edit Button clicked");
	
	int id = request.getSession().getAttribute(LOGIN_DETAILS).hashCode();
    int idz = Integer.parseInt(request.getParameter("id"));

	Date date = new Date();
	SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date dat = new java.sql.Date(date.getTime());  
	String emailUser = request.getSession().getAttribute(SESSION_USER).toString();	
		 
	PropertyList.add(property);
	res.setStatus("SUCCESS");
	res.setResult(propertyList);		 
    
	int reffNo = propertyDAO.getAllProperty().size();	
	List<Alerts> all = alertsDAO.getAlerts();
	
	if (all.listIterator().hasNext()) {
	
	String acountry = all.listIterator().next().getCountry();
	String acounty = all.listIterator().next().getCounty();
	String alocation = all.listIterator().next().getLocation();
	String apropType = all.listIterator().next().getPropertyType();
	String acategory = all.listIterator().next().getCategory();
	String abrooms = all.listIterator().next().getBedrooms();
	String arange = all.listIterator().next().getPrange();
	
	String prop = acountry+acounty+alocation+apropType+acategory+abrooms+arange;
	String propList = property.getCountry()+property.getCounty()+property.getLocation()+property.getPropertyType()+property.getCategory()+property.getBedrooms()+property.getPrange();
	
	System.out.println("Alert String: " +prop);
	System.out.println("Property List: " +propList);
	
	} else {
		System.out.println("Alerts List is Empty. Nothing to compare.");
	}	
	
	String propList = property.getCountry()+property.getCounty()+property.getLocation()+property.getPropertyType()+property.getCategory()+property.getBedrooms()+property.getPrange();
	
	String orient = "style='height:150px; width:250px; border: 1px solid #E6E6FA; border-radius:0px; margin-top: 0px !important;'";
	
	// Create property string of the alert criteria requested
	System.out.println("Adopting the property string of the alert criteria requested");
	
	try {
		int test1 = Integer.parseInt(property.getCounty());
		Counties ct = countiesDAO.getCountiesById(test1);
		String contVal = ct.getCounty();
		property.setCountyVal(contVal);
		} catch (NumberFormatException e) {
		System.err.println("Variable ct is not a number. Default Value 1 inserted : ");
		Counties ct = countiesDAO.getCountiesById(1);
		String contVal = ct.getCounty();
		property.setCountyVal(contVal);
	}
	
	try {
		int test2 = Integer.parseInt(property.getLocation());
		Locations lc = locationsDAO.getLocationsById(test2);
		String locVal = lc.getLocation();
		property.setLocationVal(locVal);
		} catch (NumberFormatException e) {
		System.err.println("Variable lc is not a number. Default Value 1 inserted : ");
		Locations lc = locationsDAO.getLocationsById(1);
		String locVal = lc.getLocation();
		property.setLocationVal(locVal);
		}	
	
	try {
		int test3 = Integer.parseInt(property.getPrange());
		RangeAmount rv = rangeAmountDAO.getRangeAmountById(test3);
		String rangeVal = rv.getPrange();
		property.setRangeValue(rangeVal);
		} catch (NumberFormatException e) {
		System.err.println("Variable rv is not a number. Default Value 6 inserted : ");
		RangeAmount rv = rangeAmountDAO.getRangeAmountById(6);
		String rangeVal = rv.getPrange();
		property.setRangeValue(rangeVal);
		}
	
	// Validate Units
	String unitx = (property.getName()+property.getBedrooms());
	
    if (checkUnit == true) {
    	result.rejectValue("bedrooms", "Unit already exists");
    	System.out.println("Unit already exists");
    
    }	else {
    	
    	property.setUnit(unitx);
    }
	property.setPropString(propList);
	property.setViews(1);
	property.setProfileId(idz);	
	property.setEmail(emailUser);	
	property.setCreatedDate(dat);
	property.setPropreff("PropReff"+reffNo);
	property.setOrientation(orient);
	property.setEnquiries(1);
	 
	propertyDAO.addProperty(property);	  
	  
	System.out.println("Property Added Succesfully");
	
	// Send Mail to User
/*
    List<MailConfig> mailcon = mailConfigDAO.getMailConfig();
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    Properties props = new Properties();
    props.put(mailcon.listIterator().next().getTransportProtocol(), mailcon.listIterator().next().getProtocol());
    props.put(mailcon.listIterator().next().getSmtpStartTls(), mailcon.listIterator().next().getStatus());

    sender.setHost(mailcon.listIterator().next().getSmtpHost());
    sender.setPort(mailcon.listIterator().next().getSmtpPort());
    sender.setUsername(mailcon.listIterator().next().getHostUserName());
    sender.setPassword(mailcon.listIterator().next().getHostPassword());
    sender.setJavaMailProperties(props);

	//use the true flag to indicate you need a multipart message
	MimeMessage message = sender.createMimeMessage();

	MimeMessageHelper helper = new MimeMessageHelper(message);
	helper.setTo(emailUser);
	helper.setFrom(mailcon.listIterator().next().getHostUserName());
	helper.setSubject("Kejani: Property Created");

	StringBuffer sb = new StringBuffer();

    sb.append("<html><body>Dear <a style='color: style='color: #191970;'>" +emailUser+ "</a>,");
    sb.append("<p><br> Your Property has been created. Below are details of your property.<p><br>");
    sb.append("<table style='border: 1px solid #483D8B; width: 650px;'><tr style='border: 1px solid #191970;'>");
    sb.append("<th style='color: #191970;'>Name</th><th style='color: #191970;'>County</th><th style='color: #191970;'>Location</th>");
    sb.append("<th style='color: #191970;'>Category</th><th style='color: #191970;'>Property Type</th><th style='color: #191970;'>Bedrooms</th>");
    sb.append("<th style='color: #191970;'>Range</th></tr><tr style='border: 1px solid #483D8B'><th style='color: #191970;'>"+ count+"</th><th style='color: #191970;'>"+ loc);
    sb.append("</th><th style='color: #191970;'>"+ property.getCategory()+"</th><th style='color: #191970;'>"+ property.getPropertyType()+"</th>");
    sb.append("<th style='color: #191970;'>"+property.getBedrooms()+"</th><th style='color: #191970;'>"+property.getRangeValue()+"</th></tr></table><p><br>");
    sb.append("<p><br> You are now able to view the details of your listed properties and advise in case of any changes.");
    sb.append("<br>Our contact Email is info@kejani.com.<br> Thanks and good day. Enjoy our services.");
    sb.append("<p><br>Regards <p><br> The Kejani Team<p><a href='www.kejani.info'><u>www.kejani.info</u></a><p>");
    sb.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");
    sb.append("<i>Kejani</i></h1>");  
    sb.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
    sb.append("<p><br></body></html>");   

	helper.setText(sb.toString(), true);

	try {
	    sender.send(message);
	    System.out.println("Email succesfully sent to " +emailUser); 
	}
	catch (MailException ex) {
	    System.err.println(ex.getMessage());
	}

*/
	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	

	} else { 

	System.err.println("A validation error has occurred. Property has not been added.");
	res.setStatus("FAIL");
	res.setResult(result.getAllErrors());

	}

	return res;
 
	}
	
	
	public MailConfigDAO getMailConfigDAO() {
		return mailConfigDAO;
	}

	public void setMailConfigDAO(MailConfigDAO mailConfigDAO){
		this.mailConfigDAO = mailConfigDAO;
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
	
	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
	
	public AlertsDAO getAlertsDAO() {
		return alertsDAO;
	}

	public void setAlertsDAO(AlertsDAO alertsDAO){
		this.alertsDAO = alertsDAO;
	}
	
	public RangeAmountDAO getRangeAmountDAO() {
		return rangeAmountDAO;
	}

	public void setRangeAmountDAO(RangeAmountDAO rangeAmountDAO){
		this.rangeAmountDAO = rangeAmountDAO;
	}
}
