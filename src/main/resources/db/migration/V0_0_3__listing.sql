create table listing
(
  id               varchar(40)                         not null    primary key,
  property_id      varchar(40)                         not null,
  owner_id         varchar(40)                         not null,
  rent_price       int                                 null,
  security_deposit int                                 null,
  available_date   timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP,
  bedroom_count    int default '1'                     null,
  bathroom_count   int default '1'                     null,
  duration         int default '0'                     null,
  contactor_name   varchar(64)                         null,
  contactor_email  varchar(128)                        null,
  contactor_phone  varchar(32)                         null,
  show_email       bit default b'0'                    not null,
  show_phone       bit default b'0'                    not null,
  description      text                                null,
  type             varchar(64)                         not null
);

create index listing_available_date_index on listing (available_date);

create index listing_owner_id_index on listing (owner_id);

create index listing_rent_price_index on listing (rent_price);

create index listing_type_index on listing (type);


create table listing_photo
(
  id          int auto_increment                  primary key,
  listing_id  varchar(40)                         not null,
  upload_date timestamp default CURRENT_TIMESTAMP not null,
  path        varchar(128)                        not null,
  description text                                null
);

create index listing_photo_listing_id_index on listing_photo (listing_id);


create table listing_utility
(
  listing_id  varchar(40)      not null    primary key,
  AC          bit default b'0' not null,
  electricity bit default b'0' not null,
  water       bit default b'0' not null,
  hot_water   bit default b'0' not null,
  heater      bit default b'0' null,
  internet    bit default b'0' null,
  parking     bit default b'0' not null
);

