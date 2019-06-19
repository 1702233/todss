package persistance;

import java.util.ArrayList;

import model.Session;


public interface SessionDao {
	
	public ArrayList<Session> findAllSessionsForArrangement(int arrangementID);
	public ArrayList<Session> findByTeacher(String username);
	public Session findByID(int ID);
	public boolean saveSession(Session session);
	public boolean updateSession(Session session);
	public boolean deleteSession(String code);
	public boolean checkSessionExists(String code);
}
