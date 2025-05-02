create or replace view view_diary_preview as
select
    d.id,
    d.diary_title,
    d.diary_content,
    d.diary_file_path,
    d.diary_file_name,
    d.updated_date,
    d.diary_read_count as view_count,
    d.diary_open,
    d.diary_name_open,
    c.channel_title,
    c.channel_file_path,
    c.channel_file_name,
    c.channel_url AS channel_url,
    m.member_nickname,
    ifnull(dl.like_count, 0) as like_count,
    dt.tag_list
from tbl_diary d
         join tbl_member m on d.member_id = m.id
         join tbl_channel c on m.id = c.member_id
         left join (
    select diary_id, count(*) as like_count
    from tbl_diary_like
    group by diary_id
) dl on d.id = dl.diary_id
         left join (
    select dt.diary_id, group_concat(t.contents) as tag_list
    from tbl_diary_tag dt
             join tbl_tag t on dt.id = t.id
    where t.tag_status = 'ACTIVE'
    group by dt.diary_id
) dt on d.id = dt.diary_id
where d.diary_status = '정상' and c.channel_status = '정상';


select *
from tbl_diary
where diary_open = '전체 공개';

SELECT * FROM tbl_channel WHERE channel_url = '1234';

SELECT *
FROM view_diary_preview
WHERE channel_url = '1234';

SELECT DISTINCT channel_url FROM view_diary_preview;

SELECT *
FROM tbl_diary
WHERE member_id = (SELECT member_id FROM tbl_channel WHERE channel_url = '1234');


SELECT * FROM view_diary_preview LIMIT 1;





