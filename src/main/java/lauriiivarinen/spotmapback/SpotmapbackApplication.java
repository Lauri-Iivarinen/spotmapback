package lauriiivarinen.spotmapback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lauriiivarinen.spotmapback.config.RsaKeyProperties;
import lauriiivarinen.spotmapback.domain.Spot;
import lauriiivarinen.spotmapback.domain.SpotRepository;
import lauriiivarinen.spotmapback.domain.User;
import lauriiivarinen.spotmapback.domain.UserRepository;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SpotmapbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotmapbackApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner launchTestBook (UserRepository userRepo, SpotRepository spotRepo) {
		return (args) ->{
			
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			 
			//----TEST DATA FOR h2 DATABASE, WILL BE REMOVED ON SUCCESFULL LAUNCH--
			/*
			//Test users
			User user1 = new User("lauri", bc.encode("lmaoxd"));
			User user2 = new User("lauri2", bc.encode("lmaoxd2"));
			
			Spot spot1 = new Spot("Kirjasto", "url", "kirjaston portaat", 16.245, 14.267, user1);
			Spot spot2 = new Spot("Alepa", "url", "Alepan Seinä", 25.245, 78.267, user1);
			
			userRepo.save(user1);
			userRepo.save(user2);
			
			spotRepo.save(spot1);
			spotRepo.save(spot2);
			*/
		};
	}


}
