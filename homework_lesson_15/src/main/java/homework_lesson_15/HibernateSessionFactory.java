package homework_lesson_15;

import java.util.Properties;

import java.util.TimeZone;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory {
	private static SessionFactory hibernateSessionFactory;

	public static SessionFactory getSessionFactory() {
		if (hibernateSessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
//				configuration.configure("hibernate.cfg.xml");
				
				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/homework_15?serverTimezone=" + TimeZone.getDefault().getID());
				properties.put(Environment.USER, "root");
				properties.put(Environment.PASS, "1236vn");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				properties.put(Environment.SHOW_SQL, "true");
				properties.put(Environment.HBM2DDL_AUTO, "create");
				configuration.setProperties(properties);
				
				configuration.addAnnotatedClass(Post.class);
				configuration.addAnnotatedClass(Comment.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				hibernateSessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hibernateSessionFactory;
	}
}
