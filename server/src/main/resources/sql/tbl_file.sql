-- file 슈퍼키
create table tbl_file(
                         id bigint auto_increment primary key,
                         file_path 	     	varchar(500) default '',
                         file_name 	     	varchar(500) default '',
                         file_size 	     	varchar(500) default '',
                         file_status        varchar(50)  default '',
                         created_date 	    datetime default current_timestamp,
                         updated_date 	    datetime default current_timestamp
);
