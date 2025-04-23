-- community 파일 테이블
create table tbl_community_post_file (
    id 		                bigint primary key,
    community_post_id 	        bigint not null,
    constraint fk_community_post_file_file foreign key (id)
    references tbl_file (id),
    constraint fk_community_post_file_community_post foreign key (community_post_id)
    references tbl_community_post (id)
);

