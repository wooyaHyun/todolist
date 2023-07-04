# todolist(장부 웹서비스)

### 서론

---

최근 프로젝트에서 지출 수입을 관리할 수 있는 DB 설계를 하며 DB 공통코드 Table을 사용하였습니다. 

회사에서는 MyBatis를 사용하였으나 JPA를 학습하며 변경이 거의 일어나지 않는 코드 성격 데이터를 Enum으로 변경해보고자 합니다.

- 프로젝트 기능은 수입 및 지출 내역을 관리하는 장부 관리 시스템의 성격이지만 프로젝트 생성할 때 todolist..이름을 잘 못지었습니다.

### 개발환경

---

- Framework: Spring Boot 3.0.5
- 개발 언어 : java 17
- view : mustache
- ORM : JPA
- Database: H2

### 학습목표

---

1. DB에서 관리되던 공통 코드 중 변경이 없는 코드를 Enum 객체로 관리하기
2. 데이터를 그룹으로 관리하기

- (변경 전) 기존 테이블

![image](https://github.com/wooyaHyun/todolist/assets/97392429/b0472079-501b-402d-b735-3b7b67d4f060)


- (변경 후) Enum
```java
public enum LedgerDsc implements EnumMapperType {
    EXPENDITURE("지출", Arrays.asList(Item.FOOD, Item.SHOPPING, Item.COFFEE, Item.DATE, Item.ALCOHOL, Item.TRANSPORTATION, Item.ETC_EXPENDITURE)),
    INCOME("수입", Arrays.asList(Item.SALARY, Item.BONUS, Item.CLOTHING_PAYMENT, Item.GIFT_CARD, Item.ETC_INCOME));

     ...
}

public enum Item implements EnumMapperType {

    FOOD("식비"),
    SHOPPING("쇼핑"),
    COFFEE("커피"),
    DATE("데이트통장"),
    ALCOHOL("음주비"),
    TRANSPORTATION("교통비"),
    ETC_EXPENDITURE("기타지출"),
    SALARY("월급"),
    BONUS("성과급"),
    CLOTHING_PAYMENT("피복비"),
    GIFT_CARD("상품권"),
    ETC_INCOME("기타수입");

    ....
}
```
