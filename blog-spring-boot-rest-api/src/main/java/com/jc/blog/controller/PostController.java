package com.jc.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jc.blog.dto.PostDto;
import com.jc.blog.dto.PostResponse;
import com.jc.blog.service.PostService;
import com.jc.blog.utils.AppConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CRUD REST APIs for Post resources")

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@ApiOperation(value = "Create Post REST API")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Get all Posts REST API")
	@GetMapping("/posts")
	public PostResponse getAllPost(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue =  AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
			
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	@ApiOperation(value = "Get Post by id REST API")
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "postId") Long postId) {
		return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
	}
	
    @ApiOperation(value = "Update Post by id REST API")
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable(name = "postId") Long postId, @Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.updatePost(postId, postDto), HttpStatus.OK);
	}
	
    @ApiOperation(value = "Delete Post by id REST API")
    @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "postId") Long postId) {
		postService.deletePost(postId);
		return new ResponseEntity<>("Post deleted successfully!", HttpStatus.OK);
	}
}