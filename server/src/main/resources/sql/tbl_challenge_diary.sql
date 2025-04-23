-- 챌린지 연결 다이어리
create table tbl_challenge_diary (
                                     id 		                    bigint primary key,
                                     challenge_id                  bigint not null,
                                     constraint fk_challenge_diary_diary foreign key (id)
                                         references tbl_diary (id),
                                     constraint fk_challenge_diary_challenge foreign key (challenge_id)
                                         references tbl_challenge (id)
);
