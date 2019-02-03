package kejamart.controller.alert;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kejamart.controller.main.UserSession;
import kejamart.dao.AlertsDAO;
import kejamart.helper.Settings;
import kejamart.model.Alerts;

@EnableWebMvc
@Controller
public class DeleteRequestController implements Settings{

	AlertsDAO alertsDAO;
	
	UserSession userSession = new UserSession();
	
	public static Logger logger = Logger.getLogger(DeleteRequestController.class);		
	
	@RequestMapping(value="/deleterequest", method=RequestMethod.GET)
    public String showForm(Model model, HttpServletRequest request) {
            Alerts alerts = new Alerts();
            ((HashMap<String, Object>) model).put("alerts", alerts);
            model.asMap().clear();
            return "redirect:" + "requests.html";
    }
	
	@RequestMapping(value="/deleterequest", method=RequestMethod.POST)
    public String deleteAlert(Model model, HttpServletRequest request) throws Exception {
		
		int id = 0;
		
		id = Integer.parseInt(request.getParameter("id"));

    	System.out.println("Delete button clicked: ");

    	alertsDAO.removeAlert(id);

    	System.out.println("Request Deleted. Refference:  >|< " +id);
    	
        return "redirect:" + "requests.html";
        
    }	


public AlertsDAO getAlertsDAO() {
	return alertsDAO;
}

public void setAlertsDAO(AlertsDAO alertsDAO){
	this.alertsDAO = alertsDAO;
}

}