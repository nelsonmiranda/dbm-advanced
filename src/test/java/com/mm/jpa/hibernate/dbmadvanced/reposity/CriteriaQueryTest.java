package com.mm.jpa.hibernate.dbmadvanced.reposity;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

}