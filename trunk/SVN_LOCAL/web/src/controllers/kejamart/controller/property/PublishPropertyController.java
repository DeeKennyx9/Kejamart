package kejamart.controller.property;

import java.text.SimpleDateFormat;
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
import kejamart.helper.Settings;
import kejamart.model.Alerts;
import kejamart.model.MailConfig;
import kejamart.model.Messages;
import kejamart.model.Property;

@Controller
public class PublishPropertyController implements Settings {

	AlertsDAO alertsDAO;
	Property property;
	MailConfigDAO mailConfigDAO;
	PropertyDAO propertyDAO;	
	CountriesDAO countriesDAO;		
	CountiesDAO countiesDAO;	
	LocationsDAO locationsDAO;	
	MessagesDAO messagesDAO;
	UserSession userSession = new UserSession();
	
	public static Logger logger = Logger.getLogger(PublishPropertyController.class);	
    
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    } 
	
	@RequestMapping(value="/publishprops", method=RequestMethod.GET)
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("public");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		int id = Integer.parseInt(request.getParameter("id"));

		List<Property> property = propertyDAO.getPropertyForId(id);
		
		modelAndView.addObject("property", property);
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
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/publishprops", method=RequestMethod.POST)
	public String publishProperty(Model model, HttpServletRequest request, @ModelAttribute(value="property") Property property, BindingResult result) throws Exception{

	Date date = new Date();
	SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
	java.sql.Date dat = new java.sql.Date(date.getTime());
	int id = Integer.parseInt(request.getParameter("id"));
	System.out.println("Publish Button clicked: >|< " +id);
	
	property.setStatus(1);
	
	propertyDAO.publishProperty(property, id);	
	
	System.out.println("Property published succesfully");
	
	// Property Values	
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
	
	System.out.println("Alert String || Prop: " +prop);
	
	} else {
		System.out.println("Alerts List is Empty. Nothing to compare.");
	}	
	
	List<Property> prd = propertyDAO.getPropertyForId(id);
	Property pr = propertyDAO.getPropertyById(id);
	
	if (prd.listIterator().hasNext()) {	
	
	String pcountry = prd.listIterator().next().getCountry();
	String pcounty = prd.listIterator().next().getCounty();
	String plocation = prd.listIterator().next().getLocation();
	String ppropType = prd.listIterator().next().getPropertyType();
	String pcategory = prd.listIterator().next().getCategory();
	String pbrooms = prd.listIterator().next().getBedrooms();
	String prange = prd.listIterator().next().getPrange();
	String pprop = pcountry+pcounty+plocation+ppropType+pcategory+pbrooms+prange;
	
	System.out.println("Property String || Pprop: " +pprop);
	System.out.println("Property Emails " +pr.getEmail());
	System.out.println("Profile ID " +pr.getProfileId());
	System.out.println("Property String " +pr.getPropString());
	
	} else {
		System.out.println("Property List is Empty. Nothing to compare.");
	}	
	
	
	List<Alerts> alt = alertsDAO.getAlertsForString(pr.getPropString());
	
	if (alt.listIterator().hasNext()) {		
	
	String plocval = alt.listIterator().next().getLocationVal();
	String pcontval = alt.listIterator().next().getCountyVal();
	String proptypep = alt.listIterator().next().getPropertyType();
	String categoryp = alt.listIterator().next().getCategory();
	String broom = alt.listIterator().next().getBedrooms();
	String pemail = alt.listIterator().next().getEmail();
	String pstring = alt.listIterator().next().getPropString();
	
	System.out.println("Alert By Property Email " +pemail);
	System.out.println("Alert By Property String " +pstring);

	//Notification Logic
	
	System.out.println("Total Alerts in List " +alt.size());
	System.out.println("Alert Emails " +alt.listIterator().next().getEmail());
	
	List<Messages> ms = messagesDAO.getMessagesForEmail(pemail);
	
	if(ms.listIterator().hasNext()){
	
    int msid = ms.listIterator().next().getPropertyId();
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
    String website = mailcon.listIterator().next().getWebsite();

	// use the true flag to indicate you need a multipart message    
	MimeMessage message = sender.createMimeMessage();	
	MimeMessageHelper helper = new MimeMessageHelper(message);
	
	helper.setTo(a.getEmail());
	System.out.println("Test Emails: "+a.getEmail());
	helper.setFrom(mailcon.listIterator().next().getHostUserName());
	helper.setSubject("kejamart Alert: Property Available");
		
	StringBuffer sb = new StringBuffer();
	StringBuffer msg = new StringBuffer();
		
	sb.append("<html><body>");
	sb.append("Dear <a style='color: style='color: #191970;'><b>" +a.getEmail()+ "</b></a>,");
    sb.append("<p><br>Based on the criteria of your Property Alert created on <b>"+ppdate+",</b> the below property is now available.<br>");
    sb.append("<table style='border: 1px solid #483D8B; width: 650px;'><tr style='border: 1px solid #191970;'>");
    sb.append("<th style='color: #191970; padding-left: 10px;'>County</th><th style='color: #191970;'>Location</th>");
    sb.append("<th style='color: #191970;'>Category</th><th style='color: #191970; text-align: center;'>Property Type</th><th style='color: #191970;'>Bedrooms</th><th style='color: #191970;'>Range</th></tr>");
    sb.append("<tr style='border: 1px solid #483D8B'><th style='color: #191970; padding-left: 10px;'>"+pcontval+"</th><th style='color: #191970;'>"+plocval+"</th>");
    sb.append("<th style='color: #191970;'>"+categoryp+"</th><th style='color: #191970; text-align: center;'>"+proptypep+"</th>");
    sb.append("<th style='color: #191970; text-align: center;'>"+broom+"</th><th style='color: #191970;'>"+a.getPrange()+"</th></tr></table><p>");
    sb.append("<br>Keep enjoying our property alert services which are and will always be free.");
    sb.append("<p><br>Regards <p><br> The "+company+" Team<p><a href='"+website+"'><u>"+website+"</u></a><p>");
    sb.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");
    sb.append("<i>"+company+"</i></h1>");  
    sb.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
    sb.append("<p><br>");
    sb.append("</body></html>");  
    
    msg.append("<html><body>");
	msg.append("Dear <a style='color: style='color: #191970;'><b>" +a.getEmail()+ "</b></a>,");
	msg.append("<p><br>Based on the criteria of your Property Alert created on <b>"+ppdate+",</b> the below property is now available.<p>");
	msg.append("<table style='border: 1px solid #483D8B; width: 650px;'><tr style='border: 1px solid #191970;'>");
	msg.append("<th style='color: #191970; padding-left: 10px;'>County</th><th style='color: #191970;'>Location</th>");
	msg.append("<th style='color: #191970;'>Category</th><th style='color: #191970;'>Property Type</th><th style='color: #191970;'>Bedrooms</th><th style='color: #191970;'>Range</th></tr>");
	msg.append("<tr style='border: 1px solid #483D8B'><th style='color: #191970; padding-left: 10px;'>"+pcontval+"</th><th style='color: #191970;'>"+plocval+"</th>");
    msg.append("<th style='color: #191970;'>"+categoryp+"</th><th style='color: #191970;'>"+proptypep+"</th>");
    msg.append("<th style='color: #191970; text-align: center;'>"+broom+"</th><th style='color: #191970;'>"+a.getRangeValue()+"</th></tr></table><p>");
    msg.append("<br>Keep enjoying our property alert services which are and shall always be free.");
    msg.append("<p><br>Regards <p><br> The "+company+" Team<p><a href='"+website+"'><u>"+website+"</u></a><p>");
    msg.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");
    msg.append("<i>"+company+"</i></h1>");  
    msg.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
    msg.append("<p><br>");
    msg.append("</body></html>");

	helper.setText(sb.toString(), true);
	
	// Insert new messages
	Messages messages = new Messages();
	
	messages.setEmail(a.getEmail());
	messages.setSender(mailcon.listIterator().next().getHostUserName());
	messages.setStatus("0");
	messages.setPropString(pstring);
	messages.setPropertyId(id);
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
	
	} else {
		System.out.println("Second Alerts List is Empty.");
	}	

	return "redirect:" + "propertylist.html";
	
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


