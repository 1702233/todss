package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Minigame;
import model.Picture;
import model.Result;
import model.Teacher;

public class ResultPostgresDaoImpl extends PostgresBaseDao implements ResultDao{
	
	MinigamePostgresDaoImpl mDao = new MinigamePostgresDaoImpl();
	
	public ArrayList<Result> queryExecutor(String query) {
		ArrayList<Result> results = new ArrayList<Result>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				
				String studentName = rs.getString("studentname");
				int minigameID = rs.getInt("minigame");
				Date starttime = rs.getDate("starttime");
				Date endTime = rs.getDate("endtime");

				Minigame minigame = mDao.findByID(minigameID);

				Picture newPicture = new Picture(ID, url, teacher);

				results.add(newPicture);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}

	@Override
	public ArrayList<Minigame> findByMinigame(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveMinigame(Minigame minigame) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMinigame(Minigame minigame) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMinigame(int ID) {
		// TODO Auto-generated method stub
		return false;
	}

}
