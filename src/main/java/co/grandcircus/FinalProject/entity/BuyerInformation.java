package co.grandcircus.FinalProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BuyerInformation {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String comments;
	@ManyToOne
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String quote;
	public String propertyId;
	
//	private String name;
//	
//	public String email;
//	
//	private String phoneNo;
//	
//	
//	
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPhoneNo() {
//		return phoneNo;
//	}
//	public void setPhoneNo(String phoneNo) {
//		this.phoneNo = phoneNo;
//	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "BuyerInformation [id=" + id + ", comments=" + comments + ", quote=" + quote + ", propertyId="
				+ propertyId + ", user=" + user + "]";
	}


	

}
