package com.myblogD2.services;

import com.myblogD2.payloads.PostDto;

public interface PostService {
    PostDto savePost(PostDto postDto);

    void deletePost(long id);

    PostDto updatePost(long id, PostDto postDto);

    PostDto getPostById(long id);
}
