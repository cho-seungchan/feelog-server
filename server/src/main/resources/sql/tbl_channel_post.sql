-- 포스트 / 응원글
create table tbl_channel_post (
                                  id 			        bigint primary key,
                                  post_type 		    varchar(50) default '포스트',
                                  post_read_count       int default 0,
                                  channel_post_status varchar(50) default '정상',
                                  post_file_path 		varchar(500) default '',
                                  post_file_name 		varchar(500) default '',
                                  member_id             bigint not null,
                                  channel_id 		    bigint not null,
                                  constraint fk_channel_post_post foreign key (id)
                                      references tbl_post (id),
                                  constraint fk_channel_post_member foreign key (member_id)
                                      references tbl_member (id),
                                  constraint fk_channel_post_channel foreign key (channel_id)
                                      references tbl_channel (id)
);

INSERT INTO tbl_post (post_title, post_content)
VALUES ('테스트 포스트 제목', '테스트 포스트 내용');

select * from tbl_post;

INSERT INTO tbl_channel_post (id, member_id, channel_id)
VALUES (49, 1, 1);

INSERT INTO tbl_reply (reply_content)
VALUES ('테스트 댓글 내용');

select * from tbl_reply;

INSERT INTO tbl_channel_post_reply (id, member_id, post_id)
VALUES (1, 2, 49);

select * from tbl_channel_post_reply;