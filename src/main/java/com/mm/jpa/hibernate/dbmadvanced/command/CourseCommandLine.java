package com.mm.jpa.hibernate.dbmadvanced.command;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mm.jpa.hibernate.dbmadvanced.entity.FullTimeEmployee;
import com.mm.jpa.hibernate.dbmadvanced.entity.PartTimeEmployee;
import com.mm.jpa.hibernate.dbmadvanced.reposity.CourseReposity;
import com.mm.jpa.hibernate.dbmadvanced.reposity.EmployeeReposity;

@Component
public class CourseCommandLine implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseReposity courseReposity;
	
	@Autowired
	private EmployeeReposity employeeReposity;
	
	@Override
	public void run(String... args) throws Exception {
//		logger.info("Course 1001 -> {}", courseReposity.findById(1001L));
//		courseReposity.deleteById(1002L);
//		logger.info("Creating -> {}", courseReposity.save(new Course("Microservices in 100 steps")));
//		courseReposity.playWithEntityManager4();
//		courseReposity.addHardcodeReviewsForCourse();
		
//		List<Review> reviews = new ArrayList<>();
//		
//		reviews.add(new Review("5", "Great Hands-on Stuff"));
//		reviews.add(new Review("5", "Hatsoff"));
//		
//		courseReposity.addReviewsForCourse(10003L, reviews);
		
//		courseReposity.addHardcodeStudentAndCourse();
		
//		courseReposity.addStudentAndCourse( new Student("Jack"), new Course("Microservices in 100 steps"));
	
		employeeReposity.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeReposity.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		logger.info("Employees -> {}", employeeReposity.retrieveAllEmployees());
	}

}
