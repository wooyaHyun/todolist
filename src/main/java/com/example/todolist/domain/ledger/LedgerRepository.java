package com.example.todolist.domain.ledger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : LedgerRepository
 * author : SHW
 * date : 2023-04-16
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-16   SHW     최초 생성
 */

public interface LedgerRepository extends JpaRepository<Ledger, Long> {

    Ledger findByLedgerDsc(LedgerDsc ledgerDsc);

    @Query("SELECT l FROM Ledger l WHERE l.userId = :userId AND l.useDate BETWEEN :fromDate AND :toDate ORDER BY l.id DESC")
    List<Ledger> findAllDesc(@Param("userId") String userId, @Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
