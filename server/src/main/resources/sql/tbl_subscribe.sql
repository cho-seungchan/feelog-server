create table tbl_subscribe(
    id bigint auto_increment primary key ,
    channel_id bigint not null ,
    member_id bigint not null ,
    subscribe_status varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_subscribe_member foreign key (member_id)
                          references tbl_member(id),
    constraint fk_subscribe_channel foreign key (channel_id)
                          references tbl_channel(id)
);



select * from view_subscribe_status;

CREATE OR REPLACE VIEW view_subscribe_status AS
SELECT
    s.id AS subscribe_id,
    s.member_id AS subscriber_id,
    m.member_nickname AS subscriber_nickname,
    c.id AS channel_id,
    c.channel_title,
    c.channel_url,
    s.subscribe_status,
    s.created_date,
    s.updated_date
FROM tbl_subscribe s
         JOIN tbl_member m ON s.member_id = m.id
         JOIN tbl_channel c ON s.channel_id = c.id;
