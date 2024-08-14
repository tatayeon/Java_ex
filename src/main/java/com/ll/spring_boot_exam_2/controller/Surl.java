package com.ll.spring_boot_exam_2.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Surl {

    private long id;

    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updateTime = LocalDateTime.now(); ;

    private String url;

    private String body;

}
