package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.dao.ResourcesDAO;
import kejamart.model.PropertyPics;
import kejamart.model.Resources;

	public class ResourcesDAOImpl extends HibernateDaoSupport implements ResourcesDAO {
		
		@SuppressWarnings("unchecked")
		public Resources getResourceById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Resources t where t.id = ?";

			List<Resources> pic = (List<Resources>) getHibernateTemplate().find(queryString, id);

			if (pic.size() > 1) {
				throw new Exception("More than one Resource with same Id");
			}
			return pic.get(0);
		}
		
		public void addResource(Resources resources) {
			getHibernateTemplate().save(resources);

		}

		public void updateResources(Resources resources, int id) throws Exception {
			
			Resources picToUpdate = getResourceById(id);			
			picToUpdate.setFileName(picToUpdate.getFileName());
			picToUpdate.setPath(picToUpdate.getPath());
			getHibernateTemplate().update(picToUpdate);
		}
		
		@SuppressWarnings("unchecked")
		public List<Resources> getResourcesForId(int id) {
			List<Resources> resources = null;
			String query="from Resources a where a.id=?";
			resources = getHibernateTemplate().find(query, id);
			return resources;
		}	
		
		@SuppressWarnings("unchecked")
		public List<Resources> getResourcesForPropId(int propertyId) {
			List<Resources> resources = null;
			String query="from Resources a where a.propertyId=?";
			resources = getHibernateTemplate().find(query, propertyId);
			return resources;
		}		
		
		@SuppressWarnings("unchecked")
		@Override
	    public boolean checkFileName(String fileName) {
	           System.out.println("Verify if filename exists");
	           boolean filenameMatch = false;
	           
	           String queryString = "from Resources o where o.fileName = ?";
	      	   List<Resources> rc = getHibernateTemplate().find(queryString,
	      			 fileName);
	           
	      		if (rc.size() == 0) {
	      			filenameMatch = false;
	       		}	
	       		
	       		else if(rc.size() == 1){
	       			filenameMatch =  true;
	       		}
	       		return filenameMatch;
	      }	
		
		
		
		public void removeResources(int id) throws Exception {
			Resources rc = getResourceById(id);
			getHibernateTemplate().delete(rc);
		}		

		public List<Resources> getResources() {
			return this.getHibernateTemplate().loadAll(Resources.class);
		
		}
			  
		public int countResources(){
			
			int iSize = this.getHibernateTemplate().loadAll(Resources.class).size();
			
			return iSize;
			
	    }

}