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
        	System.out.println("cardassignment 1");
            PreparedStatement pstmt = con.prepareStatement(query);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("cardassignment 2");
            while (rs.next()) {
                int cardRuleID = rs.getInt("cardRuleID");
                int rank = rs.getInt("rank");
                int cardID = rs.getInt("cardid");

                System.out.println("cardassignment 3");
                Card card = cDao.findById(cardID);

                System.out.println("cardassignment 4");
                CardAssignment newCardAssignment = new CardAssignment(cardRuleID, rank, card);
                
                System.out.println("cardassignment 5");
                results.add(newCardAssignment);

            }
            con.close();
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
    	System.out.println("cardassignment findbycardruleid");
        return  queryExecutor("SELECT * FROM CARDASSIGNMENT WHERE \"cardRuleID\" = '" + ID + "';");
    }



    @Override
    public boolean saveCardAssignment(CardAssignment cardAssignment, int cardruleID) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "INSERT INTO public.cardassignment(\n" + 
            		"	\"cardID\", \"cardRuleID\", rank)\n" + 
            		"	VALUES (?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setInt(1, cardAssignment.getCard().getID());
            pstmt.setInt(2, cardruleID);
            pstmt.setInt(3, cardAssignment.getRank());

            
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