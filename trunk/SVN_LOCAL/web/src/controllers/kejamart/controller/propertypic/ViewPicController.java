package kejamart.controller.propertypic;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
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

import kejamart.controller.main.UserSession;
import kejamart.dao.FeaturesDAO;
import kejamart.dao.MailConfigDAO;
import kejamart.dao.PropertyDAO;
import kejamart.dao.PropertyPicDAO;
import kejamart.dao.ResourcesDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Features;
import kejamart.model.MailConfig;
import kejamart.model.Property;
import kejamart.model.PropertyPics;
import kejamart.model.Resources;


@Controller
public class ViewPicController implements Settings {

	PropertyDAO propertyDAO;
	MailConfigDAO mailConfigDAO;
	PropertyPicDAO propertyPicDAO;
	ResourcesDAO resourcesDAO;
	FeaturesDAO featuresDAO;
	UserSession userSession = new UserSession();
	private List<Property> propertyList = new ArrayList<Property>();
	private List<Property> propertyList2 = new ArrayList<Property>();
	
	public static Logger logger = Logger.getLogger(ViewPicController.class);	
	
	@RequestMapping(value="/viewdetails", method=RequestMethod.GET)
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("viewdetails");
		request.getSession().setAttribute("errmg", "");		
	
		try {
			int id = 0;
			int views = 0;
			
			id = Integer.parseInt(request.getParameter("id"));
			views = Integer.parseInt(request.getParameter("views"));
			
			int idValue = 0;
			idValue = id;
			request.getSession().setAttribute(PROPERTY_DETAILS, idValue);
			
			int viewValue = 0;
			viewValue = views;
			request.getSession().setAttribute(VIEW_DETAILS, viewValue);			

			int x = (views+1);
			
			System.out.println("Property Views Button clicked: >|< " +id + " || " +views + " || " +x);
			
			Property property = new Property();
			
			property.setViews(x);
			propertyDAO.propertyViews(property, id, x);

			System.out.println("Property View updated succesfully: "+id+ " || " +views);

			List<Property> props = propertyDAO.getPropertyForId(id);
			List<PropertyPics> proPics = propertyPicDAO.getPropertyPicsForPropId(id);
			List<Resources> recs = resourcesDAO.getResourcesForPropId(id);
			List<Features> feats = featuresDAO.getFeaturesForPropId(id);

			System.out.println("Property ...  || " +id);
			System.out.println("Property List ...  || " +props);
			System.out.println("PropertyPics List ...  || " +proPics);
			System.out.println("Resources List ...  || " +recs);
			System.out.println("features List ...  || " +feats);
			
			if (props.listIterator().next().getBedrooms() != "0") {
				
			request.getSession().setAttribute("bedbath","<i class='fa fa-bed'></i> bedrooms &nbsp; <i class='fa fa-bath'></i> bathrooms");
				
			} 
		
			// Get Related Properties / by location
			
			if (props.listIterator().hasNext()) {
				
			String reloc = props.listIterator().next().getLocation();
			List<Property> related = propertyDAO.getPropertyForLocation(reloc);
			modelAndView.addObject("related", related);
			
		    } else {
			
			      System.out.println("Related Property NOT Found ...");
		    } 
			
			modelAndView.addObject("props", props);
			modelAndView.addObject("proPics", proPics);
			modelAndView.addObject("feats", feats);
			modelAndView.addObject("recs", recs);
			
			return modelAndView;
			
			} catch (NumberFormatException e) {
			System.err.println("ID is not a number. Default Value 1 inserted");
			
		    ModelAndView model = new ModelAndView("detailserror");
		    System.out.println("Property not clicked");
		
		    return model;			
		
			}		

	 }
		
	@RequestMapping(value="/viewdetails", method=RequestMethod.POST)
	public @ResponseBody JsonResponse sendEnquiry(HttpServletRequest request, @ModelAttribute(value="addprofile") Property property, BindingResult result) throws Exception {
		
        // Initialize variables
		JsonResponse res = new JsonResponse();
		
		int idp = request.getSession().getAttribute(PROPERTY_DETAILS).hashCode();
		
		System.out.println("Property ID: >|< " +idp);		
		Property pr = propertyDAO.getPropertyById(idp);
		String propname = pr.getName();		
		List<Property> propx = propertyDAO.getPropertyForId(idp);
		String pxEmail = propx.listIterator().next().getEmail();
		int eq = 0;
		eq = propx.listIterator().next().getEnquiries();
	    String cell = request.getParameter("cell");	
		String emailx = request.getParameter("emailx");	
		String namex = request.getParameter("namex");	


		//mobile phone pattern
		Pattern mp = Pattern.compile("[0-9]{10}");  
		Matcher mob=mp.matcher(cell);
		boolean mobz=mob.matches();		
		
		//email pattern
		Pattern ep=Pattern.compile("(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+");
		Matcher em=ep.matcher(emailx);
		boolean eb=em.matches();		
		
		// validate cell number and email
		
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "namex", "Please insert your name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "cell", "Please insert your phone number.");
		ValidationUtils.rejectIfEmptyOrWhitespace(result, "emailx", "Please insert your email.");
		
		if (eb == false) {
			result.rejectValue("emailx", "Invalid email address");
			System.out.println("Invalid email address");
		}

		if (mobz == false) {
			result.rejectValue("cell", "Invalid phone number");
			System.out.println("Invalid phone number");
		}		
		
		if(!result.hasErrors()){
			
			// update enquiries
			
			if (eq!=0) {
				
			int x = 0;
			x = eq+1;
			System.out.println("Enquire Button clicked: >|< " +idp + " || " +eq + " || " +x);
			property.setEnquiries(x);
			propertyDAO.propertyEnquiries(property, idp, x);
			System.out.println(idp);	
			
			} else {	
				
			int x = 1;
			System.out.println("Enquire Button clicked: >|< " +idp + " || " +x);
			property.setEnquiries(x);
			propertyDAO.propertyEnquiries(property, idp, x);
			System.out.println("Property Enquiries updated succesfully: "+idp);	
			
			}				
			
		  propertyList2.add(property);
		  res.setStatus("SUCCESS");
		  res.setResult(propertyList);
			
		// Send Mail to User
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
		helper.setTo(pxEmail);
		helper.setFrom(mailcon.listIterator().next().getHostUserName());
		helper.setSubject("Kejamart: Property Enquiry - " +propname);

		StringBuffer sb = new StringBuffer();

		sb.append("<html style='color:#191970 !important;'><body>Dear "+pxEmail+ ",<p><br>");   
		sb.append("Welcome to <a href='www.kejamart.com'>kejamart</a>, your reliable property listing partner.<br> The following enquiry has been made from our portal: <p><br>");
		sb.append("Property: <b>"+propname+ "</b>. <br>");  
		sb.append("Name: <b>"+namex+ "</b>. <br>");
		sb.append("Phone number: <b>"+cell+ "</b>. <br>");
		sb.append("Email: <b>"+emailx+ "</b>. <p><br> Enjoy our services. <p><br>");  
		sb.append("Regards <p><br> The "+company+" Team <p><a href='"+website+"'><u>"+website+"</u></a><p>");
		sb.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");  
		sb.append("<i>"+company+"</i></h1>");  
		sb.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
		sb.append("<p><br></body></html>");  

		helper.setText(sb.toString(), true);

		try {
		    sender.send(message);
		    System.out.println("Email succesfully sent"); 
		}
		catch (MailException ex) {
		    System.err.println("kejamart Mail Error: " +ex.getMessage());
		    System.err.println("kejamart Portal encountered a problem sending your request. Please try again");
		    if (ex.getMessage() != null) {
		    	result.rejectValue("cell", "kejamart Portal encountered a problem sending your request. Please try again");
			    res.setStatus("FAIL");
			    res.setResult(result.getAllErrors());
		    	return res;
		    }
		}		
		
		// Contact number update
		
	    propertyDAO.updateCell(property, idp);	  
		System.out.println("Phone number Updated Succesfully");
		
	    propertyDAO.updateEmailx(property, idp);	  
		System.out.println("Emailx Updated Succesfully");	
		
	    propertyDAO.updateNamex(property, idp);	  
		System.out.println("Namex Updated Succesfully");		
		
		String success = "A message has been sent to the Agent. You will be contacted shortly.";		
		System.out.println(success);
		        
		} else {
		
	    System.out.println("A validation error has occurred. Message has not been sent.");
	    System.out.println("Please insert your phone number.");
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
	
	public ResourcesDAO getResourcesDAO() {
		return resourcesDAO;
	}
	
	public void setResourcesDAO(ResourcesDAO resourcesDAO){
		this.resourcesDAO = resourcesDAO;
	}	
	
	public FeaturesDAO getFeaturesDAO() {
		return featuresDAO;
	}
	
	public void setFeaturesDAO(FeaturesDAO featuresDAO){
		this.featuresDAO = featuresDAO;
	}	
	
}
