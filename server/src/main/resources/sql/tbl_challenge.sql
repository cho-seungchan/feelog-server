--  선정 과제(챌린지) 슈퍼키
create table tbl_challenge (
                               id 		                         bigint auto_increment primary key,
                               challenge_complete           	 varchar(50) default '',
                               challengetask_type                varchar(50) not null,
                               challenge_status 		        varchar(50) default '',
                               created_date 	                 datetime default current_timestamp,
                               updated_date 	                 datetime default current_timestamp
);