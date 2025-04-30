-- #게시글 정보 찾기
-- #게시글 리스트 찾기

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
    channel_member_nickname,
    member_file_path,
    member_file_name
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
        where p.post_status = '정상' and cp.channel_post_status = '정상'
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
            m.member_nickname as channel_member_nickname,
            m.member_file_name as member_file_name,
            m.member_file_path as member_file_path
        from tbl_channel c
                 join tbl_member m
                      on c.member_id = m.id
    ) c on p.channel_id = c.channel_id;

-- #태그 찾기
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

-- #좋아요 개수
create or replace view view_find_like_count as
select l.post_id as post_id
from tbl_channel_post p
         join tbl_channel_post_like l
              on p.id = l.post_id;

-- #댓글 개수 찾기
create or replace view view_find_reply_count as
select r.post_id as post_id
from tbl_channel_post p
         join tbl_channel_post_reply r
              on p.id = r.post_id;

-- # 신고
create or replace view view_post_report as
select pr.id as id,
       pr.member_id as report_member_id,
       pr.post_id as post_id,
       r.report_status as report_status,
       r.created_date as created_date,
       r.updated_date as updated_date
from tbl_channel_post_report pr join tbl_report r on r.id = pr.id;


create or replace view view_post_info_detail as
select
    p.id as id,
    p.post_type as post_type,
    p.member_id as member_id,
    p.channel_id as channel_id,
    p.post_read_count as post_read_count,
    p.post_content as post_content,
    p.post_file_path as post_file_path,
    p.post_file_name as post_file_name,
    p.post_status as post_status,
    p.updated_date as updated_date,
    p.created_date as created_date,
    m.member_nickname as member_nickname,
    m.member_file_path as member_file_path,
    m.member_file_name as member_file_name,
    m.channel_title,
    m.channel_url,
    m.channel_file_name,
    m.channel_file_path
from(
        select
            cp.id as id,
            cp.post_type as post_type,
            cp.member_id as member_id,
            cp.channel_id as channel_id,
            cp.post_read_count as post_read_count,
            cp.post_file_path as post_file_path,
            cp.post_file_name as post_file_name,
            p.post_title as post_title,
            p.post_content as post_content,
            p.post_status as post_status,
            p.created_date as created_date,
            p.updated_date as updated_date
        from tbl_channel_post cp join tbl_post p on cp.id = p.id
    ) p join (
    select
        m.id as member_id,
        m.member_file_name,
        m.member_file_path,
        m.member_nickname,
        c.id as channel_id,
        c.channel_title,
        c.channel_url,
        c.channel_file_path,
        c.channel_file_name
    from tbl_member m join tbl_channel c on m.id = c.member_id
) m on p.member_id = m.member_id;


create or replace view view_diary as
select
    d.id as id,
    m.id as member_id,
    m.member_nickname,
    m.member_file_path,
    m.member_file_name,
    m.member_status,
    d.diary_title,
    d.diary_content,
    d.diary_open,
    d.diary_name_open,
    d.diary_file_path,
    d.diary_file_name,
    d.diary_status,
    d.updated_date as updated_date,
    d.diary_read_count,
    c.id as channel_id,
    c.channel_title,
    c.channel_url,
    c.channel_file_path,
    c.channel_file_name,
    c.channel_status
from tbl_member m
    join tbl_diary d
        on m.id = d.member_id
    join tbl_channel c
                on m.id = c.member_id;