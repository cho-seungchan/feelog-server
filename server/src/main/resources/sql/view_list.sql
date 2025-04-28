-- #게시글 정보 찾기

create or replace view view_post_info as
select
    p.post_id as id,
    p.post_type as post_type,
    p.post_main_file_name as post_main_file_name,
    p.post_main_file_path as post_main_file_path,
    p.post_read_count as post_read_count,
    p.channel_id as channel_id,
    p.post_title as post_title,
    p.post_content as post_content,
    p.post_status as post_status,
    p.updated_date as updated_date,
    channel_member_id,
    channel_title,
    channel_file_path,
    channel_file_name,
    channel_url,
    channel_member_nickname
from
    (
        select
            cp.id as post_id,
            cp.post_type as post_type,
            cp.post_file_name as post_main_file_name,
            cp.post_file_path as post_main_file_path,
            cp.post_read_count as post_read_count,
            cp.channel_id as channel_id,
            p.post_title as post_title,
            p.post_content as post_content,
            p.post_status as post_status,
            p.updated_date as updated_date
        from tbl_channel_post cp
                 join tbl_post p
                      on cp.id = p.id
        where p.post_status = '정상' and cp.channel_post_status = '정상' and cp.post_type = '포스트'
    ) p
        join
    (
        select
            c.id as channel_id,
            m.id as channel_member_id,
            c.channel_title as channel_title,
            c.channel_file_path as channel_file_path,
            c.channel_file_name as channel_file_name,
            c.channel_url as channel_url,
            m.member_nickname as channel_member_nickname
        from tbl_channel c
                 join tbl_member m
                      on c.member_id = m.id
    ) c on p.channel_id = c.channel_id;

#태그 찾기
create or replace view view_post_tag as
select
    t.id as id,
    t.contents as tag_content,
    t.tag_status as tag_status,
    pt.channel_post_id as post_id
from tbl_tag t
         join tbl_channel_post_tag pt
              on t.id = pt.id
where tag_status = 'ACTIVE';

#좋아요 개수
create or replace view view_find_like_count as
select l.post_id as post_id
from tbl_channel_post p
    join tbl_channel_post_like l
        on p.id = l.post_id;

#댓글 개수 찾기
create or replace view view_find_reply_count as
select r.post_id as post_id
from tbl_channel_post p
         join tbl_channel_post_reply r
              on p.id = r.post_id;


