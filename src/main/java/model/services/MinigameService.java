package model.services;

import java.util.List;

import model.Minigame;
import persistance.MinigameDao;
import persistance.MinigamePostgresDaoImpl;

public class MinigameService {
	private MinigameDao dao = new MinigamePostgresDaoImpl();
	
	public Minigame getMinigameByID(int ID) {
		return dao.findByID(ID);
	}
	
	public List<Minigame> getAllMinigames(){
		return dao.findAllMinigames();
	}
	
	public boolean saveMinigame(Minigame m) {
		return dao.saveMinigame(m);
	}
}
