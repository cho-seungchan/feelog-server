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