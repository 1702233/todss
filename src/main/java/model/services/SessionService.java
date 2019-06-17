package model.services;

import java.util.ArrayList;

import model.Session;
import persistance.SessionDao;
import persistance.SessionPostgresDaoImpl;

public class SessionService {

	private SessionDao dao = new SessionPostgresDaoImpl();
	
	public ArrayList<Session> getSessionsByTeacher(String username){
		return dao.findByTeacher(username);
	}
	
	public boolean saveSession(Session session) {
		return dao.saveSession(session);
	}
	
}
