package com.spring.example.Basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.example.Basic.model.Greeting;
@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}
