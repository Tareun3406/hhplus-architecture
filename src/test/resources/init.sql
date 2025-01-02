drop database if exists app;
create database app;
USE app;
CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    lecture_id BIGINT NOT NULL,
    time_created DATETIME NOT NULL default current_time,
    time_updated DATETIME NOT NULL default current_time,
    deleted_at DATETIME
);

CREATE TABLE app_user(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    time_created DATETIME NOT NULL default current_time,
    time_updated DATETIME NOT NULL default current_time,
    deleted_at DATETIME
);

CREATE TABLE lecture(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lecturer VARCHAR(30) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    reserved_counts INT DEFAULT 0,
    max_reservation INT DEFAULT 30,
    time_created DATETIME NOT NULL default current_time,
    time_updated DATETIME NOT NULL default current_time,
    deleted_at DATETIME
);

INSERT INTO lecture(name, lecturer, start_time, end_time, reserved_counts)
    VALUE  ('특강1', '강연자1', '2030-10-11 15:00:00', '2030-10-11 16:00:00', 0);
INSERT INTO lecture(name, lecturer, start_time, end_time, reserved_counts)
    VALUE  ('특강2', '강연자2', '2030-10-11 15:00:00', '2030-10-11 16:00:00', 0);

INSERT INTO app_user(name) VALUES
                               ('사용자1'), ('사용자2'), ('사용자3'), ('사용자4'), ('사용자5'),
                               ('사용자6'), ('사용자7'), ('사용자8'), ('사용자9'), ('사용자10'),
                               ('사용자11'), ('사용자12'), ('사용자13'), ('사용자14'), ('사용자15'),
                               ('사용자16'), ('사용자17'), ('사용자18'), ('사용자19'), ('사용자20'),
                               ('사용자21'), ('사용자22'), ('사용자23'), ('사용자24'), ('사용자25'),
                               ('사용자26'), ('사용자27'), ('사용자28'), ('사용자29'), ('사용자30'),
                               ('사용자31'), ('사용자32'), ('사용자33'), ('사용자34'), ('사용자35'),
                               ('사용자36'), ('사용자37'), ('사용자38'), ('사용자39'), ('사용자40'),
                               ('사용자41'), ('사용자42'), ('사용자43'), ('사용자44'), ('사용자45'),
                               ('사용자46'), ('사용자47'), ('사용자48'), ('사용자49'), ('사용자50'),
                               ('사용자51');


INSERT INTO reservation(user_id, lecture_id) value (51, 2);
UPDATE lecture SET reserved_counts = 1 where id = 2;