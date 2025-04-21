-- 슈퍼키 상속 구조 적용: file.id = diary_file.id
create table tbl_diary_file (
    id bigint primary key,
    diary_id bigint not null,
    constraint fk_diary_file_diary foreign key (diary_id) references tbl_diary(id),
    constraint fk_diary_file_file foreign key (id) references tbl_file(id)
);

drop table tbl_diary_file;
