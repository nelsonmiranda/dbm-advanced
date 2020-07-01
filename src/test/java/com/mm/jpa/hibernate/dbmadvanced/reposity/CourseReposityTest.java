package com.mm.jpa.hibernate.dbmadvanced.reposity;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.mm.jpa.hibernate.dbmadvanced.DbmAdvancedApplication;
import com.mm.jpa.hibernate.dbmadvanced.entity.Course;
import com.mm.jpa.hibernate.dbmadvanced.entity.Review;
import com.mm.jpa.hibernate.dbmadvanced.entity.Student;

@SpringBootTest(classes = DbmAdvancedApplication.class)
class CourseReposityTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseReposity courseReposity;
	
	@Autowired
	EntityManager em;
	
	@Test
	void findById() {
		Course course = courseReposity.findById(10001L);
		assertEquals("JPA in 10 Steps", course.getName());
	}

	@Test
	@DirtiesContext
	void deleteById() {
		courseReposity.deleteById(1001L);
		assertNull(courseReposity.findById(10001L));;
	}
	
	
	@Test
	void save() {
		Course course = courseReposity.findById(10001L);
		course.setName("JPA in 10 Steps - Updated");
		courseReposity.save(course);
		
		Course courseUpdate = courseReposity.findById(10001L);
		assertEquals("JPA in 10 Steps - Updated", courseUpdate.getName());
	}
	
	@Test
	void playWithEntityManager() {
		courseReposity.playWithEntityManager1();
	}
	
	@Test
	@Transactional
	void retrieveReviewsForCourse() {
		Course course= em.find(Course.class, 10001L);
//		Course course = courseReposity.findById(10001L);
		logger.info("Course ->{}", course);
		logger.info("Review for Course  ->{}", course.getReviews());
	}
	
	@Test
	void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		logger.info("Course for Review  ->{}", review.getCourse());
	}
	
	@Test
	@Transactional
	void retrieveStudentAndCourse() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student ->{}", student);
		logger.info("Courses ->{}", student.getCourses());
	}
	
	@Test
	@Transactional
	void retrieveCourseAndStudent() {
		Course course= em.find(Course.class, 10001L);
		logger.info("Course ->{}", course);
		logger.info("Students ->{}", course.getStudents());
	}
}