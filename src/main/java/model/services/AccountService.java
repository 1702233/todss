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
	
	public boolean findUser(String username, String password) {
		return dao.checkTeacher(username, password);
	}
	
	public boolean saveAccount(Teacher teacher) {
		return dao.saveTeacher(teacher);
	}
}