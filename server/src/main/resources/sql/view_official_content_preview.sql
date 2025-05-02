create or replace view view_official_content_preview as
(
-- 공지사항 영역
select
    n.id,
    n.notice_title as title,
    n.notice_file_path as file_path,
    n.notice_file_name as file_name,
    null as task_url,
    n.notice_content as notice_content,
    n.created_date,
    c.channel_url as channel_url,
    c.channel_file_path,
    c.channel_file_name,
    c.channel_title,
    c.channel_introduce,
    'NOTICE' as content_type,
    m.id as member_id,
    m.member_nickname

from tbl_notice n
         join tbl_member m on n.member_id = m.id
         join tbl_channel c on c.channel_url = 'official'
where n.notice_status = '정상'

union all

-- 챌린지 영역
select
    t.id,
    t.common_task_name as title,
    null as file_path,
    t.common_task_img as file_name,
    t.common_task_url as task_url,
    null as notice_content,
    t.created_date,
    c.channel_url as channel_url,
    c.channel_file_path,
    c.channel_file_name,
    c.channel_title,
    c.channel_introduce,
    'CHALLENGE' as content_type,
    null as member_id,
    null as member_nickname

from tbl_common_task t
         join tbl_channel c on c.channel_url = 'official'
where t.common_task_name is not null
    );




