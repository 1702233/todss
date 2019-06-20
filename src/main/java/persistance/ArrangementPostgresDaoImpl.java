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
        	System.out.println("arrangement 1");
            PreparedStatement pstmt = con.prepareStatement(query);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.println("arrangement 2");
            MinigamePostgresDaoImpl minigameDao = new MinigamePostgresDaoImpl();
            System.out.println("arrangement 3");
            TeacherPostgresDaoImpl teacherDao = new TeacherPostgresDaoImpl();
            System.out.println("arrangement 4");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String teacherName = rs.getString("teacherName");
                System.out.println("arrangement 5");

                ArrayList<Minigame> allMinigames = minigameDao.findByArrangementID(id);
                System.out.println("arrangement 6");
                Teacher teacher = teacherDao.findByUsername(teacherName);
                System.out.println("arrangement 7");

                Arrangement arrangement = new Arrangement(id, name, description, allMinigames, teacher);
                System.out.println("arrangement 8");

                results.add(arrangement);
            }
            con.close();
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
    	System.out.println("findByID");
        return queryExecutor("select * from arrangement a where a.\"ID\" = '" + ID + "'").get(0);
    }

    @Override
    public boolean saveArrangement(Arrangement arrangement) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "INSERT INTO arrangement (name, description, \"teacherName\") VALUES (?, ?, ?);";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, arrangement.getName());
            pstmt.setString(2, arrangement.getDescription());
            pstmt.setString(3, arrangement.getTeacher().getUsername());

            queryResult = pstmt.executeUpdate();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }

        return (queryResult > 0 && linkMinigameToArrangement(arrangement)); // als queryResult hoger dan 0 is is het opslaan gelukt (true), anders niet (false)
    }

    private boolean linkMinigameToArrangement(Arrangement arrangement) {
        boolean status = true;
        System.out.println(getArrangementID(arrangement.getName(), arrangement.getDescription(), arrangement.getTeacher().getUsername()));
        arrangement.setID(getArrangementID(arrangement.getName(), arrangement.getDescription(), arrangement.getTeacher().getUsername()));
        System.out.println(arrangement.toString());
        for (Minigame minigame : arrangement.getAllMinigames()) {
            try (Connection con = super.getConnection()) {
                String query = "INSERT INTO arrangementMinigame (\"arrangementID\", \"minigameID\") VALUES (?, ?);";

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
    public boolean deleteArrangement(int id) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "delete from arrangement where \"ID\" = ?;";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, id);

            queryResult = pstmt.executeUpdate();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }

        return (queryResult > 0); // als queryResult hoger dan 0 is is het opslaan gelukt (true), anders niet (false)
    }

	@Override
	public int getArrangementID(String name, String description, String teacherName) {
			try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("SELECT \"ID\" FROM arrangement where name = '" + name + "' and description = '" + description + "' and \"teacherName\" = '" + teacherName + "';");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                return rs.getInt("ID");
           
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        }
		return 0;
	}
}
