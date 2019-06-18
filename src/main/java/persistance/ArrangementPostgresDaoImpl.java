package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Arrangement;
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
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String teacherName = rs.getString("teacherName");

                ArrayList<Minigame> allMinigames = minigameDao.findByArrangementID(id);

                System.out.println("minigames: " + allMinigames.toString());

                Teacher teacher = teacherDao.findByUsername(teacherName);

                Arrangement arrangement = new Arrangement(id, name, description, allMinigames, teacher);

                results.add(arrangement);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return results; // return de lijst
    }

    @Override
    public ArrayList<Arrangement> findAllArrangements() {
        return queryExecutor("select * from arrangement a");
    }

    @Override
    public ArrayList<Arrangement> findByTeacher(String teacherName) {
        return queryExecutor("select * from arrangement a where a.\"teacherName\" = '" + teacherName + "'");
    }

    @Override
    public Arrangement findById(int ID) {
        return queryExecutor("select * from arrangement a where a.\"ID\" = '" + ID + "'").get(0);
    }

    @Override
    public boolean saveArrangement(Arrangement arrangement) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "INSERT INTO public.\"Arrangement\"(name, description, \"teacherName\") VALUES (?, ?, ?);";

            PreparedStatement pstmt = con.prepareStatement(query);


            pstmt.setString(1, arrangement.getName());
            pstmt.setString(2, arrangement.getDescription());
            pstmt.setString(3, arrangement.getTeacher().getUsername());

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

    private boolean linkMinigameToArrangement(Arrangement arrangement) {
        boolean status = true;

        for (Minigame minigame : arrangement.getAllMinigames()) {
            try (Connection con = super.getConnection()) {
                String query = "INSERT INTO public.\"ArrangementMinigame\"(\"arrangementID\", \"minigameID\") VALUES (?, ?);";

                PreparedStatement pstmt = con.prepareStatement(query);

                pstmt.setInt(1, arrangement.getID());
                pstmt.setInt(2, minigame.getId());

                if (pstmt.executeUpdate() <= 0) {
                    status = false;
                }
            } catch (SQLException sqe) {
                System.out.println(sqe.getMessage());
            }
        }
        return status;
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
