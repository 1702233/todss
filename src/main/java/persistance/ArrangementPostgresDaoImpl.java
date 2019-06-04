package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Arrangement;
import model.Cardset;
import model.Minigame;
import model.Teacher;

public class ArrangementPostgresDaoImpl extends PostgresBaseDao implements ArrangementDao {

	public ArrayList<Arrangement> queryExecutor(String query) {
		ArrayList<Arrangement> results = new ArrayList<Arrangement>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			MinigamePostgresDaoImpl minigameDao = new MinigamePostgresDaoImpl();
			TeacherPostgresDaoImpl teacherDao = new TeacherPostgresDaoImpl();

			while (rs.next()) {
				int arrangementID = rs.getInt("arrangementID");
				String arrangementName = rs.getString("arrangementName");
				String arrangementDescription = rs.getString("arrangementDescription");
				String arrangementTeacherName = rs.getString("arrangementTeacherName");

				ArrayList<Minigame> allArrangementMinigames = minigameDao.findByArrangementID(arrangementID);

				Teacher teacher = teacherDao.findByUsername(arrangementTeacherName);

				Arrangement arrangement = new Arrangement(arrangementID, arrangementName, arrangementDescription, allArrangementMinigames, teacher);

				results.add(arrangement);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}

	@Override
	public ArrayList<Arrangement> findAllArrangements() {
		return queryExecutor(
				"select " +
				"a.'ID' as arrangementID, " +
				"a.name as arrangementName, " +
				"a.description as arrangementDescription, " +
				"a.'teacherName' as arrangementTeachername, " +
				"m.'ID' as minigameID, " +
				"m.name as minigameName," +
				"m.description as minigameDescription," +
				"m.'cardsOpened', " +
				"c.'ID' as cardsetID, " +
				"c.name as cardsetName, " +
				"tm.username as minigameTeacherUsername," +
				"ta.username as arrangementTeacherUsername," +
				"tc.username as cardsetTeacherUsername," +
				"from arrangement a " +
				"left join arrangementminigame am " +
				"on a.'ID' = am.'arrangementID' " +
				"left join minigame m " +
				"on am.'minigameID' = m.'ID'" +
				"left join cardset c " +
				"on m.'cardsetID' = c.'ID'" +
				"left join teacher ta" +
				"on a.'teacherName' = ta.username" +
				"left join teacher tm" +
				"on a.'teacherName' = tm.username" +
				"left join teacher tc" +
				"on a.'teacherName' = tc.username"
		);
	}

	@Override
	public ArrayList<Arrangement> findByTeacher(int teacherUsername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arrangement findById(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveArrangement(Arrangement arrangement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArrangement(Arrangement arrangement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteArrangement(int ID) {
		// TODO Auto-generated method stub
		return false;
	}
}
