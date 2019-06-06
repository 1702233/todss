package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CardAssignment;
import model.CardRule;
import model.Minigame;

public class CardRulePostgresDaoImpl extends PostgresBaseDao implements CardRuleDao {

	CardAssignmentPostgresDaoImpl cDao = new CardAssignmentPostgresDaoImpl();

	public ArrayList<CardRule> queryExecutor(String query) {
		ArrayList<CardRule> results = new ArrayList<CardRule>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // zolang er meer in de ResultSet zit maak een Taakobject van de info en voeg de
								// aan de lijst results toe
				int ID = rs.getInt("ID");
				String type = rs.getString("type");
				boolean isDraggable = rs.getBoolean("isDragable");
				String group = rs.getString("group");
				int minigameID = rs.getInt("minigameid");
				
				ArrayList<CardAssignment> CardAssignments = cDao.findByCardRuleID(ID);
				
				CardRule newCardRule = new CardRule(ID, type, isDraggable, group, CardAssignments);

				results.add(newCardRule);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}

	@Override
	public ArrayList<CardRule> findAllCardRules() {
		return queryExecutor("SELECT * FROM CARDRULE;");
	}

	@Override
	public CardRule findByID(int ID) {
		return queryExecutor("SELECT * FROM CARDRULE WHERE \"ID\" = '" + ID + "';").get(0);
	}

	@Override
	public ArrayList<CardRule> findByMinigame(int minigameID) {
		return queryExecutor("SELECT * FROM CARDRULE WHERE \"minigameID\" = '" + minigameID + "';");
	}

	@Override
	public boolean saveCardRules(CardRule cardRule, int minigameID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO CARDRULE (TYPE, ISDRAGGABLE, GROUP, MINIGAMEID) VALUES (?, ?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, cardRule.getType());
			pstmt.setBoolean(2, cardRule.isDraggable());
			pstmt.setString(3, cardRule.getGroup());
			pstmt.setInt(4, minigameID);

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
	public boolean updateCardRule(CardRule cardRule, int minigameID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "UPDATE CARDRULE SET \"TYPE\"= ?, \"ISDRAGGABLE\"= ?, \"GROUP\" = ?, \"MINIGAMEID\"= ? WHERE \"ID\"= ?;";
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, cardRule.getType());
			pstmt.setBoolean(2, cardRule.isDraggable());
			pstmt.setString(3, cardRule.getGroup());
			pstmt.setInt(4, minigameID);
			pstmt.setInt(5, cardRule.getID());

			queryResult = pstmt.executeUpdate();
		} catch (SQLException sqe) {
			System.out.println(sqe.getMessage());
		}

		if (queryResult > 0) { // als queryResult hoger dan 0 is is het bewerken gelukt (true), anders niet
								// (false)
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteCardRule(int ID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM CARDRULE WHERE \"ID\" = ?;";
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
