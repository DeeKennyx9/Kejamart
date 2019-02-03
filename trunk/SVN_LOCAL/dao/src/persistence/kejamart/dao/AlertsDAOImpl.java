package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.model.Alerts;

	public class AlertsDAOImpl extends HibernateDaoSupport implements AlertsDAO {

		@SuppressWarnings("unchecked")
		public Alerts getAlertsById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Alerts p where p.id = ?";

			List<Alerts> alerts = (List<Alerts>) getHibernateTemplate().find(queryString, id);

			if (alerts.size() > 1) {
				throw new Exception("More than one Alerts with same Id");
			}
			return alerts.get(0);
		}

		@SuppressWarnings("unchecked")
		public Alerts getAlertsByEmail(String email) throws Exception {
			if (email == null) {
				throw new Exception("email parameter cannot be null!");
			}

			String queryString = "from Alerts p where p.email = ?";

			List<Alerts> alerts = (List<Alerts>) getHibernateTemplate().find(queryString, email);

			return alerts.get(0);
		}

		@SuppressWarnings("unchecked")
		public List<Alerts> getAlertsForUser(String email) {
			List<Alerts> alerts = null;
			String query="from Alerts a where a.email=? ";
			alerts = getHibernateTemplate().find(query,email);
			return alerts;
		}
		
		public void addAlerts(Alerts alerts) {
			getHibernateTemplate().save(alerts);

		}

		public void removeAlert(int id) throws Exception {
			Alerts al = getAlertsById(id);
			getHibernateTemplate().delete(al);
		}

		public List<Alerts> getAlerts() {
			return this.getHibernateTemplate().loadAll(Alerts.class);

		}

		public int countAlerts(){

			int iSize = this.getHibernateTemplate().loadAll(Alerts.class).size();
			
			return iSize;
	    }
		
	       @SuppressWarnings("unchecked")
	   	   public boolean checkCountry(String country){
	           System.out.println("Check if Alert belongs to Country");
	           boolean countryMatch = false;
	           
	           String queryString = "from Alerts p where p.country= ?";
	      		List<Alerts> alerts = getHibernateTemplate().find(queryString,
	      				country);

	      		if (alerts.size() == 0) {
	      			countryMatch = false;
	       		}
	       		
	       		else if(alerts.size() == 1){
	       			countryMatch =  true;
	       		}
	       		return countryMatch;
	       	}		
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkCounty(String county){
	           System.out.println("Check if Alert belongs to County");
	           boolean countryMatch = false;
	           
	           String queryString = "from Alerts p where p.county= ?";
	      		List<Alerts> alerts = getHibernateTemplate().find(queryString,
	      				county);

	      		if (alerts.size() == 0) {
	      			countryMatch = false;
	       		}
	       		
	       		else if(alerts.size() == 1){
	       			countryMatch =  true;
	       		}
	       		return countryMatch;
	       	}	  
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkLocation(String location){
	           System.out.println("Check if Alert belongs to Location");
	           boolean countyMatch = false;
	           
	           String queryString = "from Alerts p where p.location= ?";
	      		List<Alerts> alerts = getHibernateTemplate().find(queryString,
	      				location);

	      		if (alerts.size() == 0) {
	      			countyMatch = false;
	       		}
	       		
	       		else if(alerts.size() == 1){
	       			countyMatch =  true;
	       		}
	       		return countyMatch;
	       	}	
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkPropType(String propertyType){
	           System.out.println("Check if Alert belongs to Property Type");
	           boolean propTypeMatch = false;
	           
	           String queryString = "from Alerts p where p.propertyType= ?";
	      		List<Alerts> alerts = getHibernateTemplate().find(queryString,
	      				propertyType);

	      		if (alerts.size() == 0) {
	      			propTypeMatch = false;
	       		}
	       		
	       		else if(alerts.size() == 1){
	       			propTypeMatch =  true;
	       		}
	       		return propTypeMatch;
	       	}		
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkCategory(String category){
	           System.out.println("Check if Alert belongs to Category");
	           boolean categoryMatch = false;
	           
	           String queryString = "from Alerts p where p.category= ?";
	      		List<Alerts> alerts = getHibernateTemplate().find(queryString,
	      				category);

	      		if (alerts.size() == 0) {
	      			categoryMatch = false;
	       		}
	       		
	       		else if(alerts.size() == 1){
	       			categoryMatch =  true;
	       		}
	       		return categoryMatch;
	       	}
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkBedrooms(int bedrooms){
	           System.out.println("Check if Alert belongs to Bedrooms");
	           boolean bedMatch = false;
	           
	           String queryString = "from Alerts p where p.bedrooms= ?";
	      		List<Alerts> alerts = getHibernateTemplate().find(queryString,
	      				bedrooms);

	      		if (alerts.size() == 0) {
	      			bedMatch = false;
	       		}
	       		
	       		else if(alerts.size() == 1){
	       			bedMatch =  true;
	       		}
	       		return bedMatch;
	       	}      

	       @SuppressWarnings("unchecked")
	   	   public boolean checkUser(String email){
	           System.out.println("Check if Alert belongs to Email");
	           boolean emailMatch = false;
	           
	           String queryString = "from Alerts p where p.email= ?";
	      		List<Alerts> alerts = getHibernateTemplate().find(queryString,
	      				email);

	      		if (alerts.size() == 0) {
	      			emailMatch = false;
	       		}
	       		
	       		else if(alerts.size() == 1){
	       			emailMatch =  true;
	       		}
	       		return emailMatch;
	       	}  
	       
			@SuppressWarnings("unchecked")
			public List<Alerts> getAlertsForCountry(String country) {
				List<Alerts> alerts = null;
				String query="from Alerts a where a.country=? ";
				alerts = getHibernateTemplate().find(query,country);
				return alerts;
			}  
			
			@SuppressWarnings("unchecked")
			public List<Alerts> getAlertsForCounty(String county) {
				List<Alerts> alerts = null;
				String query="from Alerts a where a.county=? ";
				alerts = getHibernateTemplate().find(query,county);
				return alerts;
			} 
			
			@SuppressWarnings("unchecked")
			public List<Alerts> getAlertsForLocation(String location) {
				List<Alerts> alerts = null;
				String query="from Alerts a where a.location=? ";
				alerts = getHibernateTemplate().find(query,location);
				return alerts;
			} 			
			
			@SuppressWarnings("unchecked")
			public List<Alerts> getAlertsForPropType(String propertyType) {
				List<Alerts> alerts = null;
				String query="from Alerts a where a.propertyType=? ";
				alerts = getHibernateTemplate().find(query,propertyType);
				return alerts;
			} 
			
			@SuppressWarnings("unchecked")
			public List<Alerts> getAlertsForCategory(String category) {
				List<Alerts> alerts = null;
				String query="from Alerts a where a.category=? ";
				alerts = getHibernateTemplate().find(query,category);
				return alerts;
			} 	
			
			@SuppressWarnings("unchecked")
			public List<Alerts> getAlertList(int propIndex) {
				List<Alerts> alerts = null;
				String query="from Alerts a where a.propIndex=? ";
				alerts = getHibernateTemplate().find(query, propIndex);
				return alerts;
			}
						
			@SuppressWarnings("unchecked")
			public Alerts getAlertsByString(String propString) throws Exception {
				if (propString == null) {
					throw new Exception("propString parameter cannot be null!");
				}

				String queryString = "from Alerts p where p.status='0' and p.propString = ?";

				List<Alerts> alerts = (List<Alerts>) getHibernateTemplate().find(queryString, propString);

				return alerts.get(0);
			}
			
			@SuppressWarnings("unchecked")
			public List<Alerts> getAlertsForString(String propString) {
				List<Alerts> alerts = null;
				String query="from Alerts a where a.propString=? ";
				alerts = getHibernateTemplate().find(query, propString);
				return alerts;
			}
			
			public void markSentMessages(Alerts alerts, int id) throws Exception {	
				
				Alerts alertsUpdate = getAlertsById(id);			
				alertsUpdate.setStatus("1");
				getHibernateTemplate().update(alertsUpdate);
			}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	