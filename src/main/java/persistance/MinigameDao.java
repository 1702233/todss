package persistance;

import java.util.ArrayList;
import model.Minigame;

public interface MinigameDao {

	public ArrayList<Minigame> findAllMinigames();
	public ArrayList<Minigame> findByName(String name);
	public ArrayList<Minigame> findByTeacher(String teacher);
	public Minigame findByID(int ID);
	public boolean saveMinigame(Minigame minigame);
	public boolean updateMinigame(Minigame minigame);
	public boolean deleteMinigame(int ID);
}
