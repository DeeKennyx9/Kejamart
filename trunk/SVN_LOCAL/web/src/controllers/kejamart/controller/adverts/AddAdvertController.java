package kejamart.controller.adverts;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.AdvertDAO;
import kejamart.dao.MailConfigDAO;
import kejamart.dao.PropertyDAO;
import kejamart.helper.Settings;
import kejamart.model.Adverts;
import kejamart.model.MailConfig;

@Controller
public class AddAdvertController implements Settings {

	AdvertDAO advertDAO;
	UserSession userSession = new UserSession();
	MailConfigDAO mailConfigDAO;
	PropertyDAO propertyDAO;
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    } 
	
	public static Logger logger = Logger.getLogger(AddAdvertController.class);	
	
	@RequestMapping(value="/adverts")
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("adv");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		int idz = Integer.parseInt(request.getParameter("id"));
		String pname = request.getParameter("name");
			
		int idValue = 0;
		idValue = idz;
		request.getSession().setAttribute(PROPERTY_DETAILS, idValue);
		
		String str = "";
		str = pname;
		request.getSession().setAttribute(PROPERTY_NAME, str);	
		
		System.out.println("ID " +idValue);
		System.out.println("Name" +str);

		List<Adverts> alladverts = advertDAO.getAdverts();
		List<Adverts> adverts = advertDAO.getAdvertsForId(idz);
		List<Adverts> advertsByProperty = advertDAO.getAdvertsForPropId(idz);
		
		if (advertDAO.getAdvertsForPropId(idz).listIterator().hasNext()) {
		
		String picName = advertDAO.getAdvertsForPropId(idz).listIterator().next().getFileName();
		
		} else {
			
			System.out.println("No Images Found");
		} 
		
		modelAndView.addObject("advertsByProperty", advertsByProperty);

		System.out.println("Property ..." + " || " +idz);
		
		modelAndView.addObject("alladverts", alladverts);
    	modelAndView.addObject("adverts", adverts);
		System.out.println("User authorized to access add adverts page");		
	    return modelAndView; 
		
		} else {
			
		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access adverts page");
		request.getSession().setAttribute("errmg", "");
		
		return model;
			
		}

	}
		 
	@RequestMapping(value="/adverts", method=RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(value="adverts") Adverts adverts,
			@RequestParam CommonsMultipartFile file) throws Exception {
          
		AtomicInteger idp = new AtomicInteger();
		
		System.out.println("Add Button clicked");
		idp.set(request.getSession().getAttribute(PROPERTY_DETAILS).hashCode());
		int ids = idp.get();
		AtomicInteger profValue = new AtomicInteger(0);
		profValue.set(request.getSession().getAttribute(LOGIN_DETAILS).hashCode());
		int profs = profValue.get();
		
		String pp = request.getSession().getAttribute(PROPERTY_NAME).toString();
		String pu = request.getSession().getAttribute(PROPERTY_UNIT).toString();
		String prp = pp+pu;
		
		Date date = new Date();
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dat = new java.sql.Date(date.getTime());
		String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
		String filename = file.getOriginalFilename();          
		List<Adverts> pc = advertDAO.getAdvertsForPropId(ids);
		int totPics = 0;
		totPics = pc.size();
		String propertyName = prp;	   
		       
                String imgPath =  "/KejaniAdverts";
                String separator = "/";   
         	    
         	   File dir = new File(imgPath+separator+prp+separator);
				if (!dir.exists()) {
					dir.mkdirs();					
					System.out.println("Path created: " +imgPath+separator+prp+separator);					
				} else {					
					System.out.println("Path exists: " +imgPath+separator+prp+separator);
				}
         	    
         	   try{  
         	        byte barr[] = file.getBytes();  
         	          
         	        BufferedOutputStream bout = new BufferedOutputStream(  
         	                 new FileOutputStream(imgPath+separator+prp+separator+filename)); 
         	        if(barr==null){
         	        System.err.println("File cannot be Empty");
         	        } else
           	        bout.write(barr); 
         	        System.out.println("Picture Saved ... " +filename);
         	        
         	        bout.flush();  
         	        bout.close();  
         	          
         	        } catch(Exception e) {
         	        
         	        System.err.println(e);
         	        
         	        }  
                         
         	    if  (filename != null) {
         		  
                adverts.setFileName(filename);
                adverts.setProfileId(profs);
            	adverts.setPropertyId(ids);
            	adverts.setName(prp);
            	adverts.setEmail(emailUser);	
            	adverts.setPath(imgPath+separator+prp+separator+filename);
            	adverts.setCreatedDate(dat); 
            	adverts.setStatus("0");
            	advertDAO.addAdverts(adverts);  
            	
	            } else {
   		
 		        System.err.println("Null value not permitted for adverts ... " +filename);
 		   
                }              	
            	
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

            	//use the true flag to indicate you need a multipart message
            	MimeMessage message = sender.createMimeMessage();

            	MimeMessageHelper helper = new MimeMessageHelper(message);
            	helper.setTo(adverts.getEmail());
            	helper.setFrom(mailcon.listIterator().next().getHostUserName());
            	helper.setSubject("Kejani: Advert Created");

            	StringBuffer sb = new StringBuffer();

                sb.append("<html><body>Dear <a style='color: style='color: #191970;'>" +adverts.getEmail()+ "</a>,");
                sb.append("<p><br> Your Advert has been created. Below are details of your advert.<p><br>");
                sb.append("<table style='border: 1px solid #483D8B; width: 650px;'><tr style='border: 1px solid #191970;'>");
                sb.append("<th style='color: #191970;'>Start Date</th><th style='color: #191970;'>Expiration Date</th><th style='color: #191970;'>Days</th></tr>");
                sb.append("<tr style='border: 1px solid #483D8B'><th style='color: #191970;'>"+ adverts.getStartDate()+"</th><th style='color: #191970;'>"+ adverts.getEndDate());
                sb.append("</th><th style='color: #191970;'>"+ adverts.getDays()+"</tr></table><p><br>");
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
            	    System.out.println("Email succesfully sent to " +adverts.getEmail()); 
            	}
            	catch (MailException ex) {
            	    System.err.println(ex.getMessage());
            	}            	
  
        return "adv";
    }
	
	public AdvertDAO getAdvertDAO() {
		return advertDAO;
	}
	
	public void setAdvertDAO(AdvertDAO advertDAO){
		this.advertDAO = advertDAO;
	}
	
	public MailConfigDAO getMailConfigDAO() {
		return mailConfigDAO;
	}

	public void setMailConfigDAO(MailConfigDAO mailConfigDAO){
		this.mailConfigDAO = mailConfigDAO;
	}
	
	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
}

