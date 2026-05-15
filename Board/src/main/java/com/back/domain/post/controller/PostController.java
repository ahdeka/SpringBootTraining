package com.back.domain.post.controller;

import com.back.domain.post.dto.CreatePostRequest;
import com.back.domain.post.dto.CreatePostResponse;
import com.back.domain.post.service.PostService;
import com.back.global.rsData.RsData;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "게시글", description = "게시글 API 컨트롤러")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<RsData<CreatePostResponse>> createPost(
            @Valid @RequestBody CreatePostRequest request ) {

        CreatePostResponse response = postService.createPost(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RsData.of("201", "게시글이 작성됐습니다.", response));
    }

}