package com.ll.spring_boot_exam_2.dto;

import com.ll.spring_boot_exam_2.domain.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private long id;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private long authorId;

    private String authorName;

    private String title;

    private String body;

    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.createTime = article.getCreateDate();
        this.modifyTime = article.getModifyDate();
        this.authorId = article.getAuthor().getId();
        this.authorName = article.getAuthor().getName();
        this.title = article.getTitle();
        this.body = article.getBody();
    }

}
