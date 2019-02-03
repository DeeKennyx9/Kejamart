package kejamart.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.dao.CountriesDAO;
import kejamart.model.Countries;

	public class CountriesDAOImpl extends HibernateDaoSupport implements CountriesDAO {
		
		@SuppressWarnings("unchecked")
		public Countries getCountriesById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Countries p where p.id = ?";

			List<Countries> countries = (List<Countries>) getHibernateTemplate().find(queryString, id);

			if (countries.size() > 1) {
				throw new Exception("More than one Countries with same Id");
			}
			return countries.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public List<Countries> getCountryForId(int id) {
			List<Countries> countries = null;
			String query="from Countries a where a.id=? ";
			countries = getHibernateTemplate().find(query, id);
			return countries;
		}	
		
		public void addCountries(Countries countries) {
			getHibernateTemplate().save(countries);
	
		}

		public void deleteCountries(Countries countries){
				getHibernateTemplate().delete(countries);
		}

		public List<Countries> getCountries() {
			return this.getHibernateTemplate().loadAll(Countries.class);
		
		}
		
		public int countCountries(){
			
			int iSize = this.getHibernateTemplate().loadAll(Countries.class).size();
			
			return iSize;
			
	    }

}