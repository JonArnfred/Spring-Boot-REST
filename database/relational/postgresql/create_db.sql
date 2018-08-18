drop database if exists organisation_db ;
create database organisation_db;
\c organisation_db;

set client_encoding = 'UTF8';

drop table if exists organisation cascade;
create table organisation (
    id serial primary key,
    name text not null,
    created timestamp with time zone default (now() at time zone 'utc'),
    updated timestamp
);

drop table if exists "user" cascade;
create table "user"(
    id serial primary key,
    name text not null,
    created timestamp with time zone default (now() at time zone 'utc'),
    updated timestamp
);

drop table if exists user_organisation cascade;
create table user_organisation (
    user_id integer references "user"(id) not null,
    organisation_id integer references organisation(id) not null
);

