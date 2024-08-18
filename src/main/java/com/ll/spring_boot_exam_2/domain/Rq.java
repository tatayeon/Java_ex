package com.ll.spring_boot_exam_2.domain;

import com.ll.spring_boot_exam_2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final MemberService memberService;

    public Member getMember() {

        return memberService.getMember(1L);

    }
}
