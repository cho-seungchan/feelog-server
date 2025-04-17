-- community 좋아요 테이블
create table tbl_community_post_like (
    id 		            bigint primary key,
    member_id 	        bigint not null,
    community_id 	    bigint not null,
    constraint fk_community_post_like_like foreign key (id)
    references tbl_like (id) ,
    constraint fk_community_post_like_member foreign key (member_id)
    references tbl_member (id) ,
    constraint fk_community_post_like_community_post foreign key (community_id)
    references tbl_community_post (id)
);
