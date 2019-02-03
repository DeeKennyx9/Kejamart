package kejamart.controller.profile;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.MailConfigDAO;
import kejamart.dao.ProfileDAO;
import kejamart.helper.Settings;
import kejamart.helper.StringToMD5;
import kejamart.model.MailConfig;
import kejamart.model.Profile;

@Controller
public class ChangePassword implements Settings{
	
	public ProfileDAO profileDAO;
	public UserSession userSession;
	private List<Profile> profileList = new ArrayList<Profile>();
	private List<Profile> ProfileList = new ArrayList<Profile>();
	MailConfigDAO mailConfigDAO;
	
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }    	
	
	public static Logger logger = Logger.getLogger(ChangePassword.class);
	
    @RequestMapping(value="/changepassword")
	public ModelAndView user(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("changepass");	
		request.getSession().setAttribute("errmg", "");
		return modelAndView;

	}
	
	
	@RequestMapping(value="/changepassword",method=RequestMethod.POST)
	public ModelAndView changePass(HttpServletRequest request, @ModelAttribute(value="profile") Profile profile) throws Exception {

	ModelAndView modelAndView = new ModelAndView("changepass");	

	String passwordz = profile.getPassword().trim();
	String emailz = profile.getEmail();
	boolean checkEmail = profileDAO.checkEmail(profile.getEmail());
	
    //email pattern
    Pattern ep=Pattern.compile("(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+");
    Matcher em=ep.matcher(emailz);
    boolean eb=em.matches();	
	
	//password pattern
	//Pattern pp = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#$%]).{8,20})");
    Pattern pp=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})");
	Matcher pm = pp.matcher(passwordz);
	boolean pb = pm.matches();
	
	//Retrieve email ID to enable update
	
	if (profileDAO.getUserByEmail(emailz) != null) {
	
	int idz = 0;
	profile = profileDAO.getUserByEmail(emailz);
	int idx = profile.getId();
	idz=idx;
	
	System.out.println("ID Value: " +idz+" || " + idz);
	
    if (eb == false) {
    	System.out.println("Email is invalid");
    	//String result = "Email is invalid";
		request.getSession().setAttribute("errmg", "<div id='alert-danger' class='alert alert-danger'>&nbsp;Email is invalid</div><p><br>");
		//modelAndView.addObject("result", result);
		
    } else if (checkEmail == false) {
    	System.out.println("Exception: User not found " + profile.getEmail());
    	//String result = "Exception: User " +profile.getEmail()+ "not found ";
    	request.getSession().setAttribute("errmg", "<div id='alert-danger' class='alert alert-danger'>&nbsp;Exception: User " +profile.getEmail()+ "not found </div><p><br>");
		//modelAndView.addObject("result", result);
   
    } else if (pb == false) {
		System.out.println("Password is invalid. Must be at least 8 characters, include a number and a capital letter");
		//String result = "Password is invalid. Include 1 number and 1 special character";
		request.getSession().setAttribute("errmg", "<div id='alert-danger' class='alert alert-danger'>&nbsp;Password is invalid. Must be at least 8 characters, include a number and a capital letter </div><p><br>");
		//modelAndView.addObject("result", result);
		
	} else {
	
	ProfileList.add(profile);
	String pwordz = "";

	StringToMD5 str = new StringToMD5();
	try {
		pwordz = str.MD5(passwordz);
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	String pw = pwordz.toString();

	//profile.setPassword(pw);	
	profile.setEmail(emailz);
	profile.setId(idz);
	profileDAO.changePassword(profile, idz, pw);
	
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
	helper.setTo(emailz);
	helper.setFrom(mailcon.listIterator().next().getHostUserName());
	helper.setSubject("kejamart Portal: Password Reset");

	StringBuffer sb = new StringBuffer();

	sb.append("<html style='color:#191970 !important;'><body>Dear "+profile.getFirstName()+ ",<p><br>");   
	sb.append("Welcome to <a href='www.kejamart.com'>kejamart</a>, your reliable property listing partner.<br> Your <b>New</b> password is <b>"+passwordz+ "</b>. <br> Enjoy our services.");  
	sb.append("<p><br>Regards <p><br> The "+company+" Team <p><a href='"+website+"'><u>"+website+"</u></a><p><br>");
	sb.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");  
	sb.append("<i>"+company+"</i></h1>"); 
	sb.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
	sb.append("<p><br></body></html>");  

	helper.setText(sb.toString(), true);

	try {
	    sender.send(message);
	    System.out.println("Email succesfully sent to " +emailz); 
	}
	catch (MailException ex) {
	    System.err.println(ex.getMessage());
	}

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<	
	
	System.out.println("Profile " + profile.getEmail() + " has been updated succesfully");
	String result = "Profile " + profile.getEmail() + " updated succesfully";
	request.getSession().setAttribute("errmg", "<div class='alert alert-success'>&nbsp;Reset successful. New Password sent to "+ profile.getEmail() +".</div><p><br>");
	//modelAndView.addObject("result", result);
	
	}
    
	} else {
		
		System.out.println("Exception: User " +profile.getEmail()+ " not found ");
    	String result = "Exception: User " +profile.getEmail()+ " not found ";
    	request.getSession().setAttribute("errmg", "<div class='alert alert-danger'>User " +profile.getEmail()+ " not found </div><p><br>");
		//modelAndView.addObject("result", result);		
    	
	}

	return modelAndView;
 
	}
	
	public MailConfigDAO getMailConfigDAO() {
		return mailConfigDAO;
	}

	public void setMailConfigDAO(MailConfigDAO mailConfigDAO){
		this.mailConfigDAO = mailConfigDAO;
	}	
	
	public ProfileDAO getProfileDAO() {
		return profileDAO;
	}
	
	public void setProfileDAO(ProfileDAO profileDAO){
		this.profileDAO = profileDAO;
	}
	
}
