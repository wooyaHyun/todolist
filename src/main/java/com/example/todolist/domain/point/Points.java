package com.example.todolist.domain.point;

import com.example.todolist.domain.user.Member;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

/**
 * packageName : com.example.todolist.domain
 * fileName : Points
 * author : SHW
 * date : 2023-07-23
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-23   SHW     최초 생성
 */

/*@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "point_id")
    private Long id;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer point;

    @OneToOne(mappedBy = "points", fetch = LAZY)
    private Member member;


}*/
