package com.ll.spring_boot_exam_2.domain;

import com.ll.spring_boot_exam_2.jpaEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Surl extends BaseEntity {

    private String url;

    private String body;

    @ManyToOne
    private Member author;

    @Setter(AccessLevel.NONE)
    private long count;

    public void increaseCount() {
        count++;
    }

}
