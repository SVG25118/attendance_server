package server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Course {
	private String name = null;
	private String location = null;
	private Calendar time = null;
	private String description = null;
	private List<String> admins;
	private HashMap<String,HashMap<String,String>> questions;
	private HashMap<String,Checkin> students;
	
	public Course(String name) {
		super();
		this.name = name;
		this.location = "UoN";
		this.time = Calendar.getInstance();
		this.description = "Default description";
		this.admins = new ArrayList<String>();
		this.questions = new HashMap<String,HashMap<String,String>>();
		this.students = new HashMap<String,Checkin>();
	}
	public Course(String name, String location, String description) {
		super();
		this.name = name;
		this.location = location;
		this.time = Calendar.getInstance();
		this.description = description;
		this.admins = new ArrayList<String>();
		this.questions = new HashMap<String,HashMap<String,String>>();
		this.students = new HashMap<String,Checkin>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getAdmins() {
		return admins;
	}
	public void setAdmins(List<String> admins) {
		this.admins = admins;
	}
	public void addAdmin(String admin) {
		this.admins.add(admin);
	}
	
	public HashMap<String,HashMap<String,String>> getQuestions() {
		return questions;
	}
	public void setQuestions(HashMap<String,HashMap<String,String>> questions) {
		this.questions = questions;
	}
	public void addQuestion(String question) {
		this.questions.put(question,new HashMap<String,String>());
	}
	public void addAnswer(String question,String uid,String answer) {
		if(this.questions.containsKey(question)) {
			questions.get(question).put(uid,answer);
		}
	}
	
	public HashMap<String,Checkin> getStudents() {
		return students;
	}
	public void setStudents(HashMap<String,Checkin> students) {
		this.students = students;
	}
	public void addStudent(Checkin student) {
		this.students.put(student.getKey(),student);
	}

}
