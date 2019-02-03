package kejamart.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Messages {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//private int id;
	private int id;
	
	@Column(name="email")
	private String email;

	@Column(name="sender")
	private String sender;	
	
	@Column(name="status")
	private String status;

	@Column(name="message")
	private String message;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="dateRead")
	private Date dateRead;
	
	@Column(name="propString")
	private String propString;	
	
	@Column(name="propertyId")
	private int propertyId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDateRead() {
		return dateRead;
	}

	public void setDateRead(Date dateRead) {
		this.dateRead = dateRead;
	}

	public String getPropString() {
		return propString;
	}

	public void setPropString(String propString) {
		this.propString = propString;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}	
		
}

