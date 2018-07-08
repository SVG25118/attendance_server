package server;

import java.util.HashMap;

public class Student {
	private String studentID = null;
	private HashMap<String,Course> courses;
	
	public Student(String studentID) {
		this.studentID = studentID;
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	public HashMap<String,Course> getCourses() {
		return courses;
	}
}
