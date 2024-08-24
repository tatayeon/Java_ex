package com.ll.spring_boot_exam_2.domain;

import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {

    //현제 로그인한 회원을 임시구현
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final MemberService memberService;

    public Member getMember(){
        String actorUsername = req.getParameter("actorUsername");

//        if(Ut.str.isBlank(actorUsername)) throw new GlobalException("401-1","로그인이 필요합니다.");

        Member loginedMember = memberService.findMemberByUsername(actorUsername).orElseThrow(() -> new GlobalException("401-2", "인증정보가 올바르지 않습니다."));

        return loginedMember;
    }

    public String getCurrentUrlPath() {
        return req.getRequestURI();
    }

    public void setStatusCode(int statusCode) {
        resp.setStatus(statusCode);
    }
}
