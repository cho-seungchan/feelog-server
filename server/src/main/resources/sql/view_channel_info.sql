create view view_channel_info as
select
    ch.id as channel_id,
    ch.channel_title,
    ch.channel_introduce,
    ch.channel_url,
    ch.channel_file_path,
    ch.channel_file_name,
    ch.member_id,
    m.member_nickname,
    -- 구독자 수 (정상 상태만)
    (select count(*) from tbl_subscribe s
     where s.channel_id = ch.id
       and s.subscribe_status = '정상') as subscriber_count,
    -- 포스트 수 (정상 상태만)
    (select count(*) from tbl_channel_post cp
     where cp.channel_id = ch.id
       and cp.channel_post_status = '정상') as post_count
from tbl_channel ch
         join tbl_member m on ch.member_id = m.id
where ch.channel_status = '정상';