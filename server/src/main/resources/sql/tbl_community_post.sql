-- community 글 테이블
create table tbl_community_post (
    id 		                bigint primary key,
    channel_id 	            bigint not null,
    constraint fk_community_post_post foreign key (id)
    references tbl_post (id) ,
    constraint fk_community_post_channel foreign key (channel_id)
    references tbl_channel (id)
);

