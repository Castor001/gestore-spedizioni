package user;
/**
 * Class that implement the administrator.
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see AbstractUser
 */
public class Admin extends AbstractUser{
	/**
	 * Constant to define the administrator username.
	 */
	private static final String USER = "admin";
	
	/**
	 * Constant to define the administrator password.
	 */
	private static final String PASS = "admin";
	
	/**
	 * 
	 */
	public Admin() {
	}
	
	@Override
	public boolean login(String username, String password) {
		
		if (username.trim().equals(USER) && password.trim().equals(PASS))
			return true;
		return false;
	}
	
	
}
