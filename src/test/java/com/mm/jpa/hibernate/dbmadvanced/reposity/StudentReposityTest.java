package com.mm.jpa.hibernate.dbmadvanced.reposity;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mm.jpa.hibernate.dbmadvanced.DbmAdvancedApplication;
import com.mm.jpa.hibernate.dbmadvanced.entity.Passport;
import com.mm.jpa.hibernate.dbmadvanced.entity.Student;

@SpringBootTest(classes = DbmAdvancedApplication.class)
class StudentReposityTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Autowired
	private StudentReposity studentReposity;
	
	@BeforeEach
	void init() {
		logger.info("*************** StudentReposityTest *********************");
	}
	
	@Test
	void someOperation() {
		studentReposity.someOperationToUnderstandPersistenceContext();
	}
	
	@Test
	@Transactional
	void retrieveStudentAndAssociation() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student ->{}", student);
		logger.info("Passport-{}", student.getPassport());
	}
	
	@Test
	@Transactional
	void retrievePassportAndAssociation() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("Passport ->{}", passport);
//		logger.info("Student ->{}", passport.getStudent());
	}
	
	
}