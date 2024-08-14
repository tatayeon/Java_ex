package com.ll.spring_boot_exam_2.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Todo {
    private long id;

    private String body;

}
