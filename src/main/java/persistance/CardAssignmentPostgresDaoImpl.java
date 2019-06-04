package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Card;
import model.CardAssignment;
import model.CardRule;

public class CardAssignmentPostgresDaoImpl extends PostgresBaseDao implements CardAssignmentDao {

    CardPostgresDaoImpl cDao = new CardPostgresDaoImpl();

    public ArrayList<CardAssignment> queryExecutor(String query){
        ArrayList<CardAssignment> results = new ArrayList<CardAssignment>();

        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int cardRuleID = rs.getInt("cardruleid");
                int rank = rs.getInt("rank");
                int cardID = rs.getInt("cardid");

                Card card = cDao.findById(cardID);

                CardAssignment newCardAssignment = new CardAssignment(cardRuleID, rank, card);

                results.add(newCardAssignment);

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return results; // return de lijst
    }

    @Override
    public ArrayList<CardAssignment> findAllCardAssignments() {
        return queryExecutor("SELECT * FROM CARDASSIGNMENT;");
    }

    @Override
    public CardAssignment findByCardID(int ID) {
        return  queryExecutor("SELECT * FROM CARDASSIGNMENT WHERE ID = " + ID + ";").get(0);
    }

    @Override
    public ArrayList<CardAssignment> findByCardRuleID(int ID) {
        // TODO Auto-generated method stub
        return  queryExecutor("SELECT * FROM CARDASSIGNMENT WHERE \"CARDRULEID\" = '" + ID + "';");
    }



    @Override
    public boolean saveCardAssignment(CardAssignment cardAssignment, int cardruleID) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "INSERT INTO CARDASSIGNMENT ( RANK, CARDID) VALUES ( ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setInt(1, cardAssignment.getRank());
            pstmt.setInt(2, cardAssignment.getCard().getID());

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
    public boolean updateCardAssignment(CardAssignment cardAssignment, int cardruleID) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "UPDATE CARDASSIGNMENT SET \"CARDRULEID\" = ?, \"RANK\" = ?, \"CARDID\" = ? WHERE \"ID\" = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, cardruleID);
            pstmt.setInt(2, cardAssignment.getRank());
            pstmt.setInt(3, cardAssignment.getCard().getID());
            pstmt.setInt(4, cardAssignment.getID());

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
    public boolean deleteCardAssignment(int ID) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "DELETE FROM CARDASSIGNMENT WHERE \"ID\" = ?;";
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