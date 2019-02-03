package kejamart.controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.helper.Settings;

@Controller
public class LogoutController implements Settings {	
		
	UserSession userSession = new UserSession();
	
@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView processForm(HttpServletResponse response, HttpServletRequest request)
	        throws IOException, ServletException {

		ModelAndView modelAndView = new ModelAndView("logout");	
		
		HttpSession session = request.getSession(false);
		session.removeAttribute(" userSession");
		session.invalidate();
	    
		userSession.setLoggedIn(false);
    
		request.getSession().setAttribute("logout", "Logged out");  
		System.out.println("User Logged out");	

		return modelAndView;
	
    }	
}

