package kejamart.dao;

import java.util.List;

import kejamart.model.Property;

public interface PropertyDAO {
	
	public Property getPropertyById(int id) throws Exception;
	public Property getPropertyName(String name) throws Exception;
	public Property getPropertyByEmail(String email) throws Exception;
	public List<Property> getPropertyForUser(String email);
	public void addProperty(Property property);
	public void removeProperty(int id) throws Exception;
	public List<Property> getAllProperty();
	public int countProperty();
	public void updateProperty(Property property, int id) throws Exception;
	public boolean checkId(int id);
	public List<Property> getPropertyForId(int id);
	public List<Property> getPropertyReff(String propreff);
	public List<Property> getPropertyForStatus(int status);
	public boolean checkCountry(String country);
	public boolean checkCounty(String county);
	public boolean checkLocation(String location);
	public boolean checkPropType(String propertyType);
	public boolean checkCategory(String category);
	public boolean checkBedrooms(String bedrooms);
	public boolean checkUnit(String unit);
	public List<Property> getPropertyForString(String propString);
	public List<Property> getPublishList(int status);
	public void publishProperty(Property property, int id) throws Exception;
	public void updateMainPic(Property property, int id) throws Exception;
	public void propertyViews(Property property, int id, int views) throws Exception;
	public List<Property> getPropertyForPath(String path);
	public List<Property> getPropertybyName(String name);
	public void propertyEnquiries(Property property, int id, int enquiries) throws Exception;
	public void updateCell(Property property, int id) throws Exception;
	public List<Property> getDefaultProperty();
	public List<Property> getPropertyForStatusLimit(int status);
	public List<Property> getPropertyForStringLimit(String propString);
	public void updateEmailx(Property property, int id) throws Exception;
	public List<Property> getPropertyForLocation(String location);
	public void updateLogo(Property property, int id) throws Exception;
	public void updateNamex(Property property, int id) throws Exception;
	public List<Property> getAllProperty(String country, String county, String location, String propertyType, String category, String bedrooms, String rangeValue);

}