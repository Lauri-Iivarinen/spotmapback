package lauriiivarinen.spotmapback.domain;

public class NewUserForm {
	private String username;
	private String passwordHash;
	
	public NewUserForm(String username, String passwordHash) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	

}
