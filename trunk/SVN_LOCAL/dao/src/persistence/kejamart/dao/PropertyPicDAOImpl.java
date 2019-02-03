package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.model.PropertyPics;

	public class PropertyPicDAOImpl extends HibernateDaoSupport implements PropertyPicDAO {
		
		@SuppressWarnings("unchecked")
		public PropertyPics getPicById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from PropertyPics t where t.id = ?";

			List<PropertyPics> pic = (List<PropertyPics>) getHibernateTemplate().find(queryString, id);

			if (pic.size() > 1) {
				throw new Exception("More than one Pic with same Id");
			}
			return pic.get(0);
		}
		
		public void addPropertyPic(PropertyPics propertyPics) {
			getHibernateTemplate().save(propertyPics);

		}

		public void updatePic(PropertyPics propertyPics, int id) throws Exception {
			
			PropertyPics picToUpdate = getPicById(id);			
			picToUpdate.setStatus(propertyPics.getStatus());
			picToUpdate.setFileName(picToUpdate.getFileName());
			picToUpdate.setPath(picToUpdate.getPath());
			getHibernateTemplate().update(picToUpdate);
		}
		
		@SuppressWarnings("unchecked")
		public List<PropertyPics> getPropertyPicsForId(int id) {
			List<PropertyPics> propertyPics = null;
			String query="from PropertyPics a where a.id=?";
			propertyPics = getHibernateTemplate().find(query, id);
			return propertyPics;
		}	
		
		@SuppressWarnings("unchecked")
		public List<PropertyPics> getPropertyPicsForPropId(int propertyId) {
			List<PropertyPics> propertyPics = null;
			String query="from PropertyPics a where a.propertyId=?";
			propertyPics = getHibernateTemplate().find(query, propertyId);
			return propertyPics;
		}		
		
		@SuppressWarnings("unchecked")
		public List<PropertyPics> getPublishList(String status) {
			List<PropertyPics> propertyPics = null;
			String query="from PropertyPics a where a.status=?";
			propertyPics = getHibernateTemplate().find(query, status);
			return propertyPics;
		}		
		
		public void deletePic(PropertyPics pic) throws Exception{
			PropertyPics picToDelete = getPicById(pic.getId());
				getHibernateTemplate().delete(picToDelete);
		}
		
		public void removePic(int id) throws Exception {
			PropertyPics pr = getPicById(id);
			getHibernateTemplate().delete(pr);
		}	
		
		@SuppressWarnings("unchecked")
		@Override
	    public boolean checkFileName(String fileName){
	           System.out.println("Verify if filename exists");
	           boolean filenameMatch = false;
	           
	           String queryString = "from PropertyPics o where o.fileName = ?";
	      	   List<PropertyPics> pr = getHibernateTemplate().find(queryString,
	      			 fileName);
	           
	      		if (pr.size() == 0) {
	      			filenameMatch = false;
	       		} 		
	       		
	       		else if(pr.size() == 1){
	       			filenameMatch =  true;
	       		}
	       		return filenameMatch;
	      }			

		public List<PropertyPics> getPics() {
			return this.getHibernateTemplate().loadAll(PropertyPics.class);
		
		}
			  
		public int countPics(){

			int iSize = this.getHibernateTemplate().loadAll(PropertyPics.class).size();
			
			return iSize;
			
	    }

}