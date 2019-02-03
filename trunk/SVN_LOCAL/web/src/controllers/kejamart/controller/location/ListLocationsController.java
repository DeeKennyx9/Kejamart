package kejamart.controller.location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kejamart.controller.main.UserSession;
import kejamart.dao.LocationsDAO;
import kejamart.dao.ProfileDAO;
import kejamart.helper.JsonResponse;
import kejamart.helper.Settings;
import kejamart.model.Locations;

@Controller
public class ListLocationsController implements Settings{
	
	public LocationsDAO locationsDAO;
	public ProfileDAO profileDAO;
	public UserSession userSession;
	private List<Locations> locationsList = new ArrayList<Locations>();
		
	public static Logger logger = Logger.getLogger(ListLocationsController.class);
	
    @RequestMapping(value="/locations.html")
	public @ResponseBody JsonResponse updateProfile(HttpServletRequest request) throws Exception {

	JsonResponse res = new JsonResponse();

	List<Locations> locations = locationsDAO.getLocations();
	int locs = 0;
	locs = locationsDAO.getLocations().size();
	res.setResult(locationsList);
	
	System.out.println("Locations :" + locations);
	System.out.println("Total : " + locs);

	return res;
 
	}
	

	public LocationsDAO getLocationsDAO() {
		return locationsDAO;
	}
	
	public void setLocationsDAO(LocationsDAO locationsDAO){
		this.locationsDAO = locationsDAO;
	}
	
}
