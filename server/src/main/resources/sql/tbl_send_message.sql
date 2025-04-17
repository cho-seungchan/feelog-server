create table tbl_send_message(
    id bigint not null,
    member_id bigint not null ,
    receiver_id bigint not null ,
    receive_message_status varchar(50) default '정상',
    constraint fk_send_message_message foreign key (id)
        references tbl_message(id),
    constraint fk_send_message_receiver foreign key (member_id)
        references tbl_member(id),
    constraint fk_send_message_sender foreign key (receiver_id)
        references tbl_member(id)
)
