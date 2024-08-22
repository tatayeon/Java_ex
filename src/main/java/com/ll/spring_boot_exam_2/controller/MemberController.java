package com.ll.spring_boot_exam_2.controller;


import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.service.MemberService;
import com.ll.spring_boot_exam_2.util.ut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberController {


    private final MemberService memberService;

    @GetMapping("/join")
    @ResponseBody
    @Transactional
    public RsData join(String username, String password, String nickname){
        if(ut.str.isBlank(username)){
            throw new GlobalException("400-1", "아이디를 입력해주세요"); //throw으로 하는 이유: 리턴 표기가 정밀하게 표현하기가 어렵다. + 그리고 ExceptionHandler사용
        }

        if(ut.str.isBlank(password)){
            throw new GlobalException("400-1", "아이디를 입력해주세요");
        }

        if(ut.str.isBlank(nickname)){
            throw new GlobalException("400-1", "닉네임을 입력해주세요");
        }

        try{
            return memberService.join(username, password, nickname);
        } catch (GlobalException e) {
            if(e.getRsData().getResultCode().equals("400-1")){
                log.debug("이미 존재하는 아이디입니다.");
            }
            return RsData.of("400-A", "커스텀 예외 메세지", Member.builder().build());
        }

    }
}
