package com.example.todolist.dto.ledger;

import com.example.todolist.domain.ledger.Item;
import com.example.todolist.domain.ledger.Ledger;
import com.example.todolist.domain.ledger.LedgerDsc;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName : com.example.todolist.dto
 * fileName : LegerSaveRequestDto
 * author : SHW
 * date : 2023-04-16
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-04-16   SHW     최초 생성
 */

@NoArgsConstructor
@Getter
public class LegerSaveRequestDto {

    @NotEmpty(message = "사용자 ID는 필수값입니다.")
    private String userId;

    @NotEmpty(message = "사용일자는 필수값입니다.")
    private String useDate;

    @NotNull(message = "지출 수입 구분은 필수값입니다.")
    private LedgerDsc ledgerDsc;
    @NotNull(message = "지출 수입 별 항목은 필수값입니다.")
    private Item item;

    @NotNull(message = "금액은 필수 값입니다. ")
    @Positive(message = "금액은 0 이상의 양수만 가능합니다.")
    private int amount;

    @Size(max= 200, message = "사용 내용은 100자 미만으로 작성해주세요")
    private String cntn;

    @Builder
    public LegerSaveRequestDto(String userId, String useDate, LedgerDsc ledgerDsc, Item item, int amount, String cntn) {
        this.userId = userId;
        this.useDate = useDate;
        this.ledgerDsc = ledgerDsc;
        this.item = item;
        this.amount = amount;
        this.cntn = cntn;
    }

    public Ledger toEntity() {
        return Ledger.builder()
                .userId(userId)
                .useDate(useDate)
                .ledgerDsc(ledgerDsc)
                .item(item)
                .amount(amount)
                .cntn(cntn)
                .build();
    }


}
