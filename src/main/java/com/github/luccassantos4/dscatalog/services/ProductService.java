package com.github.luccassantos4.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.luccassantos4.dscatalog.dto.CategoryDTO;
import com.github.luccassantos4.dscatalog.dto.ProductDTO;
import com.github.luccassantos4.dscatalog.entities.Category;
import com.github.luccassantos4.dscatalog.entities.Product;
import com.github.luccassantos4.dscatalog.repositories.CategoryRepository;
import com.github.luccassantos4.dscatalog.repositories.ProductRepository;
import com.github.luccassantos4.dscatalog.services.exceptions.DataBaseException;
import com.github.luccassantos4.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	// retorna todas as categorias
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAll();

		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		
		CopyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);

		return new ProductDTO(entity);
	}

	private void CopyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName()); 
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear();
		for(CategoryDTO catDto: dto.getCategories()) {
			Category category = categoryRepository.getOne(catDto.getId());
			entity.getCategories().add(category);
		}
		
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {

		try {
			Product entity = repository.getOne(id);
			
			CopyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);

			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found" + id);

		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");

		}
	}

	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);

		return list.map(x -> new ProductDTO(x));
	}

}
