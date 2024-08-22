package com.ll.spring_boot_exam_2;


import com.ll.spring_boot_exam_2.domain.Article;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Surl;
import com.ll.spring_boot_exam_2.service.ArticleService;
import com.ll.spring_boot_exam_2.service.MemberService;
import com.ll.spring_boot_exam_2.service.SurlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Profile("!prod") // =이거는 test or dev이다
@RequiredArgsConstructor
public class NotProd {
        @Lazy
        @Autowired
        private NotProd self;
        private final ArticleService articleService;
        private final MemberService memberService;
        private final SurlService surlService;

        @Bean
        @Order(4)
        public ApplicationRunner initNotProd() {
            return args -> {
                self.work1();
            };
        }

        @Transactional
        public void work1(){
            if(articleService.count() > 0) return;  // -> 데이터베이스 숫자 세는거 숫자가 있으면 굳지 만들지 않음
            //이렇게 해야지만 RsData안에 있는 값을 뽑아서 사용이 가능하다.

            Member member1 = memberService.join("user1", "1234", "유저1").getData();
            Member member2 = memberService.join("user2", "1234", "유저1").getData();

            Article article1= articleService.write(member1,"제목1", "내용1").getData();
            Article article2 = articleService.write(member1,"제목2", "내용2").getData();

            Article article3 = articleService.write(member2,"제목1", "내용1").getData();
            Article article4 = articleService.write(member2,"제목2", "내용2").getData();


            Surl surl1 = surlService.add("https://www.naver.com", "네이버", member1).getData();
            Surl surl2 = surlService.add("https://www.daum.net", "다음", member1).getData();

            Surl surl3 = surlService.add("https://www.naver.com", "네이버", member2).getData();
            Surl surl4 = surlService.add("https://www.google.com", "구글", member2).getData();

        }

}
