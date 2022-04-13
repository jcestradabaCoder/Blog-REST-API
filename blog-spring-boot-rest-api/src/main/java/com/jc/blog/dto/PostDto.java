package com.jc.blog.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Post model information")

@Data //Lombok(Empty Constructor, Getter, Setter, Equals, Hashcode, CanEqual, toString)
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {
	
	@ApiModelProperty(value = "Post id")
	private Long id;
	
	@ApiModelProperty(value = "Post title")
	@NotEmpty(message = "Title should not be null or empty!")
	@Size(min = 2, message = "Post title should have at least 2 characters!")
	private String title;
	
	@ApiModelProperty(value = "Post description")
	@NotEmpty(message = "Description should not be null or empty!")
	@Size(min = 10, message = "Post description should have at least 10 characters!")
	private String description;
	
	@ApiModelProperty(value = "Post content")
	@NotEmpty(message = "Content should not be null or empty!")
	@Size(min = 2, message = "Post content should have at least 2 characters!")
	private String content;
	
	@ApiModelProperty(value = "Post comments")
	private Set<CommentDto> comments;
}