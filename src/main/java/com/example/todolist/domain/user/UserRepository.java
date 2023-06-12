package com.example.todolist.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName : com.example.todolist.domain.user
 * fileName : UserRepository
 * author : SHW
 * date : 2023-06-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-11   SHW     최초 생성
 */

public interface UserRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    boolean existsByUserId(String username);

}
