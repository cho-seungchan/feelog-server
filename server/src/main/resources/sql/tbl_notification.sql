create table tbl_notification(
    id bigint auto_increment primary key ,
    sender_id bigint not null ,
    receiver_id bigint not null ,
    checked varchar(50) default '안읽음',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_notification_sender foreign key (sender_id)
                             references tbl_member(id),
    constraint fk_notification_receiver foreign key (receiver_id)
                             references tbl_member(id)
);


