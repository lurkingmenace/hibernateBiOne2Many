package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Course;
import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;
import com.jdivirgilio.hibernate.demo.entity.Review;

public class GetCourseAndReviewsDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") // Default name of file. Not
																					// necessary to include here.
																					// Must be in class path though!
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)	 
								.addAnnotatedClass(Review.class)	 
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();

			// Get the course
			int courseId = 16;
			Course course = session.get(Course.class, courseId);
			
			// Print the course
			System.out.println(course);
			
			// print the reviews
			System.out.println(course.getReviews());
			
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
