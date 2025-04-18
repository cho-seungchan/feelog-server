-- 전 회원용 도전 과제 테이블
create table tbl_common_task (
                                 id 		                    bigint auto_increment primary key,
                                 common_task_name             varchar(1000) not null,
                                 common_task_img              varchar(1000),
                                 common_task_tell             varchar(50),
                                 common_task_url              varchar(1000),
                                 common_task_addr             varchar(1000),
                                 common_task_lot              decimal(10,6),                 -- 위도
                                 common_task_lat              decimal(10,6),                 -- 경도
                                 common_task_service_name 	  varchar(100) not null,    -- 열린광장 서비스 명
                                 common_task_req_page 	      varchar(20) not null,     -- 열린광장 페이지
                                 created_date 	            datetime default current_timestamp,
                                 updated_date 	            datetime default current_timestamp
);

