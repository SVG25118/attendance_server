package server;

import java.util.Calendar;

public class Checkin {
	private String uid = null;
	private String key = null;
	private Calendar time = null;
	
	public Checkin(String uid, String key)
	{
		this.uid = uid;
		this.key = key;
		this.time = Calendar.getInstance();
	}
	
	public String getUid()
	{
		return uid;
	}
	
	public String getKey()
	{
		return key;
	}
	
	public Calendar getTime()
	{
		return time;
	}
}
