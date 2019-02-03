package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.dao.RangeAmountDAO;
import kejamart.model.RangeAmount;

	public class RangeAmountDAOImpl extends HibernateDaoSupport implements RangeAmountDAO {
		
		@SuppressWarnings("unchecked")
		public RangeAmount getRangeAmountById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from RangeAmount p where p.id = ?";

			List<RangeAmount> RangeAmount = (List<RangeAmount>) getHibernateTemplate().find(queryString, id);

			if (RangeAmount.size() > 1) {
				throw new Exception("More than one RangeAmount with same Id");
			}
			return RangeAmount.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public List<RangeAmount> getRangeAmountForId(int id) {
			List<RangeAmount> RangeAmount = null;
			String query="from RangeAmount a where a.id=? ";
			RangeAmount = getHibernateTemplate().find(query, id);
			return RangeAmount;
		}	
		
		@SuppressWarnings("unchecked")
		public List<RangeAmount> getCommercialRange() {
			List<RangeAmount> RangeAmount = null;
			String query="from RangeAmount a where a.id > 6 ";
			RangeAmount = getHibernateTemplate().find(query);
			return RangeAmount;
		}		
		
		@SuppressWarnings("unchecked")
		public List<RangeAmount> getResidentialRange() {
			List<RangeAmount> RangeAmount = null;
			String query="from RangeAmount a where a.id < 6 ";
			RangeAmount = getHibernateTemplate().find(query);
			return RangeAmount;
		}		
		
		public void addRangeAmount(RangeAmount RangeAmount) {
			getHibernateTemplate().save(RangeAmount);
	
		}

		public void deleteRangeAmount(RangeAmount RangeAmount){
				getHibernateTemplate().delete(RangeAmount);
		}

		public List<RangeAmount> getAllRangeAmount() {
			return this.getHibernateTemplate().loadAll(RangeAmount.class);
		
		}
		
		public int countRangeAmount(){
			
			int iSize = this.getHibernateTemplate().loadAll(RangeAmount.class).size();

			return iSize;
			
	    }

}