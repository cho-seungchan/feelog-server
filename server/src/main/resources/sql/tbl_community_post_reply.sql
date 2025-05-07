-- community 댓글 테이블
create table tbl_community_post_reply
(
    id                             bigint primary key,
    community_post_reply_file_path varchar(500) default '',
    community_post_reply_file_name varchar(500) default '',
    member_id                      bigint not null,
    post_id                        bigint not null,
    constraint fk_community_post_reply_reply foreign key (id)
        references tbl_reply (id),
    constraint fk_community_post_reply_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_community_post_reply_community_post foreign key (post_id)
        references tbl_community_post (id)
);
