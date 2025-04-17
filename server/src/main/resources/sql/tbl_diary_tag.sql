-- 다이어리 태그 테이블
create table tbl_diary_tag (
    id bigint primary key,
    tag_id bigint not null,
    constraint fk_diary_tag_diary foreign key (id)
    references tbl_diary (id),
    constraint fk_diary_tag__tag foreign key (tag_id)
    references tbl_tag (id)
)