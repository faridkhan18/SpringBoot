package com.spring.example.Basic.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Greeting")
public class Greeting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String text;
	public Greeting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Greeting(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	@Override
	public String toString() {
		return "Greeting [id=" + id + ", text=" + text + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
