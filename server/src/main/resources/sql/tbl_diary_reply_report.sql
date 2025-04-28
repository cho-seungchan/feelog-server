-- diary  reply 신고 테이블
create table tbl_diary_reply_report (
                                        id 		                                bigint primary key ,
                                        member_id 	                            bigint not null,
                                        reply_id 		                        bigint not null,
                                        constraint fk_diary_reply_report_report foreign key (id)
                                            references tbl_report (id) ,
                                        constraint fk_diary_reply_report_member foreign key (member_id)
                                            references tbl_member (id) ,
                                        constraint fk_diary_reply_report_diary_reply foreign key (reply_id)
                                            references tbl_diary_reply (id)
);


