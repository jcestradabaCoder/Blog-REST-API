package com.jc.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jc.blog.entity.Role;
import com.jc.blog.repository.RoleRepository;

@SpringBootApplication
public class BlogSpringBootRestApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogSpringBootRestApiApplication.class, args);
	}
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	//Llenamos la tabla de roles con los roles básicos
	@Override
	public void run(String... args) throws Exception {
		List<Role> roleLst = roleRepository.findAll();
		
		if(findRolByName(roleLst, "ROLE_ADMIN") == false) {
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
		}
		
		if(findRolByName(roleLst, "ROLE_USER") == false) {
			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
		}
	}
	
	public static boolean findRolByName(List<Role> roleLst, String name) {
		Role role = roleLst.stream().filter(x -> x.getName().toLowerCase().equals(name.toLowerCase())).findFirst().orElse(null);
		return role != null;	
	}
}