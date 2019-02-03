package kejamart.dao;

import java.util.List;

import kejamart.model.Locations;

public interface LocationsDAO {

	public void addLocations(Locations locations);
	public Locations getLocationsById(int id) throws Exception;
	public Locations getLocationsByCounty(String county) throws Exception;
	public void deleteLocations(Locations locations) throws Exception;
	public List<Locations> getLocations();
	public int countLocations();
	public void deleteLocation(Locations locations) throws Exception;
	public List<Locations> getLocationsForCounty(String county);
	public List<Locations> getLocationsForCountyId(int countyId);
	public List<Locations> getLocationsForId(int id);

}