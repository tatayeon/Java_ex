package com.ll.spring_boot_exam_2.repository;

import com.ll.spring_boot_exam_2.domain.Surl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurlRepository extends JpaRepository<Surl, Long> {



}
