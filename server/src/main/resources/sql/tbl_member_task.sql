-- 개인용 도전 과제 pool 테이블
create table tbl_member_task(
       id 		                    bigint auto_increment primary key,
       task_pool_id                 bigint,
       member_task_status		    varchar(50) default '정상',
       created_date 	            datetime default current_timestamp,
       updated_date 	            datetime default current_timestamp,
       constraint fk_member_challenge_task foreign key (task_pool_id)
           references tbl_member_task_pool (id)
);

