package com.mm.jpa.hibernate.dbmadvanced.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mm.jpa.hibernate.dbmadvanced.entity.Card;
import com.mm.jpa.hibernate.dbmadvanced.reposity.CardRepository;

@Component("cardService")
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
	public List<Card> getAll(){
		return (List<Card>) cardRepository.findAll();
	}
	
	public List<Card> getByName(String name){
		return cardRepository.findByName(name);
	}
	
	public Optional<Card> getById(Long id){
		return cardRepository.findById(id);
	}
}
