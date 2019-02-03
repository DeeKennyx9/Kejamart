package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.dao.CountiesDAO;
import kejamart.model.Counties;
import kejamart.model.Locations;
import kejamart.model.Profile;
import kejamart.model.Property;

	public class CountiesDAOImpl extends HibernateDaoSupport implements CountiesDAO {
		
		@SuppressWarnings("unchecked")
		public Counties getCountiesById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Counties p where p.id = ?";

			List<Counties> counties = (List<Counties>) getHibernateTemplate().find(queryString, id);

			if (counties.size() > 1) {
				throw new Exception("More than one Counties with same Id");
			}
			return counties.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public Counties getCountiesByCounty(String county) throws Exception {
			if (county== null) {
				throw new Exception("county parameter cannot be null!");
			}

			String queryString = "from Counties p where p.county = ?";

			List<Counties> counties = (List<Counties>) getHibernateTemplate().find(queryString, county);

			return counties.get(0);
		}		
		
		@SuppressWarnings("unchecked")
		public List<Counties> getCountyForId(int id) {
			List<Counties> counties = null;
			String query="from Counties a where a.id=? ";
			counties = getHibernateTemplate().find(query, id);
			return counties;
		}	
		
		@SuppressWarnings("unchecked")
		public List<Counties> getCountyForCountryId(int countryId) {
			List<Counties> counties = null;
			String query="from Counties a where a.countryId=? ";
			counties = getHibernateTemplate().find(query, countryId);
			return counties;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Counties> getCountyForCounty(String county) {
			List<Counties> counties = null;
			String query="from Counties a where a.county=? ";
			counties = getHibernateTemplate().find(query, county);
			return counties;
		}		
		

		public void addCounties(Counties counties) {
			getHibernateTemplate().save(counties);
	
		}

		public void deleteCounties(Counties counties){
				getHibernateTemplate().delete(counties);
		}

		public List<Counties> getCounties() {
			return this.getHibernateTemplate().loadAll(Counties.class);
		
		}
		
		public int countCounties(){
			
			int iSize = this.getHibernateTemplate().loadAll(Counties.class).size();
			
			return iSize;
			
	    }


}