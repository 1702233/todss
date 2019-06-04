package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Grade;
import model.Minigame;

public class GradePostgresDaoImpl extends PostgresBaseDao implements GradeDao {

	MinigamePostgresDaoImpl mDao = new MinigamePostgresDaoImpl();
	
	public ArrayList<Grade> queryExecutor(String query){
		ArrayList<Grade> results = new ArrayList<Grade>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("id");
				int grade = rs.getInt("grade");
				int minigameID = rs.getInt("minigameid");
				
				Minigame minigame = mDao.findByID(minigameID);

				Grade newGrade = new Grade(ID, grade);

				results.add(newGrade);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}
	
	@Override
	public ArrayList<Grade> findByMinigame(int ID) {
		return queryExecutor("SELECT * FROM GRADE WHERE MINIGAMEID = " + ID + ";");
	}

	@Override
	public boolean saveGrade(Grade grade, int minigameID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO GRADE (GRADE, MINIGAMEID) VALUES (?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, grade.getGrade());
			pstmt.setInt(2, minigameID);

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
	public boolean deleteGrade(int ID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM GRADE WHERE \"ID\" = ?;";
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
