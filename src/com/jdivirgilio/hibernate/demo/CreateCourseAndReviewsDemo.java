package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Course;
import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;
import com.jdivirgilio.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

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

			// Create a course
			Course course = new Course("CSE 2302");
			
			// add some reviews
			course.addReview(new Review("Interesting course!"));
			course.addReview(new Review("Basic programming 101"));
			course.addReview(new Review("Dumb instructor"));
			course.addReview(new Review("Loved it! Can't wait for the next one!"));
			
			// save the course ... and leverage the cascade all 
			System.out.println(course);
			System.out.println(course.getReviews());
			
			session.save(course);
			
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
