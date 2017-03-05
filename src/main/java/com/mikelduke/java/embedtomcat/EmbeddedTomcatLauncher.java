package com.mikelduke.java.embedtomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcatLauncher {
	private static int port = 8090;
    
	public static void main(String[] args) throws LifecycleException {
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		
		Tomcat tomcat = new Tomcat();
	    tomcat.setPort(port);

	    Context ctx = tomcat.addContext("/hello", new File(".").getAbsolutePath());
	    tomcat.addServlet("/hello", "hello", new HelloServlet());
	    ctx.addServletMappingDecoded("/*", "hello");
	    
	    tomcat.start();
	    tomcat.getServer().await();
	}
}
