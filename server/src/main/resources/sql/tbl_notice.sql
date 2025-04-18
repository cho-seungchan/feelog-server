create table tbl_notice(
    id bigint auto_increment primary key,
    notice_title varchar(4000) not null,
    notice_content varchar(4000) not null,
    member_id bigint not null ,
    notice_status varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_notice_member foreign key (member_id)
                       references tbl_member(id)
);