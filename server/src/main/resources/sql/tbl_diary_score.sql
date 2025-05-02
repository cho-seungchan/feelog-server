create table tbl_diary_score
(
    id bigint auto_increment primary key,
    score_level_name varchar(100) not null,
    score_message varchar(2000) not null,
    score_file_path varchar(500) not null,
    score_file_name varchar(500) not null,
    created_date datetime default current_timestamp,
    updated_date datetime default current_timestamp
);

insert into tbl_diary_score (score_level_name, score_message, score_file_path, score_file_name)
values ('extremeNegativeEmotion', '괜찮아요. 오늘은 그냥 존재하는 것만으로도 충분해요.', 'emotion', '1extremNagativeEmotion.png'),
       ('severeNegativeEmotion', '상처 입은 당신을 세상이 알아주지 않아도, 나는 알아요.', 'emotion', '2severeNegativeEmotion.png'),
       ('strongNegativeEmotion', '힘들어도 여기까지 온 당신을 응원해요.', 'emotion', '3strongNegativeEmotion.png'),
       ('moderateNagativeEmotion', '오늘의 무거운 마음도 곧 가벼워질 거예요.', 'emotion', '4moderateNagativeEmotion.png'),
       ('mildNegativeEmotion', '내일은 더 환하게 웃을 수 있을 거예요.', 'emotion', '5mildNegativeEmotion.png'),
       ('neutral', '아무런 감정 없는 하루도 때로는 필요한 거예요.', 'emotion', '6neutral.png'),
       ('mildNegativeEmotion', '소소한 기쁨이 당신을 채워가고 있어요.', 'emotion', '7mildNegativeEmotion.png'),
       ('positiveEmotion', '기쁨을 느낀 당신, 스스로에게 선물을 준 거예요.', 'emotion', '8positiveEmotion.png'),
       ('strongPositiveEmotion', '마음 가득 행복을 품은 당신, 아름다워요.', 'emotion', '9strongPositiveEmotion.png'),
       ('extremPositiveEmotion', '당신이 만들어낸 행복은 세상을 환하게 해요.', 'emotion', '10extremPositiveEmotion.png');




