package kejamart.model;

import java.sql.Date;
import java.util.concurrent.atomic.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="counties")
public class Counties{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="county")
	private String county;
	
	@Column(name="country")
	private String country;
	
	@Column(name="countryId")
	private int countryId;	
	
	@Column(name="ceatedDate")
	private Date createdDate;
	
    @ManyToOne
    @JoinColumn(name="countryId", insertable = false, updatable = false)
	//Dependent LOV feature 
    private Countries countries;  
    
  //Dependent LOV feature 
    public Countries getCountries() {
        return countries;
    }
     
   //Dependent LOV feature 
    public void setCountries(Countries countries) {
        this.countries = countries;
    }	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	
}

