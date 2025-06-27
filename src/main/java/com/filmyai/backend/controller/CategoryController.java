package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.CategoryDto;
import com.filmyai.backend.exception.NotFoundException;
import com.filmyai.backend.model.Category;
import com.filmyai.backend.repository.CategoryRepository;

@RestController
@RequestMapping("/api/auth/categories")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping("/{id}")
	public Category getById(@PathVariable Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Category with id: " + id + " not found"));
	}

	@PostMapping
	public Category createCategory(@RequestBody CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		return categoryRepository.save(category);
	}

	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Category with id: " + id + " not found"));

		if (categoryDto.getName() != null && !categoryDto.getName().isBlank()) {
			category.setName(categoryDto.getName());
		}

		return categoryRepository.save(category);

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		if (!categoryRepository.existsById(id)) {
			throw new NotFoundException("Category with id: " + id + " not found");
		}
		categoryRepository.deleteById(id);

	}
}
