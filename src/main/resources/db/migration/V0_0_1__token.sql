create table token
(
  id          int auto_increment                  primary key,
  user_id     varchar(40)                         null,
  val         varchar(64)                         not null,
  create_time timestamp default CURRENT_TIMESTAMP not null
);

create index token_create_time_index on token (create_time);

create index token_user_id_index on token (user_id);

