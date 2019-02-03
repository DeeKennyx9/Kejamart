package kejamart.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Locations{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="location")
	private String location;
	
	@Column(name="county")
	private String county;
	
	@Column(name="countyId")
	private int countyId;
	
	@Column(name="country")
	private String country;
	
	@Column(name="createdDate")
	private Date createdDate;
	
    @ManyToOne
    @JoinColumn(name="countyId", insertable = false, updatable = false)
	//Dependent LOV feature 
    private Counties counties;  
    
  //Dependent LOV feature 
     public Counties getCounties() {
        return counties;
    }
     
   //Dependent LOV feature 
    public void setCounties(Counties counties) {
        this.counties = counties;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCountyId() {
		return countyId;
	}

	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}

}

