package com.mm.jpa.hibernate.dbmadvanced.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mm.jpa.hibernate.dbmadvanced.entity.Course;
import com.mm.jpa.hibernate.dbmadvanced.reposity.CourseReposity;

@Component
public class CourseCommandLine implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseReposity courseReposity;
	
	@Override
	public void run(String... args) throws Exception {
//		logger.info("Course 1001 -> {}", courseReposity.findById(1001L));
//		courseReposity.deleteById(1002L);
//		logger.info("Creating -> {}", courseReposity.save(new Course("Microservices in 100 steps")));
//		courseReposity.playWithEntityManager4();
		
	}

}
