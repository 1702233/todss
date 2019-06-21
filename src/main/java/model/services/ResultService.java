package model.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import model.Result;
import model.Student;
import persistance.StudentDao;
import persistance.StudentPostgresDaoImpl;

public class ResultService {

	private StudentDao studentDao = new StudentPostgresDaoImpl();
	
	public JsonArray getResultsForSession(String code){
		ArrayList<Student> studentsOfSession = studentDao.findStudentsBySession(code);

		JsonArray results = new JsonArray();
		for(Student student : studentsOfSession) {
			JsonObject studentObject = new JsonObject();
			studentObject.addProperty("ID", student.getID());
			studentObject.addProperty("name", student.getName());
			JsonArray minigameResult = new JsonArray();
		
			for(Result result : student.getAllResults()) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				
				JsonObject resultObject = new JsonObject();
				resultObject.addProperty("starttime", sdf.format(result.getStart()));
				resultObject.addProperty("endtime", sdf.format(result.getStart()));
				resultObject.addProperty("minigamename", result.getMinigame().getName());
				
				minigameResult.add(resultObject);
			}
			
			studentObject.add("result", minigameResult);
			results.add(studentObject);
		}
		
		return results;
	}
}
