package model.services;

import model.Arrangement;
import model.Minigame;
import persistance.ArrangementDao;
import persistance.ArrangementPostgresDaoImpl;
import persistance.MinigameDao;
import persistance.MinigamePostgresDaoImpl;

import java.util.List;

public class ArrangementService {
	private ArrangementDao dao = new ArrangementPostgresDaoImpl();
	
	public Arrangement getArrangementByID(int ID) {
		return dao.findById(ID);
	}

	public List<Arrangement> getAllArrangements(){
		return dao.findAllArrangements();
	}

	public List<Arrangement> getArrangementsByTeacher(String teacherName){
		return dao.findByTeacher(teacherName);
	}
	
	public boolean saveArrangement(Arrangement arrangement) {
		return dao.saveArrangement(arrangement);
	}
	
	public int getArrangementID(String name, String description, String teacherName) {
		return dao.getArrangementID(name, description, teacherName);
	}

}
