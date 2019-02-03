package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.dao.FeaturesDAO;
import kejamart.model.Features;

	public class FeaturesDAOImpl extends HibernateDaoSupport implements FeaturesDAO {
		
		@SuppressWarnings("unchecked")
		public Features getFeatureById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Features t where t.id = ?";

			List<Features> pic = (List<Features>) getHibernateTemplate().find(queryString, id);

			if (pic.size() > 1) {
				throw new Exception("More than one Feature with same Id");
			}
			return pic.get(0);
		}
		
		public void addFeature(Features Features) {
			getHibernateTemplate().save(Features);

		}

		public void updateFeatures(Features Features, int id) throws Exception {
			
			Features ft = getFeatureById(id);			
			ft.setFeature(ft.getFeature());
			getHibernateTemplate().update(ft);
		}
		
		@SuppressWarnings("unchecked")
		public List<Features> getFeaturesForId(int id) {
			List<Features> Features = null;
			String query="from Features a where a.id=?";
			Features = getHibernateTemplate().find(query, id);
			return Features;
		}	
		
		@SuppressWarnings("unchecked")
		public List<Features> getFeaturesForPropId(int propertyId) {
			List<Features> Features = null;
			String query="from Features a where a.propertyId=?";
			Features = getHibernateTemplate().find(query, propertyId);
			return Features;
		}		
		
		public void removeFeatures(int id) throws Exception {
			Features rc = getFeatureById(id);
			getHibernateTemplate().delete(rc);
		}		

		public List<Features> getFeatures() {
			return this.getHibernateTemplate().loadAll(Features.class);
		
		}
			  
		public int countFeatures(){
			
			int iSize = this.getHibernateTemplate().loadAll(Features.class).size();
			
			return iSize;
	    }

}