package kejamart.dao;

import java.util.List;

import kejamart.model.MailConfig;

public interface MailConfigDAO {

	public void addMailConfig(MailConfig mailConfig) throws Exception;
	public MailConfig getMailConfigById(int id) throws Exception;
	public MailConfig getMailConfigByHost(String host) throws Exception;
	public void deleteMailConfig(MailConfig mailConfig) throws Exception;
	public List<MailConfig> getMailConfig();
	public int countMailConfig();

}