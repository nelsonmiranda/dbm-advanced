package com.mm.jpa.hibernate.dbmadvanced.reposity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mm.jpa.hibernate.dbmadvanced.entity.Card;

@Repository("cardRepository")
public interface CardRepository extends CrudRepository<Card, Long> {
	
	public List<Card> findByName(String name);
	public Optional<Card> findById(Long id);

}
