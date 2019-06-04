package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Result;
import model.Session;
import model.Student;

public class StudentPostgresDaoImpl extends PostgresBaseDao implements StudentDao {

	SessionPostgresDaoImpl sDao = new SessionPostgresDaoImpl();
	ResultPostgresDaoImpl rDao = new ResultPostgresDaoImpl();
	
	public ArrayList<Student> queryExecutor(String query){
		ArrayList<Student> results = new ArrayList<Student>();

		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				
				int studentID = rs.getInt("id");
				String name = rs.getString("name");
				int sessionCode = rs.getInt("sessioncode");
				
				ArrayList<Result> allResults = rDao.findByStudent(studentID);
				Session session = sDao.findByID(sessionCode);

				

				Student newStudent = new Student(studentID, name, allResults, session);

				results.add(newStudent);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst 
	}
	
	@Override
	public Student findByID(int ID) {
		
		return queryExecutor("SELECT * FROM STUDENT WHERE ID = " + ID + ";").get(0);
	}

	@Override
	public ArrayList<Student> findStudentsBySession(String sessionCode) {
		return queryExecutor("SELECT * FROM STUDENT WHERE SESSIONCODE = " + sessionCode + ";");
	}
	@Override
	public boolean saveStudent(Student student) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO STUDENT (NAME, SESSIONCODE) VALUES (?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, student.getID());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getSession().getCode());

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

}
