package com.filmyai.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmyai.backend.dto.RoleDto;
import com.filmyai.backend.exception.BadRequestException;
import com.filmyai.backend.exception.NotFoundException;
import com.filmyai.backend.model.Currency;
import com.filmyai.backend.model.Gender;
import com.filmyai.backend.model.Project;
import com.filmyai.backend.model.Role;
import com.filmyai.backend.repository.CurrencyRepository;
import com.filmyai.backend.repository.GenderRepository;
import com.filmyai.backend.repository.ProjectRepository;
import com.filmyai.backend.repository.RoleRepository;

@RestController
@RequestMapping("/api/auth/roles")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private GenderRepository genderRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@GetMapping
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@GetMapping("/{id}")
	public Role getRoleById(@PathVariable Long id) {
		return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role with id: " + id+" not found"));
	}

	@PostMapping
	public Role createRole(@RequestBody RoleDto dto) {
		if (dto.getName() == null || dto.getName().isBlank()) {
			throw new BadRequestException("Role name must not be empty");
		}

		if (dto.getMinAge() != null && dto.getMaxAge() != null && dto.getMinAge() > dto.getMaxAge()) {
			throw new BadRequestException("minAge cannot be greater than maxAge");
		}

		if (dto.getProjectId() == null) {
			throw new BadRequestException("Project ID is required");
		}
		if (dto.getGenderId() == null) {
			throw new BadRequestException("Gender ID is required");
		}
		if (dto.getCurrencyId() == null) {
			throw new BadRequestException("Currency ID is required");
		}

		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new NotFoundException("Project with id: " + dto.getProjectId() + " not found"));

		Gender gender = genderRepository.findById(dto.getGenderId())
				.orElseThrow(() -> new NotFoundException("Gender with id: " + dto.getGenderId() + " not found"));

		Currency currency = currencyRepository.findById(dto.getCurrencyId())
				.orElseThrow(() -> new NotFoundException("Currency with id: " + dto.getCurrencyId() + " not found"));

		Role role = new Role();
		role.setName(dto.getName());
		role.setCharacterDescription(dto.getCharacterDescription());
		role.setMinAge(dto.getMinAge());
		role.setMaxAge(dto.getMaxAge());
		role.setCompensation(dto.getCompensation());
		role.setCompensationBonus(dto.getCompensationBonus());
		role.setDeadlineToApply(dto.getDeadlineToApply());
		role.setProject(project);
		role.setGender(gender);
		role.setCurrency(currency);

		return roleRepository.save(role);
	}

	@PutMapping("/{id}")
	public Role updateRole(@PathVariable Long id, @RequestBody RoleDto dto) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Role with id: " + id+" not found"));

		if (dto.getName() != null && !dto.getName().isBlank())
			role.setName(dto.getName());

		if (dto.getCharacterDescription() != null && !dto.getCharacterDescription().isBlank())
			role.setCharacterDescription(dto.getCharacterDescription());

		if (dto.getMinAge() != null)
			role.setMinAge(dto.getMinAge());

		if (dto.getMaxAge() != null)
			role.setMaxAge(dto.getMaxAge());

		if (dto.getCompensation() != null)
			role.setCompensation(dto.getCompensation());

		if (dto.getCompensationBonus() != null)
			role.setCompensationBonus(dto.getCompensationBonus());

		if (dto.getDeadlineToApply() != null)
			role.setDeadlineToApply(dto.getDeadlineToApply());

		if (dto.getProjectId() != null) {
			Project project = projectRepository.findById(dto.getProjectId())
					.orElseThrow(() -> new NotFoundException("Project with id: " + dto.getProjectId() + " not found"));
			role.setProject(project);
		}

		if (dto.getGenderId() != null) {
			Gender gender = genderRepository.findById(dto.getGenderId())
					.orElseThrow(() -> new NotFoundException("Gender with id: " + dto.getGenderId() + " not found"));
			role.setGender(gender);
		}

		if (dto.getCurrencyId() != null) {
			Currency currency = currencyRepository.findById(dto.getCurrencyId())
					.orElseThrow(() -> new NotFoundException("Currency with id: " + dto.getCurrencyId() + " not found"));
			role.setCurrency(currency);
		}

		return roleRepository.save(role);
	}

	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable Long id) {
		if (!roleRepository.existsById(id)) {
			throw new NotFoundException("Role with id: " + id+" not found");
		}
		roleRepository.deleteById(id);
	}

}