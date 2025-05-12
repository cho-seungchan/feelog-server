CREATE OR REPLACE VIEW view_diary_preview AS
SELECT
    d.id,
    d.diary_title,
    d.diary_content,
    d.diary_file_path,
    d.diary_file_name,
    d.updated_date,
    d.diary_read_count AS view_count,
    d.diary_open,
    d.diary_name_open,
    c.channel_title,
    c.channel_file_path,
    c.channel_file_name,
    c.channel_url AS channel_url,

    -- 멤버 정보 추가
    m.member_nickname,
    m.member_file_path as member_profile_path,
    m.member_file_name as member_profile_name,
    m.member_email,
    m.member_status,
    m.created_date AS member_created_date,
    m.updated_date AS member_updated_date,

    -- 좋아요 수
    IFNULL(dl.like_count, 0) AS like_count,

    -- 태그 리스트
    dt.tag_list

FROM tbl_diary d
         JOIN tbl_member m ON d.member_id = m.id
         JOIN tbl_channel c ON m.id = c.member_id

-- 좋아요 수 조인
         LEFT JOIN (
    SELECT
        diary_id,
        COUNT(*) AS like_count
    FROM tbl_diary_like
    GROUP BY diary_id
) dl ON d.id = dl.diary_id

-- 태그 리스트 조인
         LEFT JOIN (
    SELECT
        dt.diary_id,
        GROUP_CONCAT(t.contents SEPARATOR ', ') AS tag_list
    FROM tbl_diary_tag dt
             JOIN tbl_tag t ON dt.id = t.id
    WHERE t.tag_status = 'ACTIVE'
    GROUP BY dt.diary_id
) dt ON d.id = dt.diary_id

WHERE
    d.diary_status = '정상'
  AND c.channel_status = '정상';



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





