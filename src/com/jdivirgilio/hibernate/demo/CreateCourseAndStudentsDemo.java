package com.jdivirgilio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jdivirgilio.hibernate.demo.entity.Course;
import com.jdivirgilio.hibernate.demo.entity.Instructor;
import com.jdivirgilio.hibernate.demo.entity.InstructorDetail;
import com.jdivirgilio.hibernate.demo.entity.Review;
import com.jdivirgilio.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml") // Default name of file. Not
																					// necessary to include here.
																					// Must be in class path though!
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)	 
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();

			// Create a course
			Course course1 = new Course("CSE 1301");
			Course course2 = new Course("CSE 1302");
			Course course3 = new Course("CSE 2301");
			Course course4 = new Course("CSE 2302");
			
			session.save(course1);
			session.save(course2);
			session.save(course3);
			session.save(course4);
			
			// Create the students
			Student student1 = new Student("John", "DiVirgilio", "jd@anywhere.com");
			Student student2 = new Student("Johnny", "McAnally", "jm@anywhere.com");
			Student student3 = new Student("Mary", "Poppins", "mp@anywhere.com");
			Student student4 = new Student("Rachael", "Morabito", "rm@anywhere.com");
			
			// Add the students to the course
			course1.addStudent(student1);
			course1.addStudent(student2);
			course1.addStudent(student4);
			
			course2.addStudent(student1);
			course2.addStudent(student2);
			course2.addStudent(student3);
			course2.addStudent(student4);
			
			course3.addStudent(student3);
			course3.addStudent(student4);

			course4.addStudent(student4);

			// Save the students
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.save(student4);
			
			System.out.println(student1);
			System.out.println(student1.getCourses());
			System.out.println(student2);
			System.out.println(student2.getCourses());
			System.out.println(student3);
			System.out.println(student3.getCourses());
			System.out.println(student4);
			System.out.println(student4.getCourses());
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}
}
