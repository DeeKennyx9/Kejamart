package kejamart.dao;

import java.util.List;

import kejamart.model.Adverts;

public interface AdvertDAO {

	public void addAdverts(Adverts adverts) throws Exception;
	public Adverts getAdvertsById(int id) throws Exception;
	public Adverts getAdvertsByName(String name) throws Exception;
	public void deleteAdverts(Adverts adverts) throws Exception;
	public List<Adverts> getAdverts();
	public List<Adverts> getAdvertsForName(String name);
	public int countAdverts();
	public List<Adverts> getAdvertsForId(int id);
	public List<Adverts> getAdvertsForPropId(int propertyId);
	public List<Adverts> getAdvertsForUser(String email);
	public List<Adverts> getAdvertsForStatus(String status);
	public List<Adverts> getAdvertsForLocation(String pagelocation);

}