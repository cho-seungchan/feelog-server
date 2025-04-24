-- 채널 포스트 전용 태그 (태그 슈퍼키 상속)
create table tbl_channel_post_tag (
    id                bigint primary key,
    channel_post_id   bigint not null,
    constraint fk_channel_post_tag_channel_post foreign key (channel_post_id)
    references tbl_channel_post(id),
    constraint fk_channel_post_tag_tag foreign key (id)
    references tbl_tag(id)
);

select * from view_channel_post_detail ;

select * from tbl_file;
select * from tbl_tag;
CREATE VIEW view_channel_post_detail AS
SELECT
    cp.id                          AS channel_post_id,
    p.post_title                   AS title,
    p.post_content                 AS content,
    p.post_status                  AS post_status,
    p.created_date                 AS post_created_date,
    p.updated_date                 AS post_updated_date,

    cp.post_type                   AS type,
    cp.post_file_path              AS file_path,
    cp.post_file_name              AS file_name,
    cp.post_file_size              AS file_size,

    c.id                           AS channel_id,
    c.channel_title                AS channel_title,
    c.channel_url                  AS channel_url,
    c.channel_status               AS channel_status,

    m.id                           AS member_id,
    m.member_email                 AS member_email,     -- 추정 필드, 실제 있으면 포함
    m.member_nickname              AS member_nickname,  -- 추정 필드

    f.id                           AS attached_file_id,
    f.file_path                    AS attached_file_path,
    f.file_name                    AS attached_file_name,
    f.file_size                    AS attached_file_size,

    t.id                           AS tag_id,
    t.contents                     AS tag_contents

FROM tbl_channel_post cp

-- 슈퍼키 post
         JOIN tbl_post p ON cp.id = p.id

-- 채널
         JOIN tbl_channel c ON cp.channel_id = c.id

-- 멤버
         JOIN tbl_member m ON cp.member_id = m.id

-- 첨부 파일 (0 or 1)
         LEFT JOIN tbl_channel_post_file cpf ON cp.id = cpf.post_id
         LEFT JOIN tbl_file f ON cpf.id = f.id

-- 태그 (0~N)
         LEFT JOIN tbl_channel_post_tag cpt ON cp.id = cpt.channel_post_id
         LEFT JOIN tbl_tag t ON cpt.id = t.id;
