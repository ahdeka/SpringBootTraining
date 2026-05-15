package com.back.domain.post.dto;

import java.time.LocalDateTime;

public record CreatePostResponse(

        Long postId,
        LocalDateTime createdAt
) {
}
