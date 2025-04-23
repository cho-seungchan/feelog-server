-- 채널 정보 테이블
create table tbl_channel (
                             id 		            bigint auto_increment primary key,
                             channel_title 		    varchar(50) not null,
                             channel_introduce 		varchar(300) default '',
                             channel_url 		    varchar(100) not null,
                             channel_file_path 		varchar(500) default '',
                             channel_file_name 		varchar(500) default '',
                             channel_status 		 varchar(50) default '정상',
                             member_id               bigint not null,
                             created_date 	        datetime default current_timestamp,
                             updated_date 	        datetime default current_timestamp,
                             constraint fk_channel_member foreign key (member_id)
                             references tbl_member (id)
);

select * from tbl_channel;