-- 마음의 일기 테이블
-- 전체 공개 / 구독자에게만 공개 / 비공개
create table tbl_diary (
                           id 			        bigint auto_increment primary key,
                           diary_title          varchar(2000) not null,
                           diary_content 		varchar(4000) not null,
                           diary_open       	varchar(50) default '비공개',
                           diary_name_open      varchar(50) default '비공개(익명)',
                           diary_file_path 		varchar(500) default '',
                           diary_file_name 		varchar(500) default '',
                           diary_file_size 		varchar(500) default '',
                           diary_weather        varchar(500) default '',
                           member_id 		    bigint not null,
                           feel_id              bigint not null,
                           diary_status 		varchar(50) default '정상',
                           created_date 		datetime default current_timestamp,
                           updated_date 	    datetime default current_timestamp,
                           constraint fk_diary_member foreign key (member_id)
                               references tbl_member (id),
                           constraint fk_diary_feel foreign key (feel_id)
                               references tbl_feel (id)
);

alter table tbl_diary add diary_name_open      varchar(50) default '비공개(익명)';


CREATE VIEW view_diary_detail AS
SELECT
    d.id AS diary_id,
    d.diary_title,
    d.diary_content,
    d.member_id,
    d.feel_id,
    f.feel_code,
    f.feel_file_path,
    f.feel_file_name,
    f.feel_file_size,
    t.contents AS tag_content,
    fi.file_path AS thumbnail_path,
    fi.file_name AS thumbnail_name,
    fi.file_size AS thumbnail_size,
    d.created_date,
    d.updated_date
FROM tbl_diary d

         LEFT JOIN tbl_feel f
                   ON d.feel_id = f.id

         LEFT JOIN tbl_diary_tag dt
                   ON d.id = dt.id

         LEFT JOIN tbl_tag t
                   ON dt.tag_id = t.id

         LEFT JOIN tbl_diary_file df
                   ON d.id = df.diary_id

         LEFT JOIN tbl_file fi
                   ON df.file_id = fi.id;

select * from view_diary_detail;

select * from tbl_file;

select * from tbl_diary;