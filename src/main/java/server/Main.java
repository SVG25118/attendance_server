package server;

import org.apache.tomee.embedded.Configuration;
import org.apache.tomee.embedded.Container;
import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception	{
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
		
		String webPort = System.getenv("PORT");
		
		if(webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		
		Configuration config = new Configuration();
		config.setHttpPort(Integer.valueOf(webPort));
		
		try (@SuppressWarnings("resource")
		final Container container = new Container(config).deployClasspathAsWebApp("/", new File("src/main/resources/"))) {
			container.await();
		}
	}
}