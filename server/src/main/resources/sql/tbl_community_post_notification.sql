create table tbl_community_post_notification (
     id bigint primary key,
     community_post_id bigint not null,
     constraint fk_notification_community_post foreign key(id)
         references tbl_notification(id),
     constraint fk_community_post_notification foreign key(community_post_id)
         references tbl_community_post(id)
);


