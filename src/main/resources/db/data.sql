-- --superuser 1111
-- INSERT INTO member (member_id, password, name, phone, gender,  email, member_role, member_status, birth, created_at, last_login_time)
--     VALUES ('superuser', '$2a$12$xsnsy6lcQl1EM2cc9sxEbemIoXPf6jiMHIyE3lZWAr8YCz9QQDJXq', '관리자', '010-1234-5678', 'MEN', 'superuser111122@naver.com', 'SUPER', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- leehaneum 0924
-- INSERT INTO member (member_id, password, name, phone, email, member_role, member_status, birth, created_at, last_login_time)
-- VALUES ('leehaneum', '$2a$12$SYIVeoc5WTQTDOZRzxMm6u9GfYVUIEHNOwqq.vpt2vgaezM63nVke', '이한음', '010-6299-5678', 'leehaneum160924@kyonggi.ac.kr', 'ADMIN', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- -- okJang 8777
-- INSERT INTO member (member_id, password, name, phone, address, guardian_name, guardian_phone, disability_type, email, member_role, member_status, birth, created_at, last_login_time)
-- VALUES ('okJang', '$2a$12$bUjikKYJ1w1RdYFIn2DlmOcNF0zxivfAJ6hDsP/TxaAgTqb9J./wG', 'okJang', '010-6299-0733', '경기도 수원시', '이대수', '010-6299-7216', 'NONE', 'mosoon365@hanmail.net', 'USER', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- -- board
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('첫번째 공지사항 입니다.', '첫번째 공지사항 입니다.', 'hanam5123', 'ANNOUNCEMENT', '2024-02-09', '2024-02-09');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('두번째 공지사항 입니다.', '두번째 공지사항 입니다.', 'hanam5123', 'ANNOUNCEMENT', '2024-02-10', '2024-02-10');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('세번째 공지사항 입니다.', '세번째 공지사항 입니다.', 'hanam5123', 'ANNOUNCEMENT', '2024-02-11', '2024-02-11');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('네번째 공지사항 입니다.', '네번째 공지사항 입니다.', 'hanam5123', 'ANNOUNCEMENT', '2024-02-12', '2024-02-12');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('다섯번째 공지사항 입니다.', '다섯번째 공지사항 입니다.', 'hanam5123', 'ANNOUNCEMENT', '2024-02-13', '2024-02-13');

-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('첫번째 채용공고 입니다.', '첫번째 채용공고 입니다.', 'hanam5123', 'RECRUITMENT', '2024-02-09', '2024-02-09');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('두번째 채용공고 입니다.', '두번째 채용공고 입니다.', 'hanam5123', 'RECRUITMENT', '2024-02-10', '2024-02-10');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('세번째 채용공고 입니다.', '세번째 채용공고 입니다.', 'hanam5123', 'RECRUITMENT', '2024-02-11', '2024-02-11');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('네번째 채용공고 입니다.', '네번째 채용공고 입니다.', 'hanam5123', 'RECRUITMENT', '2024-02-12', '2024-02-12');
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('다섯번째 채용공고 입니다.', '다섯번째 채용공고 입니다.', 'hanam5123', 'RECRUITMENT', '2024-02-13', '2024-02-13');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('수원시 체육대회 개최', '수원시 체육대회 개최', 'okJang', 'ANNOUNCEMENT', '2021-01-01', '2021-01-01');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('평창 청소년 올림픽 한국 1위', '평창 청소년 올림픽 한국 1위', 'leehaneum', 'ANNOUNCEMENT', '2021-01-01', '2021-01-01');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('알바 모집중', '알바 모집중', 'superuser', 'RECRUITMENT', '2021-01-01', '2021-01-01');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('신입 개발자 모집 공고', '신입 개발자 모집 공고', 'superuser', 'RECRUITMENT', '2022-01-01', '2022-01-01');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('2021년 경영공시', '2021년 경영공시', 'leehaneum', 'MANAGEMENT', '2022-01-01', '2022-01-01');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('2022년 경영공시', '2022년 경영공시', 'superuser', 'MANAGEMENT', '2023-01-01', '2023-01-01');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('2023년 경영공시', '2023년 경영공시', 'leehaneum', 'MANAGEMENT', '2024-01-01', '2024-01-01');
--
-- INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
-- VALUES ('2024 장애인 체육대회', '2024 장애인 체육대회', 'leehaneum', 'EVENT', '2024-02-01', '2024-02-01');
--
-- -- notification
-- INSERT INTO notification (content, is_read, member_id, created_at)
-- VALUES ('회원가입이 완료되었습니다.', false, 'leehaneum', '2021-01-01');
--
-- INSERT INTO notification (content, is_read, member_id, created_at)
-- VALUES ('패스워드가 변경되었습니다.', true, 'okJang', '2021-01-01');
--
-- INSERT INTO notification (content, is_read, member_id, created_at)
-- VALUES ('접속이 없어 휴면 상태로 전환되었습니다.', false, 'okJang', '2021-01-01');
--
-- -- QnA
-- INSERT INTO qna (title, content,is_answered, writer_id, answer, answerer_id, created_at, updated_at)
-- VALUES ('오픈이 언제인가요?', '제곧내', true, 'leehaneum', '1월 중순입니다.', 'superuser', '2021-01-01', '2021-01-01');
--
-- INSERT INTO qna (title, content,is_answered, writer_id, created_at, updated_at)
-- VALUES ('휴가는 언제인가요?', '휴가 가고 싶어요', false, 'okJang','2021-01-01', '2021-01-01');

-- comment
-- INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
-- VALUES ('첫번째 댓글입니다.', 'leehaneum', 1, null, '2021-01-01', '2021-01-01');
--
-- INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
-- VALUES ('두번째 댓글입니다.', 'okJang', 1, null, '2021-01-01', '2021-01-01');
--
-- INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
-- VALUES ('세번째 댓글입니다.', 'leehaneum', 1, 1, '2021-01-01', '2021-01-01');
--
-- INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
-- VALUES ('네번째 댓글입니다.', 'okJang', 1, 1, '2021-01-01', '2021-01-01');
--
-- INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
-- VALUES ('다섯번째 댓글입니다.', 'leehaneum', 1, 3, '2021-01-01', '2021-01-01');

-- -- program
INSERT INTO program (name, thumbnail, available, program_status, manager_id, apply_end, start_date, end_date, time, location, cost, material, description, created_at, updated_at)
VALUES ('룰루랄라 장애인 체조 교실', 'image/휠체어.jpg', '10', 'ACCEPTING', 'hanam5123', '2024-02-29', '2024-03-04', '2024-10-30', '60', '하남 풍산멀티스포츠센터 4층 다목적체육관', '무료', '실내운동화',
        '운영대상 : 하남시 관내 거주 장애인 및 시설 이용 장애인 <br />
        운영 기간 : 2024.03.04(월) ~ 10월 30일(수)까지 총 50회차 <br /> 운영 시간 : 매주 월,수요일 오후 2시30분 ~ 3시 30분까지 <br /> 모집 기간: 2024년2월29일까지 선착순 마감 (유선 신청 가능)', '2024-02-14', '2024-02-14');

-- INSERT INTO program (name, thumbnail, available, program_status, manager_id, apply_end, start_date, end_date, time, location, cost, material, description, created_at, updated_at)
-- VALUES ('하남시 챌린저블 농구교실(발달장애인)', 'image/휠체어.jpg', '30', 'ACCEPTING', 'superuser', '2024-03-01', '2024-03-05', '2024-03-05', '120', '하남시 스타디움', '무료', '없음', '이 행사는 하남시 장애인 체육회에서 선도적으로 주관하는 장애인 조정대회로, 참가비가 무료로 제공됩니다.
-- 이를 통해 장애인들은 접근성 있는 환경에서 스포츠에 참여하고 뛰어난 실력을 겨루며 자신의 잠재력을 발휘할 수 있습니다.
-- 무료 참가비는 모든 장애인들에게 평등한 참여 기회를 제공하며, 지역 사회의 다양한 장애인들이 함께 모여 즐거운 대회를 즐길 수 있습니다.
-- 또한, 이 행사는 지역사회와의 상호작용을 통해 장애인 스포츠에 대한 인식과 이해를 높이며, 사회의 통합과 다양성 증진에 기여하는 중요한 행사로 자리잡고 있습니다.', '2024-02-01', '2024-02-01');



-- application
-- INSERT INTO application (member_id, program_id, address, phone, gender, disability_type, guardian_name, guardian_phone, status, remarks, created_at, updated_at)
-- VALUES ('okJang', 1, '경기도 수원시 장안구 대평로 27','010-1234-1233', 'MEN', 'BLIND', '고구마', '010-6299-8498', 'WAITING', '특이사항 없음', '2024-02-02', '2024-02-04');
--
-- INSERT INTO application (member_id, program_id, address, phone, gender, disability_type, guardian_name, guardian_phone, status, remarks, created_at, updated_at)
-- VALUES ('leehaneum', 1, '경기도 수원시 장안구','010-1234-1616', 'MEN', 'DEAF', '장영옥', '010-6299-1111', 'WAITING', '특이사항 없음', '2024-02-03', '2024-02-05');

-- event
-- INSERT INTO event (title, start, end, color, description, board_id, member_id, created_at, updated_at)
-- VALUES ('2024 장애인 체육대회', '2024-02-25', '2024-02-25', '#119208', '2024 장애인 체육대회', 9, 'leehaneum', '2024-02-01', '2024-02-01');
--
-- INSERT INTO event (title, start, end, color, description, board_id, member_id, created_at, updated_at)
-- VALUES ('홈페이지 개발', '2024-02-01', '2024-02-14', '#119208', '홈페이지 개발', null, 'superuser', '2024-02-04', '2024-02-05');