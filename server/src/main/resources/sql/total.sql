-- 회원 정보
create table tbl_member (
id 			                                bigint auto_increment primary key,
member_email 		                        varchar(200) not null,
member_password	                            varchar(200),
member_nickname 		                        varchar(50),
member_introduce 		                    varchar(300) default '',
member_file_path 		                    varchar(500) default '',
member_file_name 		                    varchar(500) default '',
member_type 		                        varchar(50) default '일반 회원',
member_suspend 		                        varchar(50) default '',
member_notification_post_reply              varchar(50) ,
member_notification_post_reply_like         varchar(50) ,
member_notification_post_like               varchar(50) ,
member_notification_subscribe               varchar(50) ,
member_notification_community_post          varchar(50) ,
member_notification_message                 varchar(50) ,
member_status 		                        varchar(50) default '정상',
created_date 	                            datetime default current_timestamp,
updated_date 	                            datetime default current_timestamp
);


--  선정 과제(챌린지) 슈퍼키
create table tbl_challenge (
id 		                         bigint auto_increment primary key,
challenge_complete           	 varchar(50) default '',
challenge_status 		         varchar(50) default '정상',
created_date 	                 datetime default current_timestamp,
updated_date 	                 datetime default current_timestamp
);

-- 채널 정보 테이블
create table tbl_channel (
id 		            bigint auto_increment primary key,
channel_title 		    varchar(50) not null,
channel_introduce 		varchar(300) default '',
channel_url 		        varchar(50) not null,
channel_file_path 		varchar(500) default '',
channel_file_name 		varchar(500) default '',
channel_file_size 		varchar(100) default '',
channel_status 		 varchar(50) default '정상',
member_id               bigint not null,
created_date 	        datetime default current_timestamp,
updated_date 	        datetime default current_timestamp,
constraint fk_channel_member foreign key (member_id)
references tbl_member (id)
);


-- 태그 테이블
create table tbl_tag (
id bigint auto_increment primary key,
contents varchar(50) unique,
tag_status varchar(50) default '정상',
created_date datetime default current_timestamp,
updated_date datetime default current_timestamp
);


-- ai 응답에 대한 감정 이미지 테이블
create table tbl_feel (
id 			        bigint auto_increment primary key,
feel_code              int  not null,
feel_file_path 		varchar(500) default '',
feel_file_name 		varchar(500) default '',
feel_file_size 		varchar(100) default '',
created_date 	    datetime default current_timestamp,
updated_date 	    datetime default current_timestamp
);


-- 마음의 일기 테이블
-- 전체 공개 / 구독자에게만 공개 / 비공개
create table tbl_diary (
id 			        bigint auto_increment primary key,
diary_content 		varchar(4000) not null,
diary_open       	varchar(50) default '비공개',
diary_file_path 		varchar(500) default '',
diary_file_name 		varchar(500) default '',
diary_file_size 		varchar(500) default '',
diary_weather        varchar(500) default '',
member_id 		    bigint not null,
feel_id              bigint not null,
diary_status 		varchar(50) default '정상',
created_date 		datetime default current_timestamp,
updated_date 	    datetime default current_timestamp,
constraint fk_diary_member foreign key (member_id)
references tbl_member (id),
constraint fk_diary_feel foreign key (feel_id)
references tbl_feel (id)
);



-- 포스트, 커뮤니티 게시글 슈퍼키
create table tbl_post (
id 			        bigint auto_increment primary key,
post_title 		    varchar(2000) not null,
post_content 		varchar(4000) not null,
post_status 		    varchar(50) default '정상',
created_date 		datetime default current_timestamp,
updated_date 	    datetime default current_timestamp
);



create table tbl_notice(
id bigint auto_increment primary key,
notice_title varchar(4000) not null,
notice_content varchar(4000) not null,
member_id bigint not null ,
notice_status varchar(50) default '정상',
created_date datetime default current_timestamp,
updated_date datetime default current_timestamp,
constraint fk_notice_member foreign key (member_id)
references tbl_member(id)
);


create table tbl_message(
id bigint auto_increment primary key ,
message_content varchar(1000),
created_date datetime default current_timestamp,
updated_date datetime default current_timestamp
);

-- file 슈퍼키
create table tbl_file(
id bigint auto_increment primary key,
file_path 	     	varchar(500) default '',
file_name 	     	varchar(500) default '',
file_size 	     	varchar(500) default '',
file_status        varchar(50)  default '정상',
created_date 	    datetime default current_timestamp,
updated_date 	    datetime default current_timestamp
);

-- 좋아요 테이블 슈퍼키
create table tbl_like (
id           bigint auto_increment primary key,
like_status  varchar(50) default '정상',
created_date datetime default current_timestamp,
updated_date datetime default current_timestamp
);

-- 개인용 도전 과제 pool 테이블
create table tbl_member_task_pool (
id 		                    bigint auto_increment primary key,
mamber_task_pool_content 	varchar(1000) not null,
mamber_task_pool_file_path 	varchar(1000) default '',
mamber_task_pool_file_name 	varchar(1000) default '',
mamber_task_pool_file_size 	varchar(100) default '',
member_task_pool_status 	varchar(50) default '정상',
created_date 	            datetime default current_timestamp,
updated_date 	            datetime default current_timestamp
);

-- 개인용 도전 과제 pool 테이블
create table tbl_member_task(
id 		                    bigint auto_increment primary key,
task_pool_id                 bigint,
member_task_status		    varchar(50) default '정상',
created_date 	            datetime default current_timestamp,
updated_date 	            datetime default current_timestamp,
constraint fk_member_challenge_task foreign key (task_pool_id)
references tbl_member_task_pool (id)
);

-- 선정 과제 (개인용) 테이블
create table tbl_member_challenge (
id 		                         bigint primary key,
member_id 	                     bigint not null,
task_id 		                     bigint not null,
constraint fk_member_challenge_challenge foreign key (id)
references tbl_challenge (id) ,
constraint fk_member_challenge_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_member_challenge_member_task foreign key (task_id)
references tbl_member_task (id)
);

create table tbl_faq(
id bigint auto_increment primary key,
faq_title varchar(4000) not null,
faq_content varchar(4000) not null,
member_id bigint not null ,
faq_status varchar(50) default '정상',
created_date datetime default current_timestamp,
updated_date datetime default current_timestamp,
constraint fk_faq_member foreign key (member_id)
references tbl_member(id)
);


create table tbl_notification(
id bigint auto_increment primary key ,
sender_id bigint not null ,
receiver_id bigint not null ,
checked varchar(50) default '안읽음',
created_date datetime default current_timestamp,
updated_date datetime default current_timestamp,
constraint fk_notification_sender foreign key (sender_id)
references tbl_member(id),
constraint fk_notification_receiver foreign key (receiver_id)
references tbl_member(id)
);


-- 다이어리 태그 테이블
create table tbl_diary_tag (
id bigint primary key,
tag_id bigint not null,
constraint fk_diary_tag_diary foreign key (id)
references tbl_diary (id),
constraint fk_diary_tag__tag foreign key (tag_id)
references tbl_tag (id)
);

-- 신고 테이블 슈퍼키
create table tbl_report (
id 		            bigint auto_increment primary key,
report_status               varchar(50) default '정상',
created_date 	    datetime default current_timestamp,
updated_date 	    datetime default current_timestamp
);

-- 댓글 테이블 슈퍼키
create table tbl_reply (
id 		            bigint auto_increment primary key,
reply_content 		varchar(2000),
reply_file_path 		varchar(500) default '',
reply_file_name 		varchar(500) default '',
reply_file_size 		varchar(100) default '',
reply_status               varchar(50) default '정상',
created_date 	    datetime default current_timestamp,
updated_date 	    datetime default current_timestamp
);


create table tbl_subscribe(
id bigint auto_increment primary key ,
channel_id bigint not null ,
member_id bigint not null ,
subscribe_status varchar(50) default '정상',
created_date datetime default current_timestamp,
updated_date datetime default current_timestamp,
constraint fk_subscribe_member foreign key (member_id)
references tbl_member(id),
constraint fk_subscribe_channel foreign key (channel_id)
references tbl_channel(id)
);



create table tbl_subscribe_notification (
id bigint primary key,
subscribe_id bigint not null,
constraint fk_notification_subscribe foreign key(id)
references tbl_notification(id),
constraint fk_subscribe_notification foreign key(subscribe_id)
references tbl_subscribe(id)
);

create table tbl_send_message(
id bigint not null,
member_id bigint not null ,
receiver_id bigint not null ,
receive_message_status varchar(50) default '정상',
constraint fk_send_message_message foreign key (id)
references tbl_message(id),
constraint fk_send_message_receiver foreign key (member_id)
references tbl_member(id),
constraint fk_send_message_sender foreign key (receiver_id)
references tbl_member(id)
);

create table tbl_receive_message(
id bigint not null,
member_id bigint not null ,
sender_id bigint not null ,
receive_message_status varchar(50) default '정상',
constraint fk_receive_message_message foreign key (id)
references tbl_message(id),
constraint fk_receive_message_receiver foreign key (member_id)
references tbl_member(id),
constraint fk_receive_message_sender foreign key (sender_id)
references tbl_member(id)
);

create table tbl_receive_message_notification (
id bigint primary key,
receive_message_id bigint not null,
constraint fk_notification_receive_message foreign key(id)
references tbl_notification(id),
constraint fk_receive_message_notification foreign key(receive_message_id)
references tbl_receive_message(id)
);


-- 포스트 태그 테이블
create table tbl_post_tag (
id bigint primary key,
tag_id bigint not null,
constraint fk_post_tag_post foreign key (id)
references tbl_post (id),
constraint fk_post_tag_tag foreign key (tag_id)
references tbl_tag (id)
);

-- 챌린지 연결 다이어리
create table tbl_challenge_diary (
id 		                    bigint primary key,
challenge_id                  bigint not null,
constraint fk_challenge_diary_diary foreign key (id)
references tbl_diary (id),
constraint fk_challenge_diary_challenge foreign key (challenge_id)
references tbl_challenge (id)
);

-- 포스크 게시글
-- 포스트 / 응원글
create table tbl_channel_post (
id 			        bigint primary key,
post_type 		    varchar(50) default '포스트',
post_file_path 		varchar(500) default '',
post_file_name 		varchar(500) default '',
post_file_size 		varchar(500) default '',
member_id            bigint not null,
channel_id 		    bigint not null,
constraint fk_channel_post_post foreign key (id)
references tbl_post (id),
constraint fk_channel_post_member foreign key (member_id)
references tbl_member (id),
constraint fk_channel_post_channel foreign key (channel_id)
references tbl_channel (id)
);



-- 게시물 파일 테이블
create table tbl_channel_post_file (
id 		            bigint primary key,
post_id 		        bigint not null,
constraint fk_channel_post_file_channel_post foreign key (id)
references tbl_file (id),
constraint fk_channel_post_file_post foreign key (post_id)
references tbl_channel_post (id)
);


-- 게시물 좋아요 테이블
create table tbl_channel_post_like (
id 		        bigint primary key,
member_id 	    bigint not null,
post_id 	 	    bigint not null,
constraint fk_channel_post_like_like foreign key (id)
references tbl_like (id) ,
constraint fk_channel_post_like_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_channel_post_like_channel_post foreign key (post_id)
references tbl_channel_post (id)
);

-- post 댓글 테이블
create table tbl_channel_post_reply (
id 		                    bigint primary key,
member_id 	                bigint not null,
post_id 		                bigint not null,
constraint fk_channel_post_reply_reply foreign key (id)
references tbl_reply (id) ,
constraint fk_channel_post_reply_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_channel_post_reply_channel_post foreign key (post_id)
references tbl_channel_post (id)
);

-- post 댓글 좋아요 테이블
create table tbl_channel_post_reply_like (
id 		   bigint primary key,
member_id  bigint not null,
reply_id   bigint not null,
constraint fk_channel_post_reply_like foreign key (id)
references tbl_like (id) ,
constraint fk_channel_post_reply_like_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_channel_post_reply_like_reply foreign key (reply_id)
references tbl_channel_post_reply (id)
);

-- post reply 신고 테이블
create table tbl_channel_post_reply_report (
id 		            bigint primary key ,
member_id 	        bigint not null,
reply_id 		    bigint not null,
constraint fk_channel_post_reply_report_report foreign key (id)
references tbl_report (id) ,
constraint fk_channel_post_reply_report_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_channel_post_reply_report_channel_post_reply foreign key (reply_id)
references tbl_channel_post_reply (id)
);


-- post 신고 테이블
create table tbl_channel_post_report (
id 		            bigint primary key ,
member_id 	        bigint not null,
post_id 		        bigint not null,
constraint fk_channel_post_report_report foreign key (id)
references tbl_report (id) ,
constraint fk_channel_post_report_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_channel_post_report_channel_post foreign key (post_id)
references tbl_channel_post (id)
);


-- 게시물 스크랩 테이블
create table tbl_channel_post_scrap (
id               bigint 		auto_increment primary key,
member_id 	    bigint not null,
post_id 		    bigint not null,
channel_post_scrap_status 		    varchar(50) default '정상',
created_date 	datetime default current_timestamp,
updated_date 	datetime default current_timestamp,
constraint fk_channel_post_scrap_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_channel_post_scrap_channel_post foreign key (post_id)
references tbl_post (id)
);


-- 전 회원용 도전 과제 테이블
create table tbl_common_task (
id 		                    bigint auto_increment primary key,
common_task_name             varchar(1000) not null,
common_task_img              varchar(1000),
common_task_tell             varchar(50),
common_task_url              varchar(1000),
common_task_addr             varchar(1000),
common_task_lot              decimal(10,6),                 -- 위도
common_task_lat              decimal(10,6),                 -- 경도
common_task_service_name 	  varchar(100) not null,    -- 열린광장 서비스 명
common_task_req_page 	      varchar(20) not null,     -- 열린광장 페이지
created_date 	            datetime default current_timestamp,
updated_date 	            datetime default current_timestamp
);



-- 선정 과제 (전 회원용) 테이블
create table tbl_common_challenge (
id 			                    bigint  primary key,
member_id 		                bigint not null,
task_id 		                    bigint not null,
constraint fk_common_challenge_challenge foreign key (id)
references tbl_challenge (id) ,
constraint fk_common_challenge_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_common_challenge_task foreign key (task_id)
references tbl_common_task (id)
);


-- community 글 테이블
create table tbl_community_post (
id 		                bigint primary key,
member_id	            bigint not null,
channel_id 	            bigint not null,
constraint fk_community_post_post foreign key (id)
references tbl_post (id) ,
constraint fk_community_post_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_community_post_channel foreign key (channel_id)
references tbl_channel (id)
);


-- community 파일 테이블
create table tbl_community_post_file (
id 		                bigint primary key,
community_id 	        bigint not null,
constraint fk_community_post_file_file foreign key (id)
references tbl_file (id),
constraint fk_community_post_file_community_post foreign key (community_id)
references tbl_community_post (id)
);


-- community 좋아요 테이블
create table tbl_community_post_like (
id 		            bigint primary key,
member_id 	        bigint not null,
community_id 	    bigint not null,
constraint fk_community_post_like_like foreign key (id)
references tbl_like (id) ,
constraint fk_community_post_like_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_community_post_like_community_post foreign key (community_id)
references tbl_community_post (id)
);


create table tbl_community_post_notification (
id bigint primary key,
community_post_id bigint not null,
constraint fk_notification_community_post foreign key(id)
references tbl_notification(id),
constraint fk_community_post_notification foreign key(community_post_id)
references tbl_community_post(id)
);


-- community 댓글 테이블
create table tbl_community_post_reply (
id 		                        bigint primary key ,
member_id 	                    bigint not null,
community_id	                    bigint not null,
constraint fk_community_post_reply_reply foreign key (id)
references tbl_reply (id) ,
constraint fk_community_post_reply_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_community_post_reply_community_post foreign key (community_id)
references tbl_community_post (id)
);


-- community 댓글 좋아요 테이블
create table tbl_community_post_reply_like (
id 			    bigint primary key,
member_id 		bigint not null,
reply_id 		bigint not null,
constraint fk_community_post_reply_like_like foreign key (id)
references tbl_like (id) ,
constraint fk_community_post_reply_like_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_community_post_reply_like_reply foreign key (reply_id)
references tbl_community_post_reply (id)
);

create table tbl_community_post_reply_report(
id bigint primary key ,
member_id bigint not null,
reply_id bigint not null ,
constraint fk_comunity_post_reply_report_report foreign key (id)
references tbl_report(id),
constraint fk_comunity_post_reply_report_member foreign key (member_id)
references tbl_member(id),
constraint fk_comunity_post_reply_report_reply foreign key (reply_id)
references tbl_reply(id)
);

-- community 신고 테이블
create table tbl_community_post_report (
id 			    bigint primary key ,
member_id 		bigint not null,
community_id 	bigint not null,
constraint fk_community_post_report_report foreign key (id)
references tbl_report (id) ,
constraint fk_community_post_report_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_community_post_report_community_post foreign key (community_id)
references tbl_community_post (id)
);


-- diary 좋아요 테이블
create table tbl_diary_like (
id 		            bigint primary key,
member_id 	        bigint not null,
diary_id 	        bigint not null,
constraint fk_diary_like_like foreign key (id)
references tbl_like (id) ,
constraint fk_diary_like_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_diary_like_diary foreign key (diary_id)
references tbl_diary (id)
);


-- diary 댓글 테이블
create table tbl_diary_reply (
id 		                    bigint primary key ,
member_id 	                    bigint not null,
diary_id	                    bigint not null,
constraint fk_diary_reply_reply foreign key (id)
references tbl_reply (id) ,
constraint fk_diary_reply_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_diary_reply_diary foreign key (diary_id)
references tbl_diary (id)
);


-- diary 댓글 좋아요 테이블
create table tbl_diary_reply_like (
id 			                    bigint primary key,
member_id 		                bigint not null,
reply_id 		                    bigint not null,
constraint fk_diary_reply_like_like foreign key (id)
references tbl_like (id) ,
constraint fk_diary_reply_like_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_diary_reply_like_reply foreign key (reply_id)
references tbl_diary_reply (id)
);



-- diary  reply 신고 테이블
create table tbl_diary_reply_report (
id 		                                bigint primary key ,
member_id 	                            bigint not null,
reply_id 		                        bigint not null,
constraint fk_diary_reply_report_report foreign key (id)
references tbl_report (id) ,
constraint fk_diary_reply_report_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_diary_reply_report_diary_reply foreign key (reply_id)
references tbl_diary_reply (id)
);


-- diary  신고 테이블
create table tbl_diary_report (
id 			                bigint primary key ,
member_id 		            bigint not null,
diary_id 	                    bigint not null,
constraint fk_diary_report_report foreign key (id)
references tbl_report (id) ,
constraint fk_diary_report_member foreign key (member_id)
references tbl_member (id) ,
constraint fk_diary_report_diary foreign key (diary_id)
references tbl_diary (id)
);


create table tbl_post_like_notification (
id bigint primary key,
post_like_id bigint not null,
constraint fk_notification_post_like foreign key(id)
references tbl_notification(id),
constraint fk_post_like_notification foreign key(post_like_id)
references tbl_channel_post_like(id)
);


create table tbl_post_reply_notification (
id bigint primary key,
post_reply_id bigint not null,
constraint fk_notification_post_reply foreign key(id)
references tbl_notification(id),
constraint fk_post_reply_notification foreign key(post_reply_id)
references tbl_channel_post_reply(id)
);


create table tbl_post_reply_like_notification (
id bigint primary key,
post_reply_like_id bigint not null,
constraint fk_notification_post_reply_like foreign key(id)
references tbl_notification(id),
constraint fk_post_reply_like_notification foreign key(post_reply_like_id)
references tbl_channel_post_reply_like(id)
);


-- 알림 뷰

create view view_all_notification as
select n.id,
sender_id,
receiver_id,
checked,
created_date,
updated_date,

post_like_id,
post_reply_id,
post_reply_like_id,
subscribe_id,
community_post_id,
receive_message_id
from tbl_notification n
join tbl_post_like_notification pl on n.id = pl.id
join tbl_post_reply_notification pr on n.id = pr.id
join tbl_post_reply_like_notification prl on n.id = prl.id
join tbl_subscribe_notification s on n.id = s.id
join tbl_community_post_notification cp on n.id = cp.id
join tbl_receive_message_notification rm on n.id = rm.id





