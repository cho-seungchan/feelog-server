-- ai 응답에 대한 감정 이미지 테이블
create table tbl_feel (
                          id 			        bigint auto_increment primary key,
                          feel_code             int  not null,
                          feel_file_path 		varchar(500) default '',
                          feel_file_name 		varchar(500) default '',
                          feel_file_size 		varchar(100) default '',
                          created_date 	    datetime default current_timestamp,
                          updated_date 	    datetime default current_timestamp
);

