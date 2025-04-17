-- 게시물 스크랩 테이블
create table tbl_channel_post_scrap (
    id               bigint 		auto_increment primary key,
    member_id 	    bigint not null,
    post_id 		    bigint not null,
    channel_post_scrap_status 		    varchar(50) ,
    created_date 	datetime default current_timestamp,
    updated_date 	datetime default current_timestamp,
    constraint fk_channel_post_scrap_member foreign key (member_id)
    references tbl_member (id) ,
    constraint fk_channel_post_scrap_channel_post foreign key (post_id)
    references tbl_post (id)
);
