package com.filmyai.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RoleDto {
	
	private Long projectId;
	private String name;
	private String characterDescription;
	private Integer minAge;
	private Integer maxAge;
	private Long genderId;
	private Long currencyId;
	private BigDecimal compensation;
	private BigDecimal compensationBonus;
	private LocalDate deadlineToApply;
	
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCharacterDescription() {
		return characterDescription;
	}
	public void setCharacterDescription(String characterDescription) {
		this.characterDescription = characterDescription;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	public Long getGenderId() {
		return genderId;
	}
	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}
	public Long getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}
	public BigDecimal getCompensation() {
		return compensation;
	}
	public void setCompensation(BigDecimal compensation) {
		this.compensation = compensation;
	}
	public BigDecimal getCompensationBonus() {
		return compensationBonus;
	}
	public void setCompensationBonus(BigDecimal compensationBonus) {
		this.compensationBonus = compensationBonus;
	}
	public LocalDate getDeadlineToApply() {
		return deadlineToApply;
	}
	public void setDeadlineToApply(LocalDate deadlineToApply) {
		this.deadlineToApply = deadlineToApply;
	}
	
}
