package com.ll.spring_boot_exam_2.security;

import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Rq;
import com.ll.spring_boot_exam_2.service.MemberService;
import com.ll.spring_boot_exam_2.util.UT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final MemberService memberService;
    private final Rq rq;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) {
        String apiKey = rq.getCookieValue("apiKey", null);

        if(apiKey == null){
            String authorization = req.getHeader("Authorization");
            if(authorization != null){
                apiKey = authorization.substring("bearer".length());
            }
        }

        if(UT.str.isBlank(apiKey)){
            filterChain.doFilter(req, resp);
            return;
        }

        Member loginedMember = memberService.findMemberByApiKey(apiKey).orElse(null);

        if(loginedMember == null){
            filterChain.doFilter(req, resp);
            return;
        }
        User user = new User(loginedMember.getId() + "", "", List.of());
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication); //인증 정보 만드는 것 그냥 외워

        filterChain.doFilter(req, resp);
    }


}