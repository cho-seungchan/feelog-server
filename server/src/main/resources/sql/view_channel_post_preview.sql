
CREATE OR REPLACE VIEW view_channel_post_preview AS
SELECT
    p.id AS post_id,
    p.post_title,
    p.post_content,
    cp.post_type,
    cp.channel_id,
    cp.post_file_path,
    cp.post_file_name,
    (
        SELECT GROUP_CONCAT(t.contents ORDER BY t.contents SEPARATOR ',')
        FROM tbl_channel_post_tag cpt
                 JOIN tbl_tag t ON cpt.id = t.id
        WHERE cpt.channel_post_id = cp.id
          AND t.tag_status = 'ACTIVE'
    ) AS tag_list,
    cp.post_read_count AS view_count,
    (
        SELECT COUNT(*)
        FROM tbl_channel_post_like cpl
        WHERE cpl.post_id = cp.id
    ) AS like_count,
    m.member_file_path AS member_profile_path,
    m.member_file_name AS member_profile_name,
    m.member_nickname,
    p.updated_date
FROM tbl_post p
         JOIN tbl_channel_post cp ON p.id = cp.id
         JOIN tbl_member m ON cp.member_id = m.id
WHERE cp.channel_post_status = '정상'
  AND p.post_status = '정상';
