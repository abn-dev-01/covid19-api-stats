

SET SCHEMA 'public';
drop table IF EXISTS entity_country ;

CREATE TABLE entity_country
(
    id serial primary key not null,
    country_name VARCHAR(50) NOT NULL,
    slug         VARCHAR(50) NOT NULL,
    iso2         CHAR(2)     NOT NULL
);
drop index IF EXISTS indx_country_name ;
create index indx_country_name on entity_country using btree (country_name);

drop index IF EXISTS indx_country_slug ;
create index indx_country_slug on entity_country using btree (slug);

drop index IF EXISTS indx_country_iso2 ;
create index indx_country_iso2 on entity_country using btree (iso2);
