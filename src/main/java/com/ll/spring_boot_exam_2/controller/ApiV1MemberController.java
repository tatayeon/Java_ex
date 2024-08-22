package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.dto.MemberDTO;
import com.ll.spring_boot_exam_2.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1MemberController {

    private final MemberService memberService;

    //이렇게 요청이 들어올 때의 바디를 따로 빼서 만들어도 가능하다~
    //여기는 내가 입력받을 것
    @AllArgsConstructor
    @Getter
    public static class MemberJoinReqBody{
        @NotBlank(message = "아이디를 입력해주세여") //val으로 걸어두고 사용할 때도 꼭 valid를 사용해야한다.
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String nickname;
    }
    //여기는 내가 내보내고 싶은 것
    //data안쪽을 디자인 할 수 있다.
    @AllArgsConstructor
    @Getter
    public static class MemberJoinRespBody{
        MemberDTO item;
    }

    //CRUD에 맞춰서 사용해야 한다.
    //클라이언트가 접속할 수 있는것 = 엔드포인트
    @PostMapping("") //post는 생성
    @Transactional
    public RsData<MemberJoinRespBody> join(@RequestBody @Valid MemberJoinReqBody reqBody) {

       RsData<Member> joinRs = memberService.join(reqBody.username, reqBody.password, reqBody.nickname);

       return joinRs.newDataOf( // 이렇게 하면 joinRs를 기반으로 하지만 아래의 값을 데이터로 한 것으로 나온다 newDataof 때문에
               new MemberJoinRespBody(
                       new MemberDTO(
                               joinRs.getData()
                       )
               )
       );
    }

}
