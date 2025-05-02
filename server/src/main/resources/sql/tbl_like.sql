-- 좋아요 테이블 슈퍼키
create table tbl_like (
    id           bigint auto_increment primary key,
    like_status  varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp
);

alter table tbl_like modify like_status varchar(50) default '정상';


