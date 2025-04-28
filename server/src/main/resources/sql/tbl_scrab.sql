-- 스크랩 테이블
create table tbl_scrap (
    id bigint auto_increment primary key,
    member_id  bigint not null,
    post_id    bigint not null,
    scrap_status varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_scrap_member foreign key (member_id)
        references tbl_member (id),
    constraint fk_scrap_post foreign key (post_id)
        references tbl_post (id)
);
