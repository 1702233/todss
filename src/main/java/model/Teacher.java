package model;

import java.util.ArrayList;

public class Teacher {
	private String username;
	private String password;
	private String role;

	public Teacher(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Teacher(String username) {
		this.username = username;
		this.password = null;
	}

	public Teacher(String username, String password, String role) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	public String getRole() {
		return role;
	}
}
