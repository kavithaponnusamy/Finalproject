package co.grandcircus.FinalProject.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Property {
	public String property_id;
    public String listing_id;
    @JsonProperty("rdc_web_url")
    public String weburl;
    public String prop_type;
    public String prop_status;
    public Double price;
    public Integer baths_full;
   
    public Integer baths;
    public Integer beds;
    private Address address;
    private List<Agent> agents;
    
	public Date last_update;
    public Integer photo_count;
    private List<Photo> photos;
    
    public String thumbnail;
    
    public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Integer rank;
//    @JsonProperty("virtual_tour")
//    private VirtualTour virtualTour;
    
    
    
    public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public String getProperty_id() {
		return property_id;
	}
	public void setProperty_id(String property_id) {
		this.property_id = property_id;
	}
	public String getListing_id() {
		return listing_id;
	}
	public void setListing_id(String listing_id) {
		this.listing_id = listing_id;
	}
	public String getWeburl() {
		return weburl;
	}
	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}
	public String getProp_type() {
		return prop_type;
	}
	public void setProp_type(String prop_type) {
		this.prop_type = prop_type;
	}
	public String getProp_status() {
		return prop_status;
	}
	public void setProp_status(String prop_status) {
		this.prop_status = prop_status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getBaths_full() {
		return baths_full;
	}
	public void setBaths_full(Integer baths_full) {
		this.baths_full = baths_full;
	}
	public Integer getBaths() {
		return baths;
	}
	public void setBaths(Integer baths) {
		this.baths = baths;
	}
	public Integer getBeds() {
		return beds;
	}
	public void setBeds(Integer beds) {
		this.beds = beds;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Agent> getAgents() {
		return agents;
	}
	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	public Integer getPhoto_count() {
		return photo_count;
	}
	public void setPhoto_count(Integer photo_count) {
		this.photo_count = photo_count;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
//	public VirtualTour getVirtualTour() {
//		return virtualTour;
//	}
//	public void setVirtualTour(VirtualTour virtualTour) {
//		this.virtualTour = virtualTour;
//	}
    
    
}
