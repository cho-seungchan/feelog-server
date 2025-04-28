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

CREATE OR REPLACE VIEW view_notification AS
SELECT
    n.id AS notification_id,
    n.sender_id,
    sender.member_nickname AS sender_nickname,
    sender.member_file_path AS sender_file_path,
    sender.member_file_name AS sender_file_name,
    n.receiver_id,
    CASE
        WHEN sn.id IS NOT NULL THEN 'SUBSCRIBE'
        WHEN cpn.id IS NOT NULL THEN 'COMMUNITY_POST'
        WHEN prn.id IS NOT NULL THEN 'POST_REPLY'
        WHEN prln.id IS NOT NULL THEN 'POST_REPLY_LIKE'
        WHEN pln.id IS NOT NULL THEN 'POST_LIKE'
        WHEN rmn.id IS NOT NULL THEN 'RECEIVE_MESSAGE'
        ELSE 'UNKNOWN'
        END AS notification_type,
    n.checked,
    n.created_date,

    -- 썸네일 경로 (채널 대표 or 포스트 대표)
    CASE
        WHEN sn.id IS NOT NULL THEN c.channel_file_path
        WHEN cpn.id IS NOT NULL THEN c.channel_file_path
        WHEN prn.id IS NOT NULL THEN cp.post_file_path
        WHEN prln.id IS NOT NULL THEN cp.post_file_path
        WHEN pln.id IS NOT NULL THEN cp.post_file_path
        ELSE NULL
        END AS thumbnail_path,

    CASE
        WHEN sn.id IS NOT NULL THEN c.channel_file_name
        WHEN cpn.id IS NOT NULL THEN c.channel_file_name
        WHEN prn.id IS NOT NULL THEN cp.post_file_name
        WHEN prln.id IS NOT NULL THEN cp.post_file_name
        WHEN pln.id IS NOT NULL THEN cp.post_file_name
        ELSE NULL
        END AS thumbnail_name

FROM tbl_notification n
         JOIN tbl_member sender ON n.sender_id = sender.id
         LEFT JOIN tbl_subscribe_notification sn ON n.id = sn.id
         LEFT JOIN tbl_community_post_notification cpn ON n.id = cpn.id
         LEFT JOIN tbl_post_reply_notification prn ON n.id = prn.id
         LEFT JOIN tbl_post_reply_like_notification prln ON n.id = prln.id
         LEFT JOIN tbl_post_like_notification pln ON n.id = pln.id
         LEFT JOIN tbl_receive_message_notification rmn ON n.id = rmn.id

    -- 채널 테이블
         LEFT JOIN tbl_channel c ON (
    sn.subscribe_id = c.id OR
    cpn.community_post_id = c.id
    )

    -- 포스트 테이블
         LEFT JOIN tbl_channel_post cp ON (
    prn.post_reply_id = cp.id OR
    prln.post_reply_like_id = cp.id OR
    pln.post_like_id = cp.id
    );

select * from view_notification;
SELECT * FROM view_notification WHERE receiver_id =11;

select * from tbl_notification order by id desc ;

select * from tbl_subscribe_notification order by id desc;

select * from tbl_community_post_notification;

select * from tbl_post_reply_notification;

select * from tbl_post_reply_like_notification;

select * from tbl_post_like_notification;

select * from tbl_receive_message_notification;


select * from tbl_like;

select * from tbl_channel_post_reply_like;

INSERT INTO tbl_channel_post_reply_like (id, member_id, reply_id)
VALUES (1, 3, 1);

select * from tbl_channel_post_reply;

INSERT INTO tbl_like ()
VALUES ();

select * from tbl_channel_post join tbl_post;

INSERT INTO tbl_channel_post_like (id, member_id, post_id)
VALUES (2, 1, 49);