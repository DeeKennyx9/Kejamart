package kejamart.dao;

import java.util.List;

import kejamart.model.Counties;

public interface CountiesDAO {
	public Counties getCountiesById(int id) throws Exception;
	public Counties getCountiesByCounty(String county) throws Exception;
	public void addCounties(Counties counties);
	public void deleteCounties(Counties counties);
	public List<Counties> getCounties();
	public int countCounties();
	public List<Counties> getCountyForId(int id);
	public List<Counties> getCountyForCounty(String county);
	public List<Counties> getCountyForCountryId(int countryId);

}