package com.mm.jpa.hibernate.dbmadvanced.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mm.jpa.hibernate.dbmadvanced.entity.Card;
import com.mm.jpa.hibernate.dbmadvanced.service.CardService;

@RestController
public class CardController {

	@Autowired
	private CardService cardService;
	
	@GetMapping("/cards")
	public List<Card> retrieveCards(){
		return cardService.getAll();
	}
	
//	@GetMapping("/cards/{name}")
//	public List<Card> retrieveByName(@PathVariable String name){
//		return cardService.getByName(name);		
//	}
	
	@GetMapping("/cards/{cardId}")
	public ResponseEntity<Card> retrieveById(@PathVariable Long cardId){
		
		Optional<Card> card = cardService.getById(cardId);
		
		if(!card.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(card.get());	
	}
	
}
