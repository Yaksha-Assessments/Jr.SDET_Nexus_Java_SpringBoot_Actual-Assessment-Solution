package com.appointment.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ScheduleDTO {
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotNull(message = "Doctor is required")
	private DoctorDTO doctor;

	@NotNull(message = "Day is required")
	private String day;

	@NotNull(message = "Time is required")
	private LocalTime time;

	@NotBlank(message = "Timings are required")
	private String timings;

	private String nameOfPatient;

	public ScheduleDTO() {
		super();
	}

	public ScheduleDTO(Long id, @NotBlank(message = "Name is required") String name,
			@NotNull(message = "Doctor is required") DoctorDTO doctor, @NotNull(message = "Day is required") String day,
			@NotNull(message = "Time is required") LocalTime time,
			@NotBlank(message = "Timings are required") String timings, String nameOfPatient) {
		super();
		this.id = id;
		this.name = name;
		this.doctor = doctor;
		this.day = day;
		this.time = time;
		this.timings = timings;
		this.nameOfPatient = nameOfPatient;
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

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public String getNameOfPatient() {
		return nameOfPatient;
	}

	public void setNameOfPatient(String nameOfPatient) {
		this.nameOfPatient = nameOfPatient;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [id=" + id + ", name=" + name + ", doctor=" + doctor + ", day=" + day + ", time=" + time
				+ ", timings=" + timings + ", nameOfPatient=" + nameOfPatient + "]";
	}
}
