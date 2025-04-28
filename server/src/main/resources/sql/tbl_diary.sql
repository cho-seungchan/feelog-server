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
                           diary_score          int default 0,
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


select * from view_diary_detail;


CREATE OR REPLACE VIEW view_diary_detail AS
SELECT
    d.id                     AS diary_id,
    d.diary_title,
    d.diary_content,
    d.diary_open,
    d.diary_name_open,
    d.diary_file_path        AS rep_file_path,
    d.diary_file_name        AS rep_file_name,
    d.diary_file_size        AS rep_file_size,
    d.feel_id,
    d.member_id,
    d.diary_status,
    d.created_date           AS diary_created_date,
    d.updated_date           AS diary_updated_date,

    -- 첨부 이미지 (0개 또는 여러 개)
    f.id                     AS attach_file_id,
    f.file_path              AS attach_file_path,
    f.file_name              AS attach_file_name,
    f.file_size              AS attach_file_size,
    f.file_status,
    f.created_date           AS file_created_date,

    -- 태그 (0개 또는 여러 개)
    t.id                     AS tag_id,
    t.contents               AS tag_contents,
    t.tag_status,
    t.created_date           AS tag_created_date

FROM tbl_diary d

-- 첨부 이미지 연결
         LEFT JOIN tbl_diary_file df ON d.id = df.diary_id
         LEFT JOIN tbl_file f         ON df.id = f.id -- 슈퍼키 방식

-- 태그 연결
         LEFT JOIN tbl_diary_tag dt  ON d.id = dt.diary_id
         LEFT JOIN tbl_tag t         ON dt.id = t.id;

select * from view_diary_detail where view_diary_detail.diary_id;
# = 22;

UPDATE tbl_diary
SET diary_open = '비공개'
WHERE id = 20;
SELECT * FROM tbl_diary WHERE id = 22;

select * from tbl_file;


INSERT INTO tbl_diary (
    diary_title,diary_content,diary_score,member_id,feel_id) VALUES
(
'제목',
'내용',
1,1,1
);

INSERT INTO tbl_diary (diary_title, diary_content, diary_score, member_id, feel_id) VALUES
                                                                                        ('깨어있는 것이 고통이다.', '눈을 뜨는 순간부터 다시 시작되는 고통에 지친다. 잠시라도 나를 놓아버리고 싶은 마음뿐이다. 아무리 애써도 삶의 무게는 나를 짓누르고, 살아있는 것 자체가 버거운 고통이다.', 1, 1, 1),
                                                                                        ('무언가를 해보려는 의지도 없다.', '기운이 빠지고, 아무것도 시도할 용기가 나지 않는다. 그냥 살아있는 것만으로도 벅차다. 더 이상 무엇을 시도할 힘조차 남아 있지 않다.', 1, 1, 1),
                                                                                        ('내가 느끼는 아픔은 설명할 수 없다.', '이 아픔은 아무리 말해도 전혀 전달될 수 없다. 나만 느끼는 고통이기 때문에, 아무도 이해하지 못할 것이다. 그저 내 안에서 깊이 쌓여가는 이 아픔을 홀로 감당하고 있다.', 1, 1, 1);
