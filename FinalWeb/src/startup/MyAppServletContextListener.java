package startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import util.Settings;

public class MyAppServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		String path = sce.getServletContext().getRealPath("/");
		Settings.setDir(path);
		Settings.getInstance();
		
	}

}
