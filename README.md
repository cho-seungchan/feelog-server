# Feelog

<h1>함께쓰는 마음일기 - 'Feelog'</h1>

<h2>1. 기획 의도</h2>

<img src="https://github.com/user-attachments/assets/0560e041-2cc7-44d7-ba6d-848b4ea0b412"/>

전 세계 우울증, 자살률은 대체적으로 낮아졌지만 우리나라는 <strong>여전히 높은 자살률과 우울증 유병률</strong>을 기록하고 있습니다. </br>
단순 기록에 그치는 <strong>기존 일기의 한계</strong>를 해결하고 혼자 감당해야 하는 감정의 무게를 사용자들과 <strong>감정을 공유하고 소통</strong>하면 위와 같은 문제들을 해결 하는데 도움이 되고자 이 프로젝트를 설계했습니다.

<h2>2. 기대 효과</h2>

<img src="https://github.com/user-attachments/assets/7216d8d2-99a1-4cbd-a2e8-6c8cbd878cab"/>

<strong>감정 관리 습관 형성 및 정서적 회복</strong>을 유도하고 커뮤니티 기반의 공감과 응원을 통한 <strong>정서적 안정감을 제공</strong> 할 수 있습니다.</br>
일상 속 힐링을 돕는 챌린지 참여를 권장하고, 응원 메시지와 이모티콘을 통한 <strong>감정 환기와 긍정적인 경험</strong>을 제공합니다.

<h2>3. 프로젝트 사용 툴</h2>

💻 언어 & 프레임워크
  - Java
  - Javascript
  - Spring Boot
  - MyBatis
  - JSON
  - Thymeleaf

🛠️ 개발 환경 & IDE
  - IntelliJ IDEA
  - Visual Studio Code
  - Sourcetree
  - Git, Github
  - Apache Tomcat
  - JDK 11.0.15

🗄️ 데이터베이스 & DB 툴
  - Oracle Database
  - MySql

🌐 API & 외부 서비스
  - Kakao Deverlopers (로그인 API)
  - CoolSMS API

📦 빌드 & 의존성 관리
  - Gradle

🔔 협업 & 커뮤니케이션
  - Slack

🎨 디자인 & 퍼블리싱
  - HTML / CSS

📷 기타 도구
  - Thumbnailator
  - Lombok

🧪 테스트 & 디버깅
  - Postman

<h2>4. ERD</h2>


![KakaoTalk_20250520_141913976](https://github.com/user-attachments/assets/db70783b-5c2a-40d7-a575-f484912c7519)

<h2>5. 담당 업무</h2>
5-1 publishing
<img src="https://github.com/user-attachments/assets/da176157-3262-465e-9d03-14b5277279e4"/>

▶ 마이 페이지
- 뱃지
- 마이 페이지 메인
- 찜한 목록
- 내 정보 수정
- 신청자 목록
- 쪽지함
- 쪽지 쓰기
- 내 목록
- 결제 내역

▶ 메인 페이지
- 메인 페이지
- 공지 목록
- 공지 상세

▶ 회원
- 회원 가입
- 로그인
- Kakao 로그인 API 활용
- 본인 인증
- 비밀번호 찾기
- 비밀번호 변경
- 회원 탈퇴

5-2 백엔드
<img src="https://github.com/user-attachments/assets/dea3e737-d30c-4916-abc5-9a98a12317dc"/>

▶ 메인
- 로그인 시 세션 유지
- 헤더와 메인 페이지의 각 목록에 링크 연결
- 코스별 참가자 카운트 후 목록에 높은 순으로 출력
- 최신 피드 순으로 피드 목록 출력
- 사진 대표이미지, 썸네일, 글과 제목, 작성일자 등 출력

▶ 추천 코스 상세
- 관리자 코스목록에서 각 코스별로 등록된 코스 출력
- Kakao 지도 API 활용하여 코스 시작지점을 지도 중앙으로 고정
- 배너 형태의 코스 후기 6개로 제한
- 코스에 등록되어 있는 여행 목록 출력
- 세션에 로그인 유저의 정보가 없다면 경고창 출력 후 로그인 화면으로 이동하도록 링크 연결

▶ 여행 계획 작성
- 파일 첨부기능 사용
- 계획서, 포함사항, 불포함사항, 준비물 등 여러가지 필요한 요소들 배열에 담아서 처리
- Kakao 지도 API 활용하여 장소 검색 시 해당 지역 지도 출력
- 여행 기본정보 입력후 버튼 클릭시 Insert

▶ 나의 계획 목록
- 최대 4개의 목록, 최대 2페이지 페이징 처리
- <<, >> 버튼 클릭시 제일 첫번째 페이지, 제일 마지막 페이지 출력
- <, > 버튼 클릭 시 앞, 뒤로 한 페이지씩 이동.
- 여행 목록의 썸네일, 인원수, 여행이름 등 출력
- 수정, 삭제 추가 메뉴 버튼 클릭시 모달창으로 생성

▶ 여행 계획 상세보기
- 해당 여행의 정보 출력
- 출발점 입력 후 지도 출력
- 여행 후기 목록 최대 6개 출력, 한 계획에 후기가 6개 이상이라면 더 보기 버튼에 링크 연결
- 참여하기 버튼 클릭 시 세션에 로그인 유저의 정보가 없다면 경고창 출력 후 로그인 화면으로 이동하도록 링크 연결

▶ 질의/응답
- 세션에 로그인 유저의 정보가 없다면 경고창 출력 후 로그인 화면으로 이동하도록 링크 연결
- 질문 등록시 REST방식으로 바로 화면에 출력
- 답변 등록시 REST방식으로 바로 화면에 출력

▶ 참여자 결제
- 부트페이 결제 API 활용
- 결제 상품에 관한 정보 출력
- 상품의 갯수에 따라 총 결제 금액 변경
- 세션에 담겨있는 멤버의 포인트 반영
- 포인트 사용 여부에 따라 최종 결제 금액 변경
- 결제 완료시 결제테이블과 참가자테이블 Insert로 참가자 인원수 확인 가능

▶ 공지 목록 / 공지 상세
- 최대 4개의 목록, 최대 2페이지 페이징 처리
- <<, >> 버튼 클릭시 제일 첫번째 페이지, 제일 마지막 페이지 출력
- <, > 버튼 클릭 시 앞, 뒤로 한 페이지씩 이동.
- 최신 공지 순으로 보이도록 출력

<h2>6. 트러블슈팅</h2>

트러블 : 여행계획 Insert 테스트 중 이런 오류가 발생했다.
<img src="https://github.com/user-attachments/assets/14b87b2f-38b9-4d48-93c9-a918f4e4efa9"/>

슈팅 : Could not set Parameters for mapping 이라는 오류 단어를 보고 쿼리문을 확인해본 결과 아래와 같은 쿼리문으로 짜여져 있었다.

      <insert id="insert">
              <selectKey keyProperty="id" order="BEFORE" resultType="long">
                  SELECT SEQ_PLAN.NEXTVAL FROM DUAL
              </selectKey>
              INSERT INTO TBL_PLAN
              (ID, PLAN_NAME,
              PLAN_START_DATE,
              PLAN_END_DATE,
              PLAN_DEADLINE,
              PLAN_MAX_PERSONNEL, PLAN_MIN_PERSONNEL, PLAN_PRICE, PLAN_START_ADDRESS,
              PLAN_CONTENT, MEMBER_ID, COURSE_ID, PLAN_FILE_PATH, PLAN_FILE_SIZE, PLAN_FILE_NAME)
              VALUES
              (#{id}, #{planName},
              TO_DATE(#{planStartDate}, 'YYYY.MM.DD'),
              TO_DATE(#{planEndDate}, 'YYYY.MM.DD'),
              TO_DATE(#{planDeadline}, 'YYYY.MM.DD'),
              #{planMaxPersonnel}, #{planMinPersonnel}, ${planPrice}, #{planStartAddress},
              #{planContent}, #{memberId}, #{courseId}, #{planFilePath}, #{planFileSize}, #{planFileName})
          </insert>

자세하게 보지 않아서 몰랐었는데 계속해서 확인해보니 중간에 #이 아니라 $가 들어가 있어서 났던 오류였던걸 확인했다. 
이런 문제를 겪고나서 쿼리문을 꼼꼼하게 확인하는 습관이 들여졌다.


트러블 : 여행계획 수정을 진행하던중 schedule 테이블은 소프트 딜리트를 위해 status 컬럼을 생성해놨다.
select 쿼리문을 사용하니 그 전에 없어져야 할 데이터가 같이 출력되는 상황이 생겼다.

    planDTO.getDeleteSchedules().forEach((schedule) -> {
                  ScheduleVO scheduleVO = new ScheduleVO();
                  scheduleVO.setId(schedule);
                  scheduleDAO.setSchedule(scheduleVO);
             });

슈팅 : Service 쪽에 status를 disable로 변경시켜주지 않고 그대로 가져다 썻기 때문에 생긴 문제였다. 해당 문제는 아래와 같이 코드를 변경하고 해결하였다.

     planDTO.getDeleteSchedules().forEach((schedule) -> {
                    ScheduleVO scheduleVO = new ScheduleVO();
                    scheduleVO.setId(schedule);
                    scheduleVO.setStatus("DISABLED");
                    scheduleDAO.setSchedule(scheduleVO);
               });


트러블 : 여행계획 작성 중 파일첨부가 안되서 확인하니 NoSuchFileException이 발생했다.
<img src="https://github.com/user-attachments/assets/e14cbb57-4a0c-4326-ae6d-f7b7c1399bcb"/>

슈팅 : 파일 업로드하는 Service쪽에서 rootPath가 잘못 설정되어있어서 아래코드와 같이 수정하고 오류를 잡았다.

    String todayPath = getPath();
            String rootPath = "C:/upload/" + todayPath;
            String fileName = null;
            UUID uuid = UUID.randomUUID();
    
            try {
                File directory = new File(rootPath);
                if(!directory.exists()){
                    directory.mkdirs();
                }
    
                file.transferTo(new File(rootPath, uuid.toString() + "_" + file.getOriginalFilename()));


트러블 : 여행계획 수정 중 DTO에 List형식으로 있는 필드요소들이 기존에 데이터가 없을 때 수정버튼을 누르면 NullPointExeption이 발생했다.

                planDTO.getDeleteExcludes().forEach(writeExcludeDAO::delete);
                planDTO.getDeleteIncludes().forEach(writeIncludeDAO::delete);
                planDTO.getDeletePrepares().forEach(writePrepareDAO::delete);
                planDTO.getDeleteSchedules().forEach((schedule) -> {
                    ScheduleVO scheduleVO = new ScheduleVO();
                    scheduleVO.setId(schedule);
                    scheduleVO.setStatus("DISABLED");
                    scheduleDAO.setSchedule(scheduleVO);
               });

슈팅 : 예외처리로 NullPointExeption시 대체시킬 동작이 없기때문에 간단하게 조건식을 걸어 null일 경우에는 메소드를 사용하지 않는 방식으로 해결했다.

    if(planDTO.getDeleteExcludes() != null) {
                planDTO.getDeleteExcludes().forEach(writeExcludeDAO::delete);
            }
            if(planDTO.getDeleteIncludes() != null) {
                planDTO.getDeleteIncludes().forEach(writeIncludeDAO::delete);
            }
            if(planDTO.getDeletePrepares() != null) {
                planDTO.getDeletePrepares().forEach(writePrepareDAO::delete);
            }
            if(planDTO.getDeleteSchedules() != null) {
                planDTO.getDeleteSchedules().forEach((schedule) -> {
                    ScheduleVO scheduleVO = new ScheduleVO();
                    scheduleVO.setId(schedule);
                    scheduleVO.setStatus("DISABLED");
                    scheduleDAO.setSchedule(scheduleVO);
               });
            }



<h2>7. 느낀점</h2>


✨ 모르는건 창피한게 아니다!

첫 프로젝트이기도 하고 처음 개발을 접하다 보니 그 전에 강의해주신 내용들을 바로 쓰기에는 너무 어려웠다. 그러다보니 모르는게 너무 많지만 물어보기가 조금 창피했다. 
그렇게 시간이 지나면서 내가 모르는걸 계속해서 찾다보니 결국에는 찾아서 해결되지 않을 문제들도 존재했다. 강사님께 물어보고나니 금방 해결이 되는 문제들이었다. 
그렇게 금방 해결되는걸 경험해보니 창피한 것보다 모르는건 물어보고 빨리 해결해서 작업물에 결과가 나오는게 더 기분이 좋았다. 그 이후로는 많이 물어보게 됐다.

✨ 기획의 중요성

PickCourse 프로젝트를 끝내가면서 드는 생각이 있었다. 시작할 때 기획을 좀 더 꼼꼼하게 했다면 뒤로 가면서 덜 힘들었을 거라고 생각한다.
처음 기획하고 클론코딩을 할 때에는 큰 문제가 없었다. 그렇지만 테이블을짜면서부터 빠진 것들이 보이기 시작하더니 시간이 갈수록 더 큰 문제들이 보이기 시작했다.
기획이 잘못돼니 중요테이블이 빠지는 일도 종종 나왔고, 서버와 연동하면서 빠져있는 자질구레한 것들도 조금씩 있었다.
결국은 팀원들과 소통하여 기획을 수정하는 일이 많이 발생했다. 그러면서는 다음 기획에서는 서버까지 생각해서 짜야겠다는 걸 깨달았다.

✨ 완성하는 즐거움

내가 부족했기 때문에 어렵다고 느끼는 것들이 한두가지가 아니었기 때문에 결과물을 내기 위해서 진행하는 과정들이 쉽지 않았다.
그렇지만 포기하지 않고 계속해서 공부하고, 모르는 것들은 물어봐가면서 내가 원하는대로 동작할때, 그리고 결과물을 완성하고나서 확인할 때 제대로 동작할 때는 성취감이 너무 크고 재밋게 느껴졌다.

✨ 경험을 많이하자!

프로젝트를 진행하면서 생소한 코드들을 보면 어떻게 해야할지 난감한적이 많았다. 그렇지만 시간이 지나면서 코드가 눈에 익고나니 다시보니 그만큼 반가운 코드가 없었다.
처음 gitgub를 사용할 때에는 사용법을 몰라서 한명씩 push하고 pr하고 pull하고, 이런 바보같은 짓을 반복했었다. 내가 마지막으로 받게 됐는데 그러다보니 충돌이 많이나서 나는 그걸 해결하는데 시간을 많이썻다.
처음 빨간글씨로 뜬 에러들을보면 머리가 지끈거렸다. 어떤게 문제인지 보고 해석하는데도 이해가 잘 되지 않았다.
그렇지만 이런 에러들도 여러번 고쳐보다보니 나중에는 에러가 뜨게 되더라도 금방 해결하게 됐다. 그 이후로는 팀원들에게 불려가서 에러를 자주 고쳐주곤 했다.
코드와 에러들은 많이 봐두는 것이 좋다고 이번 프로젝트에서 깨달았다

✨ 개인보단 팀

이번 프로젝트에서 가장 크게 느낀 점은 팀원들이 있었기에 내가 여기까지 올 수 있었다고 생각한다.
서버쪽 작업을 마무리 할때에 나는 작업 진행률이 좀 더뎠었다. 한 페이지에 필요한 데이터들이 생각보다 너무 많았기에 한 페이지 완성하는데 오랜 시간이 걸렸다.
그러다보니 마감기한이 되었는데 아직 작업해야 할 작업물이 두개정도 남았었는데, 이걸 다른 팀원들이 필요한 DTO나 Service까지 필요한 쿼리문이나 이런 것들을 준비해주어서 기한내에 완성 할 수 있었다.
그 외에도 내가 코드를 짜면서 잘 못하고 있는 것 같으면 옆에서 많이 도와주기도 했다. 내가 도와준부분도 있지만 그래도 받은게 더 많다고 생각이 든다.
이런 일들을 겪으면서 난 확실히 개인보다 팀이 있는 것이 작업하는데에 있어서 더 좋다고 느꼇다.
