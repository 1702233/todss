package model;

import java.util.ArrayList;

public class Teacher {
	private String username;
	private String password;

	public Teacher(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Teacher(String username) {
		this.username = username;
		this.password = null;
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
}
