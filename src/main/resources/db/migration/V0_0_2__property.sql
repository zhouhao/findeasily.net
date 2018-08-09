create table property
(
  id         varchar(40)                         not null    primary key,
  address    varchar(1024)                       not null,
  city       varchar(128)                        not null,
  state      varchar(64)                         not null,
  zip        varchar(16)                         not null,
  owner_id   varchar(40)                         not null,
  added_time timestamp default CURRENT_TIMESTAMP not null,
  active     bit default b'1'                    not null
);

create index property_active_index on property (active);

