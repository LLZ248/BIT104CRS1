package Assignment1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.*;

/**
 * @author LLZ
 *
 */
public abstract class User {
	private String username;
	private String password;
	private String name;
	private String phone;
	
	/**
	 * @param username
         * A String object represents the name of the user
	 * @param password
         * A String object represents the password of the user
	 * @param name
         * A String object represents the name of the user
	 * @param phone
         * A String object represents the phone of the user
	 */
	public User(String username, String password,
			String name, String phone) {
		setUsername(username);
		setPassword(password);
		setName(name);
		setPhone(phone);
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return Optional.ofNullable(username)
                        .orElseGet(()->"No Username");
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Optional.ofNullable(password)
                        .orElseGet(()->"No Password");
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Optional.ofNullable(name)
                        .orElseGet(()->"No Name");
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return Optional.ofNullable(phone)
                        .orElseGet(()->"No Phone");
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
        /**
	 * @param trips the phone to set
         * @return result 
         * A String object
	 */
	public String viewTrips(List<Trip> trips){
		String result = Optional.ofNullable(trips.stream().
                        map(Object::toString).
                        collect(Collectors.joining("\n"))).get();
                
                result = result.isBlank()? "\nNo Trip is available":result;
                return result;
	}

	@Override
	public String toString() {
		return  "\nUsername     : " + getUsername() + 
                        "\nPassword     : " + getPassword() + 
                        "\nName         : " + getName() + 
                        "\nPhone        : " + getPhone();
	}
	
	
	
}

