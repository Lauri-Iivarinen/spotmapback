package lauriiivarinen.spotmapback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

	@GetMapping("/api/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/api/test2")
	public String test2() {
		return "test2";
	}
	
}
