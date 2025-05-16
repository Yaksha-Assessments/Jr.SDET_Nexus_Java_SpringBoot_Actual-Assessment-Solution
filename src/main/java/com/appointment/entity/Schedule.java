package com.appointment.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedules")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	@Column(nullable = false)
	private String day;

	@Column(nullable = false)
	private LocalTime time;

	@Column(nullable = false)
	private String timings;

	@Column(name = "name_of_patient")
	private String nameOfPatient;

	public Schedule() {
		super();
	}

	public Schedule(Long id, String name, Doctor doctor, String day, LocalTime time, String timings,
			String nameOfPatient) {
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
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
		return "Schedule [id=" + id + ", name=" + name + ", doctor=" + doctor + ", day=" + day + ", time=" + time
				+ ", timings=" + timings + ", nameOfPatient=" + nameOfPatient + "]";
	}
}
