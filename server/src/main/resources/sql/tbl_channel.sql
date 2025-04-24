-- 채널 정보 테이블
create table tbl_channel (
                             id 		            bigint auto_increment primary key,
                             channel_title 		    varchar(50) not null,
                             channel_introduce 		varchar(300) default '',
                             channel_url 		    varchar(100) not null,
                             channel_file_path 		varchar(500) default '',
                             channel_file_name 		varchar(500) default '',
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
    member_file_path,
    member_file_name,
    member_type,
    member_status
) VALUES (
             'qwer@qwe.qwe',
             'qwer', -- 비밀번호는 암호화 전 값
             'qwer',
             'qwerqwer.',
             'profile/2025/04/24',
             'profile_pic.png',
             '일반 회원',
             '정상'
         );

select * from tbl_member;
select * from tbl_channel;

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
             'qwerqwerqwerqwer',
             'qwe',
             'qwer',
             'channel/2025/04/24',
             'channel_banner.png',
             '정상',
             2
         );
