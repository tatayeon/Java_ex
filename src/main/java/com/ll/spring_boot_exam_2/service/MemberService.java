package com.ll.spring_boot_exam_2.service;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import com.ll.spring_boot_exam_2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public RsData<Member> join(String username, String password, String nickname){

        if (findMemberByUsername(username).isPresent()) { //스프링은 예외 처리가 좀 더 처리가 편한 상황이 많아진다.
            throw new GlobalException("400-1", "이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        return RsData.of("회원가입이 완료", member);
    }

    public Optional<Member> findMemberByUsername(String username){
        return memberRepository.findByUsername(username);
    }


    public Member getMember(long id) {
        return memberRepository.getReferenceById(id);
    }
}
