create table tbl_subscribe_notification (
    id bigint primary key,
    subscribe_id bigint not null,
    constraint fk_notification_subscribe foreign key(id)
        references tbl_notification(id),
    constraint fk_subscribe_notification foreign key(subscribe_id)
        references tbl_subscribe(id)
);

