-- post 신고 테이블
create table tbl_channel_post_report (
    id 		            bigint primary key ,
    member_id 	        bigint not null,
    post_id 		        bigint not null,
    channel_post_report_status 		        varchar(50) ,
    constraint fk_channel_post_report_report foreign key (id)
    references tbl_report (id) ,
    constraint fk_channel_post_report_member foreign key (member_id)
    references tbl_member (id) ,
    constraint fk_channel_post_report_channel_post foreign key (post_id)
    references tbl_channel_post (id)
);