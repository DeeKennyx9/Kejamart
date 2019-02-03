package kejamart.controller.main;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kejamart.controller.main.UserSession;
import kejamart.dao.MailConfigDAO;
import kejamart.helper.Settings;
import kejamart.model.MailConfig;

@EnableWebMvc
@Controller
public class ContactUs implements Settings{

	UserSession userSession = new UserSession();
	
	MailConfigDAO mailConfigDAO;
	
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }  	

	public static Logger logger = Logger.getLogger(ContactUs.class);

@RequestMapping(value="/contact" , method=RequestMethod.GET)
public ModelAndView listAlerts(HttpServletRequest request) throws Exception {

	ModelAndView modelAndView = new ModelAndView("contact");
	request.getSession().setAttribute("errmg", "");
	return modelAndView;

}

@RequestMapping(value="/contactx", method = RequestMethod.GET)
public ModelAndView processForm(HttpServletResponse response, HttpServletRequest request)
        throws IOException, ServletException {

	ModelAndView modelAndView = new ModelAndView("contact");		

	request.getSession().invalidate();
	request.getSession().removeAttribute(SESSION_USER);
	request.getSession().removeAttribute(LOGIN_DETAILS);
	request.getRequestedSessionId();
	userSession.setLoggedIn(false);

	request.getSession().setAttribute("logout", "Logged out");  
	System.out.println("User Logged out");	
	System.out.println("Session removed: " +request.getRequestedSessionId());

	return modelAndView;
	
}	

@RequestMapping(value="/contact",method=RequestMethod.POST)
public ModelAndView markStatusRead(HttpServletRequest request) throws Exception{
	
	ModelAndView modelAndView = new ModelAndView("contact");
	    
    // Initialize variables
	
	String name = request.getParameter("name");
	String emailx = request.getParameter("email");
	String subj = request.getParameter("subject");
	String mess = request.getParameter("message");
	
    if (name.trim() == "") {
    	System.out.println("Name is missing");
    	//String result = "Name is missing";
		request.getSession().setAttribute("errmg", "<div class='alert alert-danger'>&nbsp;Name is missing</div><p><br>");
   
    } else if (subj.trim() == "") {
    	System.out.println("Subject is missing");
		//String result = "Subject is missing";
		request.getSession().setAttribute("errmg", "<div class='alert alert-danger'>&nbsp;Subject is missing</div><p><br>");
		
	} else if (mess.trim() == "") {
    	System.out.println("Message is missing");
		//String result = "Message is missing";
		request.getSession().setAttribute("errmg", "<div class='alert alert-danger'>&nbsp;Message is missing</div><p><br>");
		
	} else {
	
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
	helper.setTo(mailcon.listIterator().next().getHostUserName());
	helper.setFrom(mailcon.listIterator().next().getHostUserName());
	helper.setSubject(subj);

	StringBuffer sb = new StringBuffer();

	sb.append(mess); 

	helper.setText(sb.toString(), true);

	/*try {
	    sender.send(message);
	    System.out.println("Email succesfully sent to " +emailx); 
	}
	catch (MailException ex) {
	    System.err.println(ex.getMessage());
	}*/
	
	try {
	    sender.send(message);
	    System.out.println("Email succesfully sent to " +emailx); 
	}
	catch (MailException ex) {
	    System.err.println("kejamart Mail Error: " +ex.getMessage());
	    System.err.println("kejamart Portal encountered a problem sending your request. Please try again");
	    if (ex.getMessage() != null) {
	    request.getSession().setAttribute("errmg", "<div class='alert alert-danger'>&nbsp;kejamart Portal encountered a problem sending your request. Please try again</div><p><br>");
	    }
	}	

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	
	
	//System.out.println("Email sent succesfully");
	//String result = "Email sent succesfully";
	//request.getSession().setAttribute("errmg", "<div id='alert-success' class='alert alert-success'>&nbsp;Email sent succesfully</div><p><br>");
	
	}	

    return modelAndView;

 }

public MailConfigDAO getMailConfigDAO() {
	return mailConfigDAO;
}

public void setMailConfigDAO(MailConfigDAO mailConfigDAO){
	this.mailConfigDAO = mailConfigDAO;
}

}