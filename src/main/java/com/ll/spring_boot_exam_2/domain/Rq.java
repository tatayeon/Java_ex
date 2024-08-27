package com.ll.spring_boot_exam_2.domain;

import com.ll.spring_boot_exam_2.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

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

        long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        member = memberService.findById(id).get();

        return member;

    }

    public String getCurrentUrlPath() {
        return req.getRequestURI();
    }

    public void setStatusCode(int statusCode) {
        resp.setStatus(statusCode);
    }
    //cookie 관련
    public String getCookieValue(String cookieName, String defaultValue) {
        if(req.getCookies() == null){
            return defaultValue;
        }

        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(defaultValue);
    }
    //결국 로그아웃은 쿠키의 값을 지워버리는 것이다.
    public void removeCookie(String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0); //수명 단축
        cookie.setPath("/");
        resp.addCookie(cookie);

    }

    public void setCookie(String username, String actorUsername) {
        Cookie cookie = new Cookie(username, actorUsername);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        resp.addCookie(cookie);
    }
}
