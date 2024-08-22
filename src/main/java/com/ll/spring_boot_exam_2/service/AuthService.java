package com.ll.spring_boot_exam_2.service;

import com.ll.spring_boot_exam_2.domain.Member;
import com.ll.spring_boot_exam_2.domain.Surl;
import com.ll.spring_boot_exam_2.exceptions.GlobalException;
import org.springframework.stereotype.Service;

@Service //여기는 데이터베이스와 관련된 서비스가 아직 아니라서 생략
public class AuthService {
    //Surl권한 체크
    private boolean CheckSurl(Member actor, Surl surl) {
        if(actor == null) return false;
        if(surl == null) return false;

        return !actor.equals(surl.getAuthor());
    }

    public void checkCanGetSurl(Member actor, Surl surl) {

        if(CheckSurl(actor, surl))
            throw new GlobalException("403-1", "권한이 없습니다.");

    }

    public void checkCanDeleteSurl(Member actor, Surl surl) {

        if(CheckSurl(actor, surl))
            throw new GlobalException("403-1", "권한이 없습니다.");

    }

    public void checkCanModifySurl(Member actor, Surl surl) {

        if(CheckSurl(actor, surl))
            throw new GlobalException("403-1", "권한이 없습니다.");

    }
}
