package com.ll.spring_boot_exam_2.repository;

import com.ll.spring_boot_exam_2.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {



}
