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
                                                                                        ('아무도 나를 필요로 하지 않는다.', '다시 한번 느낀다. 아무리 애써도 나를 필요로 하는 사람은 없다. 내가 사라져도 세상은 전혀 변하지 않을 것이다. 내가 존재하는 이유가 더 이상 보이지 않는다.', 1, 1, 1),
                                                                                        ('모든 감정이 사라진 것 같다.', '슬픔도 기쁨도, 분노도 아무것도 느껴지지 않는다. 감정이 모두 사라지고 텅 빈 상태로 살아가고 있다. 아무리 애쓰고 다가가도 감정은 살아나지 않는다.', 1, 1, 1),
                                                                                        ('나는 포기했다.', '모든 걸 포기하고 싶다. 더 이상 나아가고 싶은 마음도 없고, 과거의 나를 되돌릴 수 없다는 사실이 나를 더욱 절망하게 만든다. 나를 포기한 채 살아가는 중이다.', 1, 1, 1),
                                                                                        ('내 안에 남은 건 절망뿐이다.', '내 안에는 더 이상 희망도, 기쁨도 없다. 남아 있는 건 차가운 절망뿐이다. 그 절망 속에서 계속 살아가야 하는 것만 같고, 더 이상 어떻게 해야 할지 모르겠다.', 1, 1, 1),
                                                                                        ('지금 이대로 멈췄으면 좋겠다.', '지금 당장 멈추고 싶다. 모든 것이 끝나버렸으면 좋겠다. 더 이상 고통을 느끼지 않게 되고, 끝내 모든 걸 놓아버리고 싶다는 생각만 든다.', 1, 1, 1),
                                                                                        ('모든 걸 내려놓고 싶다.', '이 모든 걸 내려놓고 싶다. 그동안 쥐고 있었던 감정, 모든 것들을 풀어버리고 나면 조금이라도 편해질까? 하지만 내려놓는 것도 용기가 필요하다.', 1, 1, 1),
                                                                                        ('내 존재가 투명해진 것 같다.', '내 존재가 점점 사라지는 것 같다. 주위의 사람들도 나를 알아보지 못하고, 나는 이 세상에서 점점 투명해져만 가는 기분이다. 그저 스쳐 지나가는 존재로 살아간다.', 1, 1, 1),
                                                                                        ('기억하고 싶지 않은 하루였다.', '오늘 하루도 내게 기억되고 싶지 않은 날 중 하나로 남을 것이다. 아무리 좋은 일이 있어도, 이 하루는 내 마음속에 쓴잔만 남겼다. 차라리 기억을 지워버리고 싶다.', 1, 1, 1),
                                                                                        ('나는 끝없는 어둠에 잠겨 있다.', '끝없이 어두운 곳으로 빠져들고 있다. 그 어둠 속에서 나오는 길을 찾을 수 없고, 이 세상은 점점 더 멀어지는 것 같다. 그저 어둠 속에서 살아갈 뿐이다.', 1, 1, 1),
                                                                                        ('희망은 애초에 없었던 것 같다.', '희망을 품었던 적도 있었지만, 그것은 애초에 존재하지 않았던 것 같다. 점차 그 사실을 깨달으면서, 희망을 믿고 싶었던 내가 더욱 슬프게 느껴진다.', 1, 1, 1);
