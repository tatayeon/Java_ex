package com.ll.spring_boot_exam_2.security;

import com.ll.spring_boot_exam_2.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthTokenService {
    public String genToken(Member member, long expireSeconds) {
        Claims claims = Jwts
                .claims()
                .add("id", member.getId()) //아 토큰 안에는 정확히 식별이 가능한 것들만 들어거야한다.
                .add("username", member.getUsername())
                .build();

        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + 1000 * expireSeconds); //토큰의 유효기간을 지정

        // JWT를 사용하는 이유는 여러가지가 있지만 성능 향상에 있다 그냥 apiKey(UUID)를 사용해서 넘긴다면 처음부터 끝가지 DB를 뒤져서 확인을하는 절차가 생김
        //하지만 JWT는 안에 패턴같은게 있어서 보면 바로 알 수 있는 기능이 있다.
        //왜 유효기간을 설정을 하냐 JWT구조상 폐기가 불가능해서 수명이 긴 토큰이 탈취당하면 골치가 아프다
        //그래서 유효기간을 설정을 해놓는 이유이다.

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, AppConfig.getJwtSecretKey())
                .compact();
    }

    public Map<String, Object> getDataFrom(String token) { //해당 토큰으로부터 정보를 가져오는 역활
        Claims payload = Jwts.parser()
                .setSigningKey(AppConfig.getJwtSecretKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();

        return Map.of(
                "id", payload.get("id", Integer.class),
                "username", payload.get("username", String.class)
        );
    }

    public boolean validateToken(String token) { //해당하는 토큰이 올바른지 체크하는 기능
        try {
            Jwts.parser().setSigningKey(AppConfig.getJwtSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}

