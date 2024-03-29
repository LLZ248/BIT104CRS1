package Assignment1;

import java.util.List;
import java.util.Optional;

/**
 * @author Lee Lin Zheng B1802130<br>
 * represents the user
 * parent class for volunteer and staff
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
		setUsername(username.toUpperCase());
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
         * abstarct method to view the trips
         * @param tripList the list of trips to be shown
         * @return the result in string
         */
        public abstract String viewTrips(List<Trip> tripList);

	@Override
	public String toString() {
		return  "\nUsername     : " + getUsername() + 
                        "\nPassword     : " + getPassword() + 
                        "\nName         : " + getName() + 
                        "\nPhone        : " + getPhone();
	}	
}

