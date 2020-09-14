package co.grandcircus.FinalProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SavedSearches {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String city;
	private String state;
	private String maxprice;
	private String minprice;
	private Double beds;
	private Double baths;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}
	public String getMinprice() {
		return minprice;
	}
	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}
	public Double getBeds() {
		return beds;
	}
	public void setBeds(Double beds) {
		this.beds = beds;
	}
	public Double getBaths() {
		return baths;
	}
	public void setBaths(Double baths) {
		this.baths = baths;
	}
	@Override
	public String toString() {
		return "SavedSearches [id=" + id + ", city=" + city + ", state=" + state + ", maxprice=" + maxprice
				+ ", minprice=" + minprice + ", beds=" + beds + ", baths=" + baths + "]";
	}
	
	

}
