
SET SCHEMA 'public';
drop table IF EXISTS entity_total_country ;

create table entity_total_country
(
    id serial primary key not null,
    country character varying(50) not null,
    country_code character varying(64) not null,
    province character varying(50) not null,
    city character varying(128) not null,
    city_code character varying(16) not null,
    lat character varying(32) not null,
    lon character varying(32) not null,
    confirmed integer not null,
    deaths integer not null,
    recovered integer not null,
    active integer not null,
    date_stat date not null,
    comment character varying(255)
);

drop index IF EXISTS indx_entity_total_country;
create index indx_entity_total_country on entity_total_country using btree (country);

drop index IF EXISTS indx_entity_total_country_code;
create index indx_entity_total_country_code on entity_total_country using btree (country_code);

drop index IF EXISTS indx_entity_total_country_date_stat;
create index indx_entity_total_country_date_stat on entity_total_country using btree (date_stat);
