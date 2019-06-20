package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Arrangement;
import model.Session;
import model.Student;

public class SessionPostgresDaoImpl extends PostgresBaseDao implements SessionDao {

	
	StudentPostgresDaoImpl sDao = new StudentPostgresDaoImpl();
	ArrangementPostgresDaoImpl aDao = new ArrangementPostgresDaoImpl();
	
	public ArrayList<Session> queryExecutor(String query){
		ArrayList<Session> results = new ArrayList<Session>();

		try (Connection con = super.getConnection()) {
			System.out.println(1);
			PreparedStatement pstmt = con.prepareStatement(query);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(2);
			while (rs.next()) { 
				
				String code = rs.getString("code");
				String opmerking = rs.getString("opmerking");
				int arrangementID = rs.getInt("arrangementID");
				
				System.out.println(3);
				ArrayList<Student> allStudents = sDao.findStudentsBySession(code);
				
				System.out.println(4);
				Arrangement arrangement = aDao.findById(arrangementID);
				System.out.println(5);

				Session newSession = new Session(code, opmerking, arrangement, allStudents);
				
				System.out.println(6);
				results.add(newSession);

			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst 
	}
	
	@Override
	public Session findByID(int ID) {
		return queryExecutor("SELECT * FROM SESSION WHERE \"\" = " + ID + ";").get(0);
	}
	
	@Override
	public ArrayList<Session> findAllSessionsForArrangement(int arrangementID) {
		return queryExecutor("SELECT * FROM SESSION WHERE \"arrangementID\" = " + arrangementID + ";");
	}
	
	public ArrayList<Session> findByTeacher(String username){
		ArrangementPostgresDaoImpl aDao = new ArrangementPostgresDaoImpl();
		ArrayList<Arrangement> allArrangements = aDao.findByTeacher(username);
		ArrayList<Session> results = new ArrayList<Session>();
		for (Arrangement arrangement : allArrangements) {
			results.addAll(findAllSessionsForArrangement(arrangement.getID()));
		}
		return results;
	}

	@Override
	public boolean saveSession(Session session) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO SESSION (CODE, opmerking, \"arrangementID\") VALUES (?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, session.getCode());
			pstmt.setString(2, session.getOpmerking());
			pstmt.setInt(3, session.getArrangement().getID());
			
			queryResult = pstmt.executeUpdate();
		} catch (SQLException sqe) {
			System.out.println(sqe.getMessage());
		}

		if (queryResult > 0) { // als queryResult hoger dan 0 is is het opslaan gelukt (true), anders niet
								// (false)
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateSession(Session session) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "UPDATE SESSION SET \"opmerking\" = ?, \"ARRANGEMENTID\" = ? WHERE CODE = ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, session.getOpmerking());
			pstmt.setInt(2, session.getArrangement().getID());
			pstmt.setString(3, session.getCode());
			
			queryResult = pstmt.executeUpdate();
		} catch (SQLException sqe) {
			System.out.println(sqe.getMessage());
		}

		if (queryResult > 0) { // als queryResult hoger dan 0 is is het opslaan gelukt (true), anders niet
								// (false)
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteSession(String code) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM SESSION WHERE CODE = ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);

			queryResult = pstmt.executeUpdate();
		} catch (SQLException sqe) {
			System.out.println(sqe.getMessage());
		}

		if (queryResult > 0) { // als queryResult hoger dan 0 is is het verwijderen gelukt (true), anders niet
								// (false)
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkSessionExists(String code) {
		List<Session> sessions  = queryExecutor("SELECT * FROM session WHERE code = '" + code + "';");
		if (sessions.isEmpty()) { 
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Session findByCode(String code) {
		System.out.println("findbycode");
		List<Session> sessions  = queryExecutor("SELECT * FROM session WHERE code = '" + code + "';");
		System.out.println(sessions);
		if (sessions.isEmpty()) { 
			return null;
		} else {
			return sessions.get(0);
		}
	}
	
	



}
