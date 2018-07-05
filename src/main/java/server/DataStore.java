package server;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DataStore {
//	private static HashMap<String, HashMap<String, Checkin>> courses = null;
	private static HashMap<String, Course> courses = null;

	public static void remove(String course, String uid)
			throws Exception
	{
		if (courses == null)
			init();
		
		List<Checkin> students = courses.get(course).getStudents();
		
		if (students == null)
		{
			throw new Exception("Course not found.");
		}
		
		students.remove(uid);
	}	
	
	public static boolean query(String course, String uid)
		throws Exception
	{
		if (courses == null)
			init();
		
		List<Checkin> students = courses.get(course).getStudents();
		
		if (students == null)
		{
			throw new Exception("Course not found.");
		}
		
		return students.contains(uid);
	}
	
	public static void checkin(String course, Checkin checkin)
			throws Exception
	{
		if (courses == null)
			init();
		
		List<Checkin> students = courses.get(course).getStudents();
		
		if (students == null) {
			throw new Exception("Course not found.");
		}
		
		students.add(checkin);
	}
	
	public static void addCourse(String course) throws Exception {
		if (courses == null)
			init();
		
		courses.put(course, new Course(course));
	}
	
	public static boolean removeCourse(String course) throws Exception {
		if ((courses != null)&&(courses.containsKey(course))) {
			courses.remove(course);
			return true;
		} else {
			return false;
		}
					
	}
	
	public static String getKey(String course, String uid) {
		if (courses == null)
			init();
		
		return courses.get(course).getStudents().get(courses.get(course).getStudents().indexOf(uid)).getKey();
//		return courses.get(course).get(uid).getKey();
	}
	
	public static Course getDevices(String course)
	{
		if (courses == null)
			init();
		
		return courses.get(course);
	}
	
	public static String getDeviceList(String course)
	{
		if (courses == null)
			init();
		
		return courses.get(course).getStudents().toString();
//		return courses.get(course).keySet().toString();
	}
	
	public static Set<String> getCourses()
	{
		if (courses == null)
			init();
		
		return courses.keySet();
	}
	
	public static void reset(String course)
	{
		courses.remove(course);
		courses.put(course, new Course(course));
	}
	
	private static void init()
	{
		courses = new HashMap<String, Course>();
		courses.put("TEST1234", new Course("TEST1234"));
		courses.put("TEST2345", new Course("TEST2345"));
	}
}
