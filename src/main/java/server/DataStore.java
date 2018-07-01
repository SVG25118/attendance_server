package server;

import java.util.HashMap;
import java.util.Set;

public class DataStore {
	private static HashMap<String, HashMap<String, Checkin>> groups = null;

	public static void remove(String group, String uid)
			throws Exception
	{
		if (groups == null)
			init();
		
		HashMap<String, Checkin> devices = groups.get(group);
		
		if (devices == null)
		{
			throw new Exception("Group not found.");
		}
		
		devices.remove(uid);
	}	
	
	public static boolean query(String group, String uid)
		throws Exception
	{
		if (groups == null)
			init();
		
		HashMap<String, Checkin> devices = groups.get(group);
		
		if (devices == null)
		{
			throw new Exception("Group not found.");
		}
		
		return devices.containsKey(uid);
	}
	
	public static void checkin(String group, Checkin checkin)
			throws Exception
	{
		if (groups == null)
			init();
		
		HashMap<String,Checkin> devices = groups.get(group);
		
		if (devices == null)
		{
			throw new Exception("Group not found.");
		}
		
		devices.put(checkin.getUid(), checkin);
	}
	
	public static HashMap<String,Checkin> getDevices(String group)
	{
		if (groups == null)
			init();
		
		return groups.get(group);
	}
	
	public static String getDeviceList(String group)
	{
		if (groups == null)
			init();
		
		return groups.get(group).keySet().toString();
	}
	
	public static Set<String> getGroups()
	{
		if (groups == null)
			init();
		
		return groups.keySet();
	}
	
	public static void reset(String group)
	{
		groups.remove(group);
		groups.put(group, new HashMap<String, Checkin>());
	}
	
	private static void init()
	{
		groups = new HashMap<String, HashMap<String, Checkin>>();
		groups.put("GROUP1", new HashMap<String, Checkin>());
		groups.put("GROUP2", new HashMap<String, Checkin>());
		groups.put("GROUP3", new HashMap<String, Checkin>());
		groups.put("GROUP4", new HashMap<String, Checkin>());
		groups.put("GROUP5", new HashMap<String, Checkin>());
		groups.put("GROUP6", new HashMap<String, Checkin>());
		groups.put("GROUP7", new HashMap<String, Checkin>());
		groups.put("GROUP8", new HashMap<String, Checkin>());
	}
}
