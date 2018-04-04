package com.spring.example.Basic.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.example.Basic.model.Greeting;
import com.spring.example.Basic.service.GreetingService;
import com.spring.example.Basic.service.GreetingServiceImpl;

@RestController
public class GreetingController extends BaseController {

	@Autowired
	@Qualifier("service1")
	GreetingService greetingService;

	/*
	 * public GreetingController(GreetingService greetingService) {
	 * this.greetingService = new GreetingServiceImpl(); logger.info(
	 * "Inside constructer of GreetingController :"+ this.greetingService); }
	 */

	@RequestMapping(value = "/api/greetings", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<Collection<Greeting>> getGreetings() {
		logger.info("> getGreetings");
		Collection<Greeting> greetings = greetingService.findAll();
		
		logger.info("< getGreetings");
        return new ResponseEntity<Collection<Greeting>>(greetings,
                HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
		logger.info("> getGreeting id:{}", id);

		Optional<Greeting> greeting = greetingService.findOne(id);
		if (!greeting.isPresent()) {
			logger.error("< getGreeting id:{} Not Found");
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getGreeting id:{} Found", greeting);

		return new ResponseEntity<Greeting>(greeting.get(), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/api/greetings", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		logger.info("> createGreeting");

        Greeting savedGreeting = greetingService.create(greeting);

        logger.info("< createGreeting");
        return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/greetings/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting, @PathVariable("id") BigInteger id) {
		logger.info("> updateGreeting id:{}", greeting.getId());

        Greeting updatedGreeting = greetingService.update(greeting);
        if (updatedGreeting == null) {
            return new ResponseEntity<Greeting>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< updateGreeting id:{}", greeting.getId());
        return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id) {
		logger.info("> deleteGreeting id:{}", id);

        greetingService.delete(id);

        logger.info("< deleteGreeting id:{}", id);
        return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}

}
