alter table listing	add status varchar(64) default 'DRAFT' not null;
create index listing_status_index on listing (status);
