create table tbl_post_reply_notification (
     id bigint primary key,
     post_reply_id bigint not null,
     constraint fk_notification_post_reply foreign key(id)
         references tbl_notification(id) ON DELETE CASCADE,
     constraint fk_post_reply_notification foreign key(post_reply_id)
         references tbl_channel_post_reply(id) ON DELETE CASCADE
);

drop table tbl_post_reply_notification;
