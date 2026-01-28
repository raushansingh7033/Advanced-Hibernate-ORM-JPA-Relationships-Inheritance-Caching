package com.training.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.training.entity.Category;
import com.training.entity.Course;
import com.training.entity.CourseSection;
import com.training.entity.FreeUser;
import com.training.entity.Lesson;
import com.training.entity.Payment;
import com.training.entity.PremiumUser;
import com.training.entity.User;
import com.training.entity.UserProfile;


public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties props = getPostgresProps();
            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .applySettings(props)
                            .build();

            MetadataSources sources = new MetadataSources(registry);
//          sources.addAnnotatedClass(Student.class);
            sources.addAnnotatedClass(User.class);
            sources.addAnnotatedClass(UserProfile.class);
            sources.addAnnotatedClass(Payment.class);
            sources.addAnnotatedClass(Course.class);
            sources.addAnnotatedClass(Category.class);
            sources.addAnnotatedClass(Lesson.class);
            sources.addAnnotatedClass(CourseSection.class);
            sources.addAnnotatedClass(FreeUser.class);
            sources.addAnnotatedClass(PremiumUser.class);

            Metadata metadata = sources.getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();

        } catch (Exception ex) {
//        	ex.printStackTrace();
            throw new ExceptionInInitializerError("SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    private static Properties getMySQLProps()
    {
    	Properties props=new Properties();
    	 // JDBC Configuration
        props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/lmsdb?createDatabaseIfNotExist=true");
        props.put("hibernate.connection.username", "root");
        props.put("hibernate.connection.password", "1234");

        // Hibernate Configuration
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.current_session_context_class", "thread");
        return props;
    }

    private static Properties getPostgresProps()
    {
    	Properties props=new Properties();
    	 // JDBC Configuration
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/lmsdb");
        props.put("hibernate.connection.username", "root");
        props.put("hibernate.connection.password", "1234");

        // Hibernate Configuration
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.current_session_context_class", "thread");
        
		/* -------- CACHE CONFIG -------- */
		props.put("hibernate.cache.use_second_level_cache", "true");
		props.put("hibernate.cache.region.factory_class",
		          "org.hibernate.cache.jcache.JCacheRegionFactory");
		
		/* JCache provider (Ehcache) */
		props.put("hibernate.javax.cache.provider",
		          "org.ehcache.jsr107.EhcacheCachingProvider");
		
		props.put("hibernate.generate_statistics", "true");
		props.put("hibernate.cache.use_query_cache", "true");

        return props;
    }
    
}
