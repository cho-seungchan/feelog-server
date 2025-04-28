create table tbl_receive_message_notification (
      id bigint primary key,
      receive_message_id bigint not null,
      constraint fk_notification_receive_message foreign key(id)
          references tbl_notification(id) ON DELETE CASCADE,
      constraint fk_receive_message_notification foreign key(receive_message_id)
          references tbl_receive_message(id) ON DELETE CASCADE
);



