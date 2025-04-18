-- 개인용 도전 과제 pool 테이블
create table tbl_member_task_pool (
        id 		                    bigint auto_increment primary key,
        mamber_task_pool_content 	varchar(1000) not null,
        mamber_task_pool_file_path 	varchar(1000) default '',
        mamber_task_pool_file_name 	varchar(1000) default '',
        mamber_task_pool_file_size 	varchar(100) default '',
        member_task_pool_status 	varchar(50) default '정상',
        created_date 	            datetime default current_timestamp,
        updated_date 	            datetime default current_timestamp
);

