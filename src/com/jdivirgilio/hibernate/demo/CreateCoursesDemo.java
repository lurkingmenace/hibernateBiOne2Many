package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Course;
import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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

			session.beginTransaction();

			// get the instructor from the DB
			int instructorId = 3; // pulled from the db query
			Instructor instructor = session.get(Instructor.class, instructorId);
			
			// create some courses
			Course course0 = new Course("CSE 1301");
			Course course1 = new Course("CSE 1302");
			Course course2 = new Course("CSE 2301");
			Course course3 = new Course("CSE 2302");
			
			// add to the instructor
			instructor.add(course0);
			instructor.add(course1);
			instructor.add(course2);
			instructor.add(course3);
			
			// save the courses
			session.save(course0);
			session.save(course1);
			session.save(course2);
			session.save(course3);
			 
			
			session.getTransaction().commit();
			System.out.println(instructor);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
