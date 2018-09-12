package com.rubiconproject.user;

/**
 * This is User POJO class
 * 
 * @author jenniec
 *
 */
public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String city;
	private String state;
	private  String birthday;
	public User() {}
	public User(long id, String firstName, String lastName, String gender,
			String email, String city, String state, String birthday){

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.city = city;
		this.state = state;
		this.birthday = birthday;
	}

	public long getId(){
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail(){
		return this.email;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	} 

	public String getBirthday(){
		return this.birthday;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


}