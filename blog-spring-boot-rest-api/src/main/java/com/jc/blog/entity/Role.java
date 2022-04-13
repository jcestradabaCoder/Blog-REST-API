package com.jc.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok(Empty Constructor, Getter, Setter, Equals, Hashcode, CanEqual, toString)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	
	@Column(name = "name", length= 60, nullable = false)
	private String name;
}