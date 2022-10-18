package com.github.luccassantos4.dscatalog.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.luccassantos4.dscatalog.dto.ProductDTO;
import com.github.luccassantos4.dscatalog.services.ProductService;

/*resource implementa o controlador rest (gerencia as requisições) */
@Controller
//@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	// página lista todos os produtos
	@GetMapping(value = "products")
	public String findAll(Model model) {
		List<ProductDTO> list = service.findAll();
			
		model.addAttribute("productsList", list);

		return "views/product/indexProduct";
	}

	// carrega o formulario de criar produtos
	@GetMapping(value = "/product/new")
	public String newCategory(@ModelAttribute("productForm") ProductDTO product) {
		
		return "views/product/productForm";
	}

	// submete o create
	@PostMapping(value = "product/save")
	public String insert(ProductDTO dto) {
		dto = service.insert(dto);

		return "redirect:/products";
	}
	
	
	//carrega o formulario de edição de produtos
	@GetMapping(value = "/product/edit/{id}")
	public String findById(@PathVariable Long id, Model model) {
		ProductDTO dto = service.findById(id);
		
		model.addAttribute("productForm", dto);

		return "views/product/productForm";
	}
	
	//submete o formulario de edição
	@PostMapping(value = "/product/edit/save/{id}")
	public String update(@PathVariable Long id, ProductDTO dto) {
		
		dto = service.update(id, dto);
		
		return "redirect:/products";
	}

	//submete a exclusão do produto
	@GetMapping(value = "/product/delete/{id}")
	public String update(@PathVariable Long id) {
		service.delete(id);
		
		return "redirect:/products";
	}

}
