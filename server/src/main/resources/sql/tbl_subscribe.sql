create table tbl_subscribe(
    id bigint auto_increment primary key ,
    channel_id bigint not null ,
    member_id bigint not null ,
    subscribe_status varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_subscribe_member foreign key (member_id)
                          references tbl_member(id),
    constraint fk_subscribe_channel foreign key (channel_id)
                          references tbl_channel(id)
)