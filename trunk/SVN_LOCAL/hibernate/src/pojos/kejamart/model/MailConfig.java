package kejamart.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mailconfig")
public class MailConfig{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="smtpHost")
	private String smtpHost;	
	
	@Column(name="smtpPort")
	private int smtpPort;
	
	@Column(name="hostUserName")
	private String hostUserName;
	
	@Column(name="hostPassword")
	private String hostPassword;
	
	@Column(name="smtpStartTls")
	private String smtpStartTls;
	
	@Column(name="transportProtocol")
	private String transportProtocol;
	
	@Column(name="protocol")
	private String protocol;	
	
	@Column(name="status")
	private String status;
	
	@Column(name="subject")
	private String subject;	
	
	@Column(name="website")
	private String website;	
	
	@Column(name="dateCreated")
	private Date dateCreated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}

	public String getHostPassword() {
		return hostPassword;
	}

	public void setHostPassword(String hostPassword) {
		this.hostPassword = hostPassword;
	}

	public String getSmtpStartTls() {
		return smtpStartTls;
	}

	public void setSmtpStartTls(String smtpStartTls) {
		this.smtpStartTls = smtpStartTls;
	}

	public String getTransportProtocol() {
		return transportProtocol;
	}

	public void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}	

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


}

