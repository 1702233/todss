package persistance;

import java.util.ArrayList;

import model.Arrangement;



public interface ArrangementDao {

	public ArrayList<Arrangement> findAllArrangements();
	public ArrayList<Arrangement> findByTeacher(String teacherName);
	public Arrangement findById(int ID);
	public int getArrangementID(String name, String description, String teacherName);
	public boolean saveArrangement(Arrangement arrangement);
	public boolean updateArrangement(Arrangement arrangement);
	public boolean deleteArrangement(int ID);
	
}
