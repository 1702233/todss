package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Teacher;

public class TeacherPostgresDaoImpl extends PostgresBaseDao implements TeacherDao {

    public ArrayList<Teacher> queryExecutor(String query) {
        ArrayList<Teacher> results = new ArrayList<Teacher>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");

                Teacher newTeacher = new Teacher(username, password);
                results.add(newTeacher);

            }
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }
        return results; // return de lijst
    }

    @Override
    public ArrayList<Teacher> findAllTeachers() {
        return queryExecutor("SELECT * FROM TEACHER;");
    }

    @Override
    public Teacher findByUsername(String username) {
        return queryExecutor("SELECT * FROM public.teacher where username = '" + username + "';").get(0);
    }

    @Override
    public boolean saveTeacher(Teacher teacher) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "INSERT INTO TEACHER (USERNAME, PASSWORD) VALUES (?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, teacher.getUsername());
            pstmt.setString(2, teacher.getPassword());

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
    public boolean updateTeacher(Teacher teacher) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "UPDATE TEACHER SET \"USERNAME\" = ?, \"PASSWORD\" = ? WHERE \"USERNAME\" = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, teacher.getUsername());
            pstmt.setString(2, teacher.getPassword());
            pstmt.setString(3, teacher.getUsername());

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
    public boolean deleteTeacher(int ID) {
        int queryResult = 0;
        try (Connection con = super.getConnection()) {
            String query = "DELETE FROM TEACHER WHERE \"USERNAME\" = ?;";
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

    @Override
    public boolean checkTeacher(String username, String password) {
        List<Teacher> teachers = queryExecutor("SELECT * FROM TEACHER WHERE \"username\" = '" + username + "' AND \"password\" = '" + password + "';");
        if (teachers.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String findRoleForUser(String name, String pass) {

        try (Connection con = super.getConnection()) {
            String antwoord = "a";
            String query = "SELECT role FROM teacher WHERE username = ? AND password = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                antwoord = rs.getString("role");
                return antwoord;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findRoleForUserByUsername(String name) {

        try (Connection con = super.getConnection()) {
            String antwoord = "a";
            String query = "SELECT role FROM teacher WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                antwoord = rs.getString("role");
                return antwoord;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
