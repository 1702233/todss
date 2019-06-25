package model.services;

import java.util.List;

import model.Arrangement;
import model.Teacher;
import persistance.TeacherDao;
import persistance.TeacherPostgresDaoImpl;

public class AccountService {

	private TeacherDao dao = new TeacherPostgresDaoImpl();
	
	public List<Teacher> getAllTeachers(){
		return dao.findAllTeachers();
	}
	
	public String findRoleForUser(String username, String password) {
		return dao.findRoleForUser(username, password);
	}

	public String findRoleForUserByUsername(String username) {
		return dao.findRoleForUserByUsername(username);
	}
	
	public boolean saveAccount(Teacher teacher) {
		return dao.saveTeacher(teacher);
	}
}
