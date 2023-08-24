package lauriiivarinen.spotmapback;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lauriiivarinen.spotmapback.domain.Spot;
import lauriiivarinen.spotmapback.domain.User;


class SpotmapbackApplicationTests {
	
	@Test
	void spotAddLikesTests() {
		User user1 = new User("usernam1", "pwhash");
		User user2 = new User("username2", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		assertEquals(spot.getLikes(), 0);
		spot.like(user1);
		assertEquals(spot.getLikes(), 1);
		spot.like(user2);
		assertEquals(spot.getLikes(), 2);
	}

	@Test
	void spotRemoveLikeTests(){
		User user1 = new User("usernam1", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		spot.like(user1);
		spot.like(user1);
		assertEquals(spot.getLikes(), 0);
	}
	
	@Test
	void spotAddDislikesTests() {
		User user1 = new User("usernam1", "pwhash");
		User user2 = new User("username2", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		assertEquals(spot.getDislikes(), 0);
		spot.dislike(user1);
		assertEquals(spot.getDislikes(), 1);
		spot.dislike(user2);
		assertEquals(spot.getDislikes(), 2);
	}

	@Test
	void spotRemoveDislikeTests(){
		User user1 = new User("usernam1", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		spot.dislike(user1);
		spot.dislike(user1);
		assertEquals(spot.getDislikes(), 0);
	}
	
	@Test
	void userLikeSpotTest() {
		User user1 = new User("usernam1", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		assertFalse(user1.getLikes().contains(spot));
		user1.addLike(spot);
		assertTrue(user1.getLikes().contains(spot));
	}
	
	@Test
	void userRemoveLikeTest() {
		User user1 = new User("usernam1", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		
		user1.addLike(spot);
		user1.addLike(spot);
		assertFalse(user1.getLikes().contains(spot));
	}
	
	@Test
	void userDislikeSpotTest() {
		User user1 = new User("usernam1", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		assertFalse(user1.getDislikes().contains(spot));
		user1.addDislike(spot);
		assertTrue(user1.getDislikes().contains(spot));
	}
	
	@Test
	void userRemoveDislikeTest() {
		User user1 = new User("usernam1", "pwhash");
		Spot spot = new Spot("name", "url", "description", 15.2, 10.4, user1);
		
		user1.addDislike(spot);
		user1.addDislike(spot);
		assertFalse(user1.getDislikes().contains(spot));
	}
}
