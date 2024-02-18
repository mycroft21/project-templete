# 에이블리 지원 과제

- 에이블리 지원을 위한 지원 과제 프로젝트입니다.
- 레이어 구조 방식의 프로젝트를 구성하였으며 아래와 같습니다.
  - ui - 외부 유저 통신 부
  - domain - 엔티티 및 로직 부
  - application - 비지니스 로직 부
  - infra - db등 외부 데이터 조회 및 등록 부
- db는 메모리 가상 h2 db를 구성 하였으며 product 리스트는 자동 입력됩니다.
- 조회시 필요한 부분은 cursor 방식 무한 스크롤 구성하였습니다.
- 로그인은 jwt 토큰 발행으로 설정하였습니다. 로그인시 발행된 토큰을 헤더에 삽입해주세요.

- 시간관계상 구성 못한 부분 중 알고 있는 부분은 아래와 같습니다.
  - 중복 입력 등 validate 처리
  - 개인 정보 및 비밀번호 암호화 등
  - h2 db 기반 테스트로 인한 오류 처리 및 테스트 코드
  - API 건별 커밋 등 git 관려 부분

## 사용 기술 스텍
  - java 21
  - springboot 3
  - h2
  - JPA, QUERY_DSL

## 요구 사항 수행 내역
  - 유저
    - 이메일과 비밀번호로 회원가입 및 로그인을 할 수 있습니다.
    - 로그인 후 내 정보를 볼 수 있습니다.
  - 찜 서랍
    - 내 찜 서랍을 생성 및 삭제 할 수 있습니다.
    - 내 찜 서랍 목록을 볼 수 있습니다.
      - 페이지네이션 또는 무한 스크롤로 탐색이 가능해야 합니다.
    - 이미 있는 내 찜 서랍의 이름으로 생성할 수 없습니다.
  - 찜
    - 상품을 찜하거나 해제를 할 수 있습니다.
    - 내 찜 서랍의 찜 목록을 볼 수 있습니다.
      - 페이지네이션 또는 무한 스크롤로 탐색이 가능해야 합니다.
    - 찜한 상품이 내 다른 찜 서랍에 있을경우 찜할 수 없습니다.
    - 찜 서랍이 하나도 없을 경우 상품을 찜 할 수 없습니다.


## 테이블 구조
- [users]
- [dib_group]
  - [dib]
- [product]

## API 구조 및 수행 순서
- [LoginController.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fui%2Flogin%2FLoginController.java)
  - userLogin [post] api/v1/ably/auth/login
    - 로그인 처리 jwt token 반환
    - {
      "userEmail": "test@test.com",
      "userPassword": 1234
      }
  - userJoin [post] api/v1/ably/auth/join
    - 유저 가입 
    - {
        "userName": "tester",
        "userEmail": "test@test.com",
        "userPassword": 1234
        }
- [UsersController.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fui%2Fuser%2FUsersController.java)
  - findUser [get] api/v1/ably/users/{userId}
    - 유저 정보 조회
  - updateUser [post] api/v1/ably/users/{userId}
    - 유저 정보 수정
    - {
         "userName": "tester",
         "userEmail": "test@test.com",
         "userPassword": 1234
      }
- [ProductController.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fui%2Fproduct%2FProductController.java)
  - getProducts [get] api/v1/ably/products
    - 상품 조회 
    - pageIndex : 마지막 product id nullable
- [DibController.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo%2Fui%2Fdib%2FDibController.java)
  - findDibGroups [get] api/v1/ably/user/{userId}/dibs
    - 찜 서랍 조회 
  - creatDibGroup [post] api/v1/ably/user/{userId}/dibs
    - 찜 서랍 생성
    - {
      "dibName": "모자"
      }
  - deleteDibGroup [delete] api/v1/ably/user/{userId}/dibs/{dibGroupId}
    - 찜 서랍 삭제
  - findDibs [get] api/v1/ably/user/{userId}/dibs/{dibGroupId}/dib
    - 찜 조회
  - addDib [post] api/v1/ably/user/{userId}/dibs/{dibGroupId}/dib
    - 찜 추가
    - {
      "productId": 4
      }
  - removeDib [delete] api/v1/ably/user/{userId}/dibs/{dibGroupId}/dib
    - 찜 해제