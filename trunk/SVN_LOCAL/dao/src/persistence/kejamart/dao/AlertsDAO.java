package kejamart.dao;

import java.util.List;

import kejamart.model.Adverts;
import kejamart.model.Alerts;
import kejamart.model.Messages;
import kejamart.model.Property;

public interface AlertsDAO {

	public Alerts getAlertsById(int id) throws Exception;
	public Alerts getAlertsByEmail(String email) throws Exception;
	public List<Alerts> getAlertsForUser(String email);
	public void removeAlert(int id) throws Exception;
	public List<Alerts> getAlerts();
	public int countAlerts();
	public boolean checkCountry(String country);
	public boolean checkCounty(String county);
	public boolean checkLocation(String location);
	public boolean checkPropType(String propertyType);
	public boolean checkCategory(String category);
	public boolean checkBedrooms(int bedrooms);
	public List<Alerts> getAlertsForCountry(String country);
	public List<Alerts> getAlertsForCounty(String county);
	public List<Alerts> getAlertsForLocation(String location);
	public List<Alerts> getAlertsForPropType(String propertyType);
	public List<Alerts> getAlertsForCategory(String category);
	public List<Alerts> getAlertList(int propIndex);
	public boolean checkUser(String email);
	public Alerts getAlertsByString(String propString) throws Exception;
	public List<Alerts> getAlertsForString(String propString);
	public void markSentMessages(Alerts alerts, int id) throws Exception;
	public void addAlerts(Alerts alerts);
}