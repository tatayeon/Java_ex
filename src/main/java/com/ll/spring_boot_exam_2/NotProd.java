package com.ll.spring_boot_exam_2;


import com.ll.spring_boot_exam_2.domain.Article;
import com.ll.spring_boot_exam_2.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Profile("!prod") // =이거는 test or dev이다
@RequiredArgsConstructor
public class NotProd {
        @Lazy
        @Autowired
        private NotProd self;
        private final ArticleService articleService;

        @Bean
        public ApplicationRunner initNotProd() {
            return args -> {
                self.work1();
                self.work2();
            };
        }

        @Transactional
        public void work1(){
            if(articleService.count() > 0) return;  // -> 데이터베이스 숫자 세는거 숫자가 있으면 굳지 만들지 않음
            //이렇게 해야지만 RsData안에 있는 값을 뽑아서 사용이 가능하다.
            Article article = articleService.write("제목1", "내용1").getData();
            Article article1 = articleService.write("제목2", "내용2").getData();

            article1.setTitle("새로운 제목");

            articleService.delete(article1.getId());
        }

        @Transactional
        public void work2(){

            List<Article> articles = articleService.findAll();
        }
}
