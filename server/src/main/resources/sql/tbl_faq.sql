create table tbl_faq(
    id bigint auto_increment primary key,
    faq_title varchar(4000) not null,
    faq_content varchar(4000) not null,
    member_id bigint not null ,
    faq_status varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_faq_member foreign key (member_id)
                       references tbl_member(id)
);


