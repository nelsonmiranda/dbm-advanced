package com.mm.jpa.hibernate.dbmadvanced.reposity;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mm.jpa.hibernate.dbmadvanced.DbmAdvancedApplication;
import com.mm.jpa.hibernate.dbmadvanced.entity.Course;
import com.mm.jpa.hibernate.dbmadvanced.entity.Student;

@SpringBootTest(classes = DbmAdvancedApplication.class)
class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseReposity courseReposity;

	@Autowired
	EntityManager em;

	@Test
	public void jpql_criteria_basic() {
		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		// result object
		// 2. Define roots for tables which are involved in the query
		// 3. Define Predicates etc using Criteria Builder
		// 4. Add Predicates etc to the Criteria Builder
		// 5. Build the TypedQuery using the entity manager and criteria query

		// 1
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 5
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		logger.info("Courses ->{}", resultList);
	}
	
	@Test
	public void jpql_criteria_like() {
		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate like = cb.like(courseRoot.get("name"), "%100%");
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(like);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		logger.info("Courses ->{}", resultList);
	}

	@Test
	public void jpql_criteria_empty() {
		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate isEmpty = cb.isEmpty(courseRoot.get("students"));
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(isEmpty);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		logger.info("Courses ->{}", resultList);
//		[Course [id=10002, name=Spring in 10 Steps]]
	}
	
	@Test
	public void jpql_criteria_join() {
		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		courseRoot.join("students");
				
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		logger.info("Courses ->{}", resultList);
//		[Course [id=10001, name=JPA in 10 Steps], Course [id=10001, name=JPA in 10 Steps], Course [id=10001, name=JPA in 10 Steps], 
//		Course [id=10003, name=Spring Boot in 100 Steps]]
	}
	
	@Test
	public void jpql_criteria_join_left() {
		// 1. Use Criteria Builder to create a Criteria Query returning the expected
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		courseRoot.join("students", JoinType.LEFT);
				
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		logger.info("Courses ->{}", resultList);
//		[Course [id=10001, name=JPA in 10 Steps], Course [id=10001, name=JPA in 10 Steps], Course [id=10001, name=JPA in 10 Steps], 
//		Course [id=10002, name=Spring in 10 Steps], Course [id=10003, name=Spring Boot in 100 Steps]]
	}
}