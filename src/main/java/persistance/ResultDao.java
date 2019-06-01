package persistance;

import java.util.ArrayList;

import model.Minigame;

public interface ResultDao {

	public ArrayList<Minigame> findByMinigame(int ID);
	public boolean saveMinigame(Minigame minigame);
	public boolean updateMinigame(Minigame minigame);
	public boolean deleteMinigame(int ID);
}
