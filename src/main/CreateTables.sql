create table tasks_statuses
(
    id_status NUMBER(3) primary KEY,
    status    varchar2(30) not null unique
);

create table employees_statuses
(
    id_status NUMBER(3) primary KEY,
    status    varchar2(30) not null unique
);

create table employees
(
    id_employee     Number(6) primary KEY,
    login           varchar2(20) not null unique,
    password        varchar2(20) not null,
    registered_date DATE         not null,
    id_status       NUMBER(3)    not null,

    CONSTRAINT fk_employees_employees_statuses
        FOREIGN KEY (id_status)
            REFERENCES employees_statuses (id_status)
);

create table tasks
(
    id_task          Number(30) primary KEY,
    id_status        Number(3)     not null,
    id_employee      Number(6),
    description      varchar2(500) not null,
    date_of_creation DATE          not null,

    CONSTRAINT fk_tasks_employees
        FOREIGN KEY (id_employee)
            REFERENCES employees (id_employee),

    CONSTRAINT fk_tasks_tasks_statuses
        FOREIGN KEY (id_status)
            REFERENCES tasks_statuses (id_status)
);
create index i_tasks_employees on tasks (id_employee);
create index i_tasks_statuses on tasks (id_status);

create table tasks_relations_types
(
    id_relation   NUMBER(3) primary KEY,
    relation_type varchar2(50) not null unique
);

create table comments
(
    id_comment       NUMBER(30) primary KEY,
    id_task          Number(30)    not null,
    id_employee      Number(6)     not null,
    text             varchar2(500) not null,
    date_of_creation DATE          not null,

    CONSTRAINT fk_comments_employees
        FOREIGN KEY (id_employee)
            REFERENCES employees (id_employee),

    CONSTRAINT fk_comments_tasks
        FOREIGN KEY (id_task)
            REFERENCES tasks (id_task)
);
create index i_comments_employees on comments (id_employee);
create index i_comments_tasks on comments (id_task);

create table tasks_relationships
(
    id_relationship Number(30) primary KEY,
    id_task1        Number(30) not null,
    id_task2        Number(30) not null,
    id_relation     NUMBER(3)  not null,

    CONSTRAINT fk_tasks_relationships_tasks1
        FOREIGN KEY (id_task1)
            REFERENCES tasks (id_task),

    CONSTRAINT fk_tasks_relationships_tasks2
        FOREIGN KEY (id_task2)
            REFERENCES tasks (id_task),

    CONSTRAINT fk_tasks_relationships_relations
        FOREIGN KEY (id_relation)
            REFERENCES tasks_relations_types (id_relation)
);
create index i_tasks_relationships_tasks1 on tasks_relationships (id_task1);
create index i_tasks_relationships_tasks2 on tasks_relationships (id_task2);
create index i_tasks_relationships_relations on tasks_relationships (id_relation);


create sequence sequence_employee_id start with 1;
create sequence sequence_task_id start with 1;
create sequence sequence_tasks_relationship_id start with 1;
create sequence sequence_task_relation_id start with 1;
create sequence sequence_comment_id start with 1;
create sequence sequence_task_status_id start with 1;
create sequence sequence_employee_status_id start with 1;