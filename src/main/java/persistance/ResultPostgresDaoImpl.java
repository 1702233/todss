package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Minigame;
import model.Result;
import model.Student;


public class ResultPostgresDaoImpl extends PostgresBaseDao implements ResultDao{
	
	MinigamePostgresDaoImpl mDao = new MinigamePostgresDaoImpl();
	StudentPostgresDaoImpl sDao = new StudentPostgresDaoImpl();
	
	public ArrayList<Result> queryExecutor(String query) {
		ArrayList<Result> results = new ArrayList<Result>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				
				int studentID = rs.getInt("studentID");
				int minigameID = rs.getInt("minigameID");
				Timestamp startTime = rs.getTimestamp("starttime");
				Timestamp endTime = rs.getTimestamp("endtime");

				Minigame minigame = mDao.findByID(minigameID);
				//Student student = sDao.findByID(studentID);

				
				Result newResult = new Result(startTime, endTime, minigame);

				results.add(newResult);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}

	@Override
	public ArrayList<Result> findByMinigame(int ID) {
		return queryExecutor("SELECT * FROM RESULT WHERE MINIGAMEID = " + ID +";");
	}
	
	@Override
	public ArrayList<Result> findByStudent(int ID) {
		return queryExecutor("SELECT * FROM RESULT WHERE \"studentID\" = " + ID +";");
	}

	@Override
	public boolean saveResult(Result result, int minigameID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO RESULT (STUDENTID, MINIGAME, STARTTIME, ENDTIME) VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, result.getStudent().getID());
			pstmt.setInt(2, minigameID);
			pstmt.setTimestamp(3, result.getStart());
			pstmt.setTimestamp(3, result.getEnd());

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



}
