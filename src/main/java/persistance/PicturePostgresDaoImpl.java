package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Picture;
import model.Teacher;

public class PicturePostgresDaoImpl extends PostgresBaseDao implements PictureDao {

	TeacherPostgresDaoImpl tDao = new TeacherPostgresDaoImpl();

	public ArrayList<Picture> queryExecutor(String query) {
		ArrayList<Picture> results = new ArrayList<Picture>();

		try (Connection con = super.getConnection()) {
			System.out.println("picture 1");
			PreparedStatement pstmt = con.prepareStatement(query);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("picture 2");
			while (rs.next()) { 
				int ID = rs.getInt("ID");
				String url = rs.getString("url");
				String teacherName = rs.getString("teacherName");

				System.out.println("picture 3");
				Teacher teacher = tDao.findByUsername(teacherName);
				System.out.println("picture 4");

				Picture newPicture = new Picture(ID, url, teacher);

				System.out.println("picture 5");
				results.add(newPicture);

			}
			con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results; // return de lijst
	}

	@Override
	public ArrayList<Picture> findAllPictures() {
		return queryExecutor("SELECT * FROM PICTURE");
	}

	@Override
	public Picture findById(int id) {
		System.out.println("picture findbyid");
		return queryExecutor("SELECT * FROM PICTURE WHERE \"ID\" = '" + id + "';").get(0);
	}
	
	public ArrayList<Picture> findByTeacher(String teacher) {
		return queryExecutor("SELECT * FROM PICTURE WHERE TEACHERUSERNAME = " + teacher + ";");
	}

	@Override
	public boolean savePicture(Picture picture) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "insert into picture (\"URL\", \"teacherName\") values (?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, picture.getUrl());
			pstmt.setString(2, picture.getTeacher().getUsername());

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
	public boolean updatePicture(Picture picture) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "UPDATE PICTURE SET \"ID\" = ?, \"ULR\" = ?, \"TEACHERUSERNAME\" = ? WHERE \"ID\" = ?;";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, picture.getID());
			pstmt.setString(2, picture.getUrl());
			pstmt.setString(3, picture.getTeacher().getUsername());
			pstmt.setInt(4, picture.getID());

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
	public boolean deletePicture(int ID) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM PICTURE WHERE \"ID\" = ?;";
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
