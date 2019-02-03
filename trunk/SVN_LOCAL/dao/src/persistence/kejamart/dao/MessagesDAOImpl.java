package kejamart.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import kejamart.model.Messages;

	public class MessagesDAOImpl extends HibernateDaoSupport implements MessagesDAO {
		
		@SuppressWarnings("unchecked")
		public Messages getMessagesById(int id) throws Exception {
			if (id == 0) {
				throw new Exception("id parameter cannot be null!");
			}

			String queryString = "from Messages p where p.id = ?";

			List<Messages> messages = (List<Messages>) getHibernateTemplate().find(queryString, id);

			if (messages.size() > 1) {
				throw new Exception("More than one Messages with same Id");
			}
			return messages.get(0);
		}
		
		@SuppressWarnings("unchecked")
		public Messages getMessagesByEmail(String email) throws Exception {
			if (email == null) {
				throw new Exception("email parameter cannot be null!");
			}

			String queryString = "from Messages p where p.email = ?";

			List<Messages> messages = (List<Messages>) getHibernateTemplate().find(queryString, email);

			if (messages.size() > 1) {
				throw new Exception("More than one Alert with same email");
			}
			return messages.get(0);
		}
		
		public void removeMessages(int id) throws Exception {
			Messages msg = getMessagesById(id);
			getHibernateTemplate().delete(msg);
		}
		
		public void markReadMessages(Messages messages, int id) throws Exception {
			
			Messages messagesUpdate = getMessagesById(id);			
			messagesUpdate.setStatus(messagesUpdate.getStatus());
			messagesUpdate.setDateRead(messagesUpdate.getDateRead());			
			getHibernateTemplate().update(messagesUpdate);
		}
		
		@SuppressWarnings("unchecked")
		public List<Messages> getMessagesByStatus(String status) {
			List<Messages> messages = null;
			String query="from Messages a where a.status=? ";
			messages = getHibernateTemplate().find(query,status);
			return messages;
		} 
		
		@SuppressWarnings("unchecked")
		public List<Messages> getMessagesForEmail(String email) {
			List<Messages> messages = null;
			String query="from Messages a where a.email=? ";
			messages = getHibernateTemplate().find(query, email);
			return messages;
		} 
		
		@SuppressWarnings("unchecked")
		public List<Messages> getMessagesByEmailStatus(String email) {
			List<Messages> messages = null;
			String query="from Messages a where a.status='0' and email=? ";
			messages = getHibernateTemplate().find(query,email);
			return messages;
		}	
		
	       @SuppressWarnings("unchecked")
	   	   public boolean checkUserReadStatus(String email){
	           System.out.println("Check if Message belongs to Email");
	           boolean emailMatch = false;
	           
	           String queryString = "from Messages p where p.status='1' and p.email= ?";
	           List<Messages> messages = getHibernateTemplate().find(queryString,
	      				email);

	      		if (messages.size() == 0) {
	      			emailMatch = false;
	       		}
	       		
	       		else if(messages.size() == 1){
	       			emailMatch =  true;
	       		}
	       		return emailMatch;
	       	}
	   
		public void addMessages(Messages messages) throws Exception{
			if (messages.getId() == 0){
			getHibernateTemplate().save(messages);
			}
			else
			throw new Exception("Alert already exists with the same ID.");	
		}

		public void deleteMessages(Messages messages) throws Exception{
			Messages messagesToDelete = getMessagesById(messages.getId());
				getHibernateTemplate().delete(messagesToDelete);
		}

		public List<Messages> getMessages() {
			return this.getHibernateTemplate().loadAll(Messages.class);
		
		}
			  
		public int countMessages(){
			
			int iSize = this.getHibernateTemplate().loadAll(Messages.class).size();
			
			return iSize;
			
	    }


}