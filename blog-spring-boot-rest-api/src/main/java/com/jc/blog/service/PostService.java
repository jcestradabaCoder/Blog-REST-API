package com.jc.blog.service;

import com.jc.blog.dto.PostDto;
import com.jc.blog.dto.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
	PostDto getPostById(Long postId);
	PostDto updatePost(Long postId, PostDto postDto);
	void deletePost(Long postId);
}