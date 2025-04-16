-- diary 댓글 좋아요 테이블
create table tbl_diary_reply_like (
                                      id 			    bigint primary key,
                                      member_id 		bigint not null,
                                      reply_id 		bigint not null,
                                      diary_reply_like_status 		    varchar(50) default '' ,
                                      constraint fk_diary_reply_like_like foreign key (id)
                                          references tbl_like (id) ,
                                      constraint fk_diary_reply_like_member foreign key (member_id)
                                          references tbl_member (id) ,
                                      constraint fk_diary_reply_like_reply foreign key (reply_id)
                                          references tbl_diary_reply (id)
);