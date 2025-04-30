-- 선정 과제 (개인용) 테이블
create table tbl_member_challenge (
      id 		                         bigint primary key,
      member_id 	                     bigint not null,
      task_id 		                 bigint not null,
      constraint fk_member_challenge_challenge foreign key (id)
          references tbl_challenge (id) ,
      constraint fk_member_challenge_member foreign key (member_id)
          references tbl_member (id) ,
      constraint fk_member_challenge_member_task_pool foreign key (task_id)
          references tbl_member_task_pool (id)
);



