package kejamart.controller.alert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kejamart.controller.main.UserSession;
import kejamart.dao.CountiesDAO;
import kejamart.dao.LocationsDAO;
import kejamart.helper.Settings;
import kejamart.model.Locations;

public class RequestLOVController implements Settings{
	
	CountiesDAO countiesDAO;
	
	LocationsDAO locationsDAO;

	UserSession userSession = new UserSession();

	public static Logger logger = Logger.getLogger(RequestLOVController.class);

	@RequestMapping(value = "/requests.html", headers = "Accept=*/*", method=RequestMethod.GET)
	public @ResponseBody List<Locations> loadLocations(HttpServletRequest request) throws IllegalStateException{

	String county = request.getParameter("county");
	String countyName = county;
	int countyId = 0;

	if(countyName != null) {
		
	countyId = Integer.parseInt(countyName);

	System.out.println("County Parameter is NOT empty : " +countyName);
	System.out.println("County To Interger Works : " +countyId);

	List<Locations> locationList = locationsDAO.getLocationsForCountyId(countyId);
	System.out.println("Dependent Location List selected : >|< " +countyId);

	System.out.println("County Param ..." + county);
	System.out.println("County Id ..." + countyId);
	System.out.println("Location List ..." + locationList);

	return locationList;

	} else {
	System.out.println("Exception: County Parameter is empty");
	return null;
	}
	} LocationsDAO getLocationsDAO() {
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

}
