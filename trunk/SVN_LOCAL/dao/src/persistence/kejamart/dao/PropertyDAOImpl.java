package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.model.Property;

	public class PropertyDAOImpl extends HibernateDaoSupport implements PropertyDAO {
		
		@SuppressWarnings("unchecked")
		public Property getPropertyById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Property p where p.id = ?";

			List<Property> property = (List<Property>) getHibernateTemplate().find(queryString, id);

			if (property.size() > 1) {
				throw new Exception("More than one Property with same Id");
			}
			return property.get(0);
		}

		@SuppressWarnings("unchecked")
		public Property getPropertyByEmail(String email) throws Exception {
			if (email == null) {
				throw new Exception("email parameter cannot be null!");
			}

			String queryString = "from Property p where p.email = ?";

			List<Property> property = (List<Property>) getHibernateTemplate().find(queryString, email);

			return property.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public Property getPropertyName(String name) throws Exception {
			if (name == null) {
				throw new Exception("name parameter cannot be null!");
			}

			String queryString = "from Property p where p.name = ?";

			List<Property> property = (List<Property>) getHibernateTemplate().find(queryString, name);

			return property.get(0);
		}		

		@SuppressWarnings("unchecked")
		public List<Property> getPropertyForUser(String email) {
			List<Property> property = null;
			String query="from Property a where a.email=? ";
			property = getHibernateTemplate().find(query,email);
			return property;
		}
		
		@SuppressWarnings("unchecked")
		public List<Property> getPropertybyName(String name) {
			List<Property> property = null;
			String query="from Property a where a.name = ? ";
			property = getHibernateTemplate().find(query, name);
			return property;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Property> getPropertyReff(String propreff) {
			List<Property> property = null;
			String query="from Property a where a.propreff = ? ";
			property = getHibernateTemplate().find(query, propreff);
			return property;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Property> getPropertyForId(int id) {
			List<Property> property = null;
			String query="from Property a where a.id=? ";
			property = getHibernateTemplate().find(query, id);
			return property;
		}	
		
		@SuppressWarnings("unchecked")
		public List<Property> getPropertyForStatus(int status) {
			List<Property> property = null;
			String query="from Property a where a.status=? ";
			property = getHibernateTemplate().find(query, status);
			return property;
		}			
		
		@SuppressWarnings("unchecked")
		public List<Property> getDefaultProperty() {
			List<Property> property = null;
			String query="from Property a where a.status > 0 ";
			property = getHibernateTemplate().find(query);
			return property;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Property> getPropertyForString(String propString) {
			List<Property> property = null;
			String query="from Property a where a.status > 0 and a.propString=? ";
			property = getHibernateTemplate().find(query, propString);
			return property;
		}
		
		@SuppressWarnings("unchecked")
		public List<Property> getAllProperty(String country, String county, String location, String propertyType, String category, String bedrooms, String rangeValue) {
			List<Property> property = null;
			String query="from Property a where a.country=? ";
			property = getHibernateTemplate().find(query, country);
			List<Property> property2 = property;
			String query2="from Property a where a.county=? ";
			property2 = getHibernateTemplate().find(query2, county);	
			List<Property> property3 = property2;
			String query3="from Property a where a.location=? ";
			property3 = getHibernateTemplate().find(query3, location);	
			List<Property> property4 = property3;
			String query4="from Property a where a.propertyType=? ";
			property4 = getHibernateTemplate().find(query4, propertyType);	
			List<Property> property5 = property4;
			String query5="from Property a where a.category=? ";
			property5 = getHibernateTemplate().find(query5, category);	
			List<Property> property6 = property5;
			String query6="from Property a where a.bedrooms=? ";
			property6 = getHibernateTemplate().find(query6, bedrooms);	
			List<Property> property7 = property6;
			String query7="from Property a where a.rangeValue=? ";
			property7 = getHibernateTemplate().find(query7, rangeValue);		
			System.out.println("DAO Property List: " +property7);
		
			return property7;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Property> getPropertyForPath(String path) {
			List<Property> property = null;
			String query="from Property a where a.path=? ";
			property = getHibernateTemplate().find(query, path);
			return property;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Property> getPropertyForLocation(String location) {
			List<Property> property = null;
			String query="from Property a where a.location=? ";
			property = getHibernateTemplate().find(query, location);
			return property;
		}		
		
		@SuppressWarnings("unchecked")
		public List<Property> getPublishList(int status) {
			List<Property> property = null;
			String query="from Property a where a.status=?";
			property = getHibernateTemplate().find(query, status);
			return property;
		}		

		public void addProperty(Property property) {
			getHibernateTemplate().save(property);

		}

		public void removeProperty(int id) throws Exception {
			Property al = getPropertyById(id);
			getHibernateTemplate().delete(al);
		}

		public List<Property> getAllProperty() {
			return this.getHibernateTemplate().loadAll(Property.class);

		}

		public int countProperty(){
		
			int iSize = this.getHibernateTemplate().loadAll(Property.class).size();
			
			return iSize;
			
	    }

		public void updateProperty(Property property, int id) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);
			
			propertyToUpdate.setName(property.getName());
			propertyToUpdate.setCompany(property.getCompany());
			propertyToUpdate.setAmount(property.getAmount());
			propertyToUpdate.setBedrooms(property.getBedrooms());
			propertyToUpdate.setCategory(property.getCategory());
			propertyToUpdate.setCountry(property.getCountry());
			propertyToUpdate.setCounty(property.getCounty());
			propertyToUpdate.setCountyVal(property.getCountyVal());
			propertyToUpdate.setDescription(property.getDescription());
			propertyToUpdate.setLocation(property.getLocation());
			propertyToUpdate.setLocationVal(property.getLocationVal());
			propertyToUpdate.setPropertyType(property.getPropertyType());
			propertyToUpdate.setContacts(property.getContacts());
			propertyToUpdate.setStreet(property.getStreet());
			propertyToUpdate.setWebsite(property.getWebsite());
			propertyToUpdate.setDays(property.getDays());
			propertyToUpdate.setStartDate(property.getStartDate());
			propertyToUpdate.setEndDate(property.getEndDate());
			propertyToUpdate.setFileName(property.getFileName());
     		//propertyToUpdate.setPath(property.getPath());
     		propertyToUpdate.setParking(property.getParking());
     		propertyToUpdate.setBathroom(property.getBathroom());
     		propertyToUpdate.setLatitude(property.getLatitude());
     		propertyToUpdate.setLongitude(property.getLongitude());
     		propertyToUpdate.setOrientation(property.getOrientation());
     		propertyToUpdate.setSchedule(property.getSchedule());
     		propertyToUpdate.setUnit(property.getUnit());
     		propertyToUpdate.setVideourl(property.getVideourl());
     		propertyToUpdate.setPrange(property.getPrange());
     		propertyToUpdate.setRangeValue(property.getRangeValue());
     		propertyToUpdate.setEmail(property.getEmail());
     		propertyToUpdate.setStatus(property.getStatus());
     		propertyToUpdate.setPropString(property.getPropString());
			
			getHibernateTemplate().update(propertyToUpdate);
		}
		
		public void updateCell(Property property, int id) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);			
     		propertyToUpdate.setCell(property.getCell());
			
			getHibernateTemplate().update(propertyToUpdate);
		}	
		
		public void updateEmailx(Property property, int id) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);			
     		propertyToUpdate.setEmailx(property.getEmailx());
			
			getHibernateTemplate().update(propertyToUpdate);
		}	
		
		public void updateNamex(Property property, int id) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);			
     		propertyToUpdate.setNamex(property.getNamex());
			
			getHibernateTemplate().update(propertyToUpdate);
		}		
		
		public void propertyViews(Property property, int id, int views) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);			
     		propertyToUpdate.setViews(views);
			
			getHibernateTemplate().update(propertyToUpdate);
		}	
		
		public void propertyEnquiries(Property property, int id, int enquiries) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);			
     		propertyToUpdate.setEnquiries(enquiries);
			
			getHibernateTemplate().update(propertyToUpdate);
		}		
		
		public void publishProperty(Property property, int id) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);			
     		propertyToUpdate.setStatus(property.getStatus());
			
			getHibernateTemplate().update(propertyToUpdate);
		}		
		
		public void updateMainPic(Property property, int id) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);	
			propertyToUpdate.setFileName(property.getFileName());
     		propertyToUpdate.setPath(property.getPath());
     		propertyToUpdate.setOrientation(property.getOrientation());
			
			getHibernateTemplate().update(propertyToUpdate);
		}		
		
		public void updateLogo(Property property, int id) throws Exception {
			
			Property propertyToUpdate = getPropertyById(id);	
     		propertyToUpdate.setLogo(property.getLogo());
			
			getHibernateTemplate().update(propertyToUpdate);
		}			

		   @SuppressWarnings("unchecked")
		   @Override
	       public boolean checkId(int id){
	           System.out.println("Verify if id exists");
	           boolean idMatch = false;
	           
	           String queryString = "from Property o where o.id = ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				id);
	           
	      		if (property.size() == 0) {
	      			idMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			idMatch =  true;
	       		}
	       		return idMatch;
	       }
		   
	       @SuppressWarnings("unchecked")
	   	   public boolean checkUnit(String unit){
	           System.out.println("Verify if Unit Exists");
	           boolean unitMatch = false;
	           
	           String queryString = "from Property p where p.unit= ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				unit);

	      		if (property.size() == 0) {
	      			unitMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			unitMatch =  true;
	       		}
	       		return unitMatch;
	       	}		   
		   
	       @SuppressWarnings("unchecked")
	   	   public boolean checkCountry(String country){
	           System.out.println("Check if Property belongs to Country");
	           boolean countryMatch = false;
	           
	           String queryString = "from Property p where p.country= ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				country);

	      		if (property.size() == 0) {
	      			countryMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			countryMatch =  true;
	       		}
	       		return countryMatch;
	       	}		
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkCounty(String county){
	           System.out.println("Check if Property belongs to County");
	           boolean countryMatch = false;
	           
	           String queryString = "from Property p where p.county= ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				county);

	      		if (property.size() == 0) {
	      			countryMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			countryMatch =  true;
	       		}
	       		return countryMatch;
	       	}	  
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkLocation(String location){
	           System.out.println("Check if Property belongs to Location");
	           boolean countyMatch = false;
	           
	           String queryString = "from Property p where p.location= ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				location);

	      		if (property.size() == 0) {
	      			countyMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			countyMatch =  true;
	       		}
	       		return countyMatch;
	       	}	
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkPropType(String propertyType){
	           System.out.println("Check if Property belongs to Property Type");
	           boolean propTypeMatch = false;
	           
	           String queryString = "from Property p where p.propertyType= ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				propertyType);

	      		if (property.size() == 0) {
	      			propTypeMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			propTypeMatch =  true;
	       		}
	       		return propTypeMatch;
	       	}		
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkCategory(String category){
	           System.out.println("Check if Property belongs to Category");
	           boolean categoryMatch = false;
	           
	           String queryString = "from Property p where p.category= ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				category);

	      		if (property.size() == 0) {
	      			categoryMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			categoryMatch =  true;
	       		}
	       		return categoryMatch;
	       	}
	       
	       @SuppressWarnings("unchecked")
	   	   public boolean checkBedrooms(String bedrooms){
	           System.out.println("Check if Property belongs to Bedrooms");
	           boolean bedMatch = false;
	           
	           String queryString = "from Property p where p.bedrooms= ?";
	      		List<Property> property = getHibernateTemplate().find(queryString,
	      				bedrooms);

	      		if (property.size() == 0) {
	      			bedMatch = false;
	       		}
	       		
	       		else if(property.size() == 1){
	       			bedMatch =  true;
	       		}
	       		return bedMatch;
	       	} 
	       
			@SuppressWarnings("unchecked")
			public Property getPropertyByString(String propString) throws Exception {
				if (propString == null) {
					throw new Exception("propString parameter cannot be null!");
				}

				String queryString = "from Property p where p.status='1' and p.propString in ?";

				List<Property> property = (List<Property>) getHibernateTemplate().find(queryString, propString);

				return property.get(0);
			}
			
			@SuppressWarnings("unchecked")
			public List<Property> getPropertyForStringLimit(String propString) {
				List<Property> property = null;
				List lst = null;
				String query="from Property a where a.status > 0 and a.propString=?";
				property = getHibernateTemplate().find(query, propString);
				
				if (property.size() > 3) {				
				lst = property.subList(0, 3);
				}				
				else if (property.size() <= 3) {					
				lst = property;
				}				
				
				return lst;
			}		
			
			@SuppressWarnings("unchecked")
			public List<Property> getPropertyForStatusLimit(int status) {
				List<Property> property = null;
				List lst = null;
				String query="from Property a where a.status=?";
				property = getHibernateTemplate().find(query, status);
				
				if (property.size() > 4) {				
				lst = property.subList(0, 4);
				}				
				else if (property.size() <= 4) {					
				lst = property;
				}				
				
				return lst;
			}	

}