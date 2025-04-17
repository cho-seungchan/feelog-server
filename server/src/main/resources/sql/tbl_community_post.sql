-- community 글 테이블
create table tbl_community_post (
    id 		                bigint primary key,
    member_id	            bigint not null,
    channel_id 	            bigint not null,
    status 		            varchar(50) ,
    constraint fk_community_post_post foreign key (id)
    references tbl_post (id) ,
    constraint fk_community_post_member foreign key (member_id)
    references tbl_member (id) ,
    constraint fk_community_post_channel foreign key (channel_id)
    references tbl_channel (id)
);