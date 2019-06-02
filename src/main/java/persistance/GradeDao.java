package persistance;

import java.util.ArrayList;

import model.Grade;

public interface GradeDao {

	public ArrayList<Grade> findByMinigame(int ID);
	public boolean saveGrade(Grade grade);
	public boolean deleteGrade(int ID); 
}
