package com.mm.jpa.hibernate.dbmadvanced.reposity;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mm.jpa.hibernate.dbmadvanced.entity.Course;
import com.mm.jpa.hibernate.dbmadvanced.entity.Review;
import com.mm.jpa.hibernate.dbmadvanced.entity.Student;

@Repository
@Transactional
public class CourseReposity {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Course findById(long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId() == null) {
			em.persist(course);
		}else {
			em.merge(course);
		}
		return course;
	}
		
	public void playWithEntityManager1() {
		logger.info("playWithEntityManager");
		Course course = new Course("Web services in 10 steps");
		em.persist(course);
		course.setName("Web services in 10 steps - updated");	
	}
	
	public void playWithEntityManager2() {
		logger.info("**************************************");
		Course course1 = new Course("Web services in 10 steps");
		em.persist(course1);
		Course course2 = new Course("Angular JS in 10 steps");
		em.persist(course2);
		
		em.flush();
		
		em.detach(course2);
		
		course1.setName("Web services in 10 steps - updated");	
		em.flush();
		
		course2.setName("Angular JS in 10 steps - updated");	
		em.flush();
	}
	
	public void playWithEntityManager3() {
		logger.info("**************************************");
		Course course1 = new Course("Web services in 10 steps");
		em.persist(course1);
		Course course2 = new Course("Angular JS in 10 steps");
		em.persist(course2);
		
		em.flush();
		
		em.clear();
		
		course1.setName("Web services in 10 steps - updated");	
		em.flush();
		
		course2.setName("Angular JS in 10 steps - updated");	
		em.flush();
	}
	
	public void playWithEntityManager4() {
		logger.info("**************************************");
		Course course1 = new Course("Web services in 10 steps");
		em.persist(course1);
		Course course2 = new Course("Angular JS in 10 steps");
		em.persist(course2);
		
		em.flush();
		
		course1.setName("Web services in 10 steps - updated");	
		course2.setName("Angular JS in 10 steps - updated");	
		
		em.refresh(course1);
		em.flush();
	}

	public void addHardcodeReviewsForCourse() {
		
		Course course = findById(10003L);
		
		Review review1 = new Review("5", "Great Hands-on Stuff");
		Review review2 = new Review("5", "Hatsoff");
		
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsForCourse(long courseId, List<Review> reviews	) {
		
		Course course = findById(courseId);
		
		for(Review review: reviews) {	
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}
	
	public void addHardcodeStudentAndCourse() {
		
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 steps");
		
		em.persist(student);
		em.persist(course);
		
		course.addStudent(student);
		student.addCourse(course);
		
		em.persist(student);
	}
	
	public void addStudentAndCourse(Student student, Course course) {
		course.addStudent(student);
		student.addCourse(course);
		
		em.persist(student);
		em.persist(course);
	}
	
}

