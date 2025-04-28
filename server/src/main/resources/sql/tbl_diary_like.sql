
-- diary 좋아요 테이블
create table tbl_diary_like (
                                id 		            bigint primary key,
                                member_id 	        bigint not null,
                                diary_id 	        bigint not null,
                                constraint fk_diary_like_like foreign key (id)
                                    references tbl_like (id) ,
                                constraint fk_diary_like_member foreign key (member_id)
                                    references tbl_member (id) ,
                                constraint fk_diary_like_diary foreign key (diary_id)
                                    references tbl_diary (id)
);


