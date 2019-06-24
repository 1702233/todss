package model.services;

public class StudentServiceProvider {
	private static StudentService service = new StudentService();
	
	public static StudentService getStudentService() {
		return service;
	}

}
