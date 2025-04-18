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

