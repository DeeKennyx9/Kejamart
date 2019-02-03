package kejamart.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="property")
public class Property {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int profileId;
	
	@Column(name="email")
	private String email;

	@Column(name="name")
	private String name;
	
	@Column(name="company")
	private String company;	

	@Column(name="category")
	private String category;

	@Column(name="propertyType")
	private String propertyType;

	@Column(name="county")
	private String county;
	
	@Column(name="countyVal")
	private String countyVal;	

	@Column(name="country")
	private String country;

	@Column(name="description")
	private String description;

	@Column(name="location")
	private String location;
	
	@Column(name="locationVal")
	private String locationVal;	
	
	@Column(name="bedrooms")
	private String bedrooms;
	
	@Column(name="prange")
	private String prange;
	
	@Column(name="rangeValue")
	private String rangeValue;	
	
	@Column(name="contacts")
	private String contacts;
	
	@Column(name="website")
	private String website;	
	
	@Column(name="propreff")
	private String propreff;

	@Column(name="amount")
	private int amount;
	
	@Column(name="status")
	private int status;
	
	@Column(name="days")
	private int days;

	@Column(name="startDate")
	private Date startDate;
	
	@Column(name="endDate")
	private Date endDate;
	
	@Column(name="createdDate")
	private Date createdDate;	
	
	@Column(name="propString")
	private String propString;	
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="longitude")
	private String longitude;
	
	@Column(name="bathroom")
	private int bathroom;
	
	@Column(name="parking")
	private int parking;
	
	@Column(name="street")
	private String street;
	
	@Column(name="path")
	private String path;
	
	@Column(name="logo")
	private String logo;	
	
	@Column(name="orientation")
	private String orientation;	
	
	@Column(name="views")
	private int views;	
	
	@Column(name="enquiries")
	private int enquiries;
	
	@Column(name="schedule")
	private String schedule;	
	
	@Column(name="fileName")
	private String fileName;
	
	@Column(name="cell")
	private String cell;	
	
	@Column(name="videourl")
	private String videourl;	
	
	@Column(name="unit")
	private String unit;	
	
	@Column(name="emailx")
	private String emailx;	
	
	@Column(name="namex")
	private String namex;		

	public boolean isIdNew() {
		return (this.id == 0);
	}
	
	public boolean isEmailNew() {
		return (this.email == "");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPropreff() {
		return propreff;
	}

	public void setPropreff(String propreff) {
		this.propreff = propreff;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPropString() {
		return propString;
	}

	public void setPropString(String propString) {
		this.propString = propString;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getBathroom() {
		return bathroom;
	}

	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}

	public int getParking() {
		return parking;
	}

	public void setParking(int parking) {
		this.parking = parking;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(int enquiries) {
		this.enquiries = enquiries;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmailx() {
		return emailx;
	}

	public void setEmailx(String emailx) {
		this.emailx = emailx;
	}

	public String getNamex() {
		return namex;
	}

	public void setNamex(String namex) {
		this.namex = namex;
	}		
			
}
