package com.ll.spring_boot_exam_2.domain;


import com.ll.spring_boot_exam_2.jpaEntity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor // -> 빌더를 추가할 거면 이 아래 두가지도 같이 추가를 해줘야한다.
@NoArgsConstructor
public class Member extends BaseTime {

    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    @Column(unique = true)
    private String apiKey; //고유의 암호화가 되어있는 키, 각각의 유저를 식별이 가능하다.

    public String getName(){
        return nickname;
    }

}
