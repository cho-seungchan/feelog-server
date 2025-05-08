create view view_channel_info as
select `ch`.`id`                                                                         AS `channel_id`,
    `ch`.`channel_title`                                                              AS `channel_title`,
    `ch`.`channel_introduce`                                                          AS `channel_introduce`,
    `ch`.`channel_url`                                                                AS `channel_url`,
    `ch`.`channel_file_path`                                                          AS `channel_file_path`,
    `ch`.`channel_file_name`                                                          AS `channel_file_name`,
    `ch`.`member_id`                                                                  AS `member_id`,
    `m`.`member_nickname`                                                             AS `member_nickname`,
        (select count(0)
         from `feelog`.`tbl_subscribe` `s`
         where ((`s`.`channel_id` = `ch`.`id`) and (`s`.`subscribe_status` = '정상')))      AS `subscriber_count`,
        (select count(0)
         from `feelog`.`tbl_channel_post` `cp`
         where ((`cp`.`channel_id` = `ch`.`id`) and (`cp`.`channel_post_status` = '정상'))) AS `post_count`
from (`feelog`.`tbl_channel` `ch` join `feelog`.`tbl_member` `m` on ((`ch`.`member_id` = `m`.`id`)))
where (`ch`.`channel_status` = '정상');

create view view_channel_post_detail as
select `cp`.`id`             AS `channel_post_id`,
    `p`.`post_title`      AS `title`,
    `p`.`post_content`    AS `content`,
    `p`.`post_status`     AS `post_status`,
    `p`.`created_date`    AS `post_created_date`,
    `p`.`updated_date`    AS `post_updated_date`,
    `cp`.`post_type`      AS `type`,
    `cp`.`post_file_path` AS `file_path`,
    `cp`.`post_file_name` AS `file_name`,
    `cp`.`post_file_size` AS `file_size`,
    `c`.`id`              AS `channel_id`,
    `c`.`channel_title`   AS `channel_title`,
    `c`.`channel_url`     AS `channel_url`,
    `c`.`channel_status`  AS `channel_status`,
    `m`.`id`              AS `member_id`,
    `m`.`member_email`    AS `member_email`,
    `m`.`member_nickname` AS `member_nickname`,
    `f`.`id`              AS `attached_file_id`,
    `f`.`file_path`       AS `attached_file_path`,
    `f`.`file_name`       AS `attached_file_name`,
    `f`.`file_size`       AS `attached_file_size`,
    `t`.`id`              AS `tag_id`,
    `t`.`contents`        AS `tag_contents`
from (((((((`feelog`.`tbl_channel_post` `cp` join `feelog`.`tbl_post` `p`
             on ((`cp`.`id` = `p`.`id`))) join `feelog`.`tbl_channel` `c`
           on ((`cp`.`channel_id` = `c`.`id`))) join `feelog`.`tbl_member` `m`
          on ((`cp`.`member_id` = `m`.`id`))) left join `feelog`.`tbl_channel_post_file` `cpf`
         on ((`cp`.`id` = `cpf`.`post_id`))) left join `feelog`.`tbl_file` `f`
        on ((`cpf`.`id` = `f`.`id`))) left join `feelog`.`tbl_channel_post_tag` `cpt`
       on ((`cp`.`id` = `cpt`.`channel_post_id`))) left join `feelog`.`tbl_tag` `t` on ((`cpt`.`id` = `t`.`id`)));

create view view_channel_post_preview as
select `p`.`id`                                                                                          AS `post_id`,
    `p`.`post_title`                                                                                  AS `post_title`,
    `p`.`post_content`                                                                                AS `post_content`,
    `cp`.`post_type`                                                                                  AS `post_type`,
    `cp`.`channel_id`                                                                                 AS `channel_id`,
    `cp`.`post_file_path`                                                                             AS `post_file_path`,
    `cp`.`post_file_name`                                                                             AS `post_file_name`,
        (select group_concat(`t`.`contents` order by `t`.`contents` ASC separator ',')
         from (`feelog`.`tbl_channel_post_tag` `cpt` join `feelog`.`tbl_tag` `t` on ((`cpt`.`id` = `t`.`id`)))
         where ((`cpt`.`channel_post_id` = `cp`.`id`) and (`t`.`tag_status` = 'ACTIVE')))                 AS `tag_list`,
    `cp`.`post_read_count`                                                                            AS `view_count`,
        (select count(0)
         from `feelog`.`tbl_channel_post_like` `cpl`
         where (`cpl`.`post_id` = `cp`.`id`))                                                             AS `like_count`,
    `m`.`member_file_path`                                                                            AS `member_profile_path`,
    `m`.`member_file_name`                                                                            AS `member_profile_name`,
    `m`.`member_nickname`                                                                             AS `member_nickname`,
    `p`.`updated_date`                                                                                AS `updated_date`
from ((`feelog`.`tbl_post` `p` join `feelog`.`tbl_channel_post` `cp`
        on ((`p`.`id` = `cp`.`id`))) join `feelog`.`tbl_member` `m` on ((`cp`.`member_id` = `m`.`id`)))
where ((`cp`.`channel_post_status` = '정상') and (`p`.`post_status` = '정상'));

create view view_diary as
select `d`.`id`                AS `id`,
    `m`.`id`                AS `member_id`,
    `m`.`member_nickname`   AS `member_nickname`,
    `m`.`member_file_path`  AS `member_file_path`,
    `m`.`member_file_name`  AS `member_file_name`,
    `m`.`member_status`     AS `member_status`,
    `d`.`diary_title`       AS `diary_title`,
    `d`.`diary_content`     AS `diary_content`,
    `d`.`diary_open`        AS `diary_open`,
    `d`.`diary_name_open`   AS `diary_name_open`,
    `d`.`diary_file_path`   AS `diary_file_path`,
    `d`.`diary_file_name`   AS `diary_file_name`,
    `d`.`diary_status`      AS `diary_status`,
    `d`.`updated_date`      AS `updated_date`,
    `d`.`diary_read_count`  AS `diary_read_count`,
    `c`.`id`                AS `channel_id`,
    `c`.`channel_title`     AS `channel_title`,
    `c`.`channel_url`       AS `channel_url`,
    `c`.`channel_file_path` AS `channel_file_path`,
    `c`.`channel_file_name` AS `channel_file_name`,
    `c`.`channel_status`    AS `channel_status`
from ((`feelog`.`tbl_member` `m` join `feelog`.`tbl_diary` `d`
        on ((`m`.`id` = `d`.`member_id`))) join `feelog`.`tbl_channel` `c` on ((`m`.`id` = `c`.`member_id`)));

create view view_diary_detail as
select `d`.`id`              AS `diary_id`,
    `d`.`diary_title`     AS `diary_title`,
    `d`.`diary_content`   AS `diary_content`,
    `d`.`diary_open`      AS `diary_open`,
    `d`.`diary_name_open` AS `diary_name_open`,
    `d`.`diary_file_path` AS `rep_file_path`,
    `d`.`diary_file_name` AS `rep_file_name`,
    `d`.`diary_file_size` AS `rep_file_size`,
    `d`.`member_id`       AS `member_id`,
    `d`.`diary_status`    AS `diary_status`,
    `d`.`created_date`    AS `diary_created_date`,
    `d`.`updated_date`    AS `diary_updated_date`,
    `f`.`id`              AS `attach_file_id`,
    `f`.`file_path`       AS `attach_file_path`,
    `f`.`file_name`       AS `attach_file_name`,
    `f`.`file_size`       AS `attach_file_size`,
    `f`.`file_status`     AS `file_status`,
    `f`.`created_date`    AS `file_created_date`,
    `t`.`id`              AS `tag_id`,
    `t`.`contents`        AS `tag_contents`,
    `t`.`tag_status`      AS `tag_status`,
    `t`.`created_date`    AS `tag_created_date`
from ((((`feelog`.`tbl_diary` `d` left join `feelog`.`tbl_diary_file` `df`
          on ((`d`.`id` = `df`.`diary_id`))) left join `feelog`.`tbl_file` `f`
        on ((`df`.`id` = `f`.`id`))) left join `feelog`.`tbl_diary_tag` `dt`
       on ((`d`.`id` = `dt`.`diary_id`))) left join `feelog`.`tbl_tag` `t` on ((`dt`.`id` = `t`.`id`)));

create view view_diary_preview as
select `d`.`id`                     AS `id`,
    `d`.`diary_title`            AS `diary_title`,
    `d`.`diary_content`          AS `diary_content`,
    `d`.`diary_file_path`        AS `diary_file_path`,
    `d`.`diary_file_name`        AS `diary_file_name`,
    `d`.`updated_date`           AS `updated_date`,
    `d`.`diary_read_count`       AS `view_count`,
    `d`.`diary_open`             AS `diary_open`,
    `d`.`diary_name_open`        AS `diary_name_open`,
    `c`.`channel_title`          AS `channel_title`,
    `c`.`channel_file_path`      AS `channel_file_path`,
    `c`.`channel_file_name`      AS `channel_file_name`,
    `c`.`channel_url`            AS `channel_url`,
    `m`.`member_nickname`        AS `member_nickname`,
        ifnull(`dl`.`like_count`, 0) AS `like_count`,
    `dt`.`tag_list`              AS `tag_list`
from ((((`feelog`.`tbl_diary` `d` join `feelog`.`tbl_member` `m`
          on ((`d`.`member_id` = `m`.`id`))) join `feelog`.`tbl_channel` `c`
        on ((`m`.`id` = `c`.`member_id`))) left join (select `feelog`.`tbl_diary_like`.`diary_id` AS `diary_id`,
                                                              count(0)                             AS `like_count`
                                                      from `feelog`.`tbl_diary_like`
                                                      group by `feelog`.`tbl_diary_like`.`diary_id`) `dl`
       on ((`d`.`id` = `dl`.`diary_id`))) left join (select `dt`.`diary_id`                            AS `diary_id`,
                                                             group_concat(`t`.`contents` separator ',') AS `tag_list`
                                                     from (`feelog`.`tbl_diary_tag` `dt` join `feelog`.`tbl_tag` `t`
                                                            on ((`dt`.`id` = `t`.`id`)))
                                                     where (`t`.`tag_status` = 'ACTIVE')
                                                     group by `dt`.`diary_id`) `dt` on ((`d`.`id` = `dt`.`diary_id`)))
where ((`d`.`diary_status` = '정상') and (`c`.`channel_status` = '정상'));

create view view_find_like_count as
select `l`.`post_id` AS `post_id`
from (`feelog`.`tbl_channel_post` `p` join `feelog`.`tbl_channel_post_like` `l` on ((`p`.`id` = `l`.`post_id`)));

create view view_find_reply_count as
select `r`.`post_id` AS `post_id`
from (`feelog`.`tbl_channel_post` `p` join `feelog`.`tbl_channel_post_reply` `r` on ((`p`.`id` = `r`.`post_id`)));

create view view_notification as
select `n`.`id`                    AS `notification_id`,
    `n`.`sender_id`             AS `sender_id`,
    `sender`.`member_nickname`  AS `sender_nickname`,
    `sender`.`member_file_path` AS `sender_file_path`,
    `sender`.`member_file_name` AS `sender_file_name`,
    `n`.`receiver_id`           AS `receiver_id`,
        (case
             when (`sn`.`id` is not null) then 'SUBSCRIBE'
             when (`cpn`.`id` is not null) then 'COMMUNITY_POST'
             when (`prn`.`id` is not null) then 'POST_REPLY'
             when (`prln`.`id` is not null) then 'POST_REPLY_LIKE'
             when (`pln`.`id` is not null) then 'POST_LIKE'
             when (`rmn`.`id` is not null) then 'RECEIVE_MESSAGE'
             else 'UNKNOWN' end)    AS `notification_type`,
    `n`.`checked`               AS `checked`,
    `n`.`created_date`          AS `created_date`,
        (case
             when (`sn`.`id` is not null) then `c`.`channel_file_path`
             when (`cpn`.`id` is not null) then `c`.`channel_file_path`
             when (`prn`.`id` is not null) then `cp`.`post_file_path`
             when (`prln`.`id` is not null) then `cp`.`post_file_path`
             when (`pln`.`id` is not null) then `cp`.`post_file_path`
             else NULL end)         AS `thumbnail_path`,
        (case
             when (`sn`.`id` is not null) then `c`.`channel_file_name`
             when (`cpn`.`id` is not null) then `c`.`channel_file_name`
             when (`prn`.`id` is not null) then `cp`.`post_file_name`
             when (`prln`.`id` is not null) then `cp`.`post_file_name`
             when (`pln`.`id` is not null) then `cp`.`post_file_name`
             else NULL end)         AS `thumbnail_name`
from (((((((((`feelog`.`tbl_notification` `n` join `feelog`.`tbl_member` `sender`
               on ((`n`.`sender_id` = `sender`.`id`))) left join `feelog`.`tbl_subscribe_notification` `sn`
             on ((`n`.`id` = `sn`.`id`))) left join `feelog`.`tbl_community_post_notification` `cpn`
            on ((`n`.`id` = `cpn`.`id`))) left join `feelog`.`tbl_post_reply_notification` `prn`
           on ((`n`.`id` = `prn`.`id`))) left join `feelog`.`tbl_post_reply_like_notification` `prln`
          on ((`n`.`id` = `prln`.`id`))) left join `feelog`.`tbl_post_like_notification` `pln`
         on ((`n`.`id` = `pln`.`id`))) left join `feelog`.`tbl_receive_message_notification` `rmn`
        on ((`n`.`id` = `rmn`.`id`))) left join `feelog`.`tbl_channel` `c` on (((`sn`.`subscribe_id` = `c`.`id`) or
                                                                                (`cpn`.`community_post_id` = `c`.`id`)))) left join `feelog`.`tbl_channel_post` `cp`
      on (((`prn`.`post_reply_id` = `cp`.`id`) or (`prln`.`post_reply_like_id` = `cp`.`id`) or
           (`pln`.`post_like_id` = `cp`.`id`))));

create view view_official_content_preview as
select `n`.`id`                AS `id`,
    `n`.`notice_title`      AS `title`,
    `n`.`notice_file_path`  AS `file_path`,
    `n`.`notice_file_name`  AS `file_name`,
        NULL                    AS `task_url`,
    `n`.`notice_content`    AS `notice_content`,
    `n`.`created_date`      AS `created_date`,
    `c`.`channel_url`       AS `channel_url`,
    `c`.`channel_file_path` AS `channel_file_path`,
    `c`.`channel_file_name` AS `channel_file_name`,
    `c`.`channel_title`     AS `channel_title`,
    `c`.`channel_introduce` AS `channel_introduce`,
        'NOTICE'                AS `content_type`,
    `m`.`id`                AS `member_id`,
    `m`.`member_nickname`   AS `member_nickname`
from ((`feelog`.`tbl_notice` `n` join `feelog`.`tbl_member` `m`
        on ((`n`.`member_id` = `m`.`id`))) join `feelog`.`tbl_channel` `c` on ((`c`.`channel_url` = 'official')))
where (`n`.`notice_status` = '정상')
union all
select `t`.`id`                AS `id`,
    `t`.`common_task_name`  AS `title`,
        NULL                    AS `file_path`,
    `t`.`common_task_img`   AS `file_name`,
    `t`.`common_task_url`   AS `task_url`,
        NULL                    AS `notice_content`,
    `t`.`created_date`      AS `created_date`,
    `c`.`channel_url`       AS `channel_url`,
    `c`.`channel_file_path` AS `channel_file_path`,
    `c`.`channel_file_name` AS `channel_file_name`,
    `c`.`channel_title`     AS `channel_title`,
    `c`.`channel_introduce` AS `channel_introduce`,
        'CHALLENGE'             AS `content_type`,
        NULL                    AS `member_id`,
        NULL                    AS `member_nickname`
from (`feelog`.`tbl_common_task` `t` join `feelog`.`tbl_channel` `c` on ((`c`.`channel_url` = 'official')))
where (`t`.`common_task_name` is not null);

create view view_post_info as
select `p`.`post_id`                 AS `id`,
    `p`.`post_type`               AS `post_type`,
    `p`.`post_main_file_name`     AS `post_main_file_name`,
    `p`.`post_main_file_path`     AS `post_main_file_path`,
    `p`.`post_read_count`         AS `post_read_count`,
    `p`.`channel_id`              AS `channel_id`,
    `p`.`post_title`              AS `post_title`,
    `p`.`post_content`            AS `post_content`,
    `p`.`post_status`             AS `post_status`,
    `p`.`updated_date`            AS `updated_date`,
    `c`.`channel_member_id`       AS `channel_member_id`,
    `c`.`channel_title`           AS `channel_title`,
    `c`.`channel_file_path`       AS `channel_file_path`,
    `c`.`channel_file_name`       AS `channel_file_name`,
    `c`.`channel_url`             AS `channel_url`,
    `c`.`channel_member_nickname` AS `channel_member_nickname`,
    `c`.`member_file_path`        AS `member_file_path`,
    `c`.`member_file_name`        AS `member_file_name`
from ((select `cp`.`id`              AS `post_id`,
           `cp`.`post_type`       AS `post_type`,
           `cp`.`post_file_name`  AS `post_main_file_name`,
           `cp`.`post_file_path`  AS `post_main_file_path`,
           `cp`.`post_read_count` AS `post_read_count`,
           `cp`.`channel_id`      AS `channel_id`,
           `p`.`post_title`       AS `post_title`,
           `p`.`post_content`     AS `post_content`,
           `p`.`post_status`      AS `post_status`,
           `p`.`updated_date`     AS `updated_date`
       from (`feelog`.`tbl_channel_post` `cp` join `feelog`.`tbl_post` `p` on ((`cp`.`id` = `p`.`id`)))
       where ((`p`.`post_status` = '정상') and (`cp`.`channel_post_status` = '정상'))) `p` join (select `c`.`id`                AS `channel_id`,
                                                                                                 `m`.`id`                AS `channel_member_id`,
                                                                                                 `c`.`channel_title`     AS `channel_title`,
                                                                                                 `c`.`channel_file_path` AS `channel_file_path`,
                                                                                                 `c`.`channel_file_name` AS `channel_file_name`,
                                                                                                 `c`.`channel_url`       AS `channel_url`,
                                                                                                 `m`.`member_nickname`   AS `channel_member_nickname`,
                                                                                                 `m`.`member_file_name`  AS `member_file_name`,
                                                                                                 `m`.`member_file_path`  AS `member_file_path`
                                                                                             from (`feelog`.`tbl_channel` `c` join `feelog`.`tbl_member` `m`
                                                                                                    on ((`c`.`member_id` = `m`.`id`)))) `c`
      on ((`p`.`channel_id` = `c`.`channel_id`)));

create view view_post_info_detail as
select `p`.`id`                AS `id`,
    `p`.`post_type`         AS `post_type`,
    `p`.`member_id`         AS `member_id`,
    `p`.`channel_id`        AS `channel_id`,
    `p`.`post_read_count`   AS `post_read_count`,
    `p`.`post_content`      AS `post_content`,
    `p`.`post_file_path`    AS `post_file_path`,
    `p`.`post_file_name`    AS `post_file_name`,
    `p`.`post_status`       AS `post_status`,
    `p`.`updated_date`      AS `updated_date`,
    `p`.`created_date`      AS `created_date`,
    `m`.`member_nickname`   AS `member_nickname`,
    `m`.`member_file_path`  AS `member_file_path`,
    `m`.`member_file_name`  AS `member_file_name`,
    `m`.`channel_title`     AS `channel_title`,
    `m`.`channel_url`       AS `channel_url`,
    `m`.`channel_file_name` AS `channel_file_name`,
    `m`.`channel_file_path` AS `channel_file_path`
from ((select `cp`.`id`              AS `id`,
           `cp`.`post_type`       AS `post_type`,
           `cp`.`member_id`       AS `member_id`,
           `cp`.`channel_id`      AS `channel_id`,
           `cp`.`post_read_count` AS `post_read_count`,
           `cp`.`post_file_path`  AS `post_file_path`,
           `cp`.`post_file_name`  AS `post_file_name`,
           `p`.`post_title`       AS `post_title`,
           `p`.`post_content`     AS `post_content`,
           `p`.`post_status`      AS `post_status`,
           `p`.`created_date`     AS `created_date`,
           `p`.`updated_date`     AS `updated_date`
       from (`feelog`.`tbl_channel_post` `cp` join `feelog`.`tbl_post` `p`
              on ((`cp`.`id` = `p`.`id`)))) `p` join (select `m`.`id`                AS `member_id`,
                                                          `m`.`member_file_name`  AS `member_file_name`,
                                                          `m`.`member_file_path`  AS `member_file_path`,
                                                          `m`.`member_nickname`   AS `member_nickname`,
                                                          `c`.`id`                AS `channel_id`,
                                                          `c`.`channel_title`     AS `channel_title`,
                                                          `c`.`channel_url`       AS `channel_url`,
                                                          `c`.`channel_file_path` AS `channel_file_path`,
                                                          `c`.`channel_file_name` AS `channel_file_name`
                                                      from (`feelog`.`tbl_member` `m` join `feelog`.`tbl_channel` `c`
                                                             on ((`m`.`id` = `c`.`member_id`)))) `m`
      on ((`p`.`member_id` = `m`.`member_id`)));

create view view_post_tag as
select `t`.`id`               AS `id`,
    `t`.`contents`         AS `tag_content`,
    `t`.`tag_status`       AS `tag_status`,
    `pt`.`channel_post_id` AS `post_id`
from (`feelog`.`tbl_tag` `t` join `feelog`.`tbl_channel_post_tag` `pt` on ((`t`.`id` = `pt`.`id`)))
where (`t`.`tag_status` = 'ACTIVE');

create view view_subscribe_status as
select `s`.`id`               AS `subscribe_id`,
    `s`.`member_id`        AS `subscriber_id`,
    `m`.`member_nickname`  AS `subscriber_nickname`,
    `c`.`id`               AS `channel_id`,
    `c`.`channel_title`    AS `channel_title`,
    `c`.`channel_url`      AS `channel_url`,
    `s`.`subscribe_status` AS `subscribe_status`,
    `s`.`created_date`     AS `created_date`,
    `s`.`updated_date`     AS `updated_date`
from ((`feelog`.`tbl_subscribe` `s` join `feelog`.`tbl_member` `m`
        on ((`s`.`member_id` = `m`.`id`))) join `feelog`.`tbl_channel` `c` on ((`s`.`channel_id` = `c`.`id`)));

