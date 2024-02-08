--superuser 1111
INSERT INTO member (member_id, password, name, phone, email, member_role, member_status, birth, created_at, last_login_time)
    VALUES ('superuser', '$2a$12$xsnsy6lcQl1EM2cc9sxEbemIoXPf6jiMHIyE3lZWAr8YCz9QQDJXq', '관리자', '010-1234-5678', 'superuser@naver.com', 'SUPER', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- leehaneum 0924
INSERT INTO member (member_id, password, name, phone, email, member_role, member_status, birth, created_at, last_login_time)
VALUES ('leehaneum', '$2a$12$SYIVeoc5WTQTDOZRzxMm6u9GfYVUIEHNOwqq.vpt2vgaezM63nVke', '이한음', '010-6299-5678', 'leehaneum160924@kyonggi.ac.kr', 'ADMIN', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- okJang 8777
INSERT INTO member (member_id, password, name, phone, email, member_role, member_status, birth, created_at, last_login_time)
VALUES ('okJang', '$2a$12$bUjikKYJ1w1RdYFIn2DlmOcNF0zxivfAJ6hDsP/TxaAgTqb9J./wG', 'okJang', '010-6299-0733', 'mosoon365@hanmail.net', 'USER', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- board
INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('가입인사', '첫번째 글입니다.', 'leehaneum', 'ANNOUNCEMENT', '2021-01-01', '2021-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('수원시 체육대회 개최', '수원시 체육대회 개최', 'okJang', 'ANNOUNCEMENT', '2021-01-01', '2021-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('평창 청소년 올림픽 한국 1위', '평창 청소년 올림픽 한국 1위', 'leehaneum', 'ANNOUNCEMENT', '2021-01-01', '2021-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('알바 모집중', '알바 모집중', 'superuser', 'RECRUITMENT', '2021-01-01', '2021-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('신입 개발자 모집 공고', '신입 개발자 모집 공고', 'superuser', 'RECRUITMENT', '2022-01-01', '2022-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('2021년 경영공시', '2021년 경영공시', 'leehaneum', 'MANAGEMENT', '2022-01-01', '2022-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('2022년 경영공시', '2022년 경영공시', 'superuser', 'MANAGEMENT', '2023-01-01', '2023-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('2023년 경영공시', '2023년 경영공시', 'leehaneum', 'MANAGEMENT', '2024-01-01', '2024-01-01');

-- notification
INSERT INTO notification (content, is_read, member_id, created_at)
VALUES ('회원가입이 완료되었습니다.', false, 'leehaneum', '2021-01-01');

INSERT INTO notification (content, is_read, member_id, created_at)
VALUES ('패스워드가 변경되었습니다.', true, 'okJang', '2021-01-01');

INSERT INTO notification (content, is_read, member_id, created_at)
VALUES ('접속이 없어 휴면 상태로 전환되었습니다.', false, 'okJang', '2021-01-01');

-- QnA
INSERT INTO qna (title, content,is_answered, writer_id, answer, answerer_id, created_at, updated_at)
VALUES ('오픈이 언제인가요?', '제곧내', true, 'leehaneum', '1월 중순입니다.', 'superuser', '2021-01-01', '2021-01-01');

INSERT INTO qna (title, content,is_answered, writer_id, created_at, updated_at)
VALUES ('휴가는 언제인가요?', '휴가 가고 싶어요', false, 'okJang','2021-01-01', '2021-01-01');

-- comment
INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
VALUES ('첫번째 댓글입니다.', 'leehaneum', 1, null, '2021-01-01', '2021-01-01');

INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
VALUES ('두번째 댓글입니다.', 'okJang', 1, null, '2021-01-01', '2021-01-01');

INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
VALUES ('세번째 댓글입니다.', 'leehaneum', 1, 1, '2021-01-01', '2021-01-01');

INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
VALUES ('네번째 댓글입니다.', 'okJang', 1, 1, '2021-01-01', '2021-01-01');

INSERT INTO comment (content, member_id, board_id, parent_id, created_at, updated_at)
VALUES ('다섯번째 댓글입니다.', 'leehaneum', 1, 3, '2021-01-01', '2021-01-01');

-- program
INSERT INTO program (name, thumbnail, category, available, program_status, manager_id, apply_end, start_date, end_date, education_time, location, cost, material, description, created_at, updated_at)
VALUES ('하남시 장애인 조정 대회', 'image/rowing.jpg', '조정', '30', 'ACCEPTING', 'leehaneum', '2024-03-01', '2024-03-05', '2024-03-05', '120', '하남시 스타디움', '무료', '없음', '이 행사는 하남시 장애인 체육회에서 선도적으로 주관하는 장애인 조정대회로, 참가비가 무료로 제공됩니다.
이를 통해 장애인들은 접근성 있는 환경에서 스포츠에 참여하고 뛰어난 실력을 겨루며 자신의 잠재력을 발휘할 수 있습니다.
무료 참가비는 모든 장애인들에게 평등한 참여 기회를 제공하며, 지역 사회의 다양한 장애인들이 함께 모여 즐거운 대회를 즐길 수 있습니다.
또한, 이 행사는 지역사회와의 상호작용을 통해 장애인 스포츠에 대한 인식과 이해를 높이며, 사회의 통합과 다양성 증진에 기여하는 중요한 행사로 자리잡고 있습니다.', '2024-02-01', '2024-02-01');

-- application
INSERT INTO application (member_id, program_id, address, guardian_name, guardian_phone, status, remarks, created_at, updated_at)
VALUES ('okJang', 1, '경기도 수원시 장안구 대평로 27', '고구마', '010-6299-8498', 'WAITING', '특이사항 없음', '2024-02-02', '2024-02-04');

INSERT INTO application (member_id, program_id, address, guardian_name, guardian_phone, status, remarks, created_at, updated_at)
VALUES ('leehaneum', 1, '경기도 수원시 장안구 대평로 27 화서역파크푸르지오', '이한음', '010-6299-1234', 'ACCEPTED', '특이사항 없음', '2024-02-03', '2024-02-05');