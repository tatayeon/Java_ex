package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Rq;
import com.ll.spring_boot_exam_2.domain.Surl;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.service.SurlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurlController {
    private final Rq rq;
    private final SurlService surlService;

    @GetMapping("/add ")
    @ResponseBody
    @Transactional
    public RsData<Surl> add(String body, String url){
        Member member = rq.getMember(); //현제 브라우저로 로그인한 회원 정보
        return surlService.add(url ,body, member);
    }

    @GetMapping("/s/{body}/**")
    @ResponseBody
    @Transactional
    public RsData<Surl> add(@PathVariable String body,
                     HttpServletRequest request){
        Member member = rq.getMember();

        String url = request.getRequestURI();

        if(request.getQueryString() != null){
            url = url + "?" + request.getQueryString();
        }

        String[] urlBits = url.split("/", 4);
        url = urlBits[3];

        return surlService.add(url, body, member);
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
