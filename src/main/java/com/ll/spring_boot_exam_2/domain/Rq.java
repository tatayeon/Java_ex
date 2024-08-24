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
    private  Member member;

    public Member getMember(){
        if(member != null){
            return member;  //최초의 한번은 모두 진행이 되지만 이걸로 인해서 두번째는 실행되지 않고 바로 넘겨준다. (메모리 캐싱)
        }

        String actorUsername = req.getParameter("actorUsername");
        String actorPassword = req.getParameter("actorPassword");

//        if(Ut.str.isBlank(actorUsername)) throw new GlobalException("401-1","로그인이 필요합니다.");

        Member loginedMember = memberService.findMemberByUsername(actorUsername).orElseThrow(() -> new GlobalException("401-2", "인증정보가 올바르지 않습니다."));
        if(!loginedMember.getPassword().equals(actorPassword)){
            throw new GlobalException("401-3", "비밀번호가 일치하지 않습니다.");
        }

        member = loginedMember;

        return loginedMember;
    }

    public String getCurrentUrlPath() {
        return req.getRequestURI();
    }

    public void setStatusCode(int statusCode) {
        resp.setStatus(statusCode);
    }
}
