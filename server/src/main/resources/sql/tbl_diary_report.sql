
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
