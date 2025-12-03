package com.back.domain.post.post.entity;

import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class PostComment extends BaseEntity {
    @ManyToOne
    private Post post;
    private String content;

    public PostComment(Post post, String content) {
        this.post = post;
        this.content = content;
    }
}
