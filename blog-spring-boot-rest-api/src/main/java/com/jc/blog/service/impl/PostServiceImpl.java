package com.jc.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jc.blog.dto.PostDto;
import com.jc.blog.dto.PostResponse;
import com.jc.blog.entity.Post;
import com.jc.blog.exception.ResourceNotFoundException;
import com.jc.blog.repository.PostRepository;
import com.jc.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Transactional
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		Post newPost = postRepository.save(post);
		
		return mapToDTO(newPost);
	}
	
	@Transactional(readOnly = true)
	@Override
	public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
		
		//Order
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		//Pageable
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postRepository.findAll(pageable);
		
		//Get content
		List<Post> postLst = posts.getContent();
		List<PostDto> content = postLst.stream()
				.map(post -> mapToDTO(post)).collect(Collectors.toList());		
		
		//Mapeo del Response
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}
	
	@Transactional(readOnly = true)
	@Override
	public PostDto getPostById(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		return mapToDTO(post);
	}
	
	@Transactional
	@Override
	public PostDto updatePost(Long postId, PostDto postDto) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
				
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		return mapToDTO(updatedPost);
	}
	
	@Transactional
	@Override
	public void deletePost(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		postRepository.delete(post);
	}
	
	//Convert Entity to DTO
	private PostDto mapToDTO(Post post) {
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
	//Convert DTO to Entity
	private Post mapToEntity(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		return post;
	}
}