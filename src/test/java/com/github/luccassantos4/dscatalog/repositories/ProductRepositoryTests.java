package com.github.luccassantos4.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.github.luccassantos4.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repository;
	
	private Long exitingId;
	private Long nonExitingId;
	
	@BeforeEach
	void setUp() throws Exception {
		long exitingId = 1L;
		Long nonExitingId = 1000L;
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(exitingId);
		
		Optional<Product> result = repository.findById(exitingId);
		
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteSholdDeleteEmptyDataAcessExceptionsWhenIdNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExitingId);
		});
	}
	
}




