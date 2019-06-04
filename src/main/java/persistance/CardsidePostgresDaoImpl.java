package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cardside;
import model.Picture;

public class CardsidePostgresDaoImpl extends PostgresBaseDao implements CardsideDao{

	PicturePostgresDaoImpl pDao = new PicturePostgresDaoImpl();
	
	public ArrayList<Cardside> queryExecutor(String query){
		ArrayList<Cardside> results = new ArrayList<Cardside>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				
				int cardsideID = rs.getInt("ID");
				String tekst = rs.getString("tekst");
				int pictureID = rs.getInt("pictureid");
				Picture picture = pDao.findById(pictureID);
				Cardside newCardside = new Cardside(cardsideID, tekst, picture);
				results.add(newCardside);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}
	
	@Override
	public ArrayList<Cardside> findAllCardsides() {
		return queryExecutor("SELECT * FROM CARDSIDE");
	}
	
	@Override
	public Cardside findByID(int ID) {
		return queryExecutor("SELECT * FROM CARDSIDE WHERE \"ID\" = '" + ID +"';").get(0);
	}

	@Override
	public boolean saveCardside(Cardside cardside) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO CARDSIDE (ID, TEKST, PICTUREID) VALUES (?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cardside.getID());
			pstmt.setString(2, cardside.getTekst());
			pstmt.setInt(3, cardside.getPicture().getID());

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
	public boolean updateCardside(Cardside cardside) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "UPDATE CARDSIDE SET \"TEKST\"= ?, \"PICUTRE\"= ? WHERE \"ID\"= ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, cardside.getTekst());
			pstmt.setInt(2, cardside.getPicture().getID());
			pstmt.setInt(3, cardside.getID());

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
	public boolean deleteCardside(int ID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM CARDSIDE WHERE \"ID\" = ?;";
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
