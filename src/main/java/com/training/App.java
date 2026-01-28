package com.training;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.training.config.HibernateUtil;
import com.training.entity.Address;
import com.training.entity.Category;
import com.training.entity.Course;
import com.training.entity.User;
import com.training.entity.UserProfile;
import org.hibernate.stat.Statistics;

public class App {
	public static void main(String[] args) {
		
        System.out.println("=== APP STARTED ===");

		SessionFactory sf = HibernateUtil.getSessionFactory();
//		Session session = sf.getCurrentSession();
		Statistics stats = sf.getStatistics();
		stats.clear(); // Always clear before measuring
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
//        UserProfile profile=new UserProfile(0,"Aman Tiwari",'M',8448179216L,
//        		true,23000.5,"Teacher",LocalDate.now(),LocalDateTime.now());

//        User u=new User();// transient   ->owning side
//        u.setEmail("amantiwari8861@gmail.com");
//        u.setPassword("1234");
//        u.setProfile(profile);
//        session.persist(u); // persistent
//        User merge = session.merge(u);
//        System.out.println("Removing user!");
//        session.remove(merge);

//        Payment p1=new Payment(1000, "UPI", "SUCCESFULL", "jfdbhjvbsj", LocalDateTime.now());
//        Payment p2=new Payment(3000, "UPI", "PENDING", "dvffvsvv", LocalDateTime.now());
//        Payment p3=new Payment(5000, "UPI", "CANCELLED", "vfvssvese", LocalDateTime.now());
//        
//        Set<Payment> payments = Set.of(p1,p2,p3);
//        u.setPayments(payments);
//        
//        session.persist(u);

//        Course c1=new Course();
//        c1.setTitle("Java FSD");
//        c1.setPrice(50000);
//        
//        Course c2=new Course();
//        c2.setTitle("Python FSD");
//        c2.setPrice(45000);
//        
//        User user = session.find(User.class, 1);
//        
//        user.enrollCourse(c1);
//        user.enrollCourse(c2);
//        session.merge(user);
//        System.out.println("user added with courses");

//        User u=new User();
//        u.setEmail("shiwangidubey@gmail.com");
//        u.setPassword("1234");
//        
//        Course c=new Course();
//        c.setTitle("Java FSD with AI");
//        c.setPrice(60000);
//        
//        Category cat=new Category();
//        cat.setName("Programming");
//        cat.setDescription("Coding Course");
//        cat.setSlug("/it");
//        cat.addCourse(c);
//        
//        u.enrollCourse(c);
//        
//        session.persist(u);

//		User shiwangi = session.find(User.class, 2);
//        Course course = session.find(Course.class,1);
//        shiwangi.enrollCourse(course);
//        session.merge(shiwangi);
//
//		UserProfile uProfile = new UserProfile(0, "Shiwangi Dubey", 'F', 345678896754L, true, 40000, "Student",
//				LocalDate.of(2000, 1, 1), LocalDateTime.now());
//		Address home = new Address("civil lines pryj", "Pryj", "UP", 201301, "INDIA");
//		Address office = new Address("sec 14, GGM", "GGM", "HR", 122001, "INDIA");
//		uProfile.setHomeAddress(home);
//		uProfile.setOfficeAddress(office);
//
//		shiwangi.setProfile(uProfile);
//
//		session.merge(shiwangi);
//		System.out.println("user and course enrolled!");

//		Role r=Role.ROLE_ADMIN;
//		
//		System.out.println(r.name());
//		System.out.println(r.authority());
//		System.out.println(r.withoutPrefix());
//		
//		User u=new User();
//		u.setEmail("raushan@gmail.com");
//		u.setPassword("1234");
//		u.setRole(Role.ROLE_ADMIN);
//		session.persist(u);

//		FreeUser u0=new FreeUser();
//		u0.setEmail("abhishek@gmail.com");
//		u0.setPassword("1234");
//		u0.setFreeCredits(500);
//		u0.setRole(Role.ROLE_ADMIN);
//		session.persist(u0);

//		PremiumUser u=new PremiumUser();
//		u.setEmail("chiranjeev@gmail.com");
//		u.setPassword("1234");
//		u.setPurchasedAt(LocalDateTime.now());
//		u.setExpiringAt(LocalDateTime.of(2027, 1, 1, 18, 05));
//		u.setRole(Role.ROLE_ADMIN);
//		session.persist(u);

		// L1 Cache
//		User e1 = session.find(User.class, 1L); // DB hit
//		User e2 = session.find(User.class, 1L); // Served from L1 cache
//		System.out.println(e1);
//		System.out.println(e2);

		// L2 Cache using ehcache
//
//		List<Category> categories = session.createQuery("from Category", Category.class).setCacheable(true)
//				.getResultList();
//		System.out.println(categories);
//		/*
//		 * ⚠ Query cache stores ONLY entity IDs ⚠ Actual entity state still comes from
//		 * L2 cache
//		 */
//		tx.commit();   // 🔴 REQUIRED for L2 PUT
//        session.close();
//
//        // -------- L2 CACHE TEST --------
//        stats.clear();
//
//// Session 1 → DB hit → L2 PUT
//		Session s1 = sf.openSession();
//		Category c1 = s1.find(Category.class, 1L);
//		System.out.println(c1);
//		s1.close();
//
//// Session 2 → L2 HIT
//		Session s2 = sf.openSession();
//		Category c2 = s2.find(Category.class, 1L);
//		System.out.println(c2);
//		s2.close();
//
//		
//		
//	      // -------- STATS --------
//        System.out.println("L2 Hit  : " + stats.getSecondLevelCacheHitCount());
//        System.out.println("L2 Miss : " + stats.getSecondLevelCacheMissCount());
//        System.out.println("L2 Put  : " + stats.getSecondLevelCachePutCount());
//
//        System.out.println("Query Hit  : " + stats.getQueryCacheHitCount());
//        System.out.println("Query Miss : " + stats.getQueryCacheMissCount());
//        System.out.println("Query Put  : " + stats.getQueryCachePutCount());

        List<Category> categories =
        	    session.createQuery(
        	        "select distinct c from Category c join fetch c.courses",
        	        Category.class
        	    ).getResultList();

        System.out.println(categories);
        
        
//		tx.commit();
//		session.close();// detached
		sf.close();
	}
}
// Embeddable = multiple fields → multiple columns
// Enum = one value → one column