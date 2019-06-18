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
				try {
					int pictureID = rs.getInt("pictureid");
					Picture picture = pDao.findById(pictureID);
					Cardside newCardside = new Cardside(cardsideID, picture);
					results.add(newCardside);
					
				}
				catch(IndexOutOfBoundsException e){
					String tekst = rs.getString("tekst");
					Cardside newCardside = new Cardside(cardsideID, tekst);
					results.add(newCardside);
				}

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
		int pictureId;
		if (cardside.getPicture() != null) {
			PicturePostgresDaoImpl picturePostgresDao = new PicturePostgresDaoImpl();

			picturePostgresDao.savePicture(cardside.getPicture());

			ArrayList<Picture> pictures = picturePostgresDao.findAllPictures();
			pictureId = pictures.get(pictures.size() - 1).getID();
		} else {
			pictureId = -1;
		}

		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "insert into cardside (tekst, \"pictureID\") values (?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, cardside.getTekst());
			if (pictureId != -1) {
				pstmt.setInt(2, pictureId);
			} else {
				pstmt.setNull(2, java.sql.Types.INTEGER);
			}

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

