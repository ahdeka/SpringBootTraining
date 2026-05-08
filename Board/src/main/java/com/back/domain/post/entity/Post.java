package com.back.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "Posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}
