
-- diary  신고 테이블
create table tbl_diary_report (
                                  id 			                bigint primary key ,
                                  member_id 		            bigint not null,
                                  diary_id 	                    bigint not null,
                                  constraint fk_diary_report_report foreign key (id)
                                      references tbl_report (id) ,
                                  constraint fk_diary_report_member foreign key (member_id)
                                      references tbl_member (id) ,
                                  constraint fk_diary_report_diary foreign key (diary_id)
                                      references tbl_diary (id)
);


create view view_diary_report as
select
    dr.id as report_id,
    report_status as report_status,
    r.created_date as report_created_date,
    dr.member_id as report_member,
    m.member_nickname as report_member_nickname,
    dr.diary_id as report_diary,
    diary_title as report_diary_title,
    diary_content as report_diary_content,
    sub.member_id as report_diary_member_id,
    sub.member_nickname as report_diary_member_nickname
from tbl_diary_report dr
         join tbl_report r
              on dr.id =r.id
         join tbl_member m
              on dr.member_id = m.id
         join(
    select dm.member_nickname, d.id, d.member_id, d.diary_content, d.diary_title
    from tbl_member dm join tbl_diary d on dm.id = d.member_id
)as sub on sub.id = dr.diary_id;


