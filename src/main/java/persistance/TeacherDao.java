package persistance;

import java.util.ArrayList;

import model.Teacher;

public interface TeacherDao {

	public ArrayList<Teacher> findAllTeachers();
	public Teacher findByUsername(String username);
	public boolean saveTeacher(Teacher teacher);
	public boolean updateTeacher(Teacher teacher);
	public boolean deleteTeacher(int ID);
}
