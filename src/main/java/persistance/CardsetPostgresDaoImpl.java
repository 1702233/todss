package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Card;
import model.Cardset;
import model.Cardside;
import model.Teacher;

public class CardsetPostgresDaoImpl extends PostgresBaseDao implements CardsetDao {

    private TeacherPostgresDaoImpl tDao = new TeacherPostgresDaoImpl();
    private CardPostgresDaoImpl cDao = new CardPostgresDaoImpl();

    public ArrayList<Cardset> queryExecutor(String query) {
        ArrayList<Cardset> results = new ArrayList<Cardset>();

        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int cardsetID = rs.getInt("ID");
                String name = rs.getString("name");

                String teacherName = rs.getString("teacherName");
                Teacher teacher = tDao.findByUsername(teacherName);

                ArrayList<Card> cardsOfCardset = cDao.findCardsOfCardset(cardsetID);

                Cardset newCardset = new Cardset(cardsetID, name, teacher, cardsOfCardset);

                results.add(newCardset);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return results; // return de lijst
    }

    @Override
    public ArrayList<Cardset> findAllCardsets() {
        return queryExecutor("SELECT * FROM CARDSET;");
    }

    @Override
    public Cardset findByID(int ID) {
        ArrayList<Cardset> results = queryExecutor("SELECT * FROM CARDSET WHERE \"ID\" = '" + ID + "';");
        return results.get(0);
    }

    @Override
    public ArrayList<Cardset> findByTeacher(String teacher) {

        return queryExecutor("SELECT * FROM CARDSET WHERE \"teacherName\" = '" + teacher + "';");
    }

    @Override
    public boolean saveCardset(Cardset cardset) {
        // Error handling, in case any of the fields are returned empty
        if (cardset.getName().equals("") || cardset.getName() == null) {
            System.out.println("No cardset name specified");
            return false;
        }

        Cardside backside = cardset.getAllCards().get(0).getBackside();
        System.out.println("Cardset backside: " + backside.toString());
        System.out.println("Backside text: " + backside.getTekst());
        System.out.println("Backside image:");
        if ((backside.getTekst() == null || backside.getTekst().equals("null") || backside.getTekst().equals("")) && (backside.getPicture().getUrl() == null || backside.getPicture().getUrl().equals("null") || backside.getPicture().getUrl().equals(""))) {
            System.out.println("No card backside specified");
            return false;
        }

        for (Card card : cardset.getAllCards()) {
            Cardside frontside = card.getFrontside();
            System.out.println("Card frontside: " + frontside.toString());
            if ((frontside.getTekst() == null || frontside.getTekst().equals("null") || frontside.getTekst().equals("")) && (frontside.getPicture().getUrl() == null || frontside.getPicture().getUrl().equals("null") || frontside.getPicture().getUrl().equals(""))) {
                System.out.println("No card frontside specified");
                return false;
            }
        }

        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "insert into cardset (name, \"teacherName\") values (?, ?);\n";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, cardset.getName());
            pstmt.setString(2, cardset.getTeacher().getUsername());

            queryResult = pstmt.executeUpdate();

            // Save all cards into this cardset
            CardPostgresDaoImpl cardPostgresDao = new CardPostgresDaoImpl();

            ArrayList<Cardset> allCardsets = this.findAllCardsets();
            int id = allCardsets.get(allCardsets.size() - 1).getId();

            for (Card card : cardset.getAllCards()) {
                cardPostgresDao.saveCard(card, id);
            }

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }

        return queryResult > 0; // als queryResult hoger dan 0 is is het opslaan gelukt (true), anders niet
    }

    @Override
    public boolean updateCardset(Cardset cardset) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "UPDATE CARDSET SET \"NAME\"= ?, \"TEACHERNAME\"= ? WHERE \"ID\"= ?;";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, cardset.getName());
            pstmt.setString(2, cardset.getTeacher().getUsername());
            pstmt.setInt(3, cardset.getId());

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
    public boolean deleteCardset(int ID) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "DELETE FROM CARDSET WHERE \"ID\" = ?;";
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
