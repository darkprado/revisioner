create table if not exists tbl_operations (
                                            id  bigserial not null,
                                            date timestamp,
                                            name text not null,
                                            type text not null,
                                            sum int4 not null,
                                            positive boolean not null,
                                            primary key (id));