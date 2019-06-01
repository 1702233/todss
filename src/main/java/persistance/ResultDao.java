package persistance;

import java.util.ArrayList;

import model.Result;

public interface ResultDao {

	public ArrayList<Result> findByMinigame(int ID);
	public ArrayList<Result> findByStudent (int ID);
	public boolean saveResult(Result result);
}
