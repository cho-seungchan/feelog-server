create table tbl_diary_file (
    id bigint auto_increment primary key,
    diary_id bigint not null,
    file_id bigint not null,
    constraint fk_diary_file_diary foreign key (diary_id) references tbl_diary(id),
    constraint fk_diary_file_file foreign key (file_id) references tbl_file(id)
);


