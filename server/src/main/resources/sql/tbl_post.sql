-- 포스트, 커뮤니티 게시글 슈퍼키
create table tbl_post (
       id 			        bigint auto_increment primary key,
       post_title 		    varchar(2000) not null,
       post_content 		varchar(4000) not null,
       post_status 		    varchar(50) default '정상',
       created_date 		datetime default current_timestamp,
       updated_date 	    datetime default current_timestamp
);

select * from tbl_post;


-- tbl_post
INSERT INTO tbl_post (post_title, post_content)
VALUES ('테스트 커뮤니티 글 제목', '테스트 커뮤니티 글 내용');

select * from tbl_community_post;
select * from tbl_notification;
select * from tbl_community_post_notification;

-- tbl_community_post
INSERT INTO tbl_community_post (id, member_id, channel_id)
VALUES (48, 1, 1);

