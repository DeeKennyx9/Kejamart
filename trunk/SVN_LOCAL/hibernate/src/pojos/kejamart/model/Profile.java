package kejamart.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="profile")
@SuppressWarnings("serial")
public class Profile implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//private int id;
	private int id;

	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;

	@Column(name="firstName")
	private String firstName;

	@Column(name="lastName")
	private String lastName;
	
	@Column(name="mobile")
	private String mobile;	

	@Column(name="category")
	private String category;

	@Column(name="status")
	private String status;

	@Column(name="role")
	private String role;
	
	@Column(name="enabled")
	private int enabled;
	
	@Column(name="mailing")
	private String mailing;
	
	@Column(name="createdDate")
	private Date createdDate;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMailing() {
		return mailing;
	}

	public void setMailing(String mailing) {
		this.mailing = mailing;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}	
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isIdNew() {
		return (this.id == 0);
	}
	
	public boolean isEmailNew() {
		return (this.email == "");
	}
	

}