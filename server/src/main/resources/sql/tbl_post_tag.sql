--포스트 태그 테이블
create table tbl_post_tag (
    id bigint primary key,
    tag_id bigint not null,
    constraint fk_post_tag_post foreign key (id)
    references tbl_post (id),
    constraint fk_post_tag_tag foreign key (tag_id)
    references tbl_tag (id)
)