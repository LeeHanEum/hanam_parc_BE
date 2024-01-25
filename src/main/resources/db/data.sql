--superuser 1111
INSERT INTO member (member_id, password, name, phone, email, member_role, member_status, birth, created_at, last_login_time)
    VALUES ('superuser', '$2a$12$xsnsy6lcQl1EM2cc9sxEbemIoXPf6jiMHIyE3lZWAr8YCz9QQDJXq', 'superuser', '010-1234-5678', 'superuser@naver.com', 'SUPER', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- leehaneum 0924
INSERT INTO member (member_id, password, name, phone, email, member_role, member_status, birth, created_at, last_login_time)
VALUES ('leehaneum', '$2a$12$SYIVeoc5WTQTDOZRzxMm6u9GfYVUIEHNOwqq.vpt2vgaezM63nVke', 'leehaneum', '010-6299-5678', 'leehaneum160924@kyonggi.ac.kr', 'ADMIN', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');
-- okJang 8777
INSERT INTO member (member_id, password, name, phone, email, member_role, member_status, birth, created_at, last_login_time)
VALUES ('okJang', '$2a$12$bUjikKYJ1w1RdYFIn2DlmOcNF0zxivfAJ6hDsP/TxaAgTqb9J./wG', 'okJang', '010-6299-0733', 'mosoon365@hanmail.net', 'USER', 'ACTIVE', '1990-01-01', '2024-01-01', '2024-01-01');

-- board
INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('가입인사', '첫번째 글입니다.', 'leehaneum', 'FREE', '2021-01-01', '2021-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('수원시 체육대회 개최', '수원시 체육대회 개최', 'okJang', 'FREE', '2021-01-01', '2021-01-01');

INSERT INTO board (title, content, member_id, board_category, created_at, updated_at)
VALUES ('평창 청소년 올림픽 한국 1위', '평창 청소년 올림픽 한국 1위', 'leehaneum', 'FREE', '2021-01-01', '2021-01-01');

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