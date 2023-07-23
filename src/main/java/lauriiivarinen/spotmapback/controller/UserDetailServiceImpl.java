package lauriiivarinen.spotmapback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lauriiivarinen.spotmapback.domain.User;
import lauriiivarinen.spotmapback.domain.UserRepository;



//SPRING MAGIC FOR CHECKING USER AUTHENTICATION
@Service
public class UserDetailServiceImpl implements UserDetailsService{

	private final UserRepository repository;

	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepo) {
		this.repository = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		User currentUser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentUser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(currentUser.getRole()));
		
		return user;
	}
}