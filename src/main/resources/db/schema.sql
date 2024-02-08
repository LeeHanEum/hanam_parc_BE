CREATE TABLE member (
                        member_id VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(60) NOT NULL,
                        name VARCHAR(15) NOT NULL,
                        gender VARCHAR(255),
                        phone VARCHAR(20) NOT NULL,
                        guardian_phone VARCHAR(20),
                        guardian_name VARCHAR(15),
                        address VARCHAR(200),
                        email VARCHAR(45),
                        member_role VARCHAR(255),
                        member_status VARCHAR(255),
                        disabilityType VARCHAR(255),
                        birth DATE,
                        created_at TIMESTAMP NOT NULL,
                        last_login_time TIMESTAMP NOT NULL,
                        PRIMARY KEY (member_id)
);
CREATE TABLE authenticator (
                               member_id VARCHAR(255) NOT NULL PRIMARY KEY,
                               secretKey VARCHAR(255) NOT NULL
);

CREATE TABLE board (
                       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(45) NOT NULL,
                       content VARCHAR(1000) NOT NULL,
                       member_id VARCHAR(255),
                       board_category VARCHAR(255),
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,
                       FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE notification (
                              id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              content VARCHAR(100) NOT NULL CHECK (LENGTH(content) >= 1 AND LENGTH(content) <= 100),
                              is_read BOOLEAN DEFAULT false,
                              member_id VARCHAR(255),
                              created_at TIMESTAMP NOT NULL,
                              FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE qna (
                     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     title VARCHAR(45) NOT NULL,
                     content VARCHAR(1000) NOT NULL,
                     is_answered BOOLEAN NOT NULL,
                     writer_id VARCHAR(255),
                     answer VARCHAR(1000),
                     answerer_id VARCHAR(255),
                     created_at TIMESTAMP NOT NULL,
                     updated_at TIMESTAMP NOT NULL,
                     FOREIGN KEY (writer_id) REFERENCES member(member_id),
                     FOREIGN KEY (answerer_id) REFERENCES member(member_id)
);
CREATE TABLE comment (
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         content VARCHAR(1000) NOT NULL,
                         board_id BIGINT NOT NULL,
                         member_id VARCHAR(255) NOT NULL,
                         parent_id BIGINT,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL,
                         FOREIGN KEY (board_id) REFERENCES board(id),
                         FOREIGN KEY (member_id) REFERENCES member(member_id),
                         FOREIGN KEY (parent_id) REFERENCES comment(id),
                         CONSTRAINT CHK_content_length CHECK (LENGTH(content) <= 1000)
);
CREATE TABLE program (
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(45) NOT NULL,
                         thumbnail VARCHAR(200),
                         available BIGINT NOT NULL,
                         program_status VARCHAR(255),
                         manager_id VARCHAR(255),
                         apply_end TIMESTAMP NOT NULL,
                         start_date DATE NOT NULL,
                         end_date DATE NOT NULL,
                         time BIGINT NOT NULL,
                         location VARCHAR(255) NOT NULL,
                         cost VARCHAR(255) NOT NULL,
                         material VARCHAR(255) NOT NULL,
                         description VARCHAR(1000) NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL,
                         FOREIGN KEY (manager_id) REFERENCES member(member_id),
                         CONSTRAINT CHK_description_length CHECK (LENGTH(description) >= 1 AND LENGTH(description) <= 1000)
);
CREATE TABLE application (
                             id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             member_id VARCHAR(255) NOT NULL,
                             program_id BIGINT NOT NULL,
                             address VARCHAR(255) NOT NULL,
                             guardian_name VARCHAR(255) NOT NULL,
                             guardian_phone VARCHAR(20) NOT NULL,
                             status VARCHAR(255),
                             remarks VARCHAR(1000),
                             created_at TIMESTAMP NOT NULL,
                             updated_at TIMESTAMP NOT NULL,
                             FOREIGN KEY (member_id) REFERENCES member(member_id),
                             FOREIGN KEY (program_id) REFERENCES program(id),
                             CONSTRAINT CHK_remarks_length CHECK (LENGTH(remarks) <= 1000)
);
CREATE TABLE event (
                       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(45) NOT NULL,
                       start DATE NOT NULL,
                       end DATE NOT NULL,
                       color VARCHAR(255) DEFAULT 'gray',
                       description VARCHAR(1000),
                       board_id BIGINT,
                       member_id VARCHAR(255),
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,
                       FOREIGN KEY (board_id) REFERENCES board(id),
                       FOREIGN KEY (member_id) REFERENCES member(member_id),
                       CONSTRAINT CHK_event_description_length CHECK (LENGTH(description) <= 1000)
);

