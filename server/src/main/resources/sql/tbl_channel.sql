-- 채널 정보 테이블
create table tbl_channel (
                             id 		            bigint auto_increment primary key,
                             channel_title 		    varchar(50) not null,
                             channel_introduce 		varchar(300) default '',
                             channel_url 		    varchar(100) not null,
                             channel_file_path 		varchar(500),
                             channel_file_name 		varchar(500),
                             channel_status 		 varchar(50) default '정상',
                             member_id               bigint not null,
                             created_date 	        datetime default current_timestamp,
                             updated_date 	        datetime default current_timestamp,
                             constraint fk_channel_member foreign key (member_id)
                             references tbl_member (id)
);

select * from tbl_channel;

-- 1. 멤버 생성
INSERT INTO tbl_member (
    member_email,
    member_password,
    member_nickname,
    member_introduce,
    member_notification_community_post,
    member_notification_message,
    member_notification_post_like,
    member_notification_post_reply,
    member_notification_post_reply_like,
    member_notification_subscribe,
    member_type,
    member_status
) VALUES (
             'feelog@feelog.com',
             'qwer123123', -- 비밀번호는 암호화 전 값
             'FEELOG',
             'FEELOG는 항상 여러분과 함께 합니다. 도움이 필요하시다면 언제든지 연락주세요. 감사합니다',
             '정상',
             '정상',
             '정상',
             '정상',
             '정상',
             '정상',
             '일반 회원',
             '정상'
         );

select * from tbl_member;
select * from tbl_channel;
select * from tbl_subscribe;
select * from feelog.tbl_subscribe_notification;

-- 2. 채널 생성
INSERT INTO tbl_channel (
    channel_title,
    channel_introduce,
    channel_url,
    channel_file_path,
    channel_file_name,
    channel_status,
    member_id
) VALUES (
             'FEELOG',
             'FEELOG는 항상 여러분과 함께 합니다. 도움이 필요하시다면 언제든지 연락주세요. 감사합니다',
             'official',
             'channel/2025/04/24',
             'channel_banner.png',
             '정상',
             16
         );
