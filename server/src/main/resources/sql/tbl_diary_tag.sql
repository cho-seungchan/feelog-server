-- 다이어리 태그 테이블
drop table if exists tbl_diary_tag;

create table tbl_diary_tag (
       diary_id bigint not null,
       tag_id bigint not null,
       primary key (diary_id, tag_id),
       constraint fk_diary_tag_diary foreign key (diary_id) references tbl_diary(id),
       constraint fk_diary_tag_tag foreign key (tag_id) references tbl_tag(id)
);
