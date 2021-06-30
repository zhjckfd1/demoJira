--liquibase formatted sql

--changeset User1:1
create table employees
(
    id_employee     Number(6) primary KEY,
    login           varchar2(20) not null unique,
    password        varchar2(20) not null,
    registered_date DATE         not null,
    status          Number(1)    not null
);
--rollback drop table employees;

--changeset User1:2
create sequence sequence_employee_id start with 1;
--rollback drop sequence sequence_employee_id;

--changeset User1:3
insert into employees (id_employee, login, password, registered_date, status)
values (sequence_employee_id.nextval, 'test1', '1111', CURRENT_DATE, 1);
--rollback delete from employees where login = 'test1';
