package lauriiivarinen.spotmapback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private long commentId;
	
	@Column(nullable=false)
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Spot spot;
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"spots", "likes", "dislikes", "comments"})
	private User user;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String comment, Spot spot, User user) {
		super();
		this.comment = comment;
		this.spot = spot;
		this.user = user;
	}

	public long getId() {
		return commentId;
	}

	public void setId(long id) {
		this.commentId = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [id=" + commentId + ", comment=" + comment + ", spot=" + spot + ", user=" + user + "]";
	}
	
	

}
