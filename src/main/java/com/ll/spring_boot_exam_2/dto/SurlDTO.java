package com.ll.spring_boot_exam_2.dto;


import com.ll.spring_boot_exam_2.domain.Surl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SurlDTO {

    private long id;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private long authorId;

    private String authorName;

    private String body;

    private String url;

    private long count;

    public SurlDTO(Surl surl) {
        this.id = surl.getId();
        this.createTime = surl.getCreateDate();
        this.modifyTime = surl.getModifyDate();
        this.authorId = surl.getAuthor().getId();
        this.authorName = surl.getAuthor().getName();
        this.body = surl.getBody();
        this.url = surl.getUrl();
        this.count = surl.getCount();
    }

}
