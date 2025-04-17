create table tbl_post_reply_like_notification (
      id bigint primary key,
      post_reply_like_id bigint not null,
      constraint fk_notification_post_reply_like foreign key(id)
          references tbl_notification(id),
      constraint fk_post_reply_like_notification foreign key(post_reply_like_id)
          references tbl_channel_post_reply_like(id)
);

