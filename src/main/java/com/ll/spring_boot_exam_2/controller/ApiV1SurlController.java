package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Rq;
import com.ll.spring_boot_exam_2.domain.Surl;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.service.SurlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/surls")
@RequiredArgsConstructor
@Slf4j
public class ApiV1SurlController {
    private final Rq rq;
    private final SurlService surlService;

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
        private Surl item;
    }

    @PostMapping("/add")
    public RsData<SurlAddRespBody> add(
            @RequestBody @Valid SurlAddReqBody reqBody
    ){
        Member member = rq.getMember(); //현제 브라우저로 로그인한 회원 정보

        RsData<Surl> addRs = surlService.add(reqBody.getBody(), reqBody.getUrl(), member);

        return addRs.newDataOf(
                new SurlAddRespBody(addRs.getData())
        );
    }

    @Getter
    @AllArgsConstructor
    public static class SurlGetRespBody{
        private Surl item;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RsData<SurlGetRespBody> get(@PathVariable long id){

        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        return RsData.of(
                new SurlGetRespBody(surl)
        );
    }





    @GetMapping("/g/{id}")
    public String go(@PathVariable long id) {

        Surl surl = surlService.findById(id).orElseThrow(GlobalException.E404::new);

        surlService.increaseCount(surl);

        return "redirect:" + surl.getUrl();  // 예: "redirect:http://example.com"
    }



    @GetMapping("/all")
    @ResponseBody
    public List<Surl> getAll(){
        return surlService.findAll();
    }

}
