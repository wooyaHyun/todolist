package com.example.todolist.dto.user;

import com.example.todolist.domain.user.Member;
import com.example.todolist.domain.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * packageName : com.example.todolist.dto.user
 * fileName : MemberJoinRequestDto
 * author : SHW
 * date : 2023-06-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-11   SHW     최초 생성
 */

@Getter
public class MemberJoinRequestDto {

    @NotBlank(message = "ID를 입력해주세요.")
    @Size(min = 2, max = 10, message = "ID는 2자 이상 10자 이하로 입력해주세요.")
    private String username;

    @NotBlank(message = "PASSWORD를 입력해주세요.")
    @Size(min = 2, max = 10, message = "PASSWORD는 2자 이상 10자 이하로 입력해주세요.")
    private String password;

    @Builder
    public MemberJoinRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member toEntity(){
        return Member.builder()
                .userId(username)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
