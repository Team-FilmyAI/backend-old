package com.filmyai.backend.dto;

import java.time.LocalDate;

public class ProjectLocationDto {

	private Long projectId;
	private Long countryId;
    private Long stateId;
    private Long cityId;
    private LocalDate shootingStartDate;
    private LocalDate shootingEndDate;
    
    public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public LocalDate getShootingStartDate() {
		return shootingStartDate;
	}
	public void setShootingStartDate(LocalDate shootingStartDate) {
		this.shootingStartDate = shootingStartDate;
	}
	public LocalDate getShootingEndDate() {
		return shootingEndDate;
	}
	public void setShootingEndDate(LocalDate shootingEndDate) {
		this.shootingEndDate = shootingEndDate;
	}
    

}
