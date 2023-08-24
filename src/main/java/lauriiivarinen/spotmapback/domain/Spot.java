package lauriiivarinen.spotmapback.domain;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "spots")
public class Spot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String image;
	@Column(nullable=false)
	private String description;
	@Column(nullable=false)
	private double lon;
	@Column(nullable=false)
	private double lat;
	@Column(nullable=false)
	private int likes;
	@Column(nullable=false)
	private int dislikes;
	
	
	@ManyToOne
	@JsonIgnoreProperties({"spots", "likes", "dislikes", "comments"})
	@JoinColumn(name="userId")
	private User user;
	
	
	@ManyToMany
	private List<User> likers = new ArrayList<User>();
	
	@ManyToMany
	private List<User> dislikers = new ArrayList<User>();
	
	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval=true, mappedBy = "spot")
	@JsonIgnoreProperties({"spot"})
	private List<Comment> comments = new ArrayList<Comment>();

	public Spot(String name, String image, String description, double lon, double lat, User user) {
		this.name = name;
		this.image = image;
		this.description = description;
		this.lon = lon;
		this.lat = lat;
		this.user = user;
		this.likes = 0;
		this.dislikes = 0;
	}

	public Spot() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void like(User user) {
		if (!this.likers.contains(user)) {
			likers.add(user);
			this.likes++;
		}else {
			this.likers.remove(user);
			this.likes--;
		}
		if (this.dislikers.contains(user)) {
			dislikers.remove(user);
			this.dislikes--;
		}
	}
	
	public void dislike(User user) {
		if (this.likers.contains(user)) {
			likers.remove(user);
			this.likes--;
		}
		if (!this.dislikers.contains(user)) {
			dislikers.add(user);
			this.dislikes++;
		}else {
			this.dislikers.remove(user);
			this.dislikes--;
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public double getLon() {
		return lon;
	}

	public double getLat() {
		return lat;
	}

	public User getUser() {
		return user;
	}
	

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	
	public List<Comment> getComments(){
		return this.comments;
	}

	

	@Override
	public String toString() {
		return "Spot [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + ", lon="
				+ lon + ", lat=" + lat + ", likes=" + likes + ", dislikes=" + dislikes + ", user=" + user + "]";
	}
	

}
