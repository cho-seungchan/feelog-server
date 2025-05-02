-- diary 댓글 테이블

create table tbl_diary_reply (
                                 id 		                    bigint primary key ,
                                 member_id 	                    bigint not null,
                                 diary_id	                    bigint not null,
                                 constraint fk_diary_reply_reply foreign key (id)
                                     references tbl_reply (id) ,
                                 constraint fk_diary_reply_member foreign key (member_id)
                                     references tbl_member (id) ,
                                 constraint fk_diary_reply_diary foreign key (diary_id)
                                     references tbl_diary (id)
);



