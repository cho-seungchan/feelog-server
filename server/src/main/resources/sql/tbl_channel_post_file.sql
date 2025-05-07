-- 게시물 파일 테이블
create table tbl_channel_post_file
(
    id      bigint primary key,
    post_id bigint not null,
    constraint fk_channel_post_file_channel_post foreign key (id)
        references tbl_file (id),
    constraint fk_channel_post_file_post foreign key (post_id)
        references tbl_channel_post (id)
);

