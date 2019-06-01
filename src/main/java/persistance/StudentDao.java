package persistance;

import java.util.ArrayList;

import model.Student;



public interface StudentDao {

	public Student findByID(int ID);
	public ArrayList<Student> findStudentsBySession(String sessionCode);
	public boolean saveStudent(Student student);

}
