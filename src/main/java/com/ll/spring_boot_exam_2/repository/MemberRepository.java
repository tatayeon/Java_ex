package com.ll.spring_boot_exam_2.repository;

import com.ll.spring_boot_exam_2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    //보통 결과가 최대 1개라면 보통 Optional을 리턴하고 그 외에는 List로 리턴한다.
    Optional<Member> findByUsername(String username);

    Optional<Member> findMemberByRefreshToken(String refreshToken);
}
