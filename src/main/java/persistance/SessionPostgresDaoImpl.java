package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Arrangement;
import model.Session;
import model.Student;

public class SessionPostgresDaoImpl extends PostgresBaseDao implements SessionDao {

	
	StudentPostgresDaoImpl sDao = new StudentPostgresDaoImpl();
	ArrangementPostgresDaoImpl aDao = new ArrangementPostgresDaoImpl();
	
	public ArrayList<Session> queryExecutor(String query){
		ArrayList<Session> results = new ArrayList<Session>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				
				String code = rs.getString("code");
				Date startDate = rs.getDate("startdate");
				Date endDate = rs.getDate("enddate");
				int arrangementID = rs.getInt("arrangementID");
				
				ArrayList<Student> allStudents = sDao.findStudentsBySession(code);
				Arrangement arrangement = aDao.findById(arrangementID);

				Session newSession = new Session(code, startDate, endDate, allStudents, arrangement);

				results.add(newSession);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst 
	}
	
	@Override
	public Session findByID(int ID) {
		return queryExecutor("SELECT * FROM SESSION WHERE ID = " + ID + ";").get(0);
	}
	
	@Override
	public ArrayList<Session> findAllSessionsForArrangement(int arrangementID) {
		return queryExecutor("SELECT * FROM SESSION WHERE ARRANGEMENTID = " + arrangementID + ";");
	}

	@Override
	public boolean saveSession(Session session) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO SESSION (CODE, STARTDATE, ENDDATE, ARRANGEMENTID) VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, session.getCode());
			pstmt.setDate(2, session.getStartDate());
			pstmt.setDate(3, session.getEndDate());
			pstmt.setInt(4, session.getArrangement().getID());
			
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
			String query = "UPDATE SESSION SET \"STARTDATE\" = ?, \"ENDDATE\" = ?, \"ARRANGEMENTID\" = ? WHERE CODE = ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setDate(1, session.getStartDate());
			pstmt.setDate(2, session.getEndDate());
			pstmt.setInt(3, session.getArrangement().getID());
			pstmt.setString(4, session.getCode());
			
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
	public boolean deleteSession(int ID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM SESSION WHERE \"CODE\" = ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ID);

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



}
