-- 포스트, 커뮤니티 게시글 슈퍼키
create table tbl_post (
                          id 			        bigint auto_increment primary key,
                          post_title 		    varchar(2000) not null,
                          post_content 		     varchar(4000) not null,
                          post_status 		     varchar(50) default '',
                          created_date 		datetime default current_timestamp,
                          updated_date 	    datetime default current_timestamp
);