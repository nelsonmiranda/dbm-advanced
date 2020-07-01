package com.mm.jpa.hibernate.dbmadvanced.reposity;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mm.jpa.hibernate.dbmadvanced.DbmAdvancedApplication;
import com.mm.jpa.hibernate.dbmadvanced.entity.Course;
import com.mm.jpa.hibernate.dbmadvanced.entity.Student;

@SpringBootTest(classes = DbmAdvancedApplication.class)
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseReposity courseReposity;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Courses without studens->{}", resultList);
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Courses with at least 2 studens->{}", resultList);
	}
	
	@Test
	public void jpql_courses_order_by_students_count() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Courses order by studens count desc->{}", resultList);
	}
	
	@Test
	public void jpql_students_with_passport_like_1234() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Students with passport contain 1234->{}", resultList);
	}
	
	@Test
	public void jpql_join() {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size ->{}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void jpql_left_join() {
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size ->{}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("{} {}", result[0], result[1]);
		}
	}
	
	@Test
	public void jpql_cross_join() {
		Query query = em.createQuery("Select c, s from Course c, Student s ");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size ->{}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("{} {}", result[0], result[1]);
		}
	}
}