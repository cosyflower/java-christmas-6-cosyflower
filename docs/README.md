# 프로젝트 목표 - 크리스마스 프로모션
- 미션은 기능 요구 사항, 프로그래밍 요구 사항, 과제 진행 요구 사항 세 가지로 구성되어 있으며 3가지 요구 사항을 만족해야 한다 
- 기능을 구현하기 전에 기능 목록을 만들고, 기능 단위로 커밋 하는 방식으로 진행한다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.

## 🚀 기능 요구 사항

12월 이벤트를 위한 개발 요청
- 12월 이벤트 상황에 맞는 프로그램을 개발한다
- 예산 걱정 없이 상한선을 따로 고려하지 않는다
- 메뉴와 달력 이미지를 참고하여 12월 이벤트 계획과 내용을 이해하고 프로그램에 반영할 수 있도록 한다 

### 메뉴
```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```
메뉴는 에피타이저, 메인, 디저트, 음료로 구성되어 있으며 종류별 서로 다른 음식 메뉴가 존재한다
- 음식 종류는 서로 다른 음식 메뉴를 가지고 있다 
- 음식 메뉴는 가격을 가지고 있다 ex) 양송이 수프 - 6,000원  

### 12월 이벤트 계획
이벤트의 진행 방식과 목적을 이해한다
- 중복된 할인과 증정을 허용한다 -> 고객들이 혜택을 많이 받는 것을 체감한다 
- 지난 5년 중 최고의 판매 금액을 12월에 달성한다 
- 12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 참여할 수 있도록 한다 

이벤트 계획은 5가지로 구분할 수 있으며 이벤트 기간, 할인 금액, 증정이 서로 다르게 구성된다
- 크리스마스 디데이 할인 
  - [x] **12.01 - 12.25일까지 진행한다**
  - [x] 할인 금액의 시작은 1000원이다
  - [x] 하루를 지날수록 100원씩 할인 금액이 증가한다 ex) 100원 - 200원 ....
  - [x] 25일 기준 해당 이벤트의 최대 할인 금액은 3400원이다 
  - 총 주문 금액에서 해당 할인 금액을 제외한다

- 평일 할인 (일-목요일)
  - [x] **12.01 - 12.31**일까지 진행한다 
  - [x] **일요일에서 목요일**까지 적용되는 할인이다
  - [x] **디저트 메뉴**를 한 개당 2,023원 할인한다 
  
- 주말 할인 (금-토요일)
  - [x] **12.01 - 12.31**일까지 진행한다
  - [x] **금요일에서 토요일**까지 적용되는 할인이다
  - [x] **메인 메뉴**를 한 개당 2,023원 할인한다

- 특별 할인 
  - [x] **12.03, 10, 17, 24 ,25, 31** 총 6일만 진행한다 
  - [x] **총 주문 금액에서 100원을 할인한다**

- 증정 이벤트 
  - [x] **12.01 - 12.31**일까지 진행한다
  - **할인 전 총 주문 금액** 12만원 이상일 때 적용된다 
  - [x] 샴페인 1개 (25,000원)을 증정한다  

### 헤택 금액에 따른 12월 이벤트 배지 부여
혜택 금액에 따라 서로 다른 이벤트 배지를 부여한다
- 5천원 이상: 별
- 1만원 이상: 트리
- 2만원 이상: 산타

### 12월 이벤트 관련 유의사항
- 이벤트 적용 가능한 금액은 총 주문 금액을 기준으로 1만원 이상이다
- 음료만 주문하는 것은 불가능하다
- 메뉴는 한 번에 최대 20개까지만 주문이 가능합니다 

### 출력 예시를 반영한 기능 명세 사항
- 고객들이 식당에 방문할 날짜와 메뉴를 미리 선택하면 이벤트 플래너가 주문과 이벤트 관련 내용을 출력한다 
  - 식당에 방문할 날짜와 메뉴를 입력받는다
  - 주문 메뉴, 할인 전 총주문 금액, 증정 메뉴, 혜택 내역, 총혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 배지 내용을 출력한다

- 식당 예상 방문 날짜를 입력한다 
  - 예상 방문 날짜는 숫자만 입력받을 수 있다
  - 숫자의 범위는 1일에서 31일까지만 가능하다
    - **예외** 1이상 31이하의 숫자가 아닌 경우
    - **모든 에러 메시지는 "[ERROR]"로 시작하도록 작성한다**
    - **예외 처리** 1 이상 31 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지를 출력한다
    - **예외 처리** 재입력을 받는다 

- 주문하실 메뉴와 개수를 입력한다 (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
  - "$메뉴명-$개수" 형태로 입력한다
  - 복수의 메뉴를 주문하는 경우 콤마(',')로 구분해서 입력한다 
  - 메뉴 개수는 1이상의 숫자만 입력할 수 있다
    - **예외** 메뉴의 개수를 숫자로 입력하지 않은 경우
    - **예외** 메뉴의 개수를 0으로 입력한 경우
    - **예외 처리** "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력하고 재입력 받는다
  - 메뉴판에 존재하는 메뉴만 입력할 수 있다
    - **예외** 메뉴판에 존재하지 않는 메뉴를 입력한 경우
    - **예외 처리** "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력하고 재입력 받는다
  - 중복 메뉴를 입력할 수 없다 
    - **예외** 중복 메뉴를 입력한 경우 
    - **예외 처리** "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력하고 재입력 받는다
  - 형식에 맞지 않게 입력할 수 없다 
    - **예외** 형식에 맞게 입력하지 않은 경우
      - **예외 1** "$메뉴명-$메뉴 개수"의 형식을 지키지 않은 경우
      - **예외 2** 형식에 맞게 입력했으나 복수의 메뉴를 주문시 구분자를 활용하지 않은 경우 
      - **예외 3** 형식에 맞게 입력했으나 복수의 메뉴를 주문시 정해진 구분자 콤마(',')가 아닌 다른 문자를 명시한 경우 
      - **예외 처리** "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 출력하고 재입력 받는다
  
- 할인 전 총 주문 금액을 출력한다
  - 할인을 적용하기 전의 가격의 총합을 출력한다 

- 증정 메뉴를 출력한다
  - 1일 ~ 31일까지 진행되는 증정 이벤트의 조건을 충족하면 샴페인을 증정한다
    - 할인 받기 전 총 주문 금액이 120000원 이상이 경우
    - 샴페인 (25,000원) 을 증정한다 

- 혜택 내역을 출력한다 
  - 적용가능한 혜택을 출력한다
  - 적용하지 못하는 혜택은 출력하지 않는다 
  - 증정된 샴페인이 있다면 포함한다 

- 총 혜택 내역을 출력한다 
  - 이벤트로 할인 받은 금액의 총액을 구한다
  - 증정된 상품의 금액을 포함한다 
    - 증정 이벤트로 샴페인을 증정한 경우 25,000원을 포함하고 출력한다   

- 할인 후 예상 결제 금액을 출력한다 
  - 총 주문 금액에서 할인 금액을 차감한 금액을 출력한다
    - 증정된 샴페인의 가격을 할인 금액에 포함하지 않는다 

- 12월 이벤트 배지를 출력한다
  - 0원 - 4999원 : 없음 
  - 5000원 - 9999원: 별
  - 1만원 이상 - 19999원: 트리
  - 2만원 이상 ~ : 산타 
  


---

## 단위 테스트 (Unit Test)
모든 메뉴는 메뉴 종류, 메뉴 이름과 메뉴 가격을 포함한다 (Enum으로 표현하자) ex) 에피타이저, 양송이 스프, 6000원
- [x] 메뉴 종류를 관리하는 Enum
- [x] 메뉴 이름값을 Wrapping 클래스
- [x] 메뉴의 가격을 Wrapping 클래스

모든 이벤트는 적용 기간, 할인 금액
- 적용 기간을 관리하는 Enum ( ex.1-31, 1-25 / Predicate - 조건으로 표현한다 )
  - 주말
  - 평일
  - 특정한 날짜는 7로 나눈 나머지를 적용
  - 특별한 날짜는 따로 표기하는 방식으로 
- 할인 금액을 Wrapping 하는 클래스 
  - 단순히 하나의 메뉴를 고려한다 (한 종목에 하나의 종류를 생각한다)
  - 메뉴의 수를 고려하지 않고 상품 종류에 따른 할인 금액을 기준으로 생성한다

혜택 금액에 따라 서로 다른 이벤트 배지를 부여한다
- 5천원 이상: 별
- 1만원 이상: 트리
- 2만원 이상: 산타