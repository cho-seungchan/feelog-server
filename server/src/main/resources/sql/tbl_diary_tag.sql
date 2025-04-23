-- 다이어리 태그 테이블
drop table if exists tbl_diary_tag;

-- 다이어리 전용 태그 (태그 슈퍼키 상속)
create table tbl_diary_tag (
    id bigint primary key,
    diary_id bigint not null,
    constraint fk_diary_tag_diary foreign key (diary_id) references tbl_diary(id),
    constraint fk_diary_tag_tag foreign key (id) references tbl_tag(id)
);

SELECT GROUP_CONCAT(t.contents)
FROM tbl_diary_tag dt
         JOIN tbl_tag t ON dt.id = t.id
WHERE dt.diary_id = 59
  AND t.tag_status = 'ACTIVE';