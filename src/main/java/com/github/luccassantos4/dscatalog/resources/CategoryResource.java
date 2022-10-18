package com.github.luccassantos4.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.luccassantos4.dscatalog.dto.CategoryDTO;
import com.github.luccassantos4.dscatalog.services.CategoryService;

/*resource implementa o controlador rest (gerencia as requisições) */
@Controller
public class CategoryResource {

	@Autowired
	private CategoryService service;

	// Carrega a pagina de listar categorias
	@GetMapping(value = "/categories")
	public String findAll(Model model) {
		List<CategoryDTO> list = service.findAll();
		model.addAttribute("categoryList", list);
		return "views/category/indexCategory";
	}
	
	// Carrega o formulario de criação de categoria
	@GetMapping(value = "/categories/new")
	public String newCategory(@ModelAttribute("categoryForm") CategoryDTO category) {
		return "views/category/categoryForm";
	}

	// Carrega o formulario de editar categoria
	@GetMapping(value = "/categories/edit/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		CategoryDTO categoryOpt = service.findById(id);
	
		model.addAttribute("categoryForm", categoryOpt);
			
		return "views/category/categoryForm";
	}
	
	//submete o editar
	@PostMapping(value = "/categories/edit/save/{id}")
	public String update(@PathVariable("id") long id, CategoryDTO dto) {
		dto = service.update(id, dto);
		return "redirect:/categories";
	}
	
	//submete o create
	@PostMapping(value = "/categories/save")
	public String insert(CategoryDTO dto, Model model) {
		dto = service.insert(dto);
		return "redirect:/categories";
	}

	//submete a exclusão
	@GetMapping(value = "/categories/delete/{id}")
	public String update(@PathVariable Long id) {
		service.delete(id);

		return "redirect:/categories";
	}

}
