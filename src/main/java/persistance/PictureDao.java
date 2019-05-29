package persistance;

import java.util.ArrayList;

import model.Picture;


public interface PictureDao {

	public ArrayList<Picture> findAllPictures();
	public Picture findById(int id);
	public ArrayList<Picture> findByTeacher(String teacher);
	public boolean savePicture(Picture picture);
	public boolean updatePicture(Picture picture);
	public boolean deletePicture(int ID);
}
