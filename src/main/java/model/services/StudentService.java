package model.services;

import model.Student;
import persistance.StudentDao;
import persistance.StudentPostgresDaoImpl;

public class StudentService {
	private StudentDao dao = new StudentPostgresDaoImpl();
	
	public int getMaxID() {
		return dao.findMaxStudentID();
	}
	
	public boolean saveStudent(Student s) {
		return dao.saveStudent(s);
	}

}
