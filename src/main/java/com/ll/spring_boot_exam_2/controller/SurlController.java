package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.domain.Surl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SurlController {

    private List<Surl> surls = new ArrayList<>();
    private long surlsLastId;

    @GetMapping("/add ")
    @ResponseBody
    public Surl add(String body, String url){

        Surl surl = Surl.builder()
                .id(++surlsLastId)
                .body(body)
                .url(url)
                .build();

        surls.add(surl);
        return surl;
    }

    @GetMapping("/s/{body}/**")
    @ResponseBody
    public Surl add(@PathVariable String body,
                     HttpServletRequest request){
        String url = request.getRequestURI();

        if(request.getQueryString() != null){
            url = url + "?" + request.getQueryString();
        }

        String[] urlBits = url.split("/", 4);
        url = urlBits[3];

        Surl surl = Surl.builder()
                .id(++surlsLastId)
                .body(body)
                .url(url)
                .build();

        surls.add(surl);

        return surl;
    }

    @GetMapping("/g/{id}")
    public String go(@PathVariable long id) {
        Surl surl = surls.stream()
                .filter(_surl -> _surl.getId() == id)
                .findFirst()
                .orElse(null);

        if (surl == null) {
            throw new RuntimeException("데이터를 찾을 수 없음");
        }

        surl.increaseCount();

        return "redirect:" + surl.getUrl();  // 예: "redirect:http://example.com"
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Surl> getAll(){
        return surls;
    }

}
