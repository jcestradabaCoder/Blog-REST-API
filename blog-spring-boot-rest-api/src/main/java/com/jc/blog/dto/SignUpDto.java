package com.jc.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Lombok(Empty Constructor, Getter, Setter, Equals, Hashcode, CanEqual, toString)
@NoArgsConstructor
@AllArgsConstructor

public class SignUpDto {
	
	private String name;
	private String username;
	private String email;
	private String password; 
}