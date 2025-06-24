package com.appointment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "hospital_name", nullable = false)
	private String hospitalName;

	@Column(nullable = false)
	private String specialty;

	@Column(name = "daily_time", nullable = false)
	private String dailyTime;

	public Doctor() {
		super();
	}

	public Doctor(Long id, String name, String hospitalName, String specialty, String dailyTime) {
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
		return "Doctor [id=" + id + ", name=" + name + ", hospitalName=" + hospitalName + ", specialty=" + specialty
				+ ", dailyTime=" + dailyTime + "]";
	}
}
