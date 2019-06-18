package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CardRule;
import model.Cardset;
import model.Minigame;
import model.Teacher;

public class MinigamePostgresDaoImpl extends PostgresBaseDao implements MinigameDao {

	private TeacherPostgresDaoImpl tDao = new TeacherPostgresDaoImpl();
	private CardsetPostgresDaoImpl csDao = new CardsetPostgresDaoImpl();
	private CardRulePostgresDaoImpl crDao = new CardRulePostgresDaoImpl();

	private ArrayList<Minigame> queryExecutor(String query) {
		ArrayList<Minigame> results = new ArrayList<Minigame>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // zolang er meer in de ResultSet zit maak een Taakobject van de info en voeg de
								// aan de lijst results toe
				String omschrijving;
				int minigameID = rs.getInt("ID");
				String name = rs.getString("name");
				String type = rs.getString("type");
				boolean cardsOpened = rs.getBoolean("cardsOpened");
				try {
					omschrijving = rs.getString("omschrijving");
				} catch(Exception e){
					System.out.println(e);
					omschrijving = "";
				}
				
				String teacherName = rs.getString("teachername");
				Teacher teacher = tDao.findByUsername(teacherName);
				
				int cardsetID = rs.getInt("cardsetID");				
				Cardset cardset = csDao.findByID(cardsetID);
				
				ArrayList<CardRule> cardrules = crDao.findByMinigame(minigameID);

				Minigame newMinigame = new Minigame(minigameID, name, type, cardsOpened, omschrijving, teacher, cardset, cardrules);



				results.add(newMinigame);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}

	@Override
	public ArrayList<Minigame> findAllMinigames() {
		return queryExecutor("SELECT * FROM MINIGAME");
	}

	@Override
	public Minigame findByID(int ID) {
		return queryExecutor("SELECT * FROM MINIGAME WHERE \"ID\" = " + ID + ";").get(0);
	}

	@Override
	public ArrayList<Minigame> findByArrangementID(int ID) {
		return queryExecutor(
				"select *" +
				"from minigame m " +
				"left join arrangementminigame am on m.\"ID\" = am.\"minigameID\"" +
				"where am.\"arrangementID\" = " + ID + ";"
		);
	}

	@Override
	public ArrayList<Minigame> findByName(String name) {
		return queryExecutor("SELECT * FROM MINIGAME WHERE NAME = " + name + ";");
	}
	
	@Override
	public ArrayList<Minigame> findByTeacher(String teacher) {
		return queryExecutor("SELECT * FROM MINIGAME WHERE \"teacherName\" = '" + teacher + "';");
	}

	@Override
	public boolean saveMinigame(Minigame minigame) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO public.minigame(\n" + 
					"	name, \"cardsOpened\", description, \"teacherName\", \"cardsetID\", \"type\")\n" + 
					"	VALUES (?, ?, ?, ?, ?, ?);";


			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, minigame.getName());
			pstmt.setBoolean(2, minigame.isCardsOpened());
			pstmt.setString(3, minigame.getOmschrijving());
			pstmt.setString(4, minigame.getTeacher().getUsername());
			pstmt.setInt(5, minigame.getCardset().getId());
			pstmt.setString(6, minigame.getType());

			System.out.println(pstmt);
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
	public boolean updateMinigame(Minigame minigame) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "UPDATE MINIGAME SET 'NAME'= ?, 'CARDSOPENED'= ?, 'OMSCHRIJVING'= ?, 'TEACHERNAME'= ?, 'CARDSET'= ? WHERE 'ID'= ?;"; // bewerk
																																							// een
																																							// afgeronde
																																							// taak
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, minigame.getName());
			pstmt.setBoolean(2, minigame.isCardsOpened());
			pstmt.setString(3, minigame.getOmschrijving());
			pstmt.setString(4, minigame.getTeacher().getUsername());
			pstmt.setInt(5, minigame.getCardset().getId());
			pstmt.setInt(6, minigame.getId());

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
	public boolean deleteMinigame(int ID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM MINIGAME WHERE 'ID' = ?;"; // verwijder een afgeronde taak
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
