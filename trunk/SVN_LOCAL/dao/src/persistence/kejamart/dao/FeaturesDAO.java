package kejamart.dao;

import java.util.List;

import kejamart.model.Features;

public interface FeaturesDAO {

	public void addFeature(Features Features);
	public void updateFeatures(Features Features, int id) throws Exception;
	public Features getFeatureById(int id) throws Exception;
	public List<Features> getFeatures();
	public int countFeatures();
	public List<Features> getFeaturesForId(int id);
	public void removeFeatures(int id) throws Exception;
	public List<Features> getFeaturesForPropId(int propertyId);

}