package com.mm.jpa.hibernate.dbmadvanced.reposity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.mm.jpa.hibernate.dbmadvanced.DbmAdvancedApplication;
import com.mm.jpa.hibernate.dbmadvanced.entity.Course;

@SpringBootTest(classes = DbmAdvancedApplication.class)
class CourseReposityTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseReposity courseReposity;
	
	@Test
	void findById() {
		Course course = courseReposity.findById(1001L);
		assertEquals("JPA in 10 Steps", course.getName());
		logger.info("Testing 'findById' method");
	}

	@Test
	@DirtiesContext
	void deleteById() {
		courseReposity.deleteById(1001L);
		assertNull(courseReposity.findById(1001L));;
		logger.info("Testing 'deleteById' method");
	}
	
	
	@Test
	void save() {
		Course course = courseReposity.findById(1001L);
		course.setName("JPA in 10 Steps - Updated");
		courseReposity.save(course);
		
		Course courseUpdate = courseReposity.findById(1001L);
		assertEquals("JPA in 10 Steps - Updated", courseUpdate.getName());
	}
}