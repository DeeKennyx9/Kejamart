package kejamart.controller.messages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kejamart.controller.main.UserSession;
import kejamart.dao.MessagesDAO;
import kejamart.helper.Settings;
import kejamart.model.Messages;

@EnableWebMvc
@Controller
public class MessagesController implements Settings{

	MessagesDAO messagesDAO;

	UserSession userSession = new UserSession();

	public static Logger logger = Logger.getLogger(MessagesController.class);

@RequestMapping(value="/notifications" , method=RequestMethod.GET)
public ModelAndView listAlerts(HttpServletRequest request) throws Exception {

	ModelAndView modelAndView = new ModelAndView("nots");

	if (request.getSession().getAttribute(SESSION_USER) != null){

	String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
	
	List<Messages> messages = messagesDAO.getMessagesForEmail(emailUser);
	
	int totals = messages.size();
	
	System.out.println("Total messages for User: " +totals);
	System.out.println("Message List: " +messages);

	request.getSession().setAttribute("mtotals", totals);
	
	modelAndView.addObject("messages", messages);

	System.out.println("User authorized to access request page");
	return modelAndView;

	} else {

	ModelAndView model = new ModelAndView("restricted");
	System.err.println("Visitor NOT authorized to access request page");

	return model;

	}

}

@RequestMapping(value="/notification",method=RequestMethod.POST)
public String markStatusRead(HttpServletRequest request, @ModelAttribute(value="messages") Messages messages) throws Exception{
	    
	    String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
	    int id = 0;
        id = Integer.parseInt(request.getParameter("id"));
        
        boolean checkEmail = messagesDAO.checkUserReadStatus(emailUser);
        Date date = new Date();
        SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dat = new java.sql.Date(date.getTime()); 
		
    	if (!checkEmail) {
    	messages.setStatus("1");
    	} else {
    	System.err.println("Email status marked as Read.");
    	}
    	messages.setDateRead(dat);
    	
    	messagesDAO.markReadMessages(messages, id);

        System.out.println("Notification marked as Read");

        return "redirect:" + "notifications.html";

 }

@RequestMapping(value="/notifications",method=RequestMethod.POST)
public String deleteMessage(Model model, HttpServletRequest request) throws Exception{

	    String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
	    int id = 0;
        id = Integer.parseInt(request.getParameter("id"));
    	
    	messagesDAO.removeMessages(id);

        System.out.println("Notification Deleted");

        return "redirect:" + "notifications.html";

 }


public MessagesDAO getMessagesDAO() {
	return messagesDAO;
}

public void setMessagesDAO(MessagesDAO messagesDAO){
	this.messagesDAO = messagesDAO;
}

}