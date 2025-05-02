create table tbl_notice(
    id bigint auto_increment primary key,
    notice_title varchar(4000) not null,
    notice_content varchar(4000) not null,
    notice_file_name varchar(4000),
    notice_file_path varchar(4000),
    member_id bigint not null ,
    notice_status varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_notice_member foreign key (member_id)
                       references tbl_member(id)
);

INSERT INTO tbl_notice (
    notice_title,
    notice_content,
    notice_file_name,
    notice_file_path,
    member_id
) VALUES
      ('[점검 안내] 5월 10일(금) 오전 2시 서비스 일시 중단',
       '시스템 안정화를 위한 점검으로 인해 일시적으로 접속이 제한됩니다. 양해 부탁드립니다.',
       'maintenance.png',
       'notice/2025/05',
       16),

      ('[신규 기능] 감정 분석 스티커 기능 출시',
       '오늘의 감정을 시각화해주는 스티커 기능이 추가되었습니다! 지금 바로 체험해보세요.',
       'sticker-launch.jpg',
       'notice/2025/05',
       16),

      ('[이벤트] 마음일기 100편 달성 이벤트 진행 중',
       '100번째 일기를 작성하면 추첨을 통해 선물을 드립니다. 지금 바로 참여하세요!',
       'event-100.jpg',
       'notice/2025/05',
       16),

      ('[안내] 개인정보 처리방침 변경 예정',
       '2025년 5월 15일부터 새로운 개인정보 처리방침이 적용됩니다. 사전 확인 부탁드립니다.',
       'policy-change.png',
       'notice/2025/05',
       16),

      ('[업데이트] 채널 커버 이미지 기능 추가',
       '채널 대표 이미지 커스터마이징이 가능해졌습니다. 나만의 커버를 설정해보세요.',
       'cover-update.jpg',
       'notice/2025/05',
       16);
