package com.example.todolist.domain.logs;

import com.example.todolist.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Logs extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userId;


    @Column
    private String apiUri;

    @Column
    private String requestIp;

    @Builder
    public Logs(String userId, String apiUri, String requestIp) {
        this.userId = userId;
        this.apiUri = apiUri;
        this.requestIp = requestIp;
    }
}
