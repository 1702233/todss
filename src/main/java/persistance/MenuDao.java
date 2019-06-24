package persistance;

import model.Arrangement;
import model.Menu;
import model.Teacher;

import java.util.ArrayList;


public interface MenuDao {

	public Menu getMenu(Teacher teacher);
	
}
