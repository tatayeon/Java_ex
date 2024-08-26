package com.ll.spring_boot_exam_2.security;

import com.ll.spring_boot_exam_2.RsData;
import com.ll.spring_boot_exam_2.util.UT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    //시큐리티에 대한 설정들
    private final CustomAuthenticationFilter customAuthenticationFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/h2-console/**")
                                .permitAll()
                                .requestMatchers("/actuator/**") //여기 안에는 보안 대상에서 좀 풀어줄 것들
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .headers(
                        headers ->
                                headers.frameOptions(
                                        frameOptions ->
                                                frameOptions.sameOrigin()
                                )
                ) //csrf끄기 //타임리프때는 사용, 아니면 사용하지 않는다.
                .csrf(
                        csrf ->
                                csrf.disable()
                )
                .formLogin( //이건 로그인 하러 가는 부분은 풀어줘
                        formLogin ->
                                formLogin
                                        .permitAll()
                ).exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(
                                        (request, response, authException) -> {
                                            response.setContentType("application/json;charset=UTF-8");
                                            response.setStatus(403);
                                            response.getWriter().write(
                                                    UT.json.toString(
                                                            RsData.of("403-1", request.getRequestURI() + ", " + authException.getLocalizedMessage())
                                                    )
                                            );
                                        }
                                )
                )
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}
