package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Rq;
import com.ll.spring_boot_exam_2.domain.Surl;
import com.ll.spring_boot_exam_2.dto.Empty;
import com.ll.spring_boot_exam_2.dto.SurlDTO;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.service.AuthService;
import com.ll.spring_boot_exam_2.service.MemberService;
import com.ll.spring_boot_exam_2.service.SurlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/surls")
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiV1SurlController {
    private final Rq rq;
    private final SurlService surlService;
    private final AuthService authService;
    private final MemberService memberService;

    @Getter
    @AllArgsConstructor //우리는 이 방법도 있긴한데 DTO를 작성해서 하는 방법을 사용했었다. 알면 좋을 듯
    public static class SurlAddReqBody{
        @NotBlank
        private String body;
        @NotBlank
        private String url;
    }

    @Getter
    @AllArgsConstructor
    public static class SurlAddRespBody{
        private SurlDTO item;
    }

    @PostMapping("/add")
    @Transactional
    public RsData<SurlAddRespBody> add(
            @RequestBody @Valid SurlAddReqBody reqBody
    ){
        Member member = rq.getMember(); //현제 브라우저로 로그인한 회원 정보

        RsData<Surl> addRs = surlService.add(reqBody.getBody(), reqBody.getUrl(), member);

        return addRs.newDataOf(
                new SurlAddRespBody(
                        new SurlDTO(addRs.getData())
                ));
    }

    @Getter
    @AllArgsConstructor
    public static class SurlGetRespBody{
        private SurlDTO item;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RsData<SurlGetRespBody> get(@PathVariable long id){

        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        authService.checkCanGetSurl(rq.getMember(), surl);

        return RsData.of(
                new SurlGetRespBody(
                        new SurlDTO(surl)
                )
        );
    }

    @Getter
    @AllArgsConstructor
    public static class SurlDeleteRespBody{
        private SurlDTO item;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public RsData<Empty> delete(@PathVariable long id){


        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        authService.checkCanDeleteSurl(rq.getMember(), surl);

        surlService.delete(id);

        return RsData.OK;

    }

    @Getter
    @AllArgsConstructor
    public static class SurlsGetItemsRespBody{
        private List<SurlDTO> items;
    }

    @GetMapping("")
    @ResponseBody
    public RsData<SurlsGetItemsRespBody> getitems(
            String actorUserName
    ){
        Member loginedMember = memberService.findMemberByUsername(actorUserName).orElseThrow(GlobalException.E404::new);
        rq.setMember(loginedMember);

        Member member = rq.getMember();

        List<Surl> surls = surlService.findByAuthorOrderByIdDesc(member); //가능하면 이거 그대로 Service명도 맞춰주면 좋다.

        return RsData.of(
                new SurlsGetItemsRespBody(
                        surls.stream()
                                .map(SurlDTO::new)
                                .toList()
                )
        );

    }

    @Getter
    @AllArgsConstructor //우리는 이 방법도 있긴한데 DTO를 작성해서 하는 방법을 사용했었다. 알면 좋을 듯
    public static class SurlModifyReqBody{
        @NotBlank
        private String body;
        @NotBlank
        private String url;
    }

    @Getter
    @AllArgsConstructor
    public static class SurlModifyRespBody{
        private SurlDTO item;
    }

    @PutMapping("/{id}")
    @Transactional
    public RsData<SurlModifyRespBody> modify(
            @RequestBody @Valid SurlModifyReqBody reqBody,
            @PathVariable long id
    ){
        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        authService.checkCanModifySurl(rq.getMember(), surl);

        RsData<Surl> modifyRs = surlService.modify(surl, reqBody.body, reqBody.getUrl());

        return modifyRs.newDataOf(
                new SurlModifyRespBody(
                        new SurlDTO(modifyRs.getData())
                ));
    }




}
