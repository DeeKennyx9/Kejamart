package kejamart.dao;

import java.util.List;

import kejamart.model.Messages;

public interface MessagesDAO {

	public void addMessages(Messages messages) throws Exception;
	public Messages getMessagesById(int id) throws Exception;
	public Messages getMessagesByEmail(String email) throws Exception;
	public void deleteMessages(Messages messages) throws Exception;
	public List<Messages> getMessages();
	public int countMessages();
	public List<Messages> getMessagesByStatus(String status);
	public List<Messages> getMessagesByEmailStatus(String email);
	public void markReadMessages(Messages messages, int id) throws Exception;
	public boolean checkUserReadStatus(String email);
	public void removeMessages(int id) throws Exception;
	public List<Messages> getMessagesForEmail(String email);

}