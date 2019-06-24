package model.services;

public class MenuServiceProvider {

private static MenuService service = new MenuService();
	
	public static MenuService getMenuService() {
		return service;
	}
}
