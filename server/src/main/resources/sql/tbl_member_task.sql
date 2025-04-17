-- 개인용 도전 과제 pool 테이블
create table tbl_member_task(
       id 		                    bigint auto_increment primary key,
       member_id                    bigint not null,
       task_pool_id                 bigint not null,
       member_task_status		    varchar(50) default '정상',
       created_date 	            datetime default current_timestamp,
       updated_date 	            datetime default current_timestamp,
       constraint fk_member_task_member foreign key (member_id)
           references tbl_member (id),
       constraint fk_member_task_member_task_pool foreign key (task_pool_id)
           references tbl_member_task_pool (id)
);