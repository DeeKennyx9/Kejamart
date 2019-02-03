package kejamart.controller.alert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kejamart.controller.main.UserSession;
import kejamart.dao.AlertsDAO;
import kejamart.dao.CountiesDAO;
import kejamart.dao.CountriesDAO;
import kejamart.dao.LocationsDAO;
import kejamart.dao.MailConfigDAO;
import kejamart.dao.RangeAmountDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Alerts;
import kejamart.model.Counties;
import kejamart.model.Countries;
import kejamart.model.Locations;
import kejamart.model.MailConfig;
import kejamart.model.RangeAmount;

@EnableWebMvc
@Controller
public class AddRequestController implements Settings{

	AlertsDAO alertsDAO;	
	CountriesDAO countriesDAO;	
	CountiesDAO countiesDAO;	
	LocationsDAO locationsDAO;	
	RangeAmountDAO rangeAmountDAO;	
	MailConfigDAO mailConfigDAO;

	UserSession userSession = new UserSession();

	private List<Alerts> alertsList2 = new ArrayList<Alerts>();

	public static Logger logger = Logger.getLogger(AddRequestController.class);

    private List<Alerts> AlertsList = new ArrayList<Alerts>();
    
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    } 

@RequestMapping(value="/requests" , method=RequestMethod.GET)
public ModelAndView listAlerts(HttpServletRequest request) throws Exception {

	ModelAndView modelAndView = new ModelAndView("req");

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
	
	System.out.println("Email ..." + emailUser);
	
	List<Alerts> alerts = alertsDAO.getAlertsForUser(emailUser);
	List<Countries> countryList = countriesDAO.getCountries();
	List<Counties> countyList = countiesDAO.getCountyForId(countryId);
	List<Locations> locationList = locationsDAO.getLocationsForCountyId(countyId);
	//List<RangeAmount> rangeList = rangeAmountDAO.getAllRangeAmount();
	List<RangeAmount> rangeList = rangeAmountDAO.getResidentialRange();
	List<RangeAmount> rangeListx = rangeAmountDAO.getCommercialRange();	
	List<RangeAmount> rangeLists = rangeAmountDAO.getAllRangeAmount();

	System.out.println("Country List ..." + countryList);
	System.out.println("County List ..." + countyList);
	System.out.println("Location List ..." + locationList);
	System.out.println("County ID Final : " +countyId);
	System.out.println("Range List : " +rangeList);
	System.out.println("Range List 2 : " +rangeListx);

	//Lists
	modelAndView.addObject("alerts", alerts);
	modelAndView.addObject("countryList", countryList);
	modelAndView.addObject("countyList", countyList);
	modelAndView.addObject("locationList", locationList);
	modelAndView.addObject("rangeList", rangeList);
	modelAndView.addObject("rangeListx", rangeListx);
	modelAndView.addObject("rangeLists", rangeLists);

	System.out.println("User authorized to access request page");
	return modelAndView;

	} else {

	ModelAndView model = new ModelAndView("restricted");
	System.err.println("Visitor NOT authorized to access request page");

	return model;

	}

}

@RequestMapping(value = "/requests2.html", headers = "Accept=*/*", method=RequestMethod.GET)
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

@RequestMapping(value = "/requests3.html", headers = "Accept=*/*", method=RequestMethod.GET)
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

@RequestMapping(value="/requests",method=RequestMethod.POST)
public @ResponseBody JsonResponse addAlerts(HttpServletRequest request, @ModelAttribute(value="requests") Alerts alerts, BindingResult result) throws Exception{

JsonResponse res = new JsonResponse();
int idValue = 0;

// Prepare key values for email message
int cntId = 0;

try {
cntId = Integer.parseInt(alerts.getCounty());
} catch (NumberFormatException e) {
System.err.println("County ID is not a number. Default Value 1 inserted : " +cntId);
cntId=1;
}

int locId = 0;

try {
locId = Integer.parseInt(alerts.getLocation());
} catch (NumberFormatException e) {
System.err.println("Location ID is not a number. Default Value 1 inserted : " +locId);
locId=1;
}

int ranId = 0;

try {
ranId = Integer.parseInt(alerts.getPrange());
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

String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
boolean checkUser = alertsDAO.checkUser(emailUser);

//Initialising validation error messages
ValidationUtils.rejectIfEmptyOrWhitespace(result, "country", "Please select a Country.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "county", "Please select a County.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "location", "Please select a Location.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "propertyType", "Please select a Property Type.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "category", "Please select a Category.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "prange", "Please select a Range.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "bedrooms", "Please insert a number.");

List<Alerts> al = alertsDAO.getAlertsForUser(emailUser);

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

String propList = alerts.getCountry()+alerts.getCounty()+alerts.getLocation()+alerts.getPropertyType()+alerts.getCategory()+alerts.getBedrooms()+alerts.getPrange();

System.out.println("Prop "+prop);
System.out.println("Prop List "+propList);

//Check if criteria exists for user
if (prop.equals(propList) && (checkUser)) {
	result.rejectValue("location", "You have a similar search criteria. Please refine your search");
	System.err.println("Search Criteria already exists");
} 

} else {
	System.out.println("Alerts List is Empty. Nothing to compare.");
}

String propList = alerts.getCountry()+alerts.getCounty()+alerts.getLocation()+alerts.getPropertyType()+alerts.getCategory()+alerts.getBedrooms()+alerts.getPrange();

if(!result.hasErrors()){

	if (request.getSession().getAttribute(SESSION_USER) != null) {

		int totAlerts = 0;
		totAlerts = al.size();
		System.out.println("Total Requests: " +totAlerts);
		Date date = new Date();
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dat = new java.sql.Date(date.getTime());

		if(totAlerts < 5) {
		// Create a new property string of the alert criteria requested
			
		System.out.println("Creating a new index of the alert criteria requested");
		
		try {
			int test1 = 0;
			test1=Integer.parseInt(alerts.getCounty());
			Counties ct = countiesDAO.getCountiesById(test1);
			String contVal = ct.getCounty();
			alerts.setCountyVal(contVal);
			} catch (NumberFormatException e) {
			System.err.println("Variable ct is not a number. Default Value 1 inserted : ");
			Counties ct = countiesDAO.getCountiesById(1);
			String contVal = ct.getCounty();
			alerts.setCountyVal(contVal);
		}
		
		try {
			int test2 = 0;
			test2=Integer.parseInt(alerts.getLocation());
			Locations lc = locationsDAO.getLocationsById(test2);
			String locVal = lc.getLocation();
			alerts.setLocationVal(locVal);
			} catch (NumberFormatException e) {
			System.err.println("Variable lc is not a number. Default Value 1 inserted : ");
			Locations lc = locationsDAO.getLocationsById(1);
			String locVal = lc.getLocation();
			alerts.setLocationVal(locVal);
			}	
		
		try {
			int test3 = 0;
			test3 = Integer.parseInt(alerts.getPrange());
			RangeAmount rv = rangeAmountDAO.getRangeAmountById(test3);
			String rangeVal = rv.getPrange();
			alerts.setRangeValue(rangeVal);
			} catch (NumberFormatException e) {
			System.err.println("Variable rv is not a number. Default Value 6 inserted : ");
			RangeAmount rv = rangeAmountDAO.getRangeAmountById(6);
			String rangeVal = rv.getPrange();
			alerts.setRangeValue(rangeVal);
			}		

		alerts.setPropString(propList);
    	alerts.setCreatedDate(dat);
		alerts.setEmail(emailUser);
		alerts.setStatus("0");
		
		alertsDAO.addAlerts(alerts);

        AlertsList.add(alerts);
        res.setStatus("SUCCESS");
        res.setResult(alertsList2);
        System.out.println("Request created succesfully: " +idValue);
        
        //Send Mail to User

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
        String company = mailcon.listIterator().next().getSubject();
        String website = mailcon.listIterator().next().getWebsite();

        //use the true flag to indicate you need a multipart message
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(emailUser);
        helper.setFrom(mailcon.listIterator().next().getHostUserName());
        helper.setSubject("kejamart: Property Alert Created");
        
        StringBuffer sb = new StringBuffer();
  
        sb.append("<html><body>Dear <a style='color: style='color: #191970;'>" +emailUser+ "</a>,");
        sb.append("<p><br> Your Property Alert has been created. Below are details of your search criteria.<p><br>");
        sb.append("<table style='border: 1px solid #483D8B; width: 650px;'><tr style='border: 1px solid #191970;'>");
        sb.append("<th style='color: #191970;'>County</th><th style='color: #191970;'>Location</th><th style='color: #191970;'>Category</th>");
        sb.append("<th style='color: #191970;'>Property Type</th><th style='color: #191970;'>Bedrooms</th><th style='color: #191970;'>Range</th></tr>");
        sb.append("<tr style='border: 1px solid #483D8B'><th style='color: #191970;'>"+ count+"</th><th style='color: #191970;'>"+ loc);
        sb.append("</th><th style='color: #191970;'>"+ alerts.getCategory()+"</th><th style='color: #191970;'>"+ alerts.getPropertyType()+"</th>");
        sb.append("<th style='color: #191970;'>"+alerts.getBedrooms()+"</th><th style='color: #191970;'>"+alerts.getRangeValue()+"</th></tr></table>");
        sb.append("<p><br> You will recieve a notification once a property within your criteria is available.");
        sb.append("<br> Thanks and good day. Enjoy our free services.<p><br>Regards <p><br> The "+company+ " Team<p><a href='"+website+ "'><u>"+website+"</u></a> <p>");
        sb.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");
        sb.append("<i>"+company+ "</i></h1>");  
        sb.append("<h3 style='color: #696969 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
        sb.append("<p><br></body></html>");         
        
        helper.setText(sb.toString(), true);

        try {
            sender.send(message);
            System.out.println("Email succesfully sent to " +emailUser); 
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }

        //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<        

	    } else {

		System.err.println("Requests Exceeded for user: " +totAlerts);
        res.setStatus("FAIL");
        res.setResult(result.getAllErrors());

	    }

        }

        } else {

        System.err.println("A validation error has occurred. Alert has not been added.");
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

public AlertsDAO getAlertsDAO() {
	return alertsDAO;
}

public void setAlertsDAO(AlertsDAO alertsDAO){
	this.alertsDAO = alertsDAO;
}

public RangeAmountDAO getPangeAmountDAO() {
	return rangeAmountDAO;
}

public void setRangeAmountDAO(RangeAmountDAO rangeAmountDAO){
	this.rangeAmountDAO = rangeAmountDAO;
}

}