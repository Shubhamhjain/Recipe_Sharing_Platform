package com.example.recipe;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.example.pojo.Recipe;
import com.example.pojo.User;


@Component
public class HibernateBean {
	
	  private static SessionFactory sessionFactory;
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                // Hibernate settings equivalent to hibernate.cfg.xml's properties
	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/recipedb");
	                settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "Indianhuskies@74");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");

	                settings.put(Environment.SHOW_SQL, "true");

	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

	                settings.put(Environment.HBM2DDL_AUTO, "create");

	                configuration.setProperties(settings);

	                //Add annotated Pojo's here
	                
	                configuration.addAnnotatedClass(User.class);
	                configuration.addAnnotatedClass(Recipe.class);
	                

	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();

	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }
}




