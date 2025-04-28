-- 연관 검색 테이블
create table tbl_related_search (
    id bigint auto_increment primary key,
    keyword varchar(100) not null,
    tag_id bigint,
    member_id bigint,
    created_date 	    datetime default current_timestamp,
    updated_date 	    datetime default current_timestamp,
    constraint fk_related_search_tag
    foreign key (tag_id) references tbl_tag(id)
    on delete set null,
    constraint fk_related_search_member
    foreign key (member_id) references tbl_member(id)
    on delete set null
);

SELECT * FROM tbl_related_search ORDER BY created_date DESC;

SELECT * FROM tbl_tag WHERE contents = 'qwe';

SELECT *
FROM tbl_tag;

SELECT * FROM tbl_related_search WHERE keyword = '응원글';