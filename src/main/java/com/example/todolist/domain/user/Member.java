package com.example.todolist.domain.user;

import com.example.todolist.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

/**
 * packageName : com.example.todolist.domain.user
 * fileName : User
 * author : SHW
 * date : 2023-06-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-11   SHW     최초 생성
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SQLDelete(sql = "UPDATE Member m SET m.deleted = true WHERE m.id = ?")
@Where(clause = "deleted = false")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /*@OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "point_id")
    private Points points;*/

    @Column(nullable = false)
    @ColumnDefault("0")
    private int points;

    @Column
    private boolean deleted;


    @Builder
    public Member(String userId, String password, Role role){
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
