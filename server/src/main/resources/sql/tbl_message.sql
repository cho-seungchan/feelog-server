create table tbl_message(
    id bigint auto_increment primary key ,
    message_content varchar(1000),
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp
);


alter table tbl_message drop column message_status;
