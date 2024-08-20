package com.ll.spring_boot_exam_2.controller;

import com.ll.spring_boot_exam_2.domain.Article;
import com.ll.spring_boot_exam_2.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article/listJson")
    @ResponseBody
    public List<Article> getArticleListJson(){
        return articleService.findAll();
    }

    @GetMapping("/article/list")
    @ResponseBody
    public List<Article> showList(){
        StringBuilder sb = new StringBuilder();

        List<Article> articleList = articleService.findAll();

        sb.append("<h1>게시물 목록</h1>");
        sb.append("<ul>");
        return articleList;
    }

}
