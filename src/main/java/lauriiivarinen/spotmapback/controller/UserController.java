package lauriiivarinen.spotmapback.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lauriiivarinen.spotmapback.domain.NewUserForm;
import lauriiivarinen.spotmapback.domain.User;
import lauriiivarinen.spotmapback.domain.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/newuser")
	public String createUser(@RequestBody NewUserForm newUser) {
		byte[] decodedBytes = Base64.getDecoder().decode(newUser.getPasswordHash());
		String decodedPassword = new String(decodedBytes);
		try {
			if(userRepo.findByUsername(newUser.getUsername()) != null) {
				return "Username already exists";
			}else {
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				userRepo.findByUsername(newUser.getUsername());
				User user = new User(newUser.getUsername(), bc.encode(decodedPassword));
				userRepo.save(user);
				return "added";
			}
			
		}catch(Exception e){
			return "Username already exists";
		}
	}
	
	@GetMapping("api/user")
	public User getUser(Authentication auth) {
		try {
			return userRepo.findByUsername(auth.getName());
		}catch(Exception e) {
			User user = new User("user not found", "");
			return user;
		}
	}
}
