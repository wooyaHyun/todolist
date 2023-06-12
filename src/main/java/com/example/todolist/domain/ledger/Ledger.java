package com.example.todolist.domain.ledger;

import com.example.todolist.domain.BaseTimeEntity;
import com.example.todolist.dto.ledger.LegerSaveRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

/**
 * packageName : com.example.todolist.domain.ledger
 * fileName : Ledger
 * author : SHW
 * date : 2023-04-11
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-11   SHW     최초 생성
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Ledger extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 8)
    private String useDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LedgerDsc ledgerDsc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Item item;

    @ColumnDefault("0")
    private int amount;

    @Column
    private String cntn;


    public void update(LegerSaveRequestDto dto){
        this.useDate = dto.getUseDate();
        this.ledgerDsc = dto.getLedgerDsc();
        this.item = dto.getItem();
        this.amount = dto.getAmount();
        this.cntn = dto.getCntn();

    }

}
