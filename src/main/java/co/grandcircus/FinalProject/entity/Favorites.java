package co.grandcircus.FinalProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Favorites {
		
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		private String propertyId;
		private String thumbnail;
		private String weburl;
		  @ManyToOne
			private User user;
		    
			
		    public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPropertyId() {
			return propertyId;
		}
		public void setPropertyId(String propertyId) {
			this.propertyId = propertyId;
		}
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}
		public String getWeburl() {
			return weburl;
		}
		public void setWeburl(String weburl) {
			this.weburl = weburl;
		}
		@Override
		public String toString() {
			return "Favorites [id=" + id + ", propertyId=" + propertyId + ", thumbnail=" + thumbnail + ", weburl="
					+ weburl + "]";
		}
		
		

}
