package com.myblogD2.services.impl;

import com.myblogD2.entities.Post;
import com.myblogD2.exceptions.ResourceNotFound;
import com.myblogD2.payloads.PostDto;
import com.myblogD2.repositories.PostRepository;
import com.myblogD2.services.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
        private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);

        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);

    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post not found with id:"+id)
        );

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());


        Post updatePost = postRepository.save(post);
        PostDto dto = mapToDto(updatePost);
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post Not found with id :" + id)
        );
        PostDto dto = mapToDto(post);
        return dto;
    }


    PostDto mapToDto(Post post){
         PostDto dto = new PostDto();

         dto.setId(post.getId());
         dto.setTitle(post.getTitle());
         dto.setDescription(post.getDescription());
         dto.setContent(post.getContent());
         
         return dto;
    }
        Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        
        return post;
    }
}
