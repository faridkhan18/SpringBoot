package com.spring.example.Basic.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.example.Basic.model.Greeting;
import com.spring.example.Basic.repository.GreetingRepository;

@Service("service1")
public class GreetingServiceImpl implements GreetingService {
	
	
	Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GreetingRepository greetingRepository;

	@Override
	public Collection<Greeting> findAll() {
		logger.info("> findAll");
		return  greetingRepository.findAll();
	}

	@Override
	public Optional<Greeting> findOne(Long id) {
		logger.info("> findOne id:{}", id);
        
		return greetingRepository.findById(id);
		

	}

	@Override
	public Greeting create(Greeting greeting) {
		logger.info("> create");
		
		
		// repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (greeting.getId() != null) {
            // Cannot create Greeting with specified ID value
            logger.error(
                    "Attempted to create a Greeting, but id attribute was not null.");
            throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
        }
		
        logger.info("< create");
		
		
		return greetingRepository.save(greeting);
	}

	@Override
	public Greeting update(Greeting greeting) {
		logger.info("> update id:{}", greeting.getId());
		
		Optional<Greeting> greetingToUpdate = findOne(greeting.getId());
		
		if(!greetingToUpdate.isPresent()){
			 logger.error(
	                    "Attempted to update a Greeting, but the entity does not exist.");
	            throw new NoResultException("Requested entity not found.");
		}
		
		greetingToUpdate.get().setText(greeting.getText());
		Greeting updatedGreeting = greetingRepository.save(greetingToUpdate.get());
		
		return updatedGreeting;
	}

	@Override
	public void delete(Long id) {
		logger.info("> delete id:{}", id);
		greetingRepository.deleteById(id);
		logger.info("< delete id:{}", id);	
	}

	@Override
	public void evictCache() {
		// TODO Auto-generated method stub

	}

}
