package com.ll.spring_boot_exam_2.controller;


import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.service.MemberService;
import com.ll.spring_boot_exam_2.util.ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @GetMapping("/join")
    @ResponseBody
    public RsData join(String username, String password, String nickname){
        if(ut.str.isBlank(username)){
            throw new GlobalException("40 0-1", "아이디를 입력해주세요");
        }

        if(ut.str.isBlank(password)){
            throw new GlobalException("400-1", "아이디를 입력해주세요");
        }

        if(ut.str.isBlank(nickname)){
            throw new GlobalException("400-1", "닉네임을 입력해주세요");
        }

        RsData<Member> joinRs = memberService.join(username,password,nickname);

        return joinRs;
    }
}
