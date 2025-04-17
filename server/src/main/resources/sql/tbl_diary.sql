-- 마음의 일기 테이블
-- 전체 공개 / 구독자에게만 공개 / 비공개
create table tbl_diary (
                           id 			        bigint auto_increment primary key,
                           diary_content 		varchar(4000) not null,
                           diary_open       	varchar(50) default '비공개',
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