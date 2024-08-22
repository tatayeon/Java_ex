package com.ll.spring_boot_exam_2.repository;

import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Surl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurlRepository extends JpaRepository<Surl, Long> {

    List<Surl> findByAuthorOrderByIdDesc(Member author);



}
