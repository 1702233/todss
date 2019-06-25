package model.services;

import model.Menu;
import model.Teacher;
import persistance.MenuDao;
import persistance.MenuPostgresDaoImpl;
import persistance.TeacherDao;
import persistance.TeacherPostgresDaoImpl;

import java.util.List;

public class MenuService {

	private MenuDao dao = new MenuPostgresDaoImpl();

	public Menu getMenu(String role) {
	    return dao.getMenu(role);
    }

}
