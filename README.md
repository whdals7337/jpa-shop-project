# jpa-shop-project

## 스프링부트와 JPA 활용 1, 2 실습 
실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발: https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1

실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화 https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-API%EA%B0%9C%EB%B0%9C-%EC%84%B1%EB%8A%A5%EC%B5%9C%EC%A0%81%ED%99%94

주의점
1. 엔티티에 가급적 setter를 사용하지 말자
2. 모든 연관관계는 지연로딩으로 설정


엔티티조회 
 - 엔티티 조회
    1. 엔티티 그대로 반환 - v1
    2. 엔티티 조회 후 DTO로 반환 - v2
    3. 페치 조인으로 쿼리수 최적화 - v3
    4. 페치조인 + 컬렉션 페이징과 한계 돌파 - v3.1
        1. 켈렉션은 페치 조인시 페이징 불가능
        2. ToOne 관계는 페치 조인으로 쿼리수 최적화
        3. 컬렉션은 페치 조인 대신 지연로딩 유지하고 배치 사이즈로 최적화


 - DTO 직접 조회
    1. JPA에서 DTO를 직접 조회 -v4
    2. 켈력센 조회 최적화(컬렉션에 IN 활용) - v5
    3. 플랫 데이터 최적화(그대로 조회후 직접 변환) -v6
    

 - 엔티티 조회 권장 순서 (v3 or v3.1 -> v5 , v6, v4 -> NativeSQL or 스프링JdbcTemplate)
   - ※ v3 or v3.1로 안되는 경우 보통 캐싱를 사용(캐싱할때는 DTO 캐싱해야 함)
    1. 엔티티 조회 방식으로 우선 접근
       1. 페치 조인으로 쿼리수 최적화
       2. 컬렉션 최적화
            1. 페이징 필요시 배치 사이즈로 최적화 v3.1
            2. 페이징 필요x -> 페치 조인 사용 v3
    2. 엔티티 조회방식으로 해결안되는 경우
        1.DTO 조회 방식 사용 - v4, v5, v6
    3. DTO 조회 방식으로도 해결 불가능한 경우 NativeSQL or 스프링JdbcTemplate 사용
    
    