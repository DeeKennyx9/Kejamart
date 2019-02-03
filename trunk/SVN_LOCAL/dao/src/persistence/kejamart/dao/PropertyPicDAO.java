package kejamart.dao;

import java.util.List;
import kejamart.model.PropertyPics;

public interface PropertyPicDAO {

	public void addPropertyPic(PropertyPics propertyPics);
	public void updatePic(PropertyPics pic, int id) throws Exception;
	public PropertyPics getPicById(int id) throws Exception;
	public void deletePic(PropertyPics pics) throws Exception;
	public List<PropertyPics> getPics();
	public int countPics();
	public List<PropertyPics> getPropertyPicsForId(int id);
	public List<PropertyPics> getPublishList(String status);
	public void removePic(int id) throws Exception;
	public List<PropertyPics> getPropertyPicsForPropId(int propertyId);
	public boolean checkFileName(String fileName);

}