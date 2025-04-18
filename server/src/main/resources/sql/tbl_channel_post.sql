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

