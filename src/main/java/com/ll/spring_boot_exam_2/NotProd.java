package com.ll.spring_boot_exam_2;


import com.ll.spring_boot_exam_2.domain.Article;
import com.ll.spring_boot_exam_2.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod") // =이거는 test or dev이다
@RequiredArgsConstructor
public class NotProd {

    private final ArticleRepository articleRepository;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            System.out.println("Start!!!!");

            articleRepository.save(
                    Article.builder()
                            .title("제목")
                            .body("내용")
                            .build()
            );
        };

    }

}
