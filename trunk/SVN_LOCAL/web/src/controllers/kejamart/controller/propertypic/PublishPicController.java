package kejamart.controller.propertypic;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.AlertsDAO;
import kejamart.dao.CountiesDAO;
import kejamart.dao.CountriesDAO;
import kejamart.dao.LocationsDAO;
import kejamart.dao.MailConfigDAO;
import kejamart.dao.MessagesDAO;
import kejamart.dao.PropertyDAO;
import kejamart.dao.PropertyPicDAO;
import kejamart.helper.Settings;
import kejamart.model.Alerts;
import kejamart.model.MailConfig;
import kejamart.model.Messages;
import kejamart.model.Property;
import kejamart.model.PropertyPics;

@Controller
public class PublishPicController implements Settings {

	AlertsDAO alertsDAO;
	PropertyPics propertyPics;
	PropertyPicDAO propertyPicDAO;
	MailConfigDAO mailConfigDAO;
	PropertyDAO propertyDAO;	
	CountriesDAO countriesDAO;		
	CountiesDAO countiesDAO;	
	LocationsDAO locationsDAO;	
	MessagesDAO messagesDAO;
	UserSession userSession = new UserSession();
	
	private List<PropertyPics> propertyPicList = new ArrayList<PropertyPics>(); 
	
	private List<Alerts> alertsList = new ArrayList<Alerts>();
	
	public static Logger logger = Logger.getLogger(PublishPicController.class);	

    private List<PropertyPics> PropertyPicList = new ArrayList<PropertyPics>();	
    
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    } 
	
	@RequestMapping(value="/publishpics", method=RequestMethod.GET)
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("public");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
		
		int id = Integer.parseInt(request.getParameter("id"));

		List<PropertyPics> propertyPics = propertyPicDAO.getPropertyPicsForId(id);
		
		int prid = Integer.parseInt(request.getParameter("propertyId"));
		int pid = 0;
		pid = prid;
		request.getSession().setAttribute(PROPERTY_DETAILS, pid);
				
		int idValue= 0;
		idValue = id;
		request.getSession().setAttribute(PICTURE_DETAILS, idValue);		
		
		modelAndView.addObject("propertyPics", propertyPics);
		System.out.println("User authorized to access property pic page");
		return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access property pic page");
		
		return model;
			
		}

	}
		 
	/**
	 * @param model
	 * @param request
	 * @param propertyPics
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/publishpics", method=RequestMethod.POST)
	public String publishPropertyPics(Model model, HttpServletRequest request, @ModelAttribute(value="propertyPics") PropertyPics propertyPics, BindingResult result) throws Exception{

	Date date = new Date();
	SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date dat = new java.sql.Date(date.getTime());
	int id = 0;
	id = (Integer.parseInt(request.getParameter("id")));
	String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
	
	int pid = 0;
	pid = (Integer.parseInt(request.getParameter("propertyId")));
	System.out.println("Publish Button clicked: >|< " +pid);
	
	//Initialising validation error messages
	ValidationUtils.rejectIfEmptyOrWhitespace(result, "fileName", "File Name is missing.");
	
	propertyPics.setStatus("1");
	propertyPics.setEmail(emailUser);	
	propertyPics.setCreatedDate(dat);
	
	propertyPicDAO.updatePic(propertyPics, id);	
	System.out.println("Property published succesfully");
	
	// Property Values	
	List<Alerts> all = alertsDAO.getAlerts();
	
	String acountry = all.listIterator().next().getCountry();
	String acounty = all.listIterator().next().getCounty();
	String alocation = all.listIterator().next().getLocation();
	String apropType = all.listIterator().next().getPropertyType();
	String acategory = all.listIterator().next().getCategory();
	String prop = acountry+acounty+alocation+apropType+acategory;
	
	System.out.println("Alert String || Prop: " +prop);
	
	List<Property> prd = propertyDAO.getPropertyForId(pid);
	Property pr = propertyDAO.getPropertyById(pid);
	int contId = pr.getId();
	String pcountry = prd.listIterator().next().getCountry();
	String pcounty = prd.listIterator().next().getCounty();
	String plocation = prd.listIterator().next().getLocation();
	String ppropType = prd.listIterator().next().getPropertyType();
	String pcategory = prd.listIterator().next().getCategory();
	String pprop = pcountry+pcounty+plocation+ppropType+pcategory;
	
	System.out.println("Property String || Pprop: " +pprop);
	System.out.println("Property Emails " +pr.getEmail());
	System.out.println("Profile ID " +pr.getProfileId());
	System.out.println("Property String " +pr.getPropString());
	
	List<Alerts> alt = alertsDAO.getAlertsForString(pr.getPropString());
	String plocval = alt.listIterator().next().getLocationVal();
	int alid = 0;
	
	alid = alt.listIterator().next().getId();
	String pcontval = alt.listIterator().next().getCountyVal();
	String proptypep = alt.listIterator().next().getPropertyType();
	String categoryp = alt.listIterator().next().getCategory();
	String broom = alt.listIterator().next().getBedrooms();
	String pemail = alt.listIterator().next().getEmail();
	String pstring = alt.listIterator().next().getPropString();
	//String pdate = (new SimpleDateFormat("dd-MMM-yyyy")).format(alt.listIterator().next().getCreatedDate());
	
	System.out.println("Alert By Property Email " +pemail);
	System.out.println("Alert By Property String " +pstring);

	//Notification Logic
	
	System.out.println("Total Alerts in List " +alt.size());
	System.out.println("Alert Emails " +alt.listIterator().next().getEmail());
	
	List<Messages> ms = messagesDAO.getMessagesForEmail(pemail);
	
	if(ms.listIterator().hasNext()){
	
	int msid = 0;
    msid = ms.listIterator().next().getPropertyId();
    System.err.println("Property Id in Message : " +msid);	
	} else {
		
	System.err.println("Notification List Empty for User");	
	
	}

	// Send Mail to Users // Begin Loop
	
	for (Alerts a: alt) {
	
	String ppdate = (new SimpleDateFormat("dd-MMM-yyyy")).format(a.getCreatedDate());
	
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

	// use the true flag to indicate you need a multipart message    
	MimeMessage message = sender.createMimeMessage();	
	MimeMessageHelper helper = new MimeMessageHelper(message);
	
	helper.setTo(a.getEmail());
	System.out.println("Test Emails: "+a.getEmail());
	helper.setFrom(mailcon.listIterator().next().getHostUserName());
	helper.setSubject("Kejani: Property Notification");
		
	StringBuffer sb = new StringBuffer();
	StringBuffer msg = new StringBuffer();
		
	sb.append("<html><body>");
	sb.append("Dear <a style='color: style='color: #191970;'><b>" +a.getEmail()+ "</b></a>,");
    sb.append("<p><br>Based on the criteria of your Property Alert created on <b>"+ppdate+",</b> the below property is now available.<p><br>");
    sb.append("<table style='border: 1px solid #483D8B; width: 650px;'><tr style='border: 1px solid #191970;'>");
    sb.append("<th style='color: #191970;'>County</th><th style='color: #191970;'>Location</th>");
    sb.append("<th style='color: #191970;'>Category</th><th style='color: #191970;'>Property Type</th><th style='color: #191970;'>Bedrooms</th></tr>");
    sb.append("<tr style='border: 1px solid #483D8B'><th style='color: #191970;'>"+pcontval+"</th><th style='color: #191970;'>"+plocval+"</th>");
    sb.append("<th style='color: #191970;'>"+categoryp+"</th><th style='color: #191970;'>"+proptypep+"</th>");
    sb.append("<th style='color: #191970;'>"+broom+"</th></tr></table><p>");
    sb.append("<br>Keep enjoying our property alert services which are and will always be free.");
    sb.append("<p><br>Regards <p><br> The Kejani Team<p><a href='www.kejani.info'><u>www.kejani.info</u></a><p>");
    sb.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");
    sb.append("<i>Kejani</i></h1>");  
    sb.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
    sb.append("<p><br>");
    sb.append("</body></html>");  
    
	msg.append("Dear <a style='color: style='color: #191970;'><b>" +a.getEmail()+ "</b></a>,");
	msg.append("<p><br>Based on the criteria of your Property Alert created on <b>"+ppdate+",</b> the below property is now available.<p><br>");
	msg.append("<table style='border: 1px solid #483D8B; width: 650px;'><tr style='border: 1px solid #191970;'>");
	msg.append("<th style='color: #191970;'>County</th><th style='color: #191970;'>Location</th>");
	msg.append("<th style='color: #191970;'>Category</th><th style='color: #191970;'>Property Type</th><th style='color: #191970;'>Bedrooms</th></tr>");
	msg.append("<tr style='border: 1px solid #483D8B'><th style='color: #191970;'>"+pcontval+"</th><th style='color: #191970;'>"+plocval+"</th>");
    msg.append("<th style='color: #191970;'>"+categoryp+"</th><th style='color: #191970;'>"+proptypep+"</th>");
    msg.append("<th style='color: #191970;'>"+broom+"</th></tr></table><p>");
    msg.append("<br>Keep enjoying our property alert services which are and will always be free.");
    msg.append("<p><br>Regards <p><br> The Kejani Team<p><a href='www.kejani.info'><u>www.kejani.info</u></a><p>");
    msg.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");
    msg.append("<i>"+company+"</i></h1>");  
    msg.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
    msg.append("<p><br>");

	helper.setText(sb.toString(), true);
	
	// Insert new messages
	Messages messages = new Messages();
	
	messages.setEmail(a.getEmail());
	messages.setSender(mailcon.listIterator().next().getHostUserName());
	messages.setStatus("0");
	messages.setPropString(pstring);
	messages.setPropertyId(pid);
	messages.setMessage(msg.toString());
	messages.setCreatedDate(dat);
	
	messagesDAO.addMessages(messages);	
	System.out.println("Messages Added for User "+a.getEmail());
	
	// Mark alerts as read
	a.setStatus("1");	
	alertsDAO.markSentMessages(a, a.getId());	
	System.out.println("Alert marked as Read for User "+a.getEmail());
	
	System.out.println("Alert Id "+a.getId());
	System.out.println("Alerts "+a);
	
	try {
        sender.send(message);
        System.out.println("Email succesfully sent to "+a.getEmail()); 
    }
    catch (MailException ex) {
        System.err.println(ex.getMessage());
    }
	
	} // End of Alerts Loop	
	
	return "redirect:" + "picturelist.html";
	
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
	
	public MailConfigDAO getMailConfigDAO() {
		return mailConfigDAO;
	}

	public void setMailConfigDAO(MailConfigDAO mailConfigDAO){
		this.mailConfigDAO = mailConfigDAO;
	}
	
	public PropertyPicDAO getPropertyPicDAO() {
		return propertyPicDAO;
	}
	
	public void setPropertyPicDAO(PropertyPicDAO propertyPicDAO){
		this.propertyPicDAO = propertyPicDAO;
	}
	
	public MessagesDAO getMessagesDAO() {
		return messagesDAO;
	}

	public void setMessagesDAO(MessagesDAO messagesDAO){
		this.messagesDAO = messagesDAO;
	}
	
	public AlertsDAO getAlertsDAO() {
		return alertsDAO;
	}

	public void setAlertsDAO(AlertsDAO alertsDAO){
		this.alertsDAO = alertsDAO;
	}
}


