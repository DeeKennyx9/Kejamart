package kejamart.dao;

import java.util.List;

import kejamart.model.Countries;

public interface CountriesDAO {

	public Countries getCountriesById(int id) throws Exception;
	public void addCountries(Countries countries);
	public void deleteCountries(Countries countries);
	public List<Countries> getCountries();
	public int countCountries();
	public List<Countries> getCountryForId(int id);

}