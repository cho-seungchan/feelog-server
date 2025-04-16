-- 신고 테이블 슈퍼키
create table tbl_report (
                            id 		            bigint auto_increment primary key,
                            report_status       varchar(50) default '',
                            created_date 	    datetime default current_timestamp,
                            updated_date 	    datetime default current_timestamp
);