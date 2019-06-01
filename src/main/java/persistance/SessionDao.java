package persistance;

import java.util.ArrayList;

import model.Session;


public interface SessionDao {
	
	public ArrayList<Session> findAllSessionsForArrangement(int arrangementID);
	public Session findByID(int ID);
	public boolean saveSession(Session session);
	public boolean updateSession(Session session);
	public boolean deleteSession(int ID);
}
