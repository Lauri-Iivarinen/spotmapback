package lauriiivarinen.spotmapback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lauriiivarinen.spotmapback.domain.Comment;
import lauriiivarinen.spotmapback.domain.CommentRepository;
import lauriiivarinen.spotmapback.domain.Spot;
import lauriiivarinen.spotmapback.domain.SpotRepository;
import lauriiivarinen.spotmapback.domain.User;
import lauriiivarinen.spotmapback.domain.UserRepository;

@RestController
public class CommentController {
	
	@Autowired
	private SpotRepository spotRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@PostMapping("/api/comments")
	public String addComment(@RequestBody Comment comment, Authentication auth) {
		User user = userRepo.findByUsername(auth.getName());
		comment.setUser(user);
		commentRepo.save(comment);
		return "idk";
	}
	
	@GetMapping("/api/comments")
	public List<Comment> getComments(){
		return (List<Comment>) commentRepo.findAll();
	}
}
