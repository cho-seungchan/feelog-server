-- 회원 정보
create table tbl_member
(
    id                                  bigint auto_increment primary key,
    member_email                        varchar(200) not null,
    member_password                     varchar(200),
    member_nickname                     varchar(50),
    member_introduce                    varchar(300) default '',
    member_file_path                    varchar(500) DEFAULT NULL,
    member_file_name                    varchar(500) DEFAULT NULL,
    member_type                         varchar(50)  default '일반 회원',
    member_notification_post_reply      varchar(50)  default '설정',
    member_notification_post_reply_like varchar(50)  default '설정',
    member_notification_post_like       varchar(50)  default '설정',
    member_notification_subscribe       varchar(50)  default '설정',
    member_notification_community_post  varchar(50)  default '설정',
    member_notification_message         varchar(50)  default '설정',
    member_status                       varchar(50)  default '정상',
    created_date                        datetime     default current_timestamp,
    updated_date                        datetime     default current_timestamp
);

ALTER TABLE tbl_member
    MODIFY COLUMN member_notification_message VARCHAR(50) DEFAULT '설정';

ALTER TABLE tbl_member
    MODIFY member_file_path VARCHAR(500) NULL;

