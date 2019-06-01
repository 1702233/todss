package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				
				int studentID = rs.getInt("studentid");
				int minigameID = rs.getInt("minigame");
				Date startTime = rs.getDate("starttime");
				Date endTime = rs.getDate("endtime");

				Minigame minigame = mDao.findByID(minigameID);
				Student student = sDao.findByID(studentID);

				Result newResult = new Result(startTime, endTime, student, minigame);

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
		return queryExecutor("SELECT * FROM RESULT WHERE STUDENTID = " + ID +";");
	}

	@Override
	public boolean saveResult(Result result) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO RESULT (STUDENTID, MINIGAME, STARTTIME, ENDTIME) VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, result.getStudent().getID());
			pstmt.setInt(2, result.getMinigame().getId());
			pstmt.setDate(3, result.getStart());
			pstmt.setDate(3, result.getEnd());

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
