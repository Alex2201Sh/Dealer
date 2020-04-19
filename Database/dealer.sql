create table user
(
    id        int auto_increment
        primary key,
    firstname varchar(255) null,
    lastname  varchar(255) null,
    phone     varchar(255) null
);

INSERT INTO user (id, firstname, lastname, phone)
VALUES (1, 'Alex', 'Sh', '+375293181025');

create table auth_user
(
    id       int auto_increment
        primary key,
    login    varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null,
    userId   int          not null,
    constraint authuser_login_uindex
        unique (login),
    constraint authuser_user_id_fk
        foreign key (userId) references user (id)
            on update cascade on delete cascade
);

INSERT INTO auth_user (id, login, password, role, userId)
VALUES (1, 'admin', 'admin', 'ADMINISTRATOR', 1);

create table car
(
    carId     int auto_increment
        primary key,
    model     varchar(255) not null,
    vinNumber varchar(255) not null
);

insert into car (model, vinNumber)
VALUES ('Volvo', 'YV1NW775412131123'),
       ('TOYOTA', 'JE1FY749942373603'),
       ('NISSAN', '4T1BB46K68U047102'),
       ('HONDA', 'Z00CT41DBFR366183');

create table order_for_car
(
    id   int auto_increment
        primary key,
    user varchar(255) null,
    date date         not null,
    vinNumber varchar(255) not null
);

insert into order_for_car (user, vinNumber, date) values
('Alex', 'YV1NW775412131123', '2020-04-08');
