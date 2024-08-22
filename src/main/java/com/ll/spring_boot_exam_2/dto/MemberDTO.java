package com.ll.spring_boot_exam_2.dto;

import com.ll.spring_boot_exam_2.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MemberDTO {

    private long id;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private String username;

    private String nickname;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.createTime = LocalDateTime.now();
        this.modifyTime = LocalDateTime.now();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
    }

}
