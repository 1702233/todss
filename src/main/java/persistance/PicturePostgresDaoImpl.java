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
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { 
				int ID = rs.getInt("ID");
				String url = rs.getString("url");
				String teacherName = rs.getString("teacherName");

				Teacher teacher = tDao.findByUsername(teacherName);

				Picture newPicture = new Picture(ID, url, teacher);

				results.add(newPicture);

			}
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
		return queryExecutor("SELECT * FROM PICTURE WHERE \"ID\" = '" + id + "';").get(0);
	}
	
	public ArrayList<Picture> findByTeacher(String teacher) {
		return queryExecutor("SELECT * FROM PICTURE WHERE TEACHERUSERNAME = " + teacher + ";");
	}

	@Override
	public boolean savePicture(Picture picture) {
		int queryResult = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO PICTURE (ID, URL, TEACHERUSERNAME) VALUES (?, ?, ?);";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, picture.getID());
			pstmt.setString(2, picture.getUrl());
			pstmt.setString(3, picture.getTeacher().getUsername());

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
