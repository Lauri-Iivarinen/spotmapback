package lauriiivarinen.spotmapback.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false , updatable=false)
	private Long userId;
	
	@Column(nullable=false , updatable=false)
	private String username;
	
	@Column(nullable=false , updatable=false)
	@JsonIgnore
	private String passwordHash;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Spot> spots;
	
	
	@ManyToMany
	@JsonIgnoreProperties({"name", "image", "description", "lon", "lat", "likes", "dislikes", "user", "comments"})
	private List<Spot> likes = new ArrayList<Spot>();
	
	@ManyToMany
	@JsonIgnoreProperties({"name", "image", "description", "lon", "lat", "likes", "dislikes", "user", "comments"})
	private List<Spot> dislikes = new ArrayList<Spot>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Comment> comments = new ArrayList<Comment>();
	
	public void addLike(Spot spot) {
		if (!this.likes.contains(spot)) this.likes.add(spot);
		if (this.dislikes.contains(spot)) this.dislikes.remove(spot);	
	}
	
	public void addDislike(Spot spot) {
		if (!this.dislikes.contains(spot)) this.dislikes.add(spot);
		if (this.likes.contains(spot)) this.likes.remove(spot);
	}
	
	public List<Spot> getLikes() {
		return this.likes;
	}
	public List<Spot> getDislikes() {
		return this.dislikes;
	}
	public List<Comment> getComments(){
		return this.comments;
	}
	/*
	

	public void setLikes(List<Spot> likes) {
		this.likes = likes;
	}

	

	public void setDislikes(List<Spot> dislikes) {
		this.dislikes = dislikes;
	}
	
	
	
	
	// */

	@Column(nullable=false)
	private String role;

	public User( String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = "user";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public void setId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setSpots(List<Spot> spots) {
		this.spots = spots;
	}

	public Long getId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public List<Spot> getSpots() {
		return spots;
	}

	public String getRole() {
		return role;
	}
	
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", passwordHash=" + passwordHash + ", spots="
				+ spots + ", role=" + role + "]";
	}
	
	

}
