-- 선정 과제 (전 회원용) 테이블
create table tbl_common_challenge (
                                      id 			                    bigint  primary key,
                                      member_id 		                bigint not null,
                                      task_id 		                    bigint not null,
                                      common_challenge_status 		    varchar(50) default '',
                                      constraint fk_common_challenge_challenge foreign key (id)
                                          references tbl_challenge (id) ,
                                      constraint fk_common_challenge_member foreign key (member_id)
                                          references tbl_member (id) ,
                                      constraint fk_common_challenge_task foreign key (task_id)
                                          references tbl_common_task (id)
);