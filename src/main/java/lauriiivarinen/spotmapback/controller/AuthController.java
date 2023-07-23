package lauriiivarinen.spotmapback.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lauriiivarinen.spotmapback.service.TokenService;

@RestController
public class AuthController {
	
	private final TokenService tokenService;

	public AuthController(TokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@PostMapping("/api/token")
	public String token(Authentication auth) {
		String token = tokenService.generateToken(auth);
		return token;
	}

}
