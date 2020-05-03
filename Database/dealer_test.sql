drop schema if exists dealer_test;
create schema dealer_test;

use dealer_test;

create table user
(
    id         int auto_increment
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    phone      varchar(255) null
);

INSERT INTO user (id, first_name, last_name, phone)
VALUES (1, 'Alex', 'Sh', '+375293181025');

create table auth_user
(
    id       int auto_increment
        primary key,
    login    varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null,
    user_Id  int          not null,
    constraint authuser_login_uindex
        unique (login),
    constraint authuser_user_id_fk
        foreign key (user_Id) references user (id)
            on update cascade on delete cascade
);

INSERT INTO auth_user (id, login, password, role, user_Id)
VALUES (1, 'admin', 'admin', 'ADMINISTRATOR', 1);

create table car
(
    id        int auto_increment
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
    id        int auto_increment
        primary key,
    user      varchar(255) null,
    date      date         not null,
    vinNumber varchar(255) not null
);

insert into order_for_car (user, vinNumber, date)
values ('Alex', 'YV1NW775412131123', '2020-04-08');

create table team
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

INSERT INTO team (id, name)
VALUES (1, 'FirstTeam'),
       (2, 'SecondTeam'),
       (3, 'ThirdTeam');
