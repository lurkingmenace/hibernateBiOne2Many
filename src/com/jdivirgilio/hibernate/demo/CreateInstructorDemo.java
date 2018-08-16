package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Course;
import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") // Default name of file. Not
																					// necessary to include here.
																					// Must be in class path though!
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)	 
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
			Instructor instructor = new Instructor("John", "DiVirgilio", "jd@gmail.com");
			InstructorDetail detail = new InstructorDetail("http://www.jd.com/youtube", "Cycling");
			instructor.setInstructorDetail(detail);

			session.beginTransaction();
			// ALSO saves the details object because of the cascade
			session.save(instructor);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
