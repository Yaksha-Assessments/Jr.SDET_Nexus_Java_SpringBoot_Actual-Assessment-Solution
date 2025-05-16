package com.appointment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DoctorDTO {
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Hospital name is required")
	private String hospitalName;

	@NotBlank(message = "Specialty is required")
	private String specialty;

	@NotNull(message = "Daily time is required")
	private String dailyTime;

	public DoctorDTO() {
		super();
	}

	public DoctorDTO(Long id, @NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Hospital name is required") String hospitalName,
			@NotBlank(message = "Specialty is required") String specialty,
			@NotNull(message = "Daily time is required") String dailyTime) {
		super();
		this.id = id;
		this.name = name;
		this.hospitalName = hospitalName;
		this.specialty = specialty;
		this.dailyTime = dailyTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getDailyTime() {
		return dailyTime;
	}

	public void setDailyTime(String dailyTime) {
		this.dailyTime = dailyTime;
	}

	@Override
	public String toString() {
		return "DoctorDTO [id=" + id + ", name=" + name + ", hospitalName=" + hospitalName + ", specialty=" + specialty
				+ ", dailyTime=" + dailyTime + "]";
	}
}
