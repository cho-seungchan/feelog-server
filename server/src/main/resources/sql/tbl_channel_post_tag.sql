-- 채널 포스트 전용 태그 (태그 슈퍼키 상속)
create table tbl_channel_post_tag (
    id                bigint primary key,
    channel_post_id   bigint not null,
    constraint fk_channel_post_tag_channel_post foreign key (channel_post_id)
    references tbl_channel_post(id),
    constraint fk_channel_post_tag_tag foreign key (id)
    references tbl_tag(id)
);
