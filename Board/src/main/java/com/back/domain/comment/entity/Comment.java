package com.back.domain.comment.entity;

import com.back.domain.post.entity.Post;
import com.back.domain.user.entity.User;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "Comments")
public class Comment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parent_id", nullable = false)
    private Comment parent;

    @Column(nullable = false, length = 100)
    private String content;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private int depth;

    @Builder
    public Comment(User user, Post post, Comment parent, String content, int depth) {
        this.user = user;
        this.post = post;
        this.parent = parent;
        this.content = content;
        this.depth = depth;
    }
}
