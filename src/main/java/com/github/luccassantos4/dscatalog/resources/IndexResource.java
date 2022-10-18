package com.github.luccassantos4.dscatalog.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexResource {

	@GetMapping("/menu")
	public String index() {
		return "layout_admin/index";
	}
}
