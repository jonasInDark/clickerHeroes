## ClickerHeroes

- 방치형 웹게임 [ClickerHeroes.com](https://www.clickerheroes.com/)을 클론 코딩한다.
- ![](assets/example.png)

### ERD

- ![](assets/erd.png)
- [ERDCloud.com](https://www.erdcloud.com/d/8c3JAmZ2Zkdy4m2wa)

🎮 Clicker Heroes Clone Project 요구사항 정의서
1. 회원 및 시스템 기반 (Account & Progress)
   - 인증(Auth)
     - ID 중복 체크 후 회원가입. 비밀번호는 암호화 저장.
     - 일치 시 JWT 또는 세션 발급.
   - 진행도 저장
     - 사용자는 **현재 스테이지(Stage)** 와 보유 골드(Gold) 정보를 실시간 또는 일정 주기마다 DB에 동기화해야 함.
     - 지수 표기법 처리를 위해 골드 데이터는 mantissa(Float)와 exponent(Integer) 필드로 나누어 저장.
2. 영웅 및 고용 시스템 (Heroes & Hiring)
   - 영웅 고용 로직
     - 사용자(User)와 영웅(Hero)은 N:M 관계이며, 조인 테이블인 users_heroes (논리명: 유저_영웅)를 통해 관리.
     - 노출 조건: 현재 고용된 모든 영웅 + 고용되지 않은 영웅 중 가장 순번이 빠른 딱 한 명만 상점에 노출.
     - 추가 노출: 현재 보유 골드가 다음 영웅의 price보다 높다면, 그 뒤의 영웅들도 리스트에 해금됨.
   - 레벨업 시스템
     - x1, x25, x50, x100, Max 모드 지원.
     - Max 계산 로직: $Price = BasePrice \times 1.07^{Level}$ 공식을 사용하여 현재 골드로 살 수 있는 최대 레벨을 서버/클라이언트에서 계산.
     - 골드 부족 시 x50을 눌러도 살 수 있는 최대치(Max) 만큼만 레벨업 진행.
3. 전투 및 스테이지 (Combat & Stage)
   - 몬스터 생성
     - 일반 스테이지: 10마리의 몬스터를 잡아야 다음 스테이지 버튼 활성화.
     - 보스 스테이지(10의 배수): 30초 타이머 작동. 시간 초과 시 이전 스테이지로 강제 후퇴.
   - 데미지 계산
     - DPS: 고용된 모든 영웅의 DPS 합산 값. 1초마다 몬스터 체력 차감.
   - 보상
     - 일반 몬스터 처치 시 스테이지 비례 골드 드랍.
     - 보스 몬스터는 일반 몬스터의 10배 골드 드랍.
4. 스킬 시스템 (Skills)
   - 스킬 타입 구분
     - dps_active: 사용 시 30초간 클릭 데미지 2배 등.
     - dps_passive: 영웅 레벨업 등에 따라 영구적으로 DPS % 상승.
     - gold_active: 사용 시 일정 시간 동안 몬스터 드랍 골드 증가.
     - gold_passive: 영구적으로 드랍 골드 증가.
   - 스킬 해금: 특정 영웅이 특정 레벨(예: 10, 25, 50)에 도달했을 때 해당 영웅의 고유 스킬 활성화.
