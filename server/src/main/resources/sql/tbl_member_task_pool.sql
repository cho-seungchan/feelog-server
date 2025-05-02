-- 개인용 도전 과제 pool 테이블
create table tbl_member_task_pool (
        id 		                    bigint auto_increment primary key,
        member_task_pool_content 	varchar(1000) not null,
        member_task_pool_file_path 	varchar(1000),
        member_task_pool_file_name 	varchar(1000),
        member_task_pool_status 	varchar(50) default '정상',
        created_date 	            datetime default current_timestamp,
        updated_date 	            datetime default current_timestamp
);


ALTER TABLE tbl_member_task_pool
    ADD COLUMN member_task_pool_content 	varchar(1000) not null;