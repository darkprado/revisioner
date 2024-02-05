create table if not exists tbl_users (
                                            id  bigserial not null,
                                            created_date timestamp,
                                            email varchar(255),
                                            firstname varchar(255) not null,
                                            lastname varchar(255) not null,
                                            password varchar(3000),
                                            username varchar(255),
                                            primary key (id));

create table if not exists user_role (
                                            user_id int8 not null,
                                            roles int4);

create table if not exists tbl_operations (
                                            id  bigserial not null,
                                            date timestamp,
                                            name text not null,
                                            type text not null,
                                            sum int4 not null,
                                            positive boolean not null,
                                            user_id int8,
                                            primary key (id));

alter table tbl_users
    add constraint email_uniq not exists unique (email);


alter table tbl_users
    add constraint username_uniq not exists unique (username);

alter table tbl_operations
    add constraint operation_user not exists
    foreign key (user_id)
    references tbl_users;