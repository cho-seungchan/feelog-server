create table tbl_faq(
    id bigint auto_increment primary key,
    faq_title varchar(4000) not null,
    faq_content varchar(4000) not null,
    member_id bigint not null ,
    faq_status varchar(50) default '정상',
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp,
    constraint fk_faq_member foreign key (member_id)
                       references tbl_member(id)
);

INSERT INTO tbl_faq (faq_title, faq_content, member_id, faq_status)
VALUES
    ('배송은 얼마나 걸리나요?', '상품 주문 후 평균 2~3일 이내에 배송됩니다.', 12, '정상'),
    ('반품 및 환불 절차는 어떻게 되나요?', '마이페이지 > 주문내역 > 반품 신청을 통해 가능합니다.', 12, '정상'),
    ('회원 탈퇴는 어떻게 하나요?', '설정 > 계정 관리 > 회원 탈퇴 메뉴를 통해 직접 탈퇴할 수 있습니다.', 12, '정상'),
    ('적립금은 언제 지급되나요?', '구매 확정 후 7일 이내에 자동 적립됩니다.', 12, '정상'),
    ('비밀번호를 잊어버렸어요.', '로그인 화면에서 비밀번호 찾기를 통해 재설정이 가능합니다.', 12, '정상'),
    ('이벤트 당첨자는 어떻게 발표하나요?', '당첨자는 공지사항 및 개별 문자로 안내드립니다.', 12, '정상');
