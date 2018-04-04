package com.spring.example.Basic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.example.Basic.model.Greeting;
import com.spring.example.Basic.repository.GreetingRepository;

@Service("service2")
public class GreetingServiceImpl2 implements GreetingService{
	@Autowired
	GreetingRepository greetingRepository;
	
	@Override
	public Collection<Greeting> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Greeting> findOne(Long id) {
		
		return greetingRepository.findById(id); 
	}
	@Override
	public Greeting create(Greeting greeting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Greeting update(Greeting greeting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evictCache() {
		// TODO Auto-generated method stub
		
	}

}
