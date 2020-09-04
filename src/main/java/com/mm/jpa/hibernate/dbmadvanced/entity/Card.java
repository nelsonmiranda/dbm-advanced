package com.mm.jpa.hibernate.dbmadvanced.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Card {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String email;

	protected Card() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
//		String.format("Review[%s %s]", rating, description);
		return "Card [id=" + id + ", name=" + name + ", title=" + title + ", email=" + email + "]";
	}
	
	
}
