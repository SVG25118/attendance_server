package server;

import java.util.Calendar;

public class Checkin {
	private Student student = null;
	private String key = null;
	private Calendar time = null;
	
	public Checkin(Student student, String key) {
		this.student = student;
		this.key = key;
		this.time = Calendar.getInstance();
	}
	
	public String getStudentID() {
		return student.getStudentID();
	}
	
	public String getKey() {
		return key;
	}
	
	public Calendar getTime() {
		return time;
	}
}
