### ERD
<img width="826" alt="image" src="https://github.com/user-attachments/assets/35545771-8a0b-44d1-beda-12ee63242ab9" />

### ERD 설명
#### app_user
사용자 정보를 저장합니다. 주된 기능이 아니기 때문에 간단하게 만들었습니다. (이름, PK)

#### lecture
특강의 정보를 저장합니다.
특강 이름, 강연자 이름, 특강 시작 일시 및 종료 일시, 예약한 인원 수 등을 담고있습니다. <br/>
예약 인원 수(reserved_counts)의 경우 예약시 정원 체크를 위해 사용합니다. <br/>
예약시 reservation 저장과 함께 reserved_counts 를 증가시켜줍니다.

#### reservation
사용자와 특강의 예약 정보 매핑 테이블입니다. 누가 어느 특강을 예약했는지를 저장합니다.<br/>

### MariaDB 테이블 생성 쿼리
```
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
```

### dbdiagram.io 코드
```
Table app_user {
  id bigint [pk]
  name varchar
  time_created datetime
  time_updated datetime
  deleted_at datetime
}

Table reservation {
  id bigint [pk]
  user_id bigint [ref: > app_user.id]
  lecture_id bigint [ref: > lecture.id]
  time_created datetime
  time_updated datetime
  deleted_at datetime
}

Table lecture {
  id bigint [pk]
  name varchar
  lecturer varchar
  start_time datetime
  end_time datetime
  reserved_counts int
  max_reservation int
  time_created datetime
  time_updated datetime
  deleted_at datetime
}
```

