package com.ll.spring_boot_exam_2.service;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Article;
import com.ll.spring_boot_exam_2.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public long count(){
        return articleRepository.count();
    }

    public RsData<Article> write(String title, String body){
        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);

        return RsData.of("%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);
    }

    public void delete(long id){
        articleRepository.deleteById(id);
    }

    public Optional<Article> findById(long id){
        return articleRepository.findById(id);
    }

    public List<Article> findAll(){
        return articleRepository.findAll();
    }





}
