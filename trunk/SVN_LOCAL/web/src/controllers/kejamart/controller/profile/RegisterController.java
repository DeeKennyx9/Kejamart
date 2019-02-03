package kejamart.controller.profile;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kejamart.controller.main.UserSession;
import kejamart.dao.MailConfigDAO;
import kejamart.dao.ProfileDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.StringToMD5;
import kejamart.model.MailConfig;
import kejamart.model.Profile;

@Controller
public class RegisterController{

	ProfileDAO profileDAO;
	
	UserSession userSession = new UserSession();
	
	MailConfigDAO mailConfigDAO;
	
	private List<Profile> profileList = new ArrayList<Profile>(); 
	
	public static Logger logger = Logger.getLogger(RegisterController.class);	

    private List<Profile> ProfileList = new ArrayList<Profile>();
    
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }    

@RequestMapping(value="/register",method=RequestMethod.GET)
 public String showForm(HttpServletRequest request){
  return "register";
}

	 
@RequestMapping(value="/register",method=RequestMethod.POST)
public @ResponseBody JsonResponse addProfile(@ModelAttribute(value="register") final Profile profile, BindingResult result) throws Exception {

JsonResponse res = new JsonResponse();

final String emailz = profile.getEmail();
final String passwordz = profile.getPassword();
boolean checkEmail = profileDAO.checkEmail(profile.getEmail());
boolean checkMobile = profileDAO.checkMobile(profile.getMobile());
final String mobilez = profile.getMobile();

//mobile phone pattern
Pattern mp = Pattern.compile("[0-9]{10}");  
Matcher mob=mp.matcher(mobilez);
boolean mobz=mob.matches();

//email pattern
Pattern ep=Pattern.compile("(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+");
Matcher em=ep.matcher(emailz);
boolean eb=em.matches();

//password pattern
//Pattern pp=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[#$%]).{8,20})");
Pattern pp=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})");
Matcher pm=pp.matcher(passwordz);
boolean pb=pm.matches();


ValidationUtils.rejectIfEmptyOrWhitespace(result, "firstName", "First Name can not be empty.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "lastName", "Last Name can not be empty.");
ValidationUtils.rejectIfEmptyOrWhitespace(result, "role", "Please select a Category.");

if (checkEmail == true) {
	result.rejectValue("email", "Email already exists");
	System.out.println("Email already exists");
}

if (eb == false) {
	result.rejectValue("email", "Email is invalid");
	System.out.println("Email is invalid");
}

if (checkMobile == true) {
	result.rejectValue("mobile", "Mobile number already exists");
	System.out.println("Mobile number already exists");
}

if (mobz == false) {
	result.rejectValue("mobile", "Mobile number is invalid");
	System.out.println("Mobile is invalid");
}

if (pb == false) {
	result.rejectValue("password", "Password is invalid. Must be at least 8 characters, include a number and a capital letter");
	System.out.println("Password is invalid. Must be at least 8 characters, include a number and a capital letter");
}

//Date dateobj = new Date();
Timestamp tm = new Timestamp(System.currentTimeMillis());

Date date = new Date();
SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
java.sql.Date dat = new java.sql.Date(date.getTime());

if(!result.hasErrors()){

ProfileList.add(profile);
res.setStatus("SUCCESS");
res.setResult(profileList);
System.out.println("Profile " + profile.getEmail() + " has been created succesfully");

// Encrypt password
String pwordz = "";

StringToMD5 str = new StringToMD5();
try {
	pwordz = str.MD5(profile.getPassword());
} catch (NoSuchAlgorithmException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

String pw = pwordz.toString();

profile.setPassword(pw);
profile.setStatus("1");
profile.setEnabled(1);
profile.setCreatedDate(dat);
profileDAO.addProfile(profile);

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
helper.setSubject("kejamart Portal Registration");

StringBuffer sb = new StringBuffer();

sb.append("<html style='color:#191970 !important;'><body>Dear "+profile.getFirstName()+ ",<p><br>");   
sb.append("Welcome to <a href='"+company+"'>"+company+"</a>, your reliable property listing partner.<br> Your password is <b>"+passwordz+ "</b>. <br> Enjoy our services.");  
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

//Send Mail to Admin
props.put(mailcon.listIterator().next().getTransportProtocol(), mailcon.listIterator().next().getProtocol());
props.put(mailcon.listIterator().next().getSmtpStartTls(), mailcon.listIterator().next().getStatus());

sender.setHost(mailcon.listIterator().next().getSmtpHost());
sender.setPort(mailcon.listIterator().next().getSmtpPort());
sender.setUsername(mailcon.listIterator().next().getHostUserName());
sender.setPassword(mailcon.listIterator().next().getHostPassword());
sender.setJavaMailProperties(props);

//use the true flag to indicate you need a multipart message

helper.setTo("kenny.oduor@yahoo.com");
helper.setFrom(mailcon.listIterator().next().getHostUserName());
helper.setSubject("kejamart Portal Registration");

sb.append("<html style='color:#191970 !important;'><body>Dear "+profile.getFirstName()+ ",<p><br>");   
sb.append("Welcome to <a href='www.kejamart.com'>kejamart</a>, your reliable property listing partner.<br> Your password is <b>"+passwordz+ "</b>. <br> Enjoy our services.");  
sb.append("<p><br>Regards <p><br> The "+company+" Team <p><a href='"+website+"'><u>"+website+"</u></a><p><br>");
sb.append("<h1 style='color:#1E90FF; text-shadow: 2px 2px #DCDCDC; font-family: Avant Garde,Avantgarde,Century Gothic,CenturyGothic,AppleGothic,sans-serif !important;'>");  
sb.append("<i>"+company+"</i></h1>");  
sb.append("<h3 style='color: #7aa329 !important; font-family: Helvetica Neue,Helvetica,Arial,sans-serif !important;'>Your Property Listing Partner.</h3>");
sb.append("<p><br></body></html>");  

helper.setText(sb.toString(), true);

try {
 sender.send(message);
 System.out.println("Email succesfully sent to Admin"); 
}
catch (MailException ex) {
 System.err.println(ex.getMessage());
}

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

} else { 

System.out.println("A validation error has occurred. Profile has not been added.");
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

public ProfileDAO getProfileDAO() {
	return profileDAO;
}

public void setProfileDAO(ProfileDAO profileDAO){
	this.profileDAO = profileDAO;
}

}