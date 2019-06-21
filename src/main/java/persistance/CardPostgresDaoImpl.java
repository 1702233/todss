package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Card;
import model.CardAssignment;
import model.Cardset;
import model.Cardside;


public class CardPostgresDaoImpl extends PostgresBaseDao implements CardDao {

	private CardsidePostgresDaoImpl csDao = new CardsidePostgresDaoImpl();

	public ArrayList<Card> queryExecutor(String query){
		ArrayList<Card> results = new ArrayList<Card>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				
				int cardID = rs.getInt("ID");
				int voorkantID = rs.getInt("voorkantID");
				int achterkantID = rs.getInt("achterkantID");
				int cardsetID = rs.getInt("cardsetid");
				
				Cardside voorkant = csDao.findByID(voorkantID);
				Cardside achterkant = csDao.findByID(achterkantID);
				Card newCard = new Card(voorkant, achterkant, cardID);
				results.add(newCard);

			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}
	@Override
	public ArrayList<Card> findAllCards() {
		return queryExecutor("SELECT * FROM CARD;");
	}
	
	@Override
	public Card findById(int ID) {
		return queryExecutor("SELECT * FROM CARD WHERE \"ID\" = '" + ID +"';").get(0);
	}
	
	@Override
	public ArrayList<Card> findCardsOfCardset(int cardsetID) {
		return queryExecutor("SELECT * FROM CARD WHERE \"cardsetID\" = " + cardsetID + ";");
	}


	@Override
	public boolean saveCard(Card card, int cardsetID) {
		CardsidePostgresDaoImpl cardsidePostgresDao = new CardsidePostgresDaoImpl();

		cardsidePostgresDao.saveCardside(card.getBackside());
		ArrayList<Cardside> cardsides = cardsidePostgresDao.findAllCardsides();
		int backsideId = cardsides.get(cardsides.size() - 1).getID();
		System.out.println(backsideId);

		cardsidePostgresDao.saveCardside(card.getFrontside());
		cardsides = cardsidePostgresDao.findAllCardsides();
		int frontsideId = cardsides.get(cardsides.size() - 1).getID();
		System.out.println(frontsideId);

		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "insert into card (\"voorkantID\", \"achterkantID\", \"cardsetID\") values (?, ?, ?);"; //zet een nieuwe afgeronde taak in de database
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, frontsideId);
			pstmt.setInt(2, backsideId);
			pstmt.setInt(3, cardsetID);
				
			queryResult = pstmt.executeUpdate();
		}
		catch(SQLException sqe) {
			System.out.println(sqe.getMessage());
		}
		
		return queryResult > 0; //als queryResult hoger dan 0 is is het opslaan gelukt (true), anders niet (false)
	}

	@Override
	public boolean updateCard(Card card, int cardsetID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "UPDATE CARD SET 'VOORKANT'= ?, 'ACHTERKANT'= ?, 'CARDSETID'= ? WHERE 'ID'= ?;"; //bewerk een afgeronde taak
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, card.getID());
			pstmt.setInt(2, card.getFrontside().getID());
			pstmt.setInt(3, card.getBackside().getID());
			pstmt.setInt(4, cardsetID);

			queryResult = pstmt.executeUpdate();
		}
		catch(SQLException sqe) {
			System.out.println(sqe.getMessage());
		}
		
		if (queryResult > 0) { //als queryResult hoger dan 0 is is het bewerken gelukt (true), anders niet (false)
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean deleteCard(int ID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM CARD WHERE 'ID' = ?;"; 
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ID);

			
			queryResult = pstmt.executeUpdate();
		}
		catch(SQLException sqe) {
			System.out.println(sqe.getMessage());
		}
		
		if (queryResult > 0) { 
			return true;
		}
		else {
			return false;
		}
	}

}
