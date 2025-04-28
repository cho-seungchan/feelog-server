create table tbl_post_like_notification (
        id bigint primary key,
        post_like_id bigint not null,
        constraint fk_notification_post_like foreign key(id)
            references tbl_notification(id) ON DELETE CASCADE,
        constraint fk_post_like_notification foreign key(post_like_id)
            references tbl_channel_post_like(id) ON DELETE CASCADE
);

drop table tbl_post_like_notification;