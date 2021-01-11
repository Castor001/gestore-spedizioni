package user;
/**
 * AbstractUser is the base class for users.
 * 
 * @author &#160; &#160; Castorini Francesco
 */
public abstract class AbstractUser {
	
	/**
	 * Method for implementing a user's login.
	 * @param username of user
	 * @param password of user
	 * @return true if login is successful, false otherwhise
	 */
	protected abstract boolean login(String username, String password);
}
