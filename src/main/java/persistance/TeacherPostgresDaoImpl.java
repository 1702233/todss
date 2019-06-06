package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cardset;
import model.Minigame;
import model.Picture;
import model.Teacher;

public class TeacherPostgresDaoImpl extends PostgresBaseDao implements TeacherDao{
	
	public ArrayList<Teacher> queryExecutor(String query){
		ArrayList<Teacher> results = new ArrayList<Teacher>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				String username = rs.getString("username");
				String password = rs.getString("password");

				Teacher newTeacher = new Teacher(username);
				results.add(newTeacher);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results; // return de lijst
	}
	@Override
	public ArrayList<Teacher> findAllTeachers() {
		return queryExecutor("SELECT * FROM TEACHER;");
	}
	
	@Override
	public Teacher findByUsername(String username) {
		//return queryExecutor("SELECT * FROM TEACHER WHERE \"username\" = " + username + ";").get(0);
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
}
