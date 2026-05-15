package com.back.domain.post.service;

import com.back.domain.post.dto.CreatePostRequest;
import com.back.domain.post.dto.CreatePostResponse;
import com.back.domain.post.entity.Post;
import com.back.domain.post.repository.PostRepository;
import com.back.domain.user.entity.User;
import com.back.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request) {

        Post post = create(request);

        log.info("게시글 작성 완료 postId: {}", post.getId());

        return new CreatePostResponse(post.getId(), post.getCreatedAt());
    }

    private Post create(CreatePostRequest request) {
        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .build();

        return postRepository.save(post);
    }

}
