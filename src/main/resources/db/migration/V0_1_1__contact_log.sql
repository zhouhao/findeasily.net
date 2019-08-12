create table contact_log
(
    id              int auto_increment primary key,
    name            varchar(128) default ''                not null,
    email           varchar(256) default ''                not null,
    message         text                                   null,
    replied_content text                                   null,
    created_time    timestamp    default CURRENT_TIMESTAMP null,
    replied_time    timestamp    default CURRENT_TIMESTAMP null
);

