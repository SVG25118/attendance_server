package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class DataStore {
	private static HashMap<String,Course> courses = null;
	private static HashMap<String,Student> students;

	public static void remove(String course, String uid) throws Exception {
		if (courses == null)
			init();
		
		HashMap<String,Checkin> students = courses.get(course).getStudents();
		
		if (students == null) {
			throw new Exception("Course not found.");
		}
		
		students.remove(uid);
	}	
	
	public static boolean query(String course, String uid) throws Exception {
		if (courses == null)
			init();
		
		HashMap<String,Checkin> students = courses.get(course).getStudents();
		
		if (students == null) {
			throw new Exception("Course not found.");
		}
		
		return students.containsKey(uid);
	}
	
	public static void checkin(String course, Checkin checkin) throws Exception {
		if (courses == null)
			init();
		
		HashMap<String,Checkin> students = courses.get(course).getStudents();
		
		if (students == null) {
			throw new Exception("Course not found.");
		}
		
		students.put(checkin.getStudentID(),checkin);
	}
	
	public static void addStudent(String student) throws Exception {
		if (students == null)
			init();
		
		students.put(student, new Student(student));
	}
	
	public static boolean removeStudent(String student) throws Exception {
		if ((students != null)&&(students.containsKey(student))) {
			students.remove(student);
			return true;
		} else {
			return false;
		}
					
	}
	
	public static void addCourse(String course) throws Exception {
		if (courses == null)
			init();
		
		courses.put(course, new Course(course));
	}
	
	public static void addCourse(String course, String loc, String desc) throws Exception {
		if (courses == null)
			init();
		
		courses.put(course, new Course(course,loc,desc));
	}
	
	public static boolean removeCourse(String course) throws Exception {
		if ((courses != null)&&(courses.containsKey(course))) {
			courses.remove(course);
			return true;
		} else {
			return false;
		}					
	}
	
	public static void addQuestion(String course,String question) {
		if ((courses != null)&&(courses.containsKey(course))) {
			courses.get(course).addQuestion(question);
		}		
	}
	public static void addAnswer(String course,String uid,String question,String answer) {
		if ((courses != null)&&(courses.containsKey(course))) {
			courses.get(course).addAnswer(question,uid,answer);
		}		
	}
	
	public static String getKey(String course, String uid) {
		if (courses == null)
			init();
		
		return courses.get(course).getStudents().get(uid).getKey();
	}
	
	public static Course getDevices(String course) {
		if (courses == null)
			init();
		
		return courses.get(course);
	}
	
	public static String getDeviceList(String course) {
		if (courses == null)
			init();
		
		return courses.get(course).getStudents().toString();
	}
	
	public static Set<Entry<String,Course>> getCourses() {
		if (courses == null)
			init();
		
		return courses.entrySet();
	}
	
	public static List<String> getCourseInfo(String course) {
		if (courses == null)
			init();
		
		List<String> courseInfo = new ArrayList<String>();
		courseInfo.add(courses.get(course).getLocation());
		courseInfo.add(courses.get(course).getDescription());
		courseInfo.add(courses.get(course).getTime().getTime().toString());
		
		return courseInfo;
	}
	
	public static HashMap<String,HashMap<String,String>> getCourseQuestions(String course) {
		if (courses == null)
			init();
		
		return courses.get(course).getQuestions();
	}
	
	public static boolean containsCourse(String course) {
		if (courses == null)
			init();
		
		if(courses.containsKey(course)) return true;
		return false;
	}
	
	public static void reset(String course) {
		courses.remove(course);
		courses.put(course, new Course(course));
	}
	
	private static void init() {
		courses = new HashMap<String, Course>();
		students = new HashMap<String, Student>();
		courses.put("TEST1234", new Course("TEST1234"));
		courses.put("TEST2345", new Course("TEST2345"));
	}
}
