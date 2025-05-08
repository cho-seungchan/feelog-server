create table tbl_challenge
(
    id                 bigint auto_increment
        primary key,
    challenge_complete varchar(50) default ''                null,
    challenge_status   varchar(50) default '정상'              null,
    created_date       datetime    default CURRENT_TIMESTAMP null,
    updated_date       datetime    default CURRENT_TIMESTAMP null
);

create table tbl_common_task
(
    id                       bigint auto_increment
        primary key,
    common_task_name         varchar(1000)                      not null,
    common_task_img          varchar(1000)                      null,
    common_task_tell         varchar(50)                        null,
    common_task_url          varchar(1000)                      null,
    common_task_addr         varchar(1000)                      null,
    common_task_lot          decimal(10, 6)                     null,
    common_task_lat          decimal(10, 6)                     null,
    common_task_service_name varchar(100)                       not null,
    common_task_req_page     varchar(20)                        not null,
    created_date             datetime default CURRENT_TIMESTAMP null,
    updated_date             datetime default CURRENT_TIMESTAMP null
);

create table tbl_diary_score
(
    id               bigint auto_increment
        primary key,
    score_level_name varchar(100)                       not null,
    score_message    varchar(2000)                      not null,
    score_file_path  varchar(500)                       not null,
    score_file_name  varchar(500)                       not null,
    created_date     datetime default CURRENT_TIMESTAMP null,
    updated_date     datetime default CURRENT_TIMESTAMP null
);

create table tbl_file
(
    id           bigint auto_increment
        primary key,
    file_path    varchar(500) default ''                null,
    file_name    varchar(500) default ''                null,
    file_size    varchar(500) default ''                null,
    file_status  varchar(50)  default '정상'              null,
    created_date datetime     default CURRENT_TIMESTAMP null,
    updated_date datetime     default CURRENT_TIMESTAMP null
);

create table tbl_like
(
    id           bigint auto_increment
        primary key,
    like_status  varchar(50) default '정상'              null,
    created_date datetime    default CURRENT_TIMESTAMP null,
    updated_date datetime    default CURRENT_TIMESTAMP null
);

create table tbl_member
(
    id                                  bigint auto_increment
        primary key,
    member_email                        varchar(200)                           not null,
    member_password                     varchar(200)                           null,
    member_nickname                     varchar(50)                            null,
    member_introduce                    varchar(300) default ''                null,
    member_file_path                    varchar(500)                           null,
    member_file_name                    varchar(500)                           null,
    member_type                         varchar(50)  default '일반 회원'           null,
    member_suspend                      varchar(50)  default ''                null,
    member_notification_post_reply      varchar(50)                            null,
    member_notification_post_reply_like varchar(50)                            null,
    member_notification_post_like       varchar(50)                            null,
    member_notification_subscribe       varchar(50)                            null,
    member_notification_community_post  varchar(50)                            null,
    member_notification_message         varchar(50)                            null,
    member_status                       varchar(50)  default '정상'              null,
    created_date                        datetime     default CURRENT_TIMESTAMP null,
    updated_date                        datetime     default CURRENT_TIMESTAMP null
);

create table tbl_channel
(
    id                bigint auto_increment
        primary key,
    channel_title     varchar(50)                            not null,
    channel_introduce varchar(300) default ''                null,
    channel_url       varchar(50)                            not null,
    channel_file_path varchar(500)                           null,
    channel_file_name varchar(500)                           null,
    channel_file_size varchar(100) default ''                null,
    channel_status    varchar(50)  default '정상'              null,
    member_id         bigint                                 not null,
    created_date      datetime     default CURRENT_TIMESTAMP null,
    updated_date      datetime     default CURRENT_TIMESTAMP null,
    constraint fk_channel_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_common_challenge
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    task_id   bigint not null,
    constraint fk_common_challenge_challenge
        foreign key (id) references tbl_challenge (id),
    constraint fk_common_challenge_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_common_challenge_task
        foreign key (task_id) references tbl_common_task (id)
);

create table tbl_diary
(
    id               bigint auto_increment
        primary key,
    diary_title      varchar(2000)                          not null,
    diary_content    varchar(4000)                          not null,
    diary_open       varchar(50)  default '비공개'             null,
    diary_name_open  varchar(50)  default '비공개(익명)'         null,
    diary_file_path  varchar(500)                           null,
    diary_file_name  varchar(500)                           null,
    diary_file_size  varchar(500) default ''                null,
    diary_score      bigint                                 null,
    member_id        bigint                                 not null,
    diary_status     varchar(50)  default '정상'              null,
    created_date     datetime     default CURRENT_TIMESTAMP null,
    updated_date     datetime     default CURRENT_TIMESTAMP null,
    diary_read_count int          default 0                 null,
    constraint fk_diary_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_score_diary_score
        foreign key (diary_score) references tbl_diary_score (id)
);

create table tbl_challenge_diary
(
    id           bigint not null
        primary key,
    challenge_id bigint not null,
    constraint fk_challenge_diary_challenge
        foreign key (challenge_id) references tbl_challenge (id),
    constraint fk_challenge_diary_diary
        foreign key (id) references tbl_diary (id)
);

create table tbl_diary_file
(
    id       bigint not null
        primary key,
    diary_id bigint not null,
    constraint fk_diary_file_diary
        foreign key (diary_id) references tbl_diary (id),
    constraint fk_diary_file_file
        foreign key (id) references tbl_file (id)
);

create table tbl_diary_like
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    diary_id  bigint not null,
    constraint fk_diary_like_diary
        foreign key (diary_id) references tbl_diary (id),
    constraint fk_diary_like_like
        foreign key (id) references tbl_like (id),
    constraint fk_diary_like_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_faq
(
    id           bigint auto_increment
        primary key,
    faq_title    varchar(4000)                         not null,
    faq_content  varchar(4000)                         not null,
    member_id    bigint                                not null,
    faq_status   varchar(50) default '정상'              null,
    created_date datetime    default CURRENT_TIMESTAMP null,
    updated_date datetime    default CURRENT_TIMESTAMP null,
    constraint fk_faq_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_member_task_pool
(
    id                         bigint auto_increment
        primary key,
    mamber_task_pool_content   varchar(1000)                           not null,
    mamber_task_pool_file_path varchar(1000) default ''                null,
    mamber_task_pool_file_name varchar(1000) default ''                null,
    member_task_pool_status    varchar(50)   default '정상'              null,
    created_date               datetime      default CURRENT_TIMESTAMP null,
    updated_date               datetime      default CURRENT_TIMESTAMP null,
    member_task_pool_content   varchar(1000)                           null,
    member_task_pool_file_path varchar(1000)                           null,
    member_task_pool_file_name varchar(1000)                           null
);

create table tbl_member_task
(
    id                 bigint auto_increment
        primary key,
    task_pool_id       bigint                                null,
    member_task_status varchar(50) default '정상'              null,
    created_date       datetime    default CURRENT_TIMESTAMP null,
    updated_date       datetime    default CURRENT_TIMESTAMP null,
    member_id          bigint                                not null,
    constraint fk_member_challenge_task
        foreign key (task_pool_id) references tbl_member_task_pool (id),
    constraint fk_member_task_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_member_challenge
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    task_id   bigint not null,
    constraint fk_member_challenge_challenge
        foreign key (id) references tbl_challenge (id),
    constraint fk_member_challenge_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_member_challenge_member_task
        foreign key (task_id) references tbl_member_task (id)
);

create table tbl_message
(
    id              bigint auto_increment
        primary key,
    message_content varchar(1000)                      null,
    created_date    datetime default CURRENT_TIMESTAMP null,
    updated_date    datetime default CURRENT_TIMESTAMP null
);

create table tbl_notice
(
    id               bigint auto_increment
        primary key,
    notice_title     varchar(4000)                         not null,
    notice_content   varchar(4000)                         not null,
    member_id        bigint                                not null,
    notice_status    varchar(50) default '정상'              null,
    created_date     datetime    default CURRENT_TIMESTAMP null,
    updated_date     datetime    default CURRENT_TIMESTAMP null,
    notice_file_name varchar(4000)                         null,
    notice_file_path varchar(4000)                         null,
    read_count       int         default 0                 null,
    constraint fk_notice_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_notification
(
    id           bigint auto_increment
        primary key,
    sender_id    bigint                                not null,
    receiver_id  bigint                                not null,
    checked      varchar(50) default '안읽음'             null,
    created_date datetime    default CURRENT_TIMESTAMP null,
    updated_date datetime    default CURRENT_TIMESTAMP null,
    constraint fk_notification_receiver
        foreign key (receiver_id) references tbl_member (id),
    constraint fk_notification_sender
        foreign key (sender_id) references tbl_member (id)
);

create table tbl_post
(
    id           bigint auto_increment
        primary key,
    post_title   varchar(2000)                         not null,
    post_content varchar(4000)                         not null,
    post_status  varchar(50) default '정상'              null,
    created_date datetime    default CURRENT_TIMESTAMP null,
    updated_date datetime    default CURRENT_TIMESTAMP null
);

create table tbl_channel_post
(
    id                  bigint                     not null
        primary key,
    post_type           varchar(50)  default '포스트' null,
    post_file_path      varchar(500) default ''    null,
    post_file_name      varchar(500) default ''    null,
    post_file_size      varchar(500) default ''    null,
    member_id           bigint                     not null,
    channel_id          bigint                     not null,
    post_read_count     int          default 0     null,
    channel_post_status varchar(50)  default '정상'  null,
    constraint fk_channel_post_channel
        foreign key (channel_id) references tbl_channel (id),
    constraint fk_channel_post_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_channel_post_post
        foreign key (id) references tbl_post (id)
);

create table tbl_channel_post_file
(
    id      bigint not null
        primary key,
    post_id bigint not null,
    constraint fk_channel_post_file_channel_post
        foreign key (id) references tbl_file (id),
    constraint fk_channel_post_file_post
        foreign key (post_id) references tbl_channel_post (id)
);

create table tbl_channel_post_like
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    post_id   bigint not null,
    constraint fk_channel_post_like_channel_post
        foreign key (post_id) references tbl_channel_post (id),
    constraint fk_channel_post_like_like
        foreign key (id) references tbl_like (id),
    constraint fk_channel_post_like_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_channel_post_scrap
(
    id                        bigint auto_increment
        primary key,
    member_id                 bigint                                not null,
    post_id                   bigint                                not null,
    channel_post_scrap_status varchar(50) default '정상'              null,
    created_date              datetime    default CURRENT_TIMESTAMP null,
    updated_date              datetime    default CURRENT_TIMESTAMP null,
    constraint fk_channel_post_scrap_channel_post
        foreign key (post_id) references tbl_post (id),
    constraint fk_channel_post_scrap_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_community_post
(
    id         bigint not null
        primary key,
    member_id  bigint not null,
    channel_id bigint not null,
    constraint fk_community_post_channel
        foreign key (channel_id) references tbl_channel (id),
    constraint fk_community_post_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_community_post_post
        foreign key (id) references tbl_post (id)
);

create table tbl_community_post_file
(
    id      bigint not null
        primary key,
    post_id bigint not null,
    constraint fk_community_post_file_community_post
        foreign key (post_id) references tbl_community_post (id),
    constraint fk_community_post_file_file
        foreign key (id) references tbl_file (id)
);

create table tbl_community_post_like
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    post_id   bigint not null,
    constraint fk_community_post_like_community_post
        foreign key (post_id) references tbl_community_post (id),
    constraint fk_community_post_like_like
        foreign key (id) references tbl_like (id),
    constraint fk_community_post_like_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_community_post_notification
(
    id                bigint not null
        primary key,
    community_post_id bigint not null,
    constraint fk_community_post_notification
        foreign key (community_post_id) references tbl_community_post (id)
            on delete cascade,
    constraint fk_notification_community_post
        foreign key (id) references tbl_notification (id)
            on delete cascade
);

create table tbl_post_like_notification
(
    id           bigint not null
        primary key,
    post_like_id bigint not null,
    constraint fk_notification_post_like
        foreign key (id) references tbl_notification (id)
            on delete cascade,
    constraint fk_post_like_notification
        foreign key (post_like_id) references tbl_channel_post_like (id)
            on delete cascade
);

create table tbl_receive_message
(
    id                     bigint                   not null,
    member_id              bigint                   not null,
    sender_id              bigint                   not null,
    receive_message_status varchar(50) default '정상' null,
    constraint fk_receive_message_message
        foreign key (id) references tbl_message (id),
    constraint fk_receive_message_receiver
        foreign key (member_id) references tbl_member (id),
    constraint fk_receive_message_sender
        foreign key (sender_id) references tbl_member (id)
);

create table tbl_receive_message_notification
(
    id                 bigint not null
        primary key,
    receive_message_id bigint not null,
    constraint fk_notification_receive_message
        foreign key (id) references tbl_notification (id)
            on delete cascade,
    constraint fk_receive_message_notification
        foreign key (receive_message_id) references tbl_receive_message (id)
            on delete cascade
);

create table tbl_reply
(
    id              bigint auto_increment
        primary key,
    reply_content   varchar(2000)                          null,
    reply_file_path varchar(500) default ''                null,
    reply_file_name varchar(500) default ''                null,
    reply_file_size varchar(100) default ''                null,
    reply_status    varchar(50)  default '정상'              null,
    created_date    datetime     default CURRENT_TIMESTAMP null,
    updated_date    datetime     default CURRENT_TIMESTAMP null
);

create table tbl_channel_post_reply
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    post_id   bigint not null,
    constraint fk_channel_post_reply_channel_post
        foreign key (post_id) references tbl_channel_post (id),
    constraint fk_channel_post_reply_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_channel_post_reply_reply
        foreign key (id) references tbl_reply (id)
);

create table tbl_channel_post_reply_like
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    reply_id  bigint not null,
    constraint fk_channel_post_reply_like
        foreign key (id) references tbl_like (id),
    constraint fk_channel_post_reply_like_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_channel_post_reply_like_reply
        foreign key (reply_id) references tbl_channel_post_reply (id)
);

create table tbl_community_post_reply
(
    id                             bigint                  not null
        primary key,
    community_post_reply_file_path varchar(500) default '' null,
    community_post_reply_file_name varchar(500) default '' null,
    member_id                      bigint                  not null,
    post_id                        bigint                  not null,
    constraint fk_community_post_reply_community_post
        foreign key (post_id) references tbl_community_post (id),
    constraint fk_community_post_reply_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_community_post_reply_reply
        foreign key (id) references tbl_reply (id)
);

create table tbl_community_post_reply_like
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    reply_id  bigint not null,
    constraint fk_community_post_reply_like_like
        foreign key (id) references tbl_like (id),
    constraint fk_community_post_reply_like_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_community_post_reply_like_reply
        foreign key (reply_id) references tbl_community_post_reply (id)
);

create table tbl_diary_reply
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    diary_id  bigint not null,
    constraint fk_diary_reply_diary
        foreign key (diary_id) references tbl_diary (id),
    constraint fk_diary_reply_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_diary_reply_reply
        foreign key (id) references tbl_reply (id)
);

create table tbl_diary_reply_like
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    reply_id  bigint not null,
    constraint fk_diary_reply_like_like
        foreign key (id) references tbl_like (id),
    constraint fk_diary_reply_like_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_diary_reply_like_reply
        foreign key (reply_id) references tbl_diary_reply (id)
);

create table tbl_post_reply_like_notification
(
    id                 bigint not null
        primary key,
    post_reply_like_id bigint not null,
    constraint fk_notification_post_reply_like
        foreign key (id) references tbl_notification (id)
            on delete cascade,
    constraint fk_post_reply_like_notification
        foreign key (post_reply_like_id) references tbl_channel_post_reply_like (id)
            on delete cascade
);

create table tbl_post_reply_notification
(
    id            bigint not null
        primary key,
    post_reply_id bigint not null,
    constraint fk_notification_post_reply
        foreign key (id) references tbl_notification (id)
            on delete cascade,
    constraint fk_post_reply_notification
        foreign key (post_reply_id) references tbl_channel_post_reply (id)
            on delete cascade
);

create table tbl_report
(
    id            bigint auto_increment
        primary key,
    report_status varchar(50) default '정상'              null,
    created_date  datetime    default CURRENT_TIMESTAMP null,
    updated_date  datetime    default CURRENT_TIMESTAMP null
);

create table tbl_channel_post_reply_report
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    reply_id  bigint not null,
    constraint fk_channel_post_reply_report_channel_post_reply
        foreign key (reply_id) references tbl_channel_post_reply (id),
    constraint fk_channel_post_reply_report_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_channel_post_reply_report_report
        foreign key (id) references tbl_report (id)
);

create table tbl_channel_post_report
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    post_id   bigint not null,
    constraint fk_channel_post_report_channel_post
        foreign key (post_id) references tbl_channel_post (id),
    constraint fk_channel_post_report_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_channel_post_report_report
        foreign key (id) references tbl_report (id)
);

create table tbl_community_post_reply_report
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    reply_id  bigint not null,
    constraint fk_comunity_post_reply_report_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_comunity_post_reply_report_reply
        foreign key (reply_id) references tbl_reply (id),
    constraint fk_comunity_post_reply_report_report
        foreign key (id) references tbl_report (id)
);

create table tbl_community_post_report
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    post_id   bigint not null,
    constraint fk_community_post_report_community_post
        foreign key (post_id) references tbl_community_post (id),
    constraint fk_community_post_report_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_community_post_report_report
        foreign key (id) references tbl_report (id)
);

create table tbl_diary_reply_report
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    reply_id  bigint not null,
    constraint fk_diary_reply_report_diary_reply
        foreign key (reply_id) references tbl_diary_reply (id),
    constraint fk_diary_reply_report_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_diary_reply_report_report
        foreign key (id) references tbl_report (id)
);

create table tbl_diary_report
(
    id        bigint not null
        primary key,
    member_id bigint not null,
    diary_id  bigint not null,
    constraint fk_diary_report_diary
        foreign key (diary_id) references tbl_diary (id),
    constraint fk_diary_report_member
        foreign key (member_id) references tbl_member (id),
    constraint fk_diary_report_report
        foreign key (id) references tbl_report (id)
);

create table tbl_send_message
(
    id                     bigint                   not null,
    member_id              bigint                   not null,
    receiver_id            bigint                   not null,
    receive_message_status varchar(50) default '정상' null,
    constraint fk_send_message_message
        foreign key (id) references tbl_message (id),
    constraint fk_send_message_receiver
        foreign key (member_id) references tbl_member (id),
    constraint fk_send_message_sender
        foreign key (receiver_id) references tbl_member (id)
);

create table tbl_subscribe
(
    id               bigint auto_increment
        primary key,
    channel_id       bigint                                not null,
    member_id        bigint                                not null,
    subscribe_status varchar(50) default '정상'              null,
    created_date     datetime    default CURRENT_TIMESTAMP null,
    updated_date     datetime    default CURRENT_TIMESTAMP null,
    constraint fk_subscribe_channel
        foreign key (channel_id) references tbl_channel (id),
    constraint fk_subscribe_member
        foreign key (member_id) references tbl_member (id)
);

create table tbl_subscribe_notification
(
    id           bigint not null
        primary key,
    subscribe_id bigint not null,
    constraint fk_notification_subscribe
        foreign key (id) references tbl_notification (id)
            on delete cascade,
    constraint fk_subscribe_notification
        foreign key (subscribe_id) references tbl_subscribe (id)
            on delete cascade
);

create table tbl_tag
(
    id           bigint auto_increment
        primary key,
    contents     varchar(50)                           null,
    tag_status   varchar(50) default 'ACTIVE'          null,
    created_date datetime    default CURRENT_TIMESTAMP null,
    updated_date datetime    default CURRENT_TIMESTAMP null
);

create table tbl_channel_post_tag
(
    id              bigint not null
        primary key,
    channel_post_id bigint not null,
    constraint fk_channel_post_tag_channel_post
        foreign key (channel_post_id) references tbl_channel_post (id),
    constraint fk_channel_post_tag_tag
        foreign key (id) references tbl_tag (id)
);

create table tbl_diary_tag
(
    id       bigint not null
        primary key,
    diary_id bigint not null,
    constraint fk_diary_tag_diary
        foreign key (diary_id) references tbl_diary (id),
    constraint fk_diary_tag_tag
        foreign key (id) references tbl_tag (id)
);

create table tbl_related_search
(
    id           bigint auto_increment
        primary key,
    keyword      varchar(100)                       not null,
    tag_id       bigint                             null,
    member_id    bigint                             null,
    created_date datetime default CURRENT_TIMESTAMP null,
    updated_date datetime default CURRENT_TIMESTAMP null,
    constraint fk_related_search_member
        foreign key (member_id) references tbl_member (id)
            on delete set null,
    constraint fk_related_search_tag
        foreign key (tag_id) references tbl_tag (id)
            on delete set null
);

