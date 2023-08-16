package lauriiivarinen.spotmapback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lauriiivarinen.spotmapback.domain.Spot;
import lauriiivarinen.spotmapback.domain.SpotRepository;
import lauriiivarinen.spotmapback.domain.User;
import lauriiivarinen.spotmapback.domain.UserRepository;

@RestController
public class SpotController {
	
	@Autowired
	private SpotRepository spotRepo;
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/api/spots")
	public List<Spot> getSpots(){
		List<Spot> spots = (List<Spot>) spotRepo.findAll();
		//System.out.println(spots.size());
		return (List<Spot>) spotRepo.findAll();
	}
	
	@PostMapping("/api/spots")
	public Spot addSpot(@RequestBody Spot spot, Authentication auth) {
		System.out.println("Adding spot to user " + auth.getName());
		User user = userRepo.findByUsername(auth.getName());
		spot.setUser(user);
		return spotRepo.save(spot);
	}
	
	@PostMapping("/api/spots/like/{id}")
	public Spot likeSpot(@PathVariable ("id") Long id, Authentication auth) {
		User user = userRepo.findByUsername(auth.getName());
		Spot spot = spotRepo.findById(id).get();
		spot.like(user);
		user.addLike(spot);
		spotRepo.save(spot);
		userRepo.save(user);
		return spot;
	}

	@GetMapping("/api/spots/{id}")
	public Spot getSpot(@PathVariable ("id") Long id, Authentication auth) {
		Spot spot = spotRepo.findById(id).get();
		return spot;
	}
	
	@PostMapping("/api/spots/dislike/{id}")
	public Spot dislikeSpot(@PathVariable ("id") Long id, Authentication auth) {
		User user = userRepo.findByUsername(auth.getName());
		Spot spot = spotRepo.findById(id).get();
		spot.dislike(user);
		user.addDislike(spot);
		spotRepo.save(spot);
		userRepo.save(user);
		return spot;
	}
	
	@GetMapping("/api/spots/delete/{id}")
	public String deleteSpot(@PathVariable ("id") Long id, Authentication auth) {
		User user = userRepo.findByUsername(auth.getName());
		Spot spot = spotRepo.findById(id).get();
		if (spot.getUser().equals(user)) {
			System.out.println("deleting spot");
			spotRepo.delete(spot);
			return "success";
		}
		return "failed";
	}
	
	
}
