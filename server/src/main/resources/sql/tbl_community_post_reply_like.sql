-- community 댓글 좋아요 테이블
create table tbl_community_post_reply_like (
    id 			    bigint primary key,
    member_id 		bigint not null,
    reply_id 		bigint not null,
    constraint fk_community_post_reply_like_like foreign key (id)
    references tbl_like (id) ,
    constraint fk_community_post_reply_like_member foreign key (member_id)
    references tbl_member (id) ,
    constraint fk_community_post_reply_like_reply foreign key (reply_id)
    references tbl_community_post_reply (id)
);



