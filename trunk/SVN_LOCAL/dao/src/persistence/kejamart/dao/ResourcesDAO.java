package kejamart.dao;

import java.util.List;

import kejamart.model.Resources;

public interface ResourcesDAO {

	public void addResource(Resources resources);
	public void updateResources(Resources resources, int id) throws Exception;
	public Resources getResourceById(int id) throws Exception;
	public List<Resources> getResources();
	public int countResources();
	public List<Resources> getResourcesForId(int id);
	public void removeResources(int id) throws Exception;
	public List<Resources> getResourcesForPropId(int propertyId);
	public boolean checkFileName(String fileName);

}