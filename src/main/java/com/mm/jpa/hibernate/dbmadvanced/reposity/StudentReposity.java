package com.mm.jpa.hibernate.dbmadvanced.reposity;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mm.jpa.hibernate.dbmadvanced.entity.Student;

@Repository
@Transactional
public class StudentReposity {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public Student findById(long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public Student save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		}else {
			em.merge(student);
		}
		return student;
	}
		
	public void playWithEntityManager1() {
		logger.info("playWithEntityManager");
		Student student = new Student("Web services in 10 steps");
		em.persist(student);
		student.setName("Web services in 10 steps - updated");	
	}
	
	public void playWithEntityManager2() {
		logger.info("**************************************");
		Student student1 = new Student("Web services in 10 steps");
		em.persist(student1);
		Student student2 = new Student("Angular JS in 10 steps");
		em.persist(student2);
		
		em.flush();
		
		em.detach(student2);
		
		student1.setName("Web services in 10 steps - updated");	
		em.flush();
		
		student2.setName("Angular JS in 10 steps - updated");	
		em.flush();
	}
	
	public void playWithEntityManager3() {
		logger.info("**************************************");
		Student student1 = new Student("Web services in 10 steps");
		em.persist(student1);
		Student student2 = new Student("Angular JS in 10 steps");
		em.persist(student2);
		
		em.flush();
		
		em.clear();
		
		student1.setName("Web services in 10 steps - updated");	
		em.flush();
		
		student2.setName("Angular JS in 10 steps - updated");	
		em.flush();
	}
	
	public void playWithEntityManager4() {
		logger.info("**************************************");
		Student student1 = new Student("Web services in 10 steps");
		em.persist(student1);
		Student student2 = new Student("Angular JS in 10 steps");
		em.persist(student2);
		
		em.flush();
		
		student1.setName("Web services in 10 steps - updated");	
		student2.setName("Angular JS in 10 steps - updated");	
		
		em.refresh(student1);
		em.flush();
	}
}

