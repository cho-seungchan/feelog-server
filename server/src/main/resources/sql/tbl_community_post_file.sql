-- community 파일 테이블
create table tbl_community_post_file (
    id 		                bigint primary key,
    community_id 	        bigint not null,
    status 		            varchar(50) ,
    constraint fk_community_post_file_community_post foreign key (community_id)
    references tbl_community_post (id)
);