alter table listing	add address1 varchar(128) default '' not null;
alter table listing	add address2 varchar(128) default '' not null;
alter table listing	add city varchar(128) default '' not null;
alter table listing	add state varchar(64) default '' null;
alter table listing	add country varchar(64) default '' not null;
alter table listing	add zip varchar(16) default '' not null;

alter table listing add latitude float default 0 null;
alter table listing add longitude float default 0 null;
