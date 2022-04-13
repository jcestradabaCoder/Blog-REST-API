package com.jc.blog.service;

import java.util.List;

import com.jc.blog.dto.CommentDto;

public interface CommentService {
	
	CommentDto createComment(Long postId, CommentDto commentDto);
	List<CommentDto> getCommentsByPostId(Long postId);
	CommentDto getCommentById(Long postId, Long commentId);
	CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
	void deleteComment(Long postId, Long commentId);
}