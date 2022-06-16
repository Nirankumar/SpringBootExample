package com.intel.assignment.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class IPInfo {
	
	@JsonIgnore
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String type;
	
	@NotBlank
	@Column(name="ip_value")
	private String value;	

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime firstSeen;

	@Range(min=0,max=100)
	private int totalCount;
}
