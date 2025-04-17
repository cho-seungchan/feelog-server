-- 태그 테이블
create table tbl_tag (
    id bigint auto_increment primary key,
    contents varchar(50) unique,
    tag_status varchar(50) default '정상'
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp
);