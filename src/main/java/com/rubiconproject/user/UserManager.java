package com.rubiconproject.user;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import com.rubiconproject.utils.UserUtils;

/**
 * A singleton User Manager that creates a sample set of users and manage users
 * 
 * @author jenniec
 *
 */
public class UserManager {
	private static UserManager $instance;
	private static final CopyOnWriteArrayList<User> userList = new CopyOnWriteArrayList<User>();
	private static final AtomicLong counter = new AtomicLong(100);

	static {
		User user = null;
		user = new User(counter.getAndIncrement(), 
				"Jennie", "Chau", "Female", 
				"Chau.Jennie@yahoo.com", "Los Angeles", 
				"CA", "01-01-1999");
		userList.add (user);

		user = new User(counter.getAndIncrement(), 
				"Joe", "Doe", "Male", 
				"Joe.Doe@yahoo.com", "Scotsdale", 
				"AZ", "12-30-1974");
		userList.add (user);
		
		user = new User(counter.getAndIncrement(), 
				"Daisy", "Bright", "Female", 
				"Daisy.Bright@yahoo.com", "Los Angeles", 
				"TX", "10-01-1974");
		userList.add (user);

		user = new User(counter.getAndIncrement(), 
				"Flower", "Bright", "Female", 
				"Flower.Bright@yahoo.com", "Rancho Cucamonga", 
				"CA", "10-01-1974");
		userList.add (user);
	}

	/**
	 * @return singleton of userManager
	 */
	public static UserManager getInstance() {
		if ( null == $instance ) {
			$instance = new UserManager();
		}
		return $instance;
	}
	
	/**
	 * @return a list of users
	 */
	public CopyOnWriteArrayList<User> getUserList(){
		return userList;
	}
	
	/**
	 * @param user
	 * @return true if this user is valid user
	 * Otherwise, return false
	 */
	public boolean validateUser(@NotNull User user) {
		return (UserUtils.hasRequiredFields(user));
	}
	
	/**
	 * @param user
	 * @return true if this user exists
	 * Otherwise, return false
	 */
	public boolean isExistentUser(@NotNull User user) {
		for (User eachUser: userList) {
			if (eachUser.getId() == user.getId()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param updatedUser
	 * Update only if updatedUser exists
	 */
	public void updateUser(@NotNull User updatedUser) {
		for (User user: userList) {
			if (user.getId() == updatedUser.getId()) {
					user.setFirstName((null != updatedUser.getFirstName()) ? updatedUser.getFirstName(): user.getFirstName());
					user.setLastName((null != updatedUser.getLastName()) ? updatedUser.getLastName(): user.getLastName());
					user.setGender((null != updatedUser.getGender()) ? updatedUser.getGender(): user.getGender());
					user.setEmail((null != updatedUser.getEmail()) ? updatedUser.getEmail(): user.getEmail());
					user.setCity((null != updatedUser.getCity()) ? updatedUser.getCity(): user.getCity());
					user.setState((null != updatedUser.getState()) ? updatedUser.getState(): user.getState());
					user.setBirthday((null != updatedUser.getBirthday()) ? updatedUser.getBirthday(): user.getBirthday());
			}
		}	
	}
	
	/**
	 * @param newUser
	 * @return newUser ID, which is auto generated to prevent duplicate
	 */
	public long createUser(@NotNull User newUser) {
		long id = counter.getAndIncrement();
		userList.add ( new User(id, 
				newUser.getFirstName(), newUser.getLastName(), newUser.getGender(), 
				newUser.getEmail(), newUser.getCity(), newUser.getState(), newUser.getBirthday()));
		return id;
		
	}
	
	/**
	 * @param user
	 * Delete user if exists
	 */
	public void deleteUser(User user) {
		userList.remove(user);
		
	}
	/**
	 * @param id
	 * @return User if found
	 */
	public User lookupUserById(Long id) {
		for(User user: userList) {
			if (id == user.getId()) {
				return user;
			}
		}
		return null;
	}		
}