package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.service.MemberService;
import com.ll.spring_boot_exam_2.util.ut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {

    private final MemberService memberService;


    //이렇게 요청이 들어올 때의 바디를 따로 빼서 만들어도 가능하다~
    @AllArgsConstructor
    @Getter
    public static class MemberJoinReqBody{
        private String username;
        private String password;
        private String nickname;
    }

    //CRUD에 맞춰서 사용해야 한다.

    @PostMapping("") //post는 생성
    public RsData<Member> join(@RequestBody MemberJoinReqBody reqBody) {

        if(ut.str.isBlank(reqBody.username)){
            throw new GlobalException("400-1", "아이디를 입력해주세요"); //throw으로 하는 이유: 리턴 표기가 정밀하게 표현하기가 어렵다. + 그리고 ExceptionHandler사용
        }

        if(ut.str.isBlank(reqBody.password)){
            throw new GlobalException("400-1", "아이디를 입력해주세요");
        }

        if(ut.str.isBlank(reqBody.nickname)){
            throw new GlobalException("400-1", "닉네임을 입력해주세요");
        }
        return memberService.join(reqBody.username, reqBody.password, reqBody.nickname);

    }

}
