package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.model.Locations;

	public class LocationsDAOImpl extends HibernateDaoSupport implements LocationsDAO {
		
		@SuppressWarnings("unchecked")
		public Locations getLocationsById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Locations p where p.id = ?";

			List<Locations> locations = (List<Locations>) getHibernateTemplate().find(queryString, id);

			if (locations.size() > 1) {
				throw new Exception("More than one Locations with same Id");
			}
			return locations.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public Locations getLocationsByCounty(String county) throws Exception {
			if (county== null) {
				throw new Exception("location parameter cannot be null!");
			}

			String queryString = "from Locations p where p.county = ?";

			List<Locations> locations = (List<Locations>) getHibernateTemplate().find(queryString, county);

			return locations.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public List<Locations> getLocationsForCounty(String county) {
			List<Locations> locations = null;
			String query="from Locations a where a.county=?";
			locations = getHibernateTemplate().find(query, county);
			return locations;
		}	
		
		@SuppressWarnings("unchecked")
		public List<Locations> getLocationsForCountyId(int countyId) {
			List<Locations> locations = null;
			String query="from Locations a where a.countyId=?";
			locations = getHibernateTemplate().find(query, countyId);
			return locations;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Locations> getLocationsForId(int id) {
			List<Locations> locations = null;
			String query="from Locations a where a.id=?";
			locations = getHibernateTemplate().find(query, id);
			return locations;
		}			
		
		public void addLocations(Locations locations) {
			getHibernateTemplate().save(locations);
	
		}

		public void deleteLocations(Locations locations) throws Exception{
			Locations locationsToDelete = getLocationsById(locations.getId());
				getHibernateTemplate().delete(locationsToDelete);
		}

		public List<Locations> getLocations() {
			return this.getHibernateTemplate().loadAll(Locations.class);
		
		}
			  
		public int countLocations(){
			
			int iSize = this.getHibernateTemplate().loadAll(Locations.class).size();
			
			return iSize;
			
	    }
		
		public void deleteLocation(Locations locations) throws Exception {
			
			Locations locationsToDelete = getLocationsById(locations.getId());
			
			getHibernateTemplate().delete(locationsToDelete);
		}


}