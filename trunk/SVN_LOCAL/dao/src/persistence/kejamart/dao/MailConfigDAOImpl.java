package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.dao.MailConfigDAO;
import kejamart.model.MailConfig;

	public class MailConfigDAOImpl extends HibernateDaoSupport implements MailConfigDAO {
		
		@SuppressWarnings("unchecked")
		public MailConfig getMailConfigById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from MailConfig p where p.id = ?";

			List<MailConfig> mailConfig = (List<MailConfig>) getHibernateTemplate().find(queryString, id);

			if (mailConfig.size() > 1) {
				throw new Exception("More than one MailConfig with same Id");
			}
			return mailConfig.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public MailConfig getMailConfigByHost(String smtpHost) throws Exception {
			if (smtpHost == null) {
				throw new Exception("smtpHost parameter cannot be null!");
			}

			String queryString = "from MailConfig p where p.smtpHost = ?";

			List<MailConfig> mailConfig = (List<MailConfig>) getHibernateTemplate().find(queryString, smtpHost);

			if (mailConfig.size() > 1) {
				throw new Exception("More than one MailConfig with same smtpHost");
			}
			return mailConfig.get(0);
		}
	   
		public void addMailConfig(MailConfig mailConfig) throws Exception{
			if (mailConfig.getId() == 0){
			getHibernateTemplate().save(mailConfig);
			}
			else
			throw new Exception("MailConfig already exists with the same ID.");	
		}

		public void deleteMailConfig(MailConfig mailConfig) throws Exception{
			MailConfig MailConfigToDelete = getMailConfigById(mailConfig.getId());
				getHibernateTemplate().delete(MailConfigToDelete);
		}

		public List<MailConfig> getMailConfig() {
			return this.getHibernateTemplate().loadAll(MailConfig.class);
		
		}
			  
		public int countMailConfig(){
			
		int iSize = this.getHibernateTemplate().loadAll(MailConfig.class).size();
		
		return iSize;
		
	    }


}