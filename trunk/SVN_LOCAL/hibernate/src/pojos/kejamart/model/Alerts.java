package kejamart.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alerts")
public class Alerts{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="country")
	private String country;	

	@Column(name="county")
	private String county;	
	
	@Column(name="countyVal")
	private String countyVal;		
	
	@Column(name="location")
	private String location;
	
	@Column(name="locationVal")
	private String locationVal;	
	
	@Column(name="propertyType")
	private String propertyType;
	
	@Column(name="category")
	private String category;
	
	@Column(name="bedrooms")
	private String bedrooms;
	
	@Column(name="prange")
	private String prange;

	@Column(name="status")
	private String status;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="propString")
	private String propString;	
	
	@Column(name="rangeValue")
	private String rangeValue;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	public String getCountyVal() {
		return countyVal;
	}

	public void setCountyVal(String countyVal) {
		this.countyVal = countyVal;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}	

	public String getLocationVal() {
		return locationVal;
	}

	public void setLocationVal(String locationVal) {
		this.locationVal = locationVal;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public boolean isIdNew() {
		return (this.id == 0);
	}
	
	public boolean isEmailNew() {
		return (this.email == "");
	}

	public String getPropString() {
		return propString;
	}

	public void setPropString(String propString) {
		this.propString = propString;
	}

	public String getPrange() {
		return prange;
	}

	public void setPrange(String prange) {
		this.prange = prange;
	}

	public String getRangeValue() {
		return rangeValue;
	}

	public void setRangeValue(String rangeValue) {
		this.rangeValue = rangeValue;
	}	
	
}

