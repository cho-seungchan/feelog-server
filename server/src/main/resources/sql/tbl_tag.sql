-- 태그 테이블
create table tbl_tag (
    id bigint auto_increment primary key,
    contents varchar(50),
    tag_status varchar(50) default 'ACTIVE',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp
);

ALTER TABLE tbl_tag
    ALTER COLUMN tag_status SET DEFAULT 'ACTIVE';

select * from tbl_tag;