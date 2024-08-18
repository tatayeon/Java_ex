package com.ll.spring_boot_exam_2.service;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Surl;
import com.ll.spring_boot_exam_2.repository.SurlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurlService {

    private final SurlRepository surlRepository;

    public List<Surl> findAll() {
        return  surlRepository.findAll();
    }

    @Transactional
    public RsData<Surl> add(String url, String body, Member author) {

        Surl surl = Surl.builder()
                .author(author)
                .url(url)
                .body(body)
                .build();

        surlRepository.save(surl);

        return RsData.of("%d번 url이 형성되었습니다.".formatted(surl.getId()) , surl);
    }

    public Optional<Surl> findById(long id) {
        return surlRepository.findById(id);
    }

    @Transactional
    public void increaseCount(Surl surl) {
        surl.increaseCount();

    }
}
