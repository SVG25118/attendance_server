package server;

import java.util.HashMap;
import java.util.Set;

public class DataStore {
	private static HashMap<String, HashMap<String, Checkin>> courses = null;

	public static void remove(String course, String uid)
			throws Exception
	{
		if (courses == null)
			init();
		
		HashMap<String, Checkin> devices = courses.get(course);
		
		if (devices == null)
		{
			throw new Exception("Course not found.");
		}
		
		devices.remove(uid);
	}	
	
	public static boolean query(String course, String uid)
		throws Exception
	{
		if (courses == null)
			init();
		
		HashMap<String, Checkin> devices = courses.get(course);
		
		if (devices == null)
		{
			throw new Exception("Course not found.");
		}
		
		return devices.containsKey(uid);
	}
	
	public static void checkin(String course, Checkin checkin)
			throws Exception
	{
		if (courses == null)
			init();
		
		HashMap<String,Checkin> devices = courses.get(course);
		
		if (devices == null)
		{
			throw new Exception("Course not found.");
		}
		
		devices.put(checkin.getUid(), checkin);
	}
	
	public static void addCourse(String course) throws Exception {
		if (courses == null)
			init();
		
		courses.put(course, new HashMap<String, Checkin>());
	}
	
	public static String getKey(String course, String uid) {
		if (courses == null)
			init();
		
		return courses.get(course).get(uid).getKey();
	}
	
	public static HashMap<String,Checkin> getDevices(String course)
	{
		if (courses == null)
			init();
		
		return courses.get(course);
	}
	
	public static String getDeviceList(String course)
	{
		if (courses == null)
			init();
		
		return courses.get(course).keySet().toString();
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
		courses.put(course, new HashMap<String, Checkin>());
	}
	
	private static void init()
	{
		courses = new HashMap<String, HashMap<String, Checkin>>();
		courses.put("TEST1234", new HashMap<String, Checkin>());
		courses.put("TEST2345", new HashMap<String, Checkin>());
	}
}
