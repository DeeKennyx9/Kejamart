package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.model.Adverts;

	public class AdvertDAOImpl extends HibernateDaoSupport implements AdvertDAO {
		
		@SuppressWarnings("unchecked")
		public Adverts getAdvertsById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Adverts p where p.id = ?";

			List<Adverts> adverts = (List<Adverts>) getHibernateTemplate().find(queryString, id);

			if (adverts.size() > 1) {
				throw new Exception("More than one Adverts with same Id");
			}
			return adverts.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public Adverts getAdvertsByName(String name) throws Exception {
			if (name == null) {
				throw new Exception("name parameter cannot be null!");
			}

			String queryString = "from Adverts p where p.name = ?";

			List<Adverts> adverts = (List<Adverts>) getHibernateTemplate().find(queryString, name);

			if (adverts.size() > 1) {
				throw new Exception("More than one Adverts with same smtpHost");
			}
			return adverts.get(0);
		}
		
		public void addAdverts(Adverts adverts) {
			getHibernateTemplate().save(adverts);

		}
		
		@SuppressWarnings("unchecked")
		public List<Adverts> getAdvertsForName(String name) {
			List<Adverts> adverts = null;
			String query="from Adverts a where a.name=? ";
			adverts = getHibernateTemplate().find(query, name);
			return adverts;
		}	
		
		@SuppressWarnings("unchecked")
		public List<Adverts> getAdvertsForId(int id) {
			List<Adverts> adverts = null;
			String query="from Adverts a where a.id=? ";
			adverts = getHibernateTemplate().find(query, id);
			return adverts;
		}
		
		@SuppressWarnings("unchecked")
		public List<Adverts> getAdvertsForUser(String email) {
			List<Adverts> adverts = null;
			String query="from Adverts a where a.email=? ";
			adverts = getHibernateTemplate().find(query,email);
			return adverts;
		}
		
		@SuppressWarnings("unchecked")
		public List<Adverts> getAdvertsForStatus(String status) {
			List<Adverts> adverts = null;
			String query="from Adverts a where a.status=? ";
			adverts = getHibernateTemplate().find(query,status);
			return adverts;
		}	
		
		@SuppressWarnings("unchecked")
		public List<Adverts> getAdvertsForLocation(String pagelocation) {
			List<Adverts> adverts = null;
			String query="from Adverts a where a.pagelocation=? ";
			adverts = getHibernateTemplate().find(query, pagelocation);
			return adverts;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Adverts> getAdvertsForPropId(int propertyId) {
			List<Adverts> adverts = null;
			String query="from Adverts a where a.propertyId=?";
			adverts = getHibernateTemplate().find(query, propertyId);
			return adverts;
		}		

		public void deleteAdverts(Adverts adverts) throws Exception{
			Adverts AdvertsToDelete = getAdvertsById(adverts.getId());
				getHibernateTemplate().delete(AdvertsToDelete);
		}

		public List<Adverts> getAdverts() {
			return this.getHibernateTemplate().loadAll(Adverts.class);
		
		}
			  
		public int countAdverts(){
			return this.getHibernateTemplate().loadAll(Adverts.class).size();
	    }


}