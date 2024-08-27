package com.ll.spring_boot_exam_2.security;

import com.ll.spring_boot_exam_2.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthTokenService {
    public String genToken(Member member, long expireSeconds) {
        Claims claims = Jwts
                .claims()
                .add("id", member.getId())
                .add("username", member.getUsername())
                .build();

        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + 1000 * expireSeconds); //토큰의 유효기간을 지정

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, AppConfig.getJwtSecretKey())
                .compact();
    }

}

